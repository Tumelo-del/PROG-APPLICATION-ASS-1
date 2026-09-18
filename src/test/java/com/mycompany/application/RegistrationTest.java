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
    
    public RegistrationTest() {
    }
   

    /**
     * Test of setUsername method, of class Registration.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "Username successfully captured";
        Registration instance = new Registration();
        instance.setUsername(username);
       
    }

    /**
     * Test of setPassword method, of class Registration.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "Password successfuly captured";
        Registration instance = new Registration();
        instance.setPassword(password);
         
    }

    /**
     * Test of setCellPhoneNumber method, of class Registration.
     */
    @Test
    public void testSetCellPhoneNumber() {
        System.out.println("setCellPhoneNumber");
        String cellPhoneNumber = "Cellphone number captured";
        Registration instance = new Registration();
        instance.setCellPhoneNumber(cellPhoneNumber);
        
    }

    /**
     * Test of getUsername method, of class Registration.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
          
    }

    /**
     * Test of getPassword method, of class Registration.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
         
    }

    /**
     * Test of getCellPhoneNumber method, of class Registration.
     */
    @Test
    public void testGetCellPhoneNumber() {
        System.out.println("getCellPhoneNumber");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.getCellPhoneNumber();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of checkUsername method, of class Registration.
     */
    @Test
    public void testCheckUsername() {
        System.out.println("checkUsername");
        String username = "";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.checkUsername(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkPassword method, of class Registration.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        String password = "";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.checkPassword(password);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkCellPhoneNumber method, of class Registration.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String cellPhoneNumber = "";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cellPhoneNumber);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of usernameStatus method, of class Registration.
     */
    @Test
    public void testUsernameStatus() {
        System.out.println("usernameStatus");
        boolean valid = false;
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.usernameStatus(valid);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of passwordStatus method, of class Registration.
     */
    @Test
    public void testPasswordStatus() {
        System.out.println("passwordStatus");
        boolean valid = false;
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.passwordStatus(valid);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of cellphoneStatus method, of class Registration.
     */
    @Test
    public void testCellphoneStatus() {
        System.out.println("cellphoneStatus");
        boolean valid = false;
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.cellphoneStatus(valid);
        assertEquals(expResult, result);
       
    }
    
}
