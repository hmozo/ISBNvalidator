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
		
		List<Integer> sumDigits= isbn.chars()
				.filter(c->Character.isDigit(c))
				.boxed()
				.collect(Collectors.toList());
		
		if (sumDigits.size()!=isbn.length()) {
			throw new NumberFormatException("Only numeric values");
		}
		
		int sum= IntStream.rangeClosed(1, isbn.length())
				.map(r->isbn.length()-r+1)
				.map(r->r*isbn.charAt(r-1))
				.sum();
		
		return (sum % 11 == 0)?true:false;

	}
	
}
