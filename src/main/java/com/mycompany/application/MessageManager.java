package com.mycompany.application;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Manages the five required arrays and the "Stored Messages" menu.
 *
 * @author Student
 */
public class MessageManager {

    public static final int MAX_MESSAGES = 50;
    public static final String FILE_NAME = "messages.json";

    // ===== The five required arrays (all filled while the app runs, nothing hard-coded) =====

    // 1. Sent Message array
    private String[] sentMessages = new String[MAX_MESSAGES];
    private int sentCount = 0;

    // 2. Disregarded Messages array
    private String[] disregardedMessages = new String[MAX_MESSAGES];
    private int disregardedCount = 0;

    // 3. Stored Messages array(s) - filled from the JSON file and from newly stored messages
    //    (parallel arrays: the same index belongs to the same message)
    private String[] storedIds = new String[MAX_MESSAGES];
    private int[] storedNumbers = new int[MAX_MESSAGES];
    private String[] storedSenders = new String[MAX_MESSAGES];
    private String[] storedRecipients = new String[MAX_MESSAGES];
    private String[] storedMessages = new String[MAX_MESSAGES];
    private int storedCount = 0;

    // 4. Message Hash array and 5. Message ID array (same index = same message)
    private String[] messageHashes = new String[MAX_MESSAGES * 3];
    private String[] messageIds = new String[MAX_MESSAGES * 3];
    private int idCount = 0;

    private int totalCaptured = 0;

 
    // POPULATING THE ARRAYS
     
    // Puts a message into the right arrays, using the counters as the next free index
    public void addMessage(Message m) {
        totalCaptured = totalCaptured + 1;
        String status = m.getStatus();

        if (status.equals("Sent") && sentCount < MAX_MESSAGES) {
            sentMessages[sentCount] = m.getMessage();
            sentCount++;
            addHashAndId(m.getMessageHash(), m.getMessageId());
        } else if (status.equals("Disregarded") && disregardedCount < MAX_MESSAGES) {
            disregardedMessages[disregardedCount] = m.getMessage();
            disregardedCount++;
        } else if (status.equals("Stored") && storedCount < MAX_MESSAGES) {
            addToStored(m);
        }
    }

    // Adds a hash and an ID to the Message Hash and Message ID arrays
    private void addHashAndId(String hash, String id) {
        if (idCount < messageHashes.length) {
            messageHashes[idCount] = hash;
            messageIds[idCount] = id;
            idCount++;
        }
    }

    // Checks whether a message ID is already in the stored arrays (prevents duplicates)
    private boolean isAlreadyStored(String id) {
        for (int i = 0; i < storedCount; i++) {
            if (storedIds[i] != null && storedIds[i].equals(id)) {
                return true;
            }
        }
        return false;
    }

    // Copies one Message object into the stored arrays
    private void addToStored(Message m) {
        addToStored(m.getMessageId(), m.getMessageNumber(), m.getSender(),
                    m.getRecipient(), m.getMessage(), m.getMessageHash());
    }

    // Copies one message (as plain values) into every stored array at the next free index
    private void addToStored(String id, int number, String sender,
                             String recipient, String message, String hash) {
        if (storedCount >= MAX_MESSAGES || isAlreadyStored(id)) {
            return;
        }
        storedIds[storedCount] = id;
        storedNumbers[storedCount] = number;
        storedSenders[storedCount] = sender;
        storedRecipients[storedCount] = recipient;
        storedMessages[storedCount] = message;
        storedCount++;
        addHashAndId(hash, id);
    }

    // Reads messages.json INTO the stored arrays (no library, just string handling)
    public String loadStoredFromJson() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_NAME)), StandardCharsets.UTF_8);
            int before = storedCount;
            int pos = 0;

            while (true) {
                int start = json.indexOf('{', pos);
                if (start == -1) {
                    break;
                }
                int end = findObjectEnd(json, start);
                if (end == -1) {
                    break;
                }
                String obj = json.substring(start, end + 1);

                int number = 0;
                try {
                    number = Integer.parseInt(getJsonValue(obj, "messageNumber").trim());
                } catch (NumberFormatException e) {
                    number = 0;
                }

                addToStored(getJsonValue(obj, "messageId"),
                            number,
                            getJsonValue(obj, "sender"),
                            getJsonValue(obj, "recipient"),
                            getJsonValue(obj, "message"),
                            getJsonValue(obj, "messageHash"));

                pos = end + 1;
            }
            return (storedCount - before) + " stored message(s) loaded from " + FILE_NAME;
        } catch (IOException e) {
            return "No stored messages on file yet.";
        }
    }

    // Finds the closing } of the JSON object that starts at 'start' (ignores braces inside text)
    private int findObjectEnd(String json, int start) {
        boolean inString = false;
        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            if (inString) {
                if (c == '\\') {
                    i++;                       // skip the escaped character
                } else if (c == '"') {
                    inString = false;
                }
            } else if (c == '"') {
                inString = true;
            } else if (c == '}') {
                return i;
            }
        }
        return -1;
    }

    // Gets the value of a key from one JSON object, e.g. "sender": "+27..." -> +27...
    private String getJsonValue(String obj, String key) {
        int k = obj.indexOf("\"" + key + "\"");
        if (k == -1) {
            return "";
        }
        int colon = obj.indexOf(':', k + key.length() + 2);
        if (colon == -1) {
            return "";
        }
        int i = colon + 1;
        while (i < obj.length() && Character.isWhitespace(obj.charAt(i))) {
            i++;
        }

        String value = "";
        if (i < obj.length() && obj.charAt(i) == '"') {
            i++;                                           // skip opening quote
            while (i < obj.length() && obj.charAt(i) != '"') {
                char c = obj.charAt(i);
                if (c == '\\' && i + 1 < obj.length()) {   // undo JSON escapes
                    i++;
                    char n = obj.charAt(i);
                    if (n == 'n') {
                        value = value + "\n";
                    } else if (n == 'r') {
                        value = value + "\r";
                    } else if (n == 't') {
                        value = value + "\t";
                    } else {
                        value = value + n;
                    }
                } else {
                    value = value + c;
                }
                i++;
            }
        } else {                                           // a number, e.g. 3
            while (i < obj.length() && obj.charAt(i) != ',' && obj.charAt(i) != '}') {
                value = value + obj.charAt(i);
                i++;
            }
        }
        return value;
    }

  

    public void showStoredMenu(Scanner input) {
        boolean inStored = true;

        while (inStored) {
            System.out.println("\nSTORED MESSAGES");
            System.out.println("1) Display the sender and recipient of all stored messages");
            System.out.println("2) Display the longest stored message");
            System.out.println("3) Search for a message ID");
            System.out.println("4) Search for all messages stored for a recipient");
            System.out.println("5) Delete a message using the message hash");
            System.out.println("6) Display a report of all stored messages");
            System.out.println("7) Back to the main menu");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {      // no crash on letters / empty input
                System.out.println("Please enter a number from 1 to 7.");
                continue;
            }

            if (choice == 1) {
                System.out.println(displaySendersAndRecipients());
            } else if (choice == 2) {
                System.out.println("Longest message: " + longestStoredMessage());
            } else if (choice == 3) {
                System.out.print("Message ID: ");
                System.out.println(searchById(input.nextLine().trim()));
            } else if (choice == 4) {
                System.out.print("Recipient cell number: ");
                System.out.println(searchByRecipient(input.nextLine().trim()));
            } else if (choice == 5) {
                System.out.print("Message hash: ");
                System.out.println(deleteByHash(input.nextLine().trim().toUpperCase()));
            } else if (choice == 6) {
                System.out.println(report());
            } else if (choice == 7) {
                inStored = false;
            } else {
                System.out.println("Please choose a number from 1 to 7.");
            }
        }
    }

   

    // a) Display the sender and recipient of all stored messages
    public String displaySendersAndRecipients() {
        if (storedCount == 0) {
            return "No messages stored yet.";
        }
        String result = "";
        for (int i = 0; i < storedCount; i++) {
            result = result + "Sender: " + storedSenders[i]
                   + " | Recipient: " + storedRecipients[i] + "\n";
        }
        return result;
    }

    // b) Display the longest stored message
    public String longestStoredMessage() {
        if (storedCount == 0) {
            return "No messages stored yet.";
        }
        String longest = storedMessages[0];
        for (int i = 1; i < storedCount; i++) {
            if (storedMessages[i].length() > longest.length()) {
                longest = storedMessages[i];
            }
        }
        return longest;
    }

    // c) Search for a message ID and display the corresponding recipient and message
    public String searchById(String id) {
        for (int i = 0; i < storedCount; i++) {
            if (storedIds[i].equals(id)) {
                return "Recipient: " + storedRecipients[i]
                     + " | Message: " + storedMessages[i];
            }
        }
        return "Message ID " + id + " not found.";
    }

    // d) Search for all the messages stored for a particular recipient
    public String searchByRecipient(String recipient) {
        String result = "";
        for (int i = 0; i < storedCount; i++) {
            if (storedRecipients[i].equals(recipient)) {
                result = result + storedMessages[i] + "\n";
            }
        }
        if (result.equals("")) {
            return "No stored messages found for " + recipient + ".";
        }
        return result;
    }

    // e) Delete a message using the message hash
    public String deleteByHash(String hash) {
        // Step 1: find the message in the stored arrays by its hash
        int storedIndex = -1;
        for (int i = 0; i < storedCount; i++) {
            Message m = new Message(storedIds[i], storedNumbers[i],
                                    storedRecipients[i], storedMessages[i]);
            if (m.getMessageHash().equals(hash)) {
                storedIndex = i;
            }
        }
        if (storedIndex == -1) {
            return "Message hash " + hash + " not found.";
        }

        String deleted = storedMessages[storedIndex];
        String deletedId = storedIds[storedIndex];

        // Step 2: shift everything after it one place left, in EVERY stored array
        for (int i = storedIndex; i < storedCount - 1; i++) {
            storedIds[i] = storedIds[i + 1];
            storedNumbers[i] = storedNumbers[i + 1];
            storedSenders[i] = storedSenders[i + 1];
            storedRecipients[i] = storedRecipients[i + 1];
            storedMessages[i] = storedMessages[i + 1];
        }

        // Step 3: clear the last slot and lower the counter
        storedIds[storedCount - 1] = null;
        storedNumbers[storedCount - 1] = 0;
        storedSenders[storedCount - 1] = null;
        storedRecipients[storedCount - 1] = null;
        storedMessages[storedCount - 1] = null;
        storedCount--;

        // Step 4: remove it from the Message Hash and Message ID arrays too
        int hashIndex = -1;
        for (int i = 0; i < idCount; i++) {
            if (messageHashes[i].equals(hash) && messageIds[i].equals(deletedId)) {
                hashIndex = i;
            }
        }
        if (hashIndex != -1) {
            for (int i = hashIndex; i < idCount - 1; i++) {
                messageHashes[i] = messageHashes[i + 1];
                messageIds[i] = messageIds[i + 1];
            }
            messageHashes[idCount - 1] = null;
            messageIds[idCount - 1] = null;
            idCount--;
        }

        // Step 5: rewrite messages.json so the message does not come back next time
        rewriteJsonFile();

        return "Message \"" + deleted + "\" successfully deleted.";
    }

    // Rewrites messages.json from the stored arrays
    private void rewriteJsonFile() {
        String content = "[\n";
        for (int i = 0; i < storedCount; i++) {
            Message m = new Message(storedIds[i], storedNumbers[i],
                                    storedRecipients[i], storedMessages[i]);
            content = content + "  {\"messageId\": \"" + escapeJson(storedIds[i]) + "\", "
                    + "\"messageNumber\": " + storedNumbers[i] + ", "
                    + "\"sender\": \"" + escapeJson(storedSenders[i]) + "\", "
                    + "\"recipient\": \"" + escapeJson(storedRecipients[i]) + "\", "
                    + "\"message\": \"" + escapeJson(storedMessages[i]) + "\", "
                    + "\"messageHash\": \"" + escapeJson(m.getMessageHash()) + "\"}";
            if (i < storedCount - 1) {
                content = content + ",";
            }
            content = content + "\n";
        }
        content = content + "]";

        try {
            Files.write(Paths.get(FILE_NAME), content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println("Could not update " + FILE_NAME);
        }
    }

    // Makes text safe to put inside a JSON string
    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }

    // f) Display a report that lists the full details of all the stored messages
    public String report() {
        if (storedCount == 0) {
            return "No messages stored yet.";
        }
        String result = "STORED MESSAGES REPORT\n";
        for (int i = 0; i < storedCount; i++) {
            Message m = new Message(storedIds[i], storedNumbers[i],
                                    storedRecipients[i], storedMessages[i]);
            result = result + "----------\n"
                   + "Message ID: " + storedIds[i] + "\n"
                   + "Message Hash: " + m.getMessageHash() + "\n"
                   + "Sender: " + storedSenders[i] + "\n"
                   + "Recipient: " + storedRecipients[i] + "\n"
                   + "Message: " + storedMessages[i] + "\n";
        }
        result = result + "----------\nTotal stored: " + storedCount;
        return result;
    }

     

    public int getSentCount() {
        return sentCount;
    }

    public int getDisregardedCount() {
        return disregardedCount;
    }

    public int getStoredCount() {
        return storedCount;
    }

    public int getTotalCaptured() {
        return totalCaptured;
    }

    public int getIdCount() {
        return idCount;
    }

    public String getSentMessage(int index) {
        return sentMessages[index];
    }

    public String getDisregardedMessage(int index) {
        return disregardedMessages[index];
    }

    public String getStoredMessage(int index) {
        return storedMessages[index];
    }

    public String getMessageHash(int index) {
        return messageHashes[index];
    }

    public String getMessageId(int index) {
        return messageIds[index];
    }
}