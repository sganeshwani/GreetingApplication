package com.example.greetingapp.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    /**
     * Returns a greeting based on provided firstName and lastName.
     * If both names are provided, greet using both.
     * If one of them is provided, greet with that name.
     * If neither is provided, return "Hello World".
     */
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
}
