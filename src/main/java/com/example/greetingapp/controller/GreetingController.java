package com.example.greetingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

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