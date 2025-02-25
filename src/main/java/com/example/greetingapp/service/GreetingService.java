package com.example.greetingapp.service;

import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    public String getGreeting(String firstName, String lastName) {
        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            return "Hello " + firstName + " " + lastName;
        } else if (firstName != null && !firstName.isEmpty()) {
            return "Hello " + firstName;
        } else if (lastName != null && !lastName.isEmpty()) {
            return "Hello " + lastName;
        }
        return "Hello World";
    }

    public Greeting saveGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    // New method for UC5: Find greeting by ID
    public Optional<Greeting> getGreetingById(Long id) {
        return greetingRepository.findById(id);
    }
}