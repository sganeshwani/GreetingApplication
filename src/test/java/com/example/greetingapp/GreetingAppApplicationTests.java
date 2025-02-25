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
		// Test passes if the Spring Application context loads successfully.
	}

	@Test
	void testGetGreetingById() {
		// First, save a greeting message.
		Greeting savedGreeting = greetingService.saveGreeting("Test Greeting");
		Long id = savedGreeting.getId();

		// Now, retrieve it by ID.
		Optional<Greeting> retrievedGreeting = greetingService.getGreetingById(id);
		assertTrue(retrievedGreeting.isPresent());
		assertEquals("Test Greeting", retrievedGreeting.get().getMessage());
	}
}