package org.starsyndrome;

import org.starsyndrome.controller.UserInput;

public class Main {
    public static void main(String[] args) {
        try {
            UserInput userInput = new UserInput();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}