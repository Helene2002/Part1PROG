package org.example;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        // --- Registration ---
        String firstName = JOptionPane.showInputDialog("Please enter your First Name:");
        String lastName = JOptionPane.showInputDialog("Please enter your Last Name:");
        String username = JOptionPane.showInputDialog("Please enter username (must contain '_' and be <= 5 characters):");
        String password = JOptionPane.showInputDialog("Please enter password (must contain special character, uppercase letter, number, and be >= 8 characters):");
        String cellNumbers = JOptionPane.showInputDialog("Please enter your cell number (must start with +27 and be 12 characters):");

        // ✅ Correctly construct Account object
        Account account = new Account();

        // ✅ Perform registration
        String registrationStatus = account.registerUser(username, password, cellNumbers);
        JOptionPane.showMessageDialog(null, registrationStatus);

        // Stop program if registration fails
        if (!registrationStatus.equals("User successfully registered.")) {
            JOptionPane.showMessageDialog(null, "Registration unsuccessful. Exiting program.");
            System.exit(0);
        }

        // --- Login ---
        JOptionPane.showMessageDialog(null, "********** LOGIN USER **********");
        String loginUsername = JOptionPane.showInputDialog("Enter Username you registered with:");
        String loginPassword = JOptionPane.showInputDialog("Enter Password you registered with:");

        if (!account.loginUser(loginUsername, loginPassword)) {
            JOptionPane.showMessageDialog(null, "Username or Password incorrect. Please try again.");
            System.exit(0);
        }

        JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName + "!\nWelcome to QuickChat.");

// --- Menu Loop ---
        boolean running = true;
        int messageCount = 0;

        while (running) {
            String menuInput = JOptionPane.showInputDialog("""
                    Choose an option:
                    1) Send Messages
                    2) Show Recently Sent Messages
                    3) Quit""");

            if (menuInput == null) break; // user pressed cancel

            int menuChoice;
            try {
                menuChoice = Integer.parseInt(menuInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                continue;
            }

            switch (menuChoice) {
                case 1 -> {
                    String totalInput = JOptionPane.showInputDialog("How many messages would you like to send?");
                    int totalMessages;
                    try {
                        totalMessages = Integer.parseInt(totalInput);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid number entered.");
                        continue;
                    }

                    for (int i = 0; i < totalMessages; i++) {
                        messageCount++;

                        String recipient = JOptionPane.showInputDialog("Enter recipient number (+27xxxxxxxxxx):");
                        String messageText = JOptionPane.showInputDialog("Enter message text (max 250 characters):");

                        if (messageText.length() > 250) {
                            JOptionPane.showMessageDialog(null, "Message too long! Please enter less than 250 characters.");
                            continue;
                        }

                        long id = Message.generateRandomID(); // ensure you have this static method in Message.java
                        Message msg = new Message(id, messageCount, recipient, messageText);

                        if (msg.checkRecipientCell() == 0 || !msg.checkMessageID()) {
                            JOptionPane.showMessageDialog(null, "Invalid recipient or message ID.");
                            continue;
                        }

                        String hash = msg.createMessageHash();

                        JOptionPane.showMessageDialog(null, """
                                Message Details:
                                Message ID: %d
                                Hash: %s
                                Recipient: %s
                                Message: %s
                                """.formatted(id, hash, recipient, messageText));

                        String choiceInput = JOptionPane.showInputDialog("""
                                Choose an option:
                                1) Send Message
                                2) Disregard Message
                                3) Store Message for Later""");

                        int choice;
                        try {
                            choice = Integer.parseInt(choiceInput);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid option, please try again.");
                            continue;
                        }

                        JOptionPane.showMessageDialog(null, msg.sendMessage(choice));
                    }
                }

                case 2 -> JOptionPane.showMessageDialog(null, " Coming soon...!");

                case 3 -> {
                    running = false;
                    JOptionPane.showMessageDialog(null, "Goodbye " + firstName + "!");
                }

                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }
}