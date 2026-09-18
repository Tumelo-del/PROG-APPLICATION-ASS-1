/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.application;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    public LoginTest() {
    }
    // USERNAME TESTS
     
    // Username correctly formatted
     
    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(login.checkUserName("kyl_1"));
    }

    // Username incorrectly formatted
     
    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }


     
    // PASSWORD TESTS
     
    // Password meets complexity requirements
     
    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(
                login.checkPasswordComplexity("Ch&&sec@ke99!")
        );
    }

    // Password does not meet complexity requirements
   
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertFalse(
                login.checkPasswordComplexity("password")
        );
    }


   
    // CELL PHONE NUMBER TESTS
  

    // Cell phone number correctly formatted
    
    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(
                login.checkCellPhoneNumber("+27838968976")
        );
    }

    // Cell phone number incorrectly formatted
     
    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertFalse(
                login.checkCellPhoneNumber("08966553")
        );
    }


    
    // LOGIN TESTS
    
    // Login successful
     
    @Test
    public void testLoginSuccessful() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(
                login.loginUser(
                        "kyl_1",
                        "Ch&&sec@ke99!"
                )
        );
    }

    // Login failed
     
    @Test
    public void testLoginFailed() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertFalse(
                login.loginUser(
                        "wrong_username",
                        "wrong_password"
                )
        );
    }


     
    // LOGIN STATUS MESSAGE TESTS

    // Successful login message
    @Test
    public void testSuccessfulLoginMessage() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(
                "Welcome, it is great to see you again.",
                login.returnLoginStatus(true)
        );
    }

    // Failed login message
    @Test
    public void testFailedLoginMessage() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus(false)
        );
    }


     
    // REGISTRATION TESTS IN LOGIN CLASS
    
    
    // Successful registration
    @Test
    public void testUserSuccessfullyRegistered() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(
                "User has been successfully registered.",
                login.registerUser(
                        "kyl_1",
                        "Ch&&sec@ke99!",
                        "+27838968976"
                )
        );
    }

    // Incorrect username during registration
    @Test
    public void testRegistrationWithIncorrectUsername() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(
                "Username is not correctly formatted.",
                login.registerUser(
                        "kyle!!!!!!!",
                        "Ch&&sec@ke99!",
                        "+27838968976"
                )
        );
    }

    // Incorrect password during registration
    @Test
    public void testRegistrationWithIncorrectPassword() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(
                "Password is not correctly formatted.",
                login.registerUser(
                        "kyl_1",
                        "password",
                        "+27838968976"
                )
        );
    }

    // Incorrect cellphone number during registration
    @Test
    public void testRegistrationWithIncorrectCellPhoneNumber() {
        Login login = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(
                "Cell phone number incorrectly formatted or does not contain international code.",
                login.registerUser(
                        "kyl_1",
                        "Ch&&sec@ke99!",
                        "08966553"
                )
        );
    }
    
}
