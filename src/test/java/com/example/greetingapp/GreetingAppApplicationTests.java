package com.example.greetingapp;

import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GreetingAppApplicationTests {

	@Autowired
	private GreetingService greetingService;

	@Test
	void contextLoads() {
		// Application context loads successfully.
	}

	@Test
	void testGetGreetingById() {
		// Save a greeting for testing
		Greeting savedGreeting = greetingService.saveGreeting("Test Greeting");
		Long id = savedGreeting.getId();

		// Retrieve by ID and check the message
		Optional<Greeting> retrievedGreeting = greetingService.getGreetingById(id);
		assertTrue(retrievedGreeting.isPresent());
		assertEquals("Test Greeting", retrievedGreeting.get().getMessage());
	}
}