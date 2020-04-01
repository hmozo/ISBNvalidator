package com.learning.linkedin.tddjava.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.learning.linkedin.tddjava.services.ValidationService;

public class ValidationServiceImpl implements ValidationService {

	@Override
	public boolean validateISBN(String isbn) {
		if(isbn.length()!=10 && isbn.length()!=13) {
			throw new NumberFormatException("Wrong number of digits");
		}
		
		if (listDigits(isbn).size()!=isbn.length() && !ISBNendsWithX(isbn)) {
			throw new NumberFormatException("Only numeric values or ends with X");
		}
		
		int divisor= isbn.length()==10?11:10;
		return (sumDigits(isbn) % divisor == 0)?true:false;
	
	}
	
	private int sumDigits(String isbn) {
		List<Integer> listFactors13= IntStream.rangeClosed(1, isbn.length())
			.map(r->r%2==0?3:1).boxed()
			.collect(Collectors.toList());
			
		int result= IntStream.rangeClosed(1, isbn.length())
			.map(r->isbn.length()-r+1)
			.map(r->r==isbn.length() && isbn.charAt(r-1)==(int)'X'?10:
				(isbn.length()==10?r:
					listFactors13.get(r-1))*isbn.charAt(isbn.length()-r))
			.sum();
		
		return result;
	}

	private List<Integer> listDigits(String isbn) {
		return isbn.chars()
				.filter(c->Character.isDigit(c))
				.boxed()
				.collect(Collectors.toList());
	}
	
	
	private boolean ISBNendsWithX(String isbn) {
		return 'X' == isbn.charAt(isbn.length()-1)?true:false;
	}
	
	
	
}
