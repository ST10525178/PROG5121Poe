/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chat;

/**
 *
 * @author mmaka
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Chat {

    // Message class definition
    static class Message {
        private static int totalMessages = 0;
        private static List<Message> sentMessages = new ArrayList<>();

        public String messageID;   // public for testing
        private String recipient;
        private String message;
        private String messageHash;

        public Message(String recipient, String message) {
            this.messageID = generateMessageID(); // auto-generate ID
            this.recipient = recipient;
            this.message = message;
        }

        // Auto-generate a random 10-digit Message ID
        private String generateMessageID() {
            Random rand = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(rand.nextInt(10));
            }
            return sb.toString();
        }

        public boolean checkMessageID() {
            return messageID.length() <= 10;
        }

        public boolean checkRecipientCell() {
            return recipient.length() <= 10 && recipient.matches("\\d+");
        }

        public String createMessageHash(int msgNum) {
            String[] words = message.split(" ");
            String firstWord = words[0];
            String lastWord = words[words.length - 1];
            messageHash = String.format("%02d:%s:%s", msgNum, firstWord.toUpperCase(), lastWord.toUpperCase());
            return messageHash;
        }

        public String sendMessage(String option) {
            switch(option) {
                case "Send":
                    sentMessages.add(this);
                    totalMessages++;
                    return "Message successfully sent";
                case "Store":
                    storeMessage(); // call JSON storage
                    return "Message successfully stored";
                case "Discard":
                    return "Press 0 to delete the message";
                default:
                    return "Invalid option";
            }
        }

        // ✅ Store message in JSON file (manual formatting, no external libs)
        public void storeMessage() {
            String json = String.format(
                "{ \"MessageID\": \"%s\", \"Recipient\": \"%s\", \"Message\": \"%s\", \"MessageHash\": \"%s\" }",
                messageID, recipient, message.replace("\"", "\\\""), messageHash
            );
            try (FileWriter writer = new FileWriter("messages.json", true)) {
                writer.write(json + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Error storing message: " + e.getMessage());
            }
        }

        public static void printMessages() {
            for (Message m : sentMessages) {
                System.out.println("ID: " + m.messageID + " | Hash: " + m.messageHash +
                                   " | Recipient: " + m.recipient + " | Message: " + m.message);
            }
        }

        public static int returnTotalMessages() {
            return totalMessages;
        }
    }

    // Main application
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to QuickChat");
        System.out.print("How many messages would you like to send? ");
        int numMessages = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 1; i <= numMessages; i++) {
            System.out.print("Enter Recipient Number: ");
            String recipient = sc.nextLine();

            System.out.print("Enter Message: ");
            String msg = sc.nextLine();

            if (msg.length() > 250) {
                System.out.println("Message exceeds 250 characters. Please reduce size.");
                i--; // retry same message
                continue;
            }

            Message m = new Message(recipient, msg);

            if (!m.checkMessageID()) {
                System.out.println("Invalid Message ID.");
                i--;
                continue;
            }

            if (!m.checkRecipientCell()) {
                System.out.println("Invalid Recipient Number.");
                i--;
                continue;
            }

            m.createMessageHash(i);

            System.out.println("Choose option: Send / Store / Discard");
            String option = sc.nextLine();
            System.out.println(m.sendMessage(option));
        }

        System.out.println("Total messages sent: " + Message.returnTotalMessages());
        Message.printMessages();

        sc.close();
    }
}
