/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.application;

/**
 *
 * @author Student
 */
public class Registration {
   private String username;
    private String password;
    private String cellPhoneNumber;

    // Captures the username
    public void setUsername(String username) {
        this.username = username;
    }

    // Captures the password
    public void setPassword(String password) {
        this.password = password;
    }

    // Captures the cellphone number
    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // Returns the captured username
    public String getUsername() {
        return username;
    }

    // Returns the captured password
    public String getPassword() {
        return password;
    }

    // Returns the captured cellphone number
    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    // Checks whether the username is correctly formatted
    public boolean checkUsername(String username) {
        if (username.contains("_") && username.length() <= 5) {
            setUsername(username);
            return true;
        }
        return false;
    }

    // Checks whether the password meets the complexity requirements
    public boolean checkPassword(String password) {
        if (password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[^a-zA-Z0-9].*")) {

            setPassword(password);
            return true;
        }
        return false;
    }

    // Checks whether the cellphone number has the South African
    // international country code and is correctly formatted
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber.matches("^\\+27\\d{9}$")) {
            setCellPhoneNumber(cellPhoneNumber);
            return true;
        }
        return false;
    }

    // Registration username message
    public String usernameStatus(boolean valid) {
        if (valid) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
    }

    // Registration password message
    public String passwordStatus(boolean valid) {
        if (valid) {
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
    }

    // Registration cellphone message
    public String cellphoneStatus(boolean valid) {
        if (valid) {
            return "Cell phone number successfully added.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
    }
}
