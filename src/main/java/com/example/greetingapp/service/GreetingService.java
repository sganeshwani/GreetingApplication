package com.example.greetingapp.service;

import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    // UC5: Find greeting by ID
    public Optional<Greeting> getGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

    // UC6: Retrieve all greeting messages
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    // UC7: Update greeting message
    public Optional<Greeting> updateGreeting(Long id, String newMessage) {
        Optional<Greeting> optionalGreeting = greetingRepository.findById(id);
        if (optionalGreeting.isPresent()) {
            Greeting greetingToUpdate = optionalGreeting.get();
            greetingToUpdate.setMessage(newMessage);
            greetingRepository.save(greetingToUpdate);
            return Optional.of(greetingToUpdate);
        }
        return Optional.empty();
    }

    // UC8: Delete greeting by ID
    public boolean deleteGreeting(Long id) {
        Optional<Greeting> optionalGreeting = greetingRepository.findById(id);
        if (optionalGreeting.isPresent()) {
            greetingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}