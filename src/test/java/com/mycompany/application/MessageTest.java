/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.application;

 import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 

/**
 *
 * @author Student
 */
public class MessageTest {
    
    public MessageTest() {
        
    }
    
   

    /**
     * Test of generateMessageID method, of class Message.
     */
    @Test
    public void ValidtestGenerateMessageID() {
        System.out.println("generateMessageID");
        String result = Message.generateMessageID();
        assertNotNull(result);
        assertEquals(10, result.length());
        assertTrue(result.matches("\\d{10}"));
    }
    @Test
     public void InvalidtestGenerateMessageID() {
        System.out.println("generateMessageID");
        String result = Message.generateMessageID();
        assertNotEquals("", result);
        assertFalse(result.length() > 10);
        assertFalse(result.matches(".*\\D.*"));
    }
    /**
     * Test of checkMessageID method, of class Message.
     */
    @Test
  public void ValidtestCheckMessageID() {
        System.out.println("checkMessageID");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        boolean expResult = true;
        boolean result = instance.checkMessageID();
        assertEquals(expResult, result);
    }
       @Test
     public void InvalidtestCheckMessageID() {
        System.out.println("checkMessageID");
        Message instance = new Message("00000000011", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        boolean expResult = false;
        boolean result = instance.checkMessageID();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkRecipientCell method, of class Message.
     */
    @Test
     public void ValidtestCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String cell = "+27718693002";
        String expResult = "Cell phone number successfully captured.";
        String result = Message.checkRecipientCell(cell);
        assertEquals(expResult, result);
    }  
     @Test
     public void InvalidtestCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String cell = "08575975889";
        String expResult = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        String result = Message.checkRecipientCell(cell);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkMessage method, of class Message.
     */
    @Test
    public void ValidtestCheckMessage() {
        System.out.println("checkMessage");
        String msg = "Hi Mike, can you join us for dinner tonight?";
        String expResult = "Message ready to send.";
        String result = Message.checkMessage(msg);
        assertEquals(expResult, result);
    }
    @Test
     public void InvalidtestCheckMessage() {
        System.out.println("checkMessage");
        String msg = "a".repeat(255); // 5 characters over the limit
        String expResult = "Message exceeds 250 characters by 5; please reduce the size.";
        String result = Message.checkMessage(msg);
        assertEquals(expResult, result);
    }
    /**
     * Test of createMessageHash method, of class Message.
     */
    @Test
       public void ValidtestCreateMessageHash() {
        System.out.println("createMessageHash");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expResult = "00:0:HITONIGHT";
        String result = instance.createMessageHash();
        assertEquals(expResult, result);
    }
      @Test
        public void InValidtestCreateMessageHash() {
        System.out.println("createMessageHash");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String result = instance.createMessageHash();
        assertNotEquals("00:0:hitonight", result); // hash must be uppercase
        assertNotEquals("00:1:HITONIGHT", result); // wrong message number
    }
    /**
     * Test of sentMessage method, of class Message.
     */
    @Test
   public void ValidtestSentMessage() {
        System.out.println("sentMessage");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", instance.sentMessage(1));
        assertEquals("Press 0 to delete the message.", instance.sentMessage(2));
        assertEquals("Message successfully stored.", instance.sentMessage(3));
    }
       @Test
    public void InvalidtestSentMessage() {
        System.out.println("sentMessage");
        Message instance = new Message("0000000002", 1, "08575975889",
                "Hi Keegan, did you receive the payment?");
        String result = instance.sentMessage(2);
        assertNotEquals("Message successfully sent.", result);
        assertNotEquals("Message successfully stored.", result);
    }
    /**
     * Test of recordMessage method, of class Message.
     */
 
     public void ValidtestRecordMessage() {
        System.out.println("recordMessage");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        int before = Message.returnTotalMessages();
        instance.sentMessage(1);
        instance.recordMessage();
        assertEquals(before + 1, Message.returnTotalMessages());
    }
     @Test
    public void InvalidtestRecordMessage() {
        System.out.println("recordMessage");
        Message instance = new Message("0000000002", 1, "08575975889",
                "Hi Keegan, did you receive the payment?");
        int before = Message.returnTotalMessages();
        instance.sentMessage(2);
        instance.recordMessage();
        assertEquals(before, Message.returnTotalMessages());
    }

    /**
     * Test of getDetails method, of class Message.
     */
    @Test
   public void ValidtestGetDetails() {
        System.out.println("getDetails");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String result = instance.getDetails();
        assertTrue(result.contains("0000000001"));
        assertTrue(result.contains("00:0:HITONIGHT"));
        assertTrue(result.contains("+27718693002"));
        assertTrue(result.contains("Hi Mike, can you join us for dinner tonight?"));
    }
       @Test
      public void InvalidtestGetDetails() {
        System.out.println("getDetails");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String result = instance.getDetails();
        assertFalse(result.contains("08575975889"));
        assertFalse(result.contains("Hi Keegan, did you receive the payment?"));
    }
    /**
     * Test of printMessages method, of class Message.
     */
    @Test
    public void ValidtestPrintMessages() {
        System.out.println("printMessages");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        instance.sentMessage(1);
        instance.recordMessage();
        String result = Message.printMessages();
        assertTrue(result.contains("Hi Mike, can you join us for dinner tonight?"));
    }
     @Test
     public void InvalidtestPrintMessages() {
        System.out.println("printMessages");
        Message instance = new Message("0000000002", 1, "08575975889",
                "Hi Keegan, did you receive the payment?");
        instance.sentMessage(2);
        instance.recordMessage();
        String result = Message.printMessages();
        assertFalse(result.contains("Hi Keegan, did you receive the payment?"));
    }

    /**
     * Test of returnTotalMessages method, of class Message.
     */
    @Test
   public void ValidtestReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        int before = Message.returnTotalMessages();
        instance.sentMessage(1);
        instance.recordMessage();
        int expResult = before + 1;
        int result = Message.returnTotalMessages();
        assertEquals(expResult, result);
    }
    @Test
       public void InvalidtestReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        Message instance = new Message("0000000002", 1, "08575975889",
                "Hi Keegan, did you receive the payment?");
        int before = Message.returnTotalMessages();
        instance.sentMessage(2);
        instance.recordMessage();
        int expResult = before;
        int result = Message.returnTotalMessages();
        assertEquals(expResult, result);
    }

    /**
     * Test of storeMessage method, of class Message.
     */
    @Test
      public void ValidtestStoreMessage() throws IOException {
        System.out.println("storeMessage");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        instance.storeMessage();
        String contents = new String(Files.readAllBytes(Paths.get("messages.json")));
        assertTrue(contents.contains("0000000001"));
        assertTrue(contents.contains("Hi Mike, can you join us for dinner tonight?"));
    }
     @Test
      public void InvalidtestStoreMessage() throws IOException {
        System.out.println("storeMessage");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        instance.storeMessage();
        String contents = new String(Files.readAllBytes(Paths.get("messages.json")));
        assertFalse(contents.contains("9999999999")); // an ID that was never stored
    }
    /**
     * Test of getMessageId method, of class Message.
     */
    @Test
   public void ValidtestGetMessageId() {
        System.out.println("getMessageId");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expResult = "0000000001";
        String result = instance.getMessageId();
        assertEquals(expResult, result);
    }
    @Test
   public void InvalidtestGetMessageId() {
        System.out.println("getMessageId");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String result = instance.getMessageId();
        assertNotEquals("0000000002", result);
    }
    /**
     * Test of getMessageHash method, of class Message.
     */
    @Test
   public void ValidtestGetMessageHash() {
        System.out.println("getMessageHash");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expResult = "00:0:HITONIGHT";
        String result = instance.getMessageHash();
        assertEquals(expResult, result);
    }
   @Test
   public void InvalidtestGetMessageHash() {
        System.out.println("getMessageHash");
        Message instance = new Message("0000000001", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String result = instance.getMessageHash();
        assertNotEquals("00:1:HIPAYMENT", result);
    }
}
