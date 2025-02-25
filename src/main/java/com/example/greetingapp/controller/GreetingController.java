package com.example.greetingapp.controller;

import com.example.greetingapp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    // UC1: GET method - Returns a default greeting message
    @GetMapping
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("{\"message\": \"Hello, welcome to the Greeting App!\"}");
    }

    // UC1: POST method - Accepts a name and returns a greeting message
    @PostMapping
    public ResponseEntity<String> postGreeting(@RequestBody GreetingRequest request) {
        String responseMessage = "{\"message\": \"Hello, " + request.getName() + "!\"}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    // UC2: GET endpoint using service layer to return a simple greeting "Hello World"
    @GetMapping("/simple")
    public ResponseEntity<String> getSimpleGreeting() {
        String message = greetingService.getGreeting(null, null);
        return ResponseEntity.ok("{\"message\": \"" + message + "\"}");
    }

    // UC3: POST endpoint to greet based on first and/or last name
    @PostMapping("/user")
    public ResponseEntity<String> getUserGreeting(@RequestBody UserGreetingRequest request) {
        String message = greetingService.getGreeting(request.getFirstName(), request.getLastName());
        return ResponseEntity.ok("{\"message\": \"" + message + "\"}");
    }

    // Inner class for the basic greeting request (for UC1/others)
    public static class GreetingRequest {
        private String name;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    // New inner class for UC3: Greeting request with first and last names.
    public static class UserGreetingRequest {
        private String firstName;
        private String lastName;
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
    }
}