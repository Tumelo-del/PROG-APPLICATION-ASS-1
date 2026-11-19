package com.mycompany.application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    // messages.json is backed up before each test and restored afterwards,
    // because Message.sentMessage(3) and MessageManager.deleteByHash() write to it
    private Path file;
    private boolean fileExisted;
    private byte[] fileBackup;

    // ===== Test data from the brief =====
    // Message 1: +27834557896, "Did you get the cake?", Sent
    // Message 2: +27838884567, "Where are you? You are late! I have asked you to be on time.", Stored
    // Message 3: +27834484567, "Yohoooo, I am at your gate.", Disregard
    // Message 4: 0838884567,   "It is dinner time!", Sent
    // Message 5: +27838884567, "Ok, I am leaving without you.", Stored
    private static final String TEXT1 = "Did you get the cake?";
    private static final String TEXT2 = "Where are you? You are late! I have asked you to be on time.";
    private static final String TEXT3 = "Yohoooo, I am at your gate.";
    private static final String TEXT4 = "It is dinner time!";
    private static final String TEXT5 = "Ok, I am leaving without you.";

    private static final String ID1 = "0123456781";
    private static final String ID2 = "0123456782";
    private static final String ID3 = "0123456783";
    private static final String ID4 = "0838884567";
    private static final String ID5 = "0123456785";

    private static final String HASH1 = "01:1:DIDCAKE";
    private static final String HASH2 = "01:2:WHERETIME";
    private static final String HASH4 = "08:4:ITTIME";
    private static final String HASH5 = "01:5:OKYOU";

    private static final String RECIPIENT2_AND_5 = "+27838884567";

    // builds test message 1 to 5 and sets its flag (Sent / Stored / Disregarded)
    private Message makeMessage(int n) {
        Message m;
        if (n == 1) {
            m = new Message(ID1, 1, "+27834557896", TEXT1);
            m.sentMessage(1);          // Sent
        } else if (n == 2) {
            m = new Message(ID2, 2, RECIPIENT2_AND_5, TEXT2);
            m.sentMessage(3);          // Stored
        } else if (n == 3) {
            m = new Message(ID3, 3, "+27834484567", TEXT3);
            m.sentMessage(2);          // Disregarded
        } else if (n == 4) {
            m = new Message(ID4, 4, "0838884567", TEXT4);
            m.sentMessage(1);          // Sent
        } else if (n == 5) {
            m = new Message(ID5, 5, RECIPIENT2_AND_5, TEXT5);
            m.sentMessage(3);          // Stored
        } else {
            throw new IllegalArgumentException("No test message " + n);
        }
        return m;
    }

    // adds test messages 1 to 5 (in order) to the manager
    private void addTestData(MessageManager instance) {
        for (int i = 1; i <= 5; i++) {
            instance.addMessage(makeMessage(i));
        }
    }

    // runs the Stored Messages menu with the typed text and returns what it printed
    private String runMenu(MessageManager instance, String typed) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(captured));
            instance.showStoredMenu(new Scanner(new ByteArrayInputStream(
                    typed.getBytes(StandardCharsets.UTF_8))));
        } finally {
            System.setOut(originalOut);
        }
        return captured.toString();
    }

    public MessageManagerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        file = Paths.get(MessageManager.FILE_NAME);
        fileExisted = Files.exists(file);
        if (fileExisted) {
            fileBackup = Files.readAllBytes(file);
        }
        Files.deleteIfExists(file);        // every test starts without a messages.json
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (fileExisted) {
            Files.write(file, fileBackup);  // put the real file back
        } else {
            Files.deleteIfExists(file);
        }
    }

    /**
     * Test of addMessage method, of class MessageManager.
     */
    @Test
    public void ValidtestAddMessage() {
        System.out.println("addMessage");
        MessageManager instance = new MessageManager();
        addTestData(instance);

        // 2 sent (1 and 4), 1 disregarded (3), 2 stored (2 and 5)
        assertEquals(2, instance.getSentCount());
        assertEquals(1, instance.getDisregardedCount());
        assertEquals(2, instance.getStoredCount());
        assertEquals(5, instance.getTotalCaptured());
    }
     @Test
    public void InvalidtestAddMessage() {
        System.out.println("addMessage");
        MessageManager instance = new MessageManager();
        // a message that was never sent, disregarded or stored is not put in any array
        Message m = new Message("0123456786", 6, "+27831234567", "Hello there");
        instance.addMessage(m);

        assertEquals(0, instance.getSentCount());
        assertEquals(0, instance.getDisregardedCount());
        assertEquals(0, instance.getStoredCount());
        assertEquals(0, instance.getIdCount());
    }

    /**
     * Test of loadStoredFromJson method, of class MessageManager.
     */
    @Test
    public void ValidtestLoadStoredFromJson() {
        System.out.println("loadStoredFromJson");
        // storing messages 2 and 5 writes them into messages.json
        makeMessage(2);
        makeMessage(5);

        MessageManager instance = new MessageManager();
        String expResult = "2 stored message(s) loaded from messages.json";
        String result = instance.loadStoredFromJson();
        assertEquals(expResult, result);
        assertEquals(2, instance.getStoredCount());
        assertEquals(TEXT2, instance.getStoredMessage(0));
        assertEquals(TEXT5, instance.getStoredMessage(1));
    }
       @Test
    public void InvalidtestLoadStoredFromJson() {
        System.out.println("loadStoredFromJson");
        // setUp() removed messages.json, so there is nothing to read
        MessageManager instance = new MessageManager();
        String expResult = "No stored messages on file yet.";
        String result = instance.loadStoredFromJson();
        assertEquals(expResult, result);
        assertEquals(0, instance.getStoredCount());
    }

    /**
     * Test of showStoredMenu method, of class MessageManager.
     */
    @Test
    public void ValidtestShowStoredMenu() {
        System.out.println("showStoredMenu");
        MessageManager instance = new MessageManager();
        addTestData(instance);

        // option 2 = longest stored message, option 7 = back to the main menu
        String output = runMenu(instance, "2\n7\n");
        assertTrue(output.contains("STORED MESSAGES"));
        assertTrue(output.contains("Longest message: " + TEXT2));
    }
    @Test
     public void InvalidtestShowStoredMenu() {
        System.out.println("showStoredMenu");
        MessageManager instance = new MessageManager();

        // letters and a number outside 1-7 are rejected, then 7 leaves the menu
        String output = runMenu(instance, "abc\n9\n7\n");
        assertTrue(output.contains("Please enter a number from 1 to 7."));
        assertTrue(output.contains("Please choose a number from 1 to 7."));
    }
    /**
     * Test of displaySendersAndRecipients method, of class MessageManager.
     */
    @Test
    public void ValidtestDisplaySendersAndRecipients() {
        System.out.println("displaySendersAndRecipients");
        MessageManager instance = new MessageManager();
        addTestData(instance);

        // only the stored messages (2 and 5) are displayed
        String expResult = "Sender: Unknown | Recipient: +27838884567\n"
                         + "Sender: Unknown | Recipient: +27838884567\n";
        String result = instance.displaySendersAndRecipients();
        assertEquals(expResult, result);
    }
       @Test
    public void InvalidtestDisplaySendersAndRecipients() {
        System.out.println("displaySendersAndRecipients");
        MessageManager instance = new MessageManager();
        String expResult = "No messages stored yet.";
        String result = instance.displaySendersAndRecipients();
        assertEquals(expResult, result);
    }

    /**
     * Test of longestStoredMessage method, of class MessageManager.
     */
    @Test
    public void ValidtestLongestStoredMessage() {
        System.out.println("longestStoredMessage");
        MessageManager instance = new MessageManager();
        // Test data: message 1-4
        for (int i = 1; i <= 4; i++) {
            instance.addMessage(makeMessage(i));
        }
        String expResult = "Where are you? You are late! I have asked you to be on time.";
        String result = instance.longestStoredMessage();
        assertEquals(expResult, result);
    }
    @Test
    public void InvalidtestLongestStoredMessage() {
        System.out.println("longestStoredMessage");
        MessageManager instance = new MessageManager();
        String expResult = "No messages stored yet.";
        String result = instance.longestStoredMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of searchById method, of class MessageManager.
     */
    @Test
    public void ValidtestSearchById() {
        System.out.println("searchById");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String id = ID2;
        String expResult = "Recipient: +27838884567 | Message: " + TEXT2;
        String result = instance.searchById(id);
        assertEquals(expResult, result);
    }
    @Test
    public void invalidtestSearchById() {
        System.out.println("searchById");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String id = "9999999999";
        String expResult = "Message ID 9999999999 not found.";
        String result = instance.searchById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchByRecipient method, of class MessageManager.
     */
    @Test
    public void ValidtestSearchByRecipient() {
        System.out.println("searchByRecipient");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String recipient = "+27838884567";
        String expResult = "Where are you? You are late! I have asked you to be on time.\n"
                         + "Ok, I am leaving without you.\n";
        String result = instance.searchByRecipient(recipient);
        assertEquals(expResult, result);
    }
@Test
    public void InvalidtestSearchByRecipient() {
        System.out.println("searchByRecipient");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String recipient = "+27830000000";
        String expResult = "No stored messages found for +27830000000.";
        String result = instance.searchByRecipient(recipient);
        assertEquals(expResult, result);
    }
    /**
     * Test of deleteByHash method, of class MessageManager.
     */
    @Test
    public void ValidtestDeleteByHash() {
        System.out.println("deleteByHash");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String hash = HASH2;                        // Test message 2
        String expResult = "Message \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        String result = instance.deleteByHash(hash);
        assertEquals(expResult, result);
        assertEquals(1, instance.getStoredCount());
        assertEquals(TEXT5, instance.getStoredMessage(0));
    }
     @Test
    public void InvalidtestDeleteByHash() {
        System.out.println("deleteByHash");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String hash = "99:9:NOPE";
        String expResult = "Message hash 99:9:NOPE not found.";
        String result = instance.deleteByHash(hash);
        assertEquals(expResult, result);
        assertEquals(2, instance.getStoredCount());
    }

    /**
     * Test of report method, of class MessageManager.
     */
    @Test
    public void ValidtestReport() {
        System.out.println("report");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String expResult = "STORED MESSAGES REPORT\n"
                + "----------\n"
                + "Message ID: " + ID2 + "\n"
                + "Message Hash: " + HASH2 + "\n"
                + "Sender: Unknown\n"
                + "Recipient: +27838884567\n"
                + "Message: " + TEXT2 + "\n"
                + "----------\n"
                + "Message ID: " + ID5 + "\n"
                + "Message Hash: " + HASH5 + "\n"
                + "Sender: Unknown\n"
                + "Recipient: +27838884567\n"
                + "Message: " + TEXT5 + "\n"
                + "----------\nTotal stored: 2";
        String result = instance.report();
        assertEquals(expResult, result);
    }
    @Test
    public void InvalidtestReport() {
        System.out.println("report");
        MessageManager instance = new MessageManager();
        String expResult = "No messages stored yet.";
        String result = instance.report();
        assertEquals(expResult, result);
    }
    /**
     * Test of getSentCount method, of class MessageManager.
     */
    @Test
    public void ValidtestGetSentCount() {
        System.out.println("getSentCount");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        int expResult = 2;
        int result = instance.getSentCount();
        assertEquals(expResult, result);
    }
    @Test
    public void InvalidtestGetSentCount() {
        System.out.println("getSentCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getSentCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDisregardedCount method, of class MessageManager.
     */
    @Test
    public void ValidtestGetDisregardedCount() {
        System.out.println("getDisregardedCount");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        int expResult = 1;
        int result = instance.getDisregardedCount();
        assertEquals(expResult, result);
    }
     @Test
    public void InvalidtestGetDisregardedCount() {
        System.out.println("getDisregardedCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getDisregardedCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStoredCount method, of class MessageManager.
     */
    @Test
    public void ValidtestGetStoredCount() {
        System.out.println("getStoredCount");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        int expResult = 2;
        int result = instance.getStoredCount();
        assertEquals(expResult, result);
    }
    @Test
    public void InvalidtestGetStoredCount() {
        System.out.println("getStoredCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getStoredCount();
        assertEquals(expResult, result);
    }
    /**
     * Test of getTotalCaptured method, of class MessageManager.
     */
    @Test
    public void ValidtestGetTotalCaptured() {
        System.out.println("getTotalCaptured");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        int expResult = 5;
        int result = instance.getTotalCaptured();
        assertEquals(expResult, result);
    }
      @Test
    public void InvalidtestGetTotalCaptured() {
        System.out.println("getTotalCaptured");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getTotalCaptured();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdCount method, of class MessageManager.
     */
    @Test
    public void ValidtestGetIdCount() {
        System.out.println("getIdCount");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        // sent (1 and 4) + stored (2 and 5) each add an ID and a hash
        int expResult = 4;
        int result = instance.getIdCount();
        assertEquals(expResult, result);
    }
      @Test
    public void InvalidtestGetIdCount() {
        System.out.println("getIdCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getIdCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSentMessage method, of class MessageManager.
     */
    @Test
    public void ValidtestGetSentMessage() {
        System.out.println("getSentMessage");
        MessageManager instance = new MessageManager();
        // Test data: developer entry for message 1-4
        for (int i = 1; i <= 4; i++) {
            instance.addMessage(makeMessage(i));
        }
        assertEquals("Did you get the cake?", instance.getSentMessage(0));
        assertEquals("It is dinner time!", instance.getSentMessage(1));
    }
    @Test
    public void InvalidtestGetSentMessage() {
        System.out.println("getSentMessage");
        int index = 2;                              // only 2 messages were sent
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String result = instance.getSentMessage(index);
        assertNull(result);
    }

    /**
     * Test of getDisregardedMessage method, of class MessageManager.
     */
    @Test
    public void ValidtestGetDisregardedMessage() {
        System.out.println("getDisregardedMessage");
        int index = 0;
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String expResult = "Yohoooo, I am at your gate.";
        String result = instance.getDisregardedMessage(index);
        assertEquals(expResult, result);
    }
    @Test
    public void InvalidtestGetDisregardedMessage() {
        System.out.println("getDisregardedMessage");
        int index = 1;                              // only 1 message was disregarded
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String result = instance.getDisregardedMessage(index);
        assertNull(result);
    }

    /**
     * Test of getStoredMessage method, of class MessageManager.
     */
    @Test
    public void ValidtestGetStoredMessage() {
        System.out.println("getStoredMessage");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                instance.getStoredMessage(0));
        assertEquals("Ok, I am leaving without you.", instance.getStoredMessage(1));
    }
    @Test
    public void InvalidtestGetStoredMessage() {
        System.out.println("getStoredMessage");
        int index = 2;                              // only 2 messages were stored
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String result = instance.getStoredMessage(index);
        assertNull(result);
    }

    /**
     * Test of getMessageHash method, of class MessageManager.
     */
    @Test
    public void ValidtestGetMessageHash() {
        System.out.println("getMessageHash");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        // order of the Message Hash array: message 1, 2, 4, 5 (message 3 was disregarded)
        assertEquals(HASH1, instance.getMessageHash(0));
        assertEquals(HASH2, instance.getMessageHash(1));
        assertEquals(HASH4, instance.getMessageHash(2));
        assertEquals(HASH5, instance.getMessageHash(3));
    }
      @Test
    public void InvalidtestGetMessageHash() {
        System.out.println("getMessageHash");
        int index = 4;                              // only 4 hashes exist (index 0 to 3)
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String result = instance.getMessageHash(index);
        assertNull(result);
    }

    /**
     * Test of getMessageId method, of class MessageManager.
     */
    @Test
    public void ValidtestGetMessageId() {
        System.out.println("getMessageId");
        MessageManager instance = new MessageManager();
        addTestData(instance);
        // order of the Message ID array: message 1, 2, 4, 5 (message 3 was disregarded)
        assertEquals(ID1, instance.getMessageId(0));
        assertEquals(ID2, instance.getMessageId(1));
        assertEquals("0838884567", instance.getMessageId(2));
        assertEquals(ID5, instance.getMessageId(3));
    }
       @Test
    public void InvalidtestGetMessageId() {
        System.out.println("getMessageId");
        int index = 4;                              // only 4 IDs exist (index 0 to 3)
        MessageManager instance = new MessageManager();
        addTestData(instance);
        String result = instance.getMessageId(index);
        assertNull(result);
    }

}