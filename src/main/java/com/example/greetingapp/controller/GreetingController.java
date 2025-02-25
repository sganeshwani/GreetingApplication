package com.example.greetingapp.controller;

import com.example.greetingapp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    // Autowire the GreetingService
    @Autowired
    private GreetingService greetingService;

    // GET method: Returns a default greeting message
    @GetMapping
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("{\"message\": \"Hello, welcome to the Greeting App!\"}");
    }

    // POST method: Accepts a name and returns a greeting message
    @PostMapping
    public ResponseEntity<String> postGreeting(@RequestBody GreetingRequest request) {
        String responseMessage = "{\"message\": \"Hello, " + request.getName() + "!\"}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    // New Endpoint for UC2: Use Service to get a simple greeting "Hello World"
    @GetMapping("/simple")
    public ResponseEntity<String> getSimpleGreeting() {
        String message = greetingService.getSimpleGreeting();
        return ResponseEntity.ok("{\"message\": \"" + message + "\"}");
    }

    // Inner class to map JSON request body
    public static class GreetingRequest {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}