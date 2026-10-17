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
        boolean cellPhoneValid =
                registration.checkCellPhoneNumber(cellPhoneNumber);

        System.out.println(registration.cellphoneStatus(cellPhoneValid));


        // Registration
        if (usernameValid && passwordValid && cellPhoneValid) {

            System.out.println("User has been successfully registered.");

            // Create Login object
            Login login = new Login(registration.getUsername(),registration.getPassword(),registration.getCellPhoneNumber());


            // Login
            System.out.println("Please enter your username to login:");
            String loginUsername = input.nextLine();

            System.out.println("Please enter your password to login:");
            String loginPassword = input.nextLine();


            // Check login details
            boolean loginSuccessful = login.loginUser(loginUsername, loginPassword);


            // Display login status
            System.out.println(login.returnLoginStatus(loginSuccessful));


         
            // PART 2 - QUICKCHAT
               if (loginSuccessful) {

                System.out.println();
                System.out.println("Welcome to QuickChat.");

                // Ask how many messages the user wants to enter
                System.out.print("How many messages would you like to enter? ");
                int numberOfMessages = input.nextInt();

                input.nextLine();

                int messagesEntered = 0;


                // Application keeps running until user chooses Quit
                while (true) {

                    System.out.println();
                    System.out.println("Please select an option:");
                    System.out.println("1. Send Messages");
                    System.out.println("2. Show recently sent messages");
                    System.out.println("3. Quit");

                    System.out.print("Enter your choice: ");
                    String choice = input.nextLine();


                    switch (choice) {

                        case "1":

                            // Check whether the user has reached
                            // the number of messages they selected
                            if (messagesEntered >= numberOfMessages) {

                                System.out.println(
                                        "You have entered the maximum number of messages."
                                );

                                break;
                            }


                            // Recipient
                            System.out.println();
                            System.out.print("Enter recipient cell number: ");
                            String recipient = input.nextLine();


                            // Message
                            System.out.print("Enter your message: ");
                            String messageText = input.nextLine();


                            // Check message length
                            if (messageText.length() > 250) {

                                System.out.println(
                                        "Please enter a message of less than 250 characters."
                                );

                                break;
                            }


                            // Create Message object
                            messagesEntered++;

                            Message message = new Message(
                                    messagesEntered,
                                    recipient,
                                    messageText
                            );


                            // Check Message ID
                            if (!message.checkMessageID()) {

                                System.out.println(
                                        "Message ID is incorrectly formatted."
                                );

                                break;
                            }


                            // Check recipient
                            System.out.println(
                                    message.checkRecipientCell()
                            );


                            // Check whether recipient is valid
                            if (!recipient.startsWith("+")
                                    || recipient.length() > 10) {

                                break;
                            }


                            // Display Message Hash
                            System.out.println(
                                    "Message Hash: "
                                    + message.createMessageHash()
                            );


                            // Send, store or disregard
                            System.out.println(
                                    message.SentMessage()
                            );

                            break;


                        case "2":

                            System.out.println("Coming Soon.");

                            break;


                        case "3":

                            System.out.println(
                                    "Thank you for using QuickChat."
                            );

                            // Display total messages
                            System.out.println(
                                    "Total messages sent: "
                                    + new Message(
                                            0,
                                            "+0",
                                            "temporary"
                                    ).returnTotalMessages()
                            );

                            input.close();

                            return;


                        default:

                            System.out.println(
                                    "Invalid option. Please select 1, 2 or 3."
                            );
                    }
                }

            } else {

                // User cannot access QuickChat
                // if login failed
                System.out.println(
                        "You must successfully log in before using QuickChat."
                );
            }

        } else {

            System.out.println(
                    "Registration failed. Please try again."
            );
        }

        input.close();
    }
}
