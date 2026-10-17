/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.application;

 
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
 

/**
 *
 * @author Student
 */
public class Message {
    
        private String messageId;
        private int messageNumber;
        private String recipient;
        private String message;
        private String messageHash;
        private String status;
        
        
        private static int totalSent = 0;
        private static String sentReport = "";
        
        //constructor that runs everytime a new message is created
        public Message(String messageId, int messageNumber, String recipient,
        String message){
        this.messageId = messageId;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.status = "Captured";
        this.messageHash = createMessageHash();
}
        //makes a random 10 digit number from 2 5 digits
        //int cannot hold 10 digit.
        public static String generateMessageID(){
        Random rand = new Random();
        int firstHalf = rand.nextInt(90000) + 10000;
        int secondHalf = rand.nextInt(90000) + 10000;
        return ""+firstHalf+secondHalf;
}
        //checking if the ID is not more than 10 chars
        public boolean checkMessageID(){
        return messageId.length()<=10;
        }
        
        //recipient must be no more than 10 chars and start with an
        //international code (+)
      public static String checkRecipientCell(String cell) {
    if (cell != null && cell.matches("^\\+27\\d{9}$")) {
        return "Cell phone number successfully captured.";
    }
    return "Cell phone number is incorrectly formatted or does not contain an "
            + "international code. Please correct the number and try again.";
}
        
        
        //validate that the message is not more than 250 chars
        public static String checkMessage(String msg){
        if (msg.length()<=250){
        return "Message sent";
        }else{
        return "Please enter a message of less than 250 characters.";
        }
        }
        
        
        public String createMessageHash(){
        String msg = message.trim();
        //first 2 digits of ID
        String idFirst2 = messageId.substring(0,2);
        //First word -from where it starts to where theres a space
        String firstWord;
        int firstSpace = msg.indexOf(" ");
        if(firstSpace == -1){
        firstWord = msg;
        }else{
        firstWord = msg.substring(0,firstSpace);
        }
        //last word -from after the last space to the end
        int lastSpace = msg.lastIndexOf(" ");
        String lastWord = msg.substring(lastSpace+1);
        String code = idFirst2 + ":" + messageNumber +":"+
        firstWord + lastWord;
        return code.toUpperCase();
        }
        
        
        //1 - send, 2 - disregard, 3 - store to send later
        public String sentMessage(int choice){
        if(choice == 1){
        status = "Sent";
        return "Message successfully sent";
        }else if(choice == 2){
        status = "Disregarded";
        return "Press 0 to delete the message";
        }else if(choice == 3){
        status = "Stored";
        storeMessage();
        return "Message successfully stored";
        }else{
        return "invalid option";
        }
        }
         
       // add a message to the shared total
        public void recordMessage() {
            if ("Sent".equals(status)) {
                totalSent = totalSent + 1; // accumulator
                sentReport = sentReport + getDetails() + "\n\n";
            }
        }
       public String getDetails() {
    return String.format(
            "Message ID:   %s%n" +
            "Message Hash: %s%n" +
            "Recipient:    %s%n" +
            "Message:      %s",
            messageId, messageHash, recipient, message);
}
        //every message that is sent details
        public static String printMessages(){
        if (sentReport.equals("")){
        return "No messages sent yet";
        }else{
        return sentReport;
        }
        }
        public static int returnTotalMessages(){
        return totalSent;
        }
        
        //saves the message to a JSON file (one JSON object per line)
        
        public void storeMessage() {
    String json = String.format(
        "{\"messageId\": \"%s\", \"messageHash\": \"%s\", \"recipient\": \"%s\", \"message\": \"%s\"}",
        escapeJson(messageId),
        escapeJson(messageHash),
        escapeJson(recipient),
        escapeJson(message));

    try (FileWriter writer = new FileWriter("messages.json", true)) {
        writer.write(json + System.lineSeparator());
    } catch (IOException e) {
        System.out.println("Could not store the message: " + e.getMessage());
    }
}

private String escapeJson(String value) {
    if (value == null) {
        return "";
    }
    return value.replace("\\", "\\\\")   // backslash first
                .replace("\"", "\\\"")   // double quotes
                .replace("\n", "\\n")    // newlines
                .replace("\r", "\\r")
                .replace("\t", "\\t");
}

public String getMessageId() {
    return messageId;
}

public String getMessageHash() {
    return messageHash;
}

// TODO: add a resetTotals() method (depends on which total fields you're tracking)
}
