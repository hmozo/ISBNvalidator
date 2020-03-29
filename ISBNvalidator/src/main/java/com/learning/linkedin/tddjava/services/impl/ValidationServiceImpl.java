package com.learning.linkedin.tddjava.services.impl;

import java.util.stream.IntStream;

import com.learning.linkedin.tddjava.services.ValidationService;

public class ValidationServiceImpl implements ValidationService {

	@Override
	public boolean validateISBN(String isbn) {

		
		int sum= IntStream.rangeClosed(1, isbn.length())
				.map(r->isbn.length()-r+1)
				.map(r->r*isbn.charAt(r-1))
				.sum();
		
		return (sum % 11 == 0)?true:false;

	}
	
}
