/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.application;

/**
 *
 * @author Student
 */
public class Login {
    private String registeredUsername;
    private String registeredPassword;

    // Constructor receives the details captured during registration
    public Login(String username, String password, String cellPhoneNumber) {
        registeredUsername = username;
        registeredPassword = password;
    }

    // Checks username format
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Checks password complexity
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*")  && password.matches(".*[^a-zA-Z0-9].*");
    }

    // Checks cellphone number format
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        return cellPhoneNumber.matches("^\\+27\\d{9}$");
    }

    // Returns registration message
    public String registerUser(String username, String password, String cellPhoneNumber) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted.";
        }

        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "User has been successfully registered.";
    }

    // Checks whether login details match registered details
    public boolean loginUser(String username, String password) {
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }

    // Returns login status message
    public String returnLoginStatus(boolean loginSuccessful) {

        if (loginSuccessful) {
            return "Welcome, it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
