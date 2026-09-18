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
 

    /**
     * Test of checkUserName method, of class Login.
     */
    @Test
    public void testCheckUserNameValid() {
        System.out.println("checkUserName");
        String username = "kyl_1";
        Login instance = new Login("","","");
        boolean expResult = true;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
         
    }
    @Test
    public void testCheckUserNameInvalid() {
        System.out.println("checkUserName");
        String username = "kyle!!!!!!";
        Login instance = new Login("","","");
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexityValid() {
        System.out.println("checkPasswordComplexity");
        String password = "Ch&&sec@ke99!";
        Login instance = new Login("","","");
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }
    @Test
    public void testCheckPasswordComplexityInvalid() {
        System.out.println("checkPasswordComplexity");
        String password = "password";
        Login instance = new Login("","","");
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }


    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumberValid() {
        System.out.println("checkCellPhoneNumber");
        String cellPhoneNumber = "+27838968976";
        Login instance = new Login("","","");
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(cellPhoneNumber);
        assertEquals(expResult, result);
         
    }
    @Test
    public void testCheckCellPhoneNumberInvalid() {
        System.out.println("checkCellPhoneNumber");
        String cellPhoneNumber = "08966553";
        Login instance = new Login("","","");
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cellPhoneNumber);
        assertEquals(expResult, result);
         
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String username = "Kyl_1";
        String password = "Ch&&sec@ke99!";
        String cellPhoneNumber = "+2738968976";
        Login instance = new Login("","","");
        String expResult = "Cell phone number incorrectly formatted or does not contain international code.";
        String result = instance.registerUser(username, password, cellPhoneNumber);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String username = "Kyl_1";
        String password = "Ch&&sec@ke99!";
        Login instance = new Login("","","");
        boolean expResult = false;
        boolean result = instance.loginUser(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        boolean loginSuccessful = false;
        Login instance = new Login("","","");
        String expResult = "Username or password incorrect, please try again.";
        String result = instance.returnLoginStatus(loginSuccessful);
        assertEquals(expResult, result);
        
    }
    
}
