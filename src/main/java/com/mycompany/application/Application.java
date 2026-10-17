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

        // ===================== PART 1: REGISTRATION & LOGIN =====================

        Registration registration = new Registration();

        // Username
        System.out.println("Please enter your username:");
        String username = input.nextLine();
        boolean usernameValid = registration.checkUsername(username);
        System.out.println(registration.usernameStatus(usernameValid));

        // Password
        System.out.println("Please enter your password:");
        String password = input.nextLine();
        boolean passwordValid = registration.checkPassword(password);
        System.out.println(registration.passwordStatus(passwordValid));

        // Cellphone number
        System.out.println("Please enter your cellphone number with the international code:");
        String cellPhoneNumber = input.nextLine();
        boolean cellPhoneValid = registration.checkCellPhoneNumber(cellPhoneNumber);
        System.out.println(registration.cellphoneStatus(cellPhoneValid));

        // Stop if registration failed
        if (!(usernameValid && passwordValid && cellPhoneValid)) {
            System.out.println("Registration failed. Goodbye.");
            return;
        }

        System.out.println("User has been successfully registered.");

        // Login
        Login login = new Login(registration.getUsername(),
                registration.getPassword(),
                registration.getCellPhoneNumber());

        System.out.println("Please enter your username to login:");
        String loginUsername = input.nextLine();

        System.out.println("Please enter your password to login:");
        String loginPassword = input.nextLine();

        boolean loginSuccessful = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(loginSuccessful));

        // Stop if login failed
        if (!loginSuccessful) {
            System.out.println("Login failed. Goodbye.");
            return;
        }

        // ============================ PART 2: QUICKCHAT ============================

        System.out.println("Welcome to QuickChat.");

        System.out.print("How many messages do you wish to enter? ");
        int maxMessages = Integer.parseInt(input.nextLine());

        int messageNumber = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");

            int option = Integer.parseInt(input.nextLine());

            if (option == 1) {
                    if (messageNumber >= maxMessages) {
                    System.out.println("You have already entered the " + "number of messages you set.");
                }

                for (int i = messageNumber; i < maxMessages; i++) {
                    messageNumber = messageNumber + 1;
                    System.out.println("\nMessage " + messageNumber);
                    System.out.println("----------------------------");

                    // Recipient
                    System.out.print("Recipient cell number: ");
                    
                    String recipient = input.nextLine();
                    String recipientResult = Message.checkRecipientCell(recipient);
                    while (!recipientResult.equals("Cell phone number successfully captured.")) {
                        System.out.println(recipientResult);
                        System.out.print("Recipient cell number: ");
                        recipient = input.nextLine();
                        recipientResult = Message.checkRecipientCell(recipient);
                    }
                    System.out.println(recipientResult);

                    // Message text
                    System.out.print("Message: ");
                    String message = input.nextLine();
                    String msgResults = Message.checkMessage(message);
                    while (!msgResults.equals("Message sent")) {
                        System.out.println(msgResults);
                        System.out.print("Message: ");
                        message = input.nextLine();
                        msgResults = Message.checkMessage(message);
                    }
                    System.out.println(msgResults);

                    // Create the message object
                    String messageId = Message.generateMessageID();
                    Message msg = new Message(messageId, messageNumber, recipient, message);

                    System.out.println("Message ID generated: " + msg.getMessageId());
                    System.out.println("1) Send Message \n2) Disregard Message \n3) Store Message to send later");
                    int choice = Integer.parseInt(input.nextLine());
                    System.out.println(msg.sentMessage(choice));

                    // Save to JSON / record
                    msg.recordMessage();
                    System.out.println(msg.getDetails());
                }

                System.out.println("\nSENT MESSAGES:");
                System.out.println(Message.printMessages());
                System.out.println("Total messages sent: " + Message.returnTotalMessages());

            } else if (option == 2) {
                System.out.println("Coming Soon.");
            } else if (option == 3) {
                running = false;
            } else {
                System.out.println("Please choose 1, 2 or 3");
            }
        }

        System.out.println("Goodbye!");
    }

       
         
             
}
