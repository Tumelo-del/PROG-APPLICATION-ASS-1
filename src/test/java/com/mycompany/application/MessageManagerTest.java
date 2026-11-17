/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.application;

import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageManagerTest {
    
    public MessageManagerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addMessage method, of class MessageManager.
     */
    @Test
    public void testAddMessage() {
        System.out.println("addMessage");
        Message m = null;
        MessageManager instance = new MessageManager();
        instance.addMessage(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadStoredFromJson method, of class MessageManager.
     */
    @Test
    public void testLoadStoredFromJson() {
        System.out.println("loadStoredFromJson");
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.loadStoredFromJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showStoredMenu method, of class MessageManager.
     */
    @Test
    public void testShowStoredMenu() {
        System.out.println("showStoredMenu");
        Scanner input = null;
        MessageManager instance = new MessageManager();
        instance.showStoredMenu(input);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displaySendersAndRecipients method, of class MessageManager.
     */
    @Test
    public void testDisplaySendersAndRecipients() {
        System.out.println("displaySendersAndRecipients");
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.displaySendersAndRecipients();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of longestStoredMessage method, of class MessageManager.
     */
    @Test
    public void testLongestStoredMessage() {
        System.out.println("longestStoredMessage");
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.longestStoredMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchById method, of class MessageManager.
     */
    @Test
    public void testSearchById() {
        System.out.println("searchById");
        String id = "";
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.searchById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchByRecipient method, of class MessageManager.
     */
    @Test
    public void testSearchByRecipient() {
        System.out.println("searchByRecipient");
        String recipient = "";
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.searchByRecipient(recipient);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByHash method, of class MessageManager.
     */
    @Test
    public void testDeleteByHash() {
        System.out.println("deleteByHash");
        String hash = "";
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.deleteByHash(hash);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of report method, of class MessageManager.
     */
    @Test
    public void testReport() {
        System.out.println("report");
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.report();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentCount method, of class MessageManager.
     */
    @Test
    public void testGetSentCount() {
        System.out.println("getSentCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getSentCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisregardedCount method, of class MessageManager.
     */
    @Test
    public void testGetDisregardedCount() {
        System.out.println("getDisregardedCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getDisregardedCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoredCount method, of class MessageManager.
     */
    @Test
    public void testGetStoredCount() {
        System.out.println("getStoredCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getStoredCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalCaptured method, of class MessageManager.
     */
    @Test
    public void testGetTotalCaptured() {
        System.out.println("getTotalCaptured");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getTotalCaptured();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdCount method, of class MessageManager.
     */
    @Test
    public void testGetIdCount() {
        System.out.println("getIdCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getIdCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentMessage method, of class MessageManager.
     */
    @Test
    public void testGetSentMessage() {
        System.out.println("getSentMessage");
        int index = 0;
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.getSentMessage(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisregardedMessage method, of class MessageManager.
     */
    @Test
    public void testGetDisregardedMessage() {
        System.out.println("getDisregardedMessage");
        int index = 0;
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.getDisregardedMessage(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoredMessage method, of class MessageManager.
     */
    @Test
    public void testGetStoredMessage() {
        System.out.println("getStoredMessage");
        int index = 0;
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.getStoredMessage(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageHash method, of class MessageManager.
     */
    @Test
    public void testGetMessageHash() {
        System.out.println("getMessageHash");
        int index = 0;
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.getMessageHash(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageId method, of class MessageManager.
     */
    @Test
    public void testGetMessageId() {
        System.out.println("getMessageId");
        int index = 0;
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.getMessageId(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
