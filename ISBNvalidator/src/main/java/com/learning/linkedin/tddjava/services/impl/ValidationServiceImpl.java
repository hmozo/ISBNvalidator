package com.learning.linkedin.tddjava.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.learning.linkedin.tddjava.services.ValidationService;

public class ValidationServiceImpl implements ValidationService {

	@Override
	public boolean validateISBN(String isbn) {
		if(isbn.length()!=10) {
			throw new NumberFormatException("Wrong number of digits");
		}
		
		if (listDigits(isbn).size()!=isbn.length() && !ISBNendsWithX(isbn)) {
			throw new NumberFormatException("Only numeric values or ends with X");
		}
		
		return (sumDigits(isbn) % 11 == 0)?true:false;
	}
	
	private int sumDigits(String isbn) {
		int result= IntStream.rangeClosed(1, isbn.length())
			.map(r->isbn.length()-r+1)
			.map(r->isbn.charAt(r-1)==(int)'X'?0:r*isbn.charAt(isbn.length()-r))
			.sum();
		
		result += ISBNendsWithX(isbn)==true?10:0;
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
