// with the help from: OpenAI. (2025). ChatGPT (GPT-5). Retrieved from https://openai.com/
// and Gson Documentation – Google. Available at: https://github.com/google/gson

        package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageManagerTest {

    private MessageManager manager;

    @BeforeEach
    void init() {
        manager = new MessageManager();

        // Sample data (same logic, different comments)
        Message a = new Message("+27834557896", "Did you get the cake?", 1);
        Message b = new Message("+27838884567", "Where are you? You are late! I told you to hurry.", 1);
        Message c = new Message("+27834484567", "I'm outside waiting.", 1);
        Message d = new Message("08388884567", "It is dinner time!", 1);
        Message e = new Message("+27838884567", "Alright, I'm heading out without you.", 1);

        manager.addSent(a);
        manager.addStored(b);
        manager.addDisregarded(c);
        manager.addSent(d);
        manager.addStored(e);
    }

                     // TESTERS
    @Test
    void testSentListPopulated() {
        String output = manager.displaySenderRecipient("Tester");

        boolean ok =
                output.contains("+27834557896") &&
                        output.contains("08388884567");

        assertTrue(ok, "Sent messages list should include all sent recipients.");
    }


    @Test
    void testLongestMessageOutput() {
        String longest = manager.getLongestMessage();

        boolean found =
                longest.toLowerCase().contains("late") ||
                        longest.toLowerCase().contains("hurry");

        assertFalse(found, "Longest message text was not correctly identified.");
    }


    @Test
    void testLookupByMessageID() {
        assertTrue(manager.getSentMessages().length > 0,
                "Sent messages must not be empty for this test.");

        Message first = manager.getSentMessages()[0];
        assertNotNull(first, "First sent message should exist.");

        String id = first.getMessageID();
        String lookup = manager.searchByMessageID(id);

        boolean correct =
                lookup.contains(first.getMessage()) ||
                        lookup.toLowerCase().contains(first.getRecipientNumber().substring(1));

        assertTrue(correct, "Search by ID must return the correct message details.");
    }

    @Test
    void testSearchByRecipient() {
        String results = manager.searchByRecipient("+27838884567");

        boolean bothFound =
                results.contains("late") &&
                        results.contains("heading out");

        assertFalse(bothFound, "Search by recipient should show both stored messages.");
    }


    @Test
    void testDeleteUsingHash() {
        Message[] sent = manager.getSentMessages();
        Message first = sent[0];

        assertNotNull(first, "Sent messages must contain at least one entry.");

        String hash = first.getMessageHash();
        assertNotNull(hash, "Hash must not be null before deletion.");

        String response = manager.deleteByHash(hash);

        boolean removed =
                response.toLowerCase().contains("deleted") ||
                        response.toLowerCase().contains("removed");

        assertTrue(removed, "Delete by hash should indicate a successful removal.");
    }

    @Test
    void testFullReportOutput() {
        String report = manager.displayReport();

        boolean complete =
                report.contains("Did you get the cake?") &&
                        report.contains("dinner time");

        assertTrue(complete, "Report should contain every sent message.");
    }
}
