package org.metrodatacademy;

import org.metrodatacademy.controller.UserInput;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            UserInput userInput = new UserInput();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}