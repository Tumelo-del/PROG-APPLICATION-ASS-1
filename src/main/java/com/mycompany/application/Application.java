/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.application;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Application {

    public static void main(String[] args) {
         Scanner input = new Scanner(System.in);

        // Create Registration object
        Registration registration = new Registration();


        // Capture username
        System.out.println("Please enter your username:");
        String username = input.nextLine();

        // Validate and capture username
        boolean usernameValid = registration.checkUsername(username);
        System.out.println(registration.usernameStatus(usernameValid));

        // Capture password
        System.out.println("Please enter your password:");
        String password = input.nextLine();

        // Validate and capture password
        boolean passwordValid = registration.checkPassword(password);
        System.out.println(registration.passwordStatus(passwordValid));

        // Capture cellphone number
        System.out.println("Please enter your cellphone number with the international code:");
        String cellPhoneNumber = input.nextLine();

        // Validate and capture cellphone number
        boolean cellPhoneValid = registration.checkCellPhoneNumber(cellPhoneNumber);
        System.out.println(registration.cellphoneStatus(cellPhoneValid));


        //Registration
        if (usernameValid && passwordValid && cellPhoneValid) {

            System.out.println("User has been successfully registered.");

            // Create Login object using the successfully
            // captured registration details
            Login login = new Login(
                    registration.getUsername(),
                    registration.getPassword(),
                    registration.getCellPhoneNumber()
            );


            // Login

            System.out.println("Please enter your username to login:");
            String loginUsername = input.nextLine();

            System.out.println("Please enter your password to login:");
            String loginPassword = input.nextLine();

            // Check login details
            boolean loginSuccessful = login.loginUser(
                    loginUsername,
                    loginPassword
            );

            // Display login status
            System.out.println(
                    login.returnLoginStatus(loginSuccessful)
            );

        } else {

            System.out.println("Registration failed. Please try again.");
        }

        input.close();
    }
}
