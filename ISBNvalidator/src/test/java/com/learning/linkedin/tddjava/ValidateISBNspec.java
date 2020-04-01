package com.learning.linkedin.tddjava;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.learning.linkedin.tddjava.services.ValidationService;
import com.learning.linkedin.tddjava.services.impl.ValidationServiceImpl;

class ValidateISBNspec {
	
	private static ValidationService validationService;
	
	@BeforeAll
	static void init() {
		validationService= new ValidationServiceImpl();
	}
	
	@Test
	void givenCorrect10ISBNWhenValidateISBNthenOK() {
		//Book book= new Book("9780140449112", "The Oddyssey");
		boolean validationResult= validationService.validateISBN("0140449116");
		assertThat(validationResult).isTrue();
		validationResult= validationService.validateISBN("1586633546");
		assertThat(validationResult).isTrue();

	}
	
	@Test
	void givenWrong10ISBNwhenValidateISBNthenKO() {
		//Book book= new Book("9780140449112", "The Oddyssey");
		boolean result= validationService.validateISBN("1140449116");
		assertThat(result).isFalse();
		result= validationService.validateISBN("2586633546");
		assertThat(result).isFalse();

	}
	
	@Test
	void given9DigitsISBNWhenvalidateThenNumberFormatException() {

		Throwable exception= assertThrows(NumberFormatException.class, 
				()->validationService.validateISBN("123456789"));
		assertThat(exception.getMessage()).isEqualTo("Wrong number of digits");
		
	}
	
	@Test
	void givenTextISBNWhenValidateThenNumberFormatException() {
		Throwable exception= assertThrows(NumberFormatException.class, 
				()->validationService.validateISBN("helloworld"));
		assertThat(exception.getMessage()).isEqualTo("Only numeric values or ends with X");
	}
	
	@Test
	void givenCorrect10ISBNEndingInXwhenValidateISBNthenOK() {
		boolean result= validationService.validateISBN("012000030X");
		assertThat(result).isEqualTo(true);
	}
	
	@Test
	void givenCorrect13ISBNwhenValidateThenOK() {
		boolean result= validationService.validateISBN("9781853265747");
		assertThat(result).isEqualTo(true);
	}
	
	@Test
	void givenWrong13ISBNwhenValidateThenKO() {
		boolean result= validationService.validateISBN("9781853265746");
		assertThat(result).isEqualTo(false);
	}
	
	
	

}
