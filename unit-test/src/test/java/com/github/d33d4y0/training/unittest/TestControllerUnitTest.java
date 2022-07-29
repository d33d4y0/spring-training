package com.github.d33d4y0.training.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.d33d4y0.training.unittest.controller.TestController;
import com.github.d33d4y0.training.unittest.service.TestService;

public class TestControllerUnitTest {

	@Mock
	private TestService service;
	
	@InjectMocks
	private TestController controller;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void givenTenAndFive_whenGetAdd_ThenReturnFifteen() {
		int a = 10;
		int b = 5;
		
		when(service.add(a, b)).thenReturn(a+b);
		int result = controller.add(a, b);
		assertEquals(15, result);
	}
}
