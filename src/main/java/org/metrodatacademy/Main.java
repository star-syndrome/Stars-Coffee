package org.metrodatacademy;

import org.metrodatacademy.controller.UserInput;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            UserInput userInput = new UserInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}