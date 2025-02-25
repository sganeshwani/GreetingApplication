package com.example.greetingapp.controller;

import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // UC4: POST endpoint to save a greeting message in the repository
    @PostMapping("/save")
    public ResponseEntity<Greeting> saveGreeting(@RequestBody SaveGreetingRequest request) {
        String message;
        if (request.getMessage() != null && !request.getMessage().isEmpty()) {
            message = request.getMessage();
        } else {
            message = greetingService.getGreeting(request.getFirstName(), request.getLastName());
        }
        Greeting savedGreeting = greetingService.saveGreeting(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGreeting);
    }

    // UC5: GET endpoint to find a greeting by ID in the repository
    @GetMapping("/{id}")
    public ResponseEntity<?> getGreetingById(@PathVariable Long id) {
        Optional<Greeting> greetingOptional = greetingService.getGreetingById(id);
        if (greetingOptional.isPresent()) {
            return ResponseEntity.ok(greetingOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"Greeting not found for ID: " + id + "\"}");
        }
    }

    // UC6: GET endpoint to list all greeting messages in the repository
    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // Inner class for the basic greeting request (for UC1)
    public static class GreetingRequest {
        private String name;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    // Inner class for UC3: Greeting request with first and last names.
    public static class UserGreetingRequest {
        private String firstName;
        private String lastName;
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
    }

    // Inner class for UC4: Request to save a greeting message.
    public static class SaveGreetingRequest {
        private String message;
        private String firstName;
        private String lastName;
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}