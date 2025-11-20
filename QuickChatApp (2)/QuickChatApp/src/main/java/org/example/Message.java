package org.example;

/**
 * Message.java
 * Represents a chat message with unique ID and hash.
 *
 * Author: Helene (2025)
 */
// with the help from: OpenAI. (2025). ChatGPT (GPT-5). Retrieved from https://openai.com/
// and Gson Documentation – Google. Available at: https://github.com/google/gson


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {

    private long messageID;
    private int messageCount;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Keeps all sent messages in memory
    private static List<Message> sentMessages = new ArrayList<>();

    // Constructor
    public Message(long messageID, int messageCount, String recipient, String messageText) {
        this.messageID = messageID;
        this.messageCount = messageCount;
        this.recipient = recipient;
        this.messageText = messageText;
    }

    public Message(String s, String s1, int i) {
    }

    //  1. checkMessageID – ensures messageID ≤ 10 digits
    public boolean checkMessageID() {
        return String.valueOf(messageID).length() <= 10;
    }

    //  2. checkRecipientCell – ensures +27 prefix and ≤ 10 digits after
    public int checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() <= 13) {  // +27 + 10 digits = 13 chars
            return 1;
        }
        return 0;
    }

    // --- Validate Message Length ---
    public String validateMessageLength() {
        if (messageText.length() > 250) {
            return "Message too long. Please limit your message to 250 characters.";
        }
        return "Message ready to send.";
    }

    //  3. createMessageHash – first 2 digits of ID + ":" + messageCount + ":" + first & last word
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words.length > 1 ? words[words.length - 1].toUpperCase() : firstWord;
        String idPrefix = String.valueOf(messageID).substring(0, 2);
        messageHash = idPrefix + ":" + messageCount + ":" + firstWord + lastWord;
        return messageHash;
    }

    //  4. sendMessage – user chooses send, disregard, or store
    public String sendMessage(int choice) {
        switch (choice) {
            case 1:
                sentMessages.add(this);
                return "Message sent successfully.";
            case 2:
                return "Message disregarded.";
            case 3:
                return storeMessage() == 1 ? "Message stored for later." : "Error storing message.";
            default:
                return "Invalid option.";
        }
    }

    //  5. printMessages – returns list of all sent messages
    public static String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
        StringBuilder sb = new StringBuilder("Sent Messages:\n");
        for (Message msg : sentMessages) {
            sb.append("ID: ").append(msg.messageID)
                    .append(" | To: ").append(msg.recipient)
                    .append(" | Msg: ").append(msg.messageText)
                    .append(" | Hash: ").append(msg.messageHash)
                    .append("\n");
        }
        return sb.toString();
    }

    //  6. returnTotalMessages – total number of sent messages
    public static int returnTotalMessages() {
        return sentMessages.size();
    }

    //  7. storeMessage – saves to JSON file
    public int storeMessage() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("stored_messages.json", true)) {
            gson.toJson(this, writer);
            writer.write(System.lineSeparator());
            return 1;
        } catch (IOException e) {
            System.out.println("Error writing to JSON: " + e.getMessage());
            return 0;
        }
    }

    //  Static helper – random 10-digit ID generator
    public static long generateRandomID() {
        Random random = new Random();
        long id = (long) (1000000000L + random.nextDouble() * 8999999999L);
        return id;
    }
}
