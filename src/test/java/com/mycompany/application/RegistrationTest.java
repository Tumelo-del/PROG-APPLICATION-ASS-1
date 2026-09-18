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
public class RegistrationTest {
    
     
    // USERNAME TESTS
     
    // Username correctly formatted
     
    @Test
    public void testUsernameCorrectlyFormatted() {
        Registration registration = new Registration();

        assertTrue(registration.checkUsername("kyl_1"));
    }

    // Username incorrectly formatted
  
    @Test
    public void testUsernameIncorrectlyFormatted() {
        Registration registration = new Registration();

        assertFalse(registration.checkUsername("kyle!!!!!!!"));
    }

    // Username successfully captured
 
    @Test
    public void testUsernameStatusCorrect() {
        Registration registration = new Registration();

        assertEquals(
                "Username successfully captured.",
                registration.usernameStatus(true)
        );
    }

    // Username incorrectly formatted message
    @Test
    public void testUsernameStatusIncorrect() {
        Registration registration = new Registration();

        assertEquals(
                "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                registration.usernameStatus(false)
        );
    }


  
    // PASSWORD TESTS
    

    // Password meets complexity requirements
     
    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Registration registration = new Registration();

        assertTrue(
                registration.checkPassword("Ch&&sec@ke99!")
        );
    }

    // Password does not meet complexity requirements
     
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Registration registration = new Registration();

        assertFalse(
                registration.checkPassword("password")
        );
    }

    // Password successfully captured
    
    @Test
    public void testPasswordStatusCorrect() {
        Registration registration = new Registration();

        assertEquals(
                "Password successfully captured.",
                registration.passwordStatus(true)
        );
    }

    // Password incorrectly formatted message
    @Test
    public void testPasswordStatusIncorrect() {
        Registration registration = new Registration();

        assertEquals(
                "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                registration.passwordStatus(false)
        );
    }


    
    // CELL PHONE NUMBER TESTS
   

    // Cell phone number correctly formatted
     
    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {
        Registration registration = new Registration();

        assertTrue(
                registration.checkCellPhoneNumber("+27838968976")
        );
    }

    // Cell phone number incorrectly formatted
     
    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {
        Registration registration = new Registration();

        assertFalse(
                registration.checkCellPhoneNumber("08966553")
        );
    }

    // Cell phone number successfully captured
    @Test
    public void testCellphoneStatusCorrect() {
        Registration registration = new Registration();

        assertEquals(
                "Cell phone number successfully added.",
                registration.cellphoneStatus(true)
        );
    }

    // Cell phone number incorrectly formatted message
    @Test
    public void testCellphoneStatusIncorrect() {
        Registration registration = new Registration();

        assertEquals(
                "Cell phone number incorrectly formatted or does not contain international code.",
                registration.cellphoneStatus(false)
        );
    }


     
    // GETTER and CAPTURE TESTS

    // Test that the username is captured correctly
    @Test
    public void testUsernameIsCaptured() {
        Registration registration = new Registration();

        registration.checkUsername("kyl_1");

        assertEquals(
                "kyl_1",
                registration.getUsername()
        );
    }

    // Test that the password is captured correctly
    @Test
    public void testPasswordIsCaptured() {
        Registration registration = new Registration();

        registration.checkPassword("Ch&&sec@ke99!");

        assertEquals(
                "Ch&&sec@ke99!",
                registration.getPassword()
        );
    }

    // Test that the cell phone number is captured correctly
    @Test
    public void testCellPhoneNumberIsCaptured() {
        Registration registration = new Registration();

        registration.checkCellPhoneNumber("+27838968976");

        assertEquals(
                "+27838968976",
                registration.getCellPhoneNumber()
        );
    }
    
}
