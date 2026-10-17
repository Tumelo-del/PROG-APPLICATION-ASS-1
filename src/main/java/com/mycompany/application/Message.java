/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.application;

 
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Message {
    
    // Message variables
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    
     // Stores messages that have been successfully sent
    // Stores sent messages
    private static ArrayList<Message> sentMessages = new ArrayList<>();

    // Total number of messages sent
    private static int totalMessages = 0;

    


    // Constructor
    public Message(int messageNumber,String recipient,String message) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;

      // Generate a random 10-digit Message ID
    public static String generateMessageID() {
    Random random = new Random();
    int firstHalf = random.nextInt(90000) + 10000;   // 10000-99999
    int secondHalf = random.nextInt(90000) + 10000;  // 10000-99999

    return "" + firstHalf + secondHalf;
}

public boolean checkMessageID() {
    return messageID.length() == 10;
}
        
     
    // String: check recipient cellphone
    public static String checkRecipientCell(String recipient) {

    if (recipient.matches("^\\+27\\d{9}$")) {
        return "Cell phone number successfully captured.";
    } else {

    return "Cell phone number incorrectly formatted or does not contain international code.";
}

    }
    public static String checkMessageNumber(String number){
     if(number.length()<=250){
         
     }else {
      int over = number.length()-250;
      return "Description is " + over + "Characters" + "too long";
     }  
    }
    // String: create Message Hash
    public String createMessageHash() {

        String[] words = message.trim().split("\\s+");

        String firstTwoNumbers = messageID.substring(0, 2);
        int messageLength = message.length();
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return (firstTwoNumbers + ":" + messageLength + ":" + firstWord + lastWord
        ).toUpperCase();
    }


    // String: Send, store or disregard message
    public String SentMessage() {

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Choose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");

        System.out.print("Enter your choice: ");

        int choice = input.nextInt();

        switch (choice) {

            case 1:

                sentMessages.add(this);
                totalMessages++;

                return "Message successfully sent";

            case 2:

                return "Press 0 to delete the message";

            case 3:

                storeMessage();

                return "Message successfully stored";

            default:

                return "Invalid choice.";
        }
    }


    // String: print all sent messages
    public String printMessages() {

        if (sentMessages.isEmpty()) {

            return "No messages have been sent.";
        }

        String result = "";

        for (Message currentMessage : sentMessages) {

            result += "Message ID: "
                    + currentMessage.messageID
                    + "\n";

            result += "Message Hash: "
                    + currentMessage.messageHash
                    + "\n";

            result += "Recipient: "
                    + currentMessage.recipient
                    + "\n";

            result += "Message: "
                    + currentMessage.message
                    + "\n";

            result += "-------------------------\n";
        }

        return result;
    }


    // Int: return total messages
    public static int returnTotalMessages() {

        return totalMessages;
    }


    // Store message
    public void storeMessage() {

        System.out.println(
                "Message successfully stored"
        );
    }


    // Getters
    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }
}
