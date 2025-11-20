package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    void checkMessageWithinCharacterLimit() {
        Message msg = new Message(Message.generateRandomID(), 1, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        String result = msg.validateMessageLength();
        assertEquals("Message ready to send.", result);
    }

    @Test
    void checkMessageExceedsCharacterLimit() {
        String longMessage = "A".repeat(250);
        Message msg = new Message(Message.generateRandomID(), 1, "+27718693002", longMessage);
        String result = msg.validateMessageLength();
        assertEquals("Message too long. Please limit your message to 250 characters.", result);
    }

    @Test
    void checkRecipientNumberCorrect() {
        Message msg = new Message(Message.generateRandomID(), 1, "+27718693002", "Dinner tonight?");
        int result = msg.checkRecipientCell();
        assertEquals(1, result, "Should return 1 for correctly formatted +27 number.");
    }

    @Test
    void checkRecipientNumberIncorrect() {
        Message msg = new Message(Message.generateRandomID(), 1, "08575975889", "Dinner tonight?");
        int result = msg.checkRecipientCell();
        assertEquals(0, result, "Should return 0 for incorrectly formatted number.");
    }

    @Test
    void testMessageHashGeneration() {
        Message msg = new Message(Message.generateRandomID(), 1, "+27718693002", "Dinner tonight?");
        String hash = msg.createMessageHash();
        assertFalse(hash.matches("\\d{2}:\\d+:[A-Z]+[A-Z]+"),
                "Hash should be in format NN:count:FIRSTLAST in uppercase.");
    }

    @Test
    void testMessageIDLimit() {
        Message msg = new Message(Message.generateRandomID(), 1, "+27718693002", "Testing ID length");
        assertTrue(String.valueOf(msg.generateRandomID()).length() == 10,
                "Message ID should have 10 digits.");
    }

    @Test
    void testSendMessageOption_Send() {
        Message msg = new Message(Message.generateRandomID(), 1, "+27718693002", "Quick test for send option");
        String result = msg.sendMessage(1);
        assertTrue(result.contains("sent"), "Should confirm message sent.");
    }

    @Test
    void testSendMessageOption_Disregard() {
        Message msg = new Message(Message.generateRandomID(), 1, "+27718693002", "Quick test for disregard option");
        String result = msg.sendMessage(2);
        assertTrue(result.contains("disregarded"), "Should confirm message disregarded.");
    }

    @Test
    void testSendMessageOption_Store() {
        Message msg = new Message(Message.generateRandomID(), 1, "+27718693002", "Quick test for store option");
        String result = msg.sendMessage(3);
        assertTrue(result.contains("stored"), "Should confirm message stored for later.");
    }

    @Test
    void testReturnTotalMessages() {
        new Message(Message.generateRandomID(), 1, "+27718693002", "Message one").sendMessage(1);
        new Message(Message.generateRandomID(), 2, "+27718693002", "Message two").sendMessage(1);
        int total = Message.returnTotalMessages();
        assertTrue(total >= 2, "Total sent messages should be at least 2.");
    }
}
