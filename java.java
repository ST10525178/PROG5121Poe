import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

private class QuickChatApp {
    static ArrayList<String> sentMessages = new ArrayList<>();
    static ArrayList<String> disregardedMessages = new ArrayList<>();
    static ArrayList<String> storedMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== QuickChat Main Menu ===");
            System.out.println("1. Send Message");
            System.out.println("2. Disregard Message");
            System.out.println("3. Store Message");
            System.out.println("4. Stored Messages Menu");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> sendMessage(input);
                case 2 -> disregardMessage(input);
                case 3 -> storeMessage(input);
                case 4 -> storedMessagesMenu(input);
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    static void sendMessage(Scanner input) {
        System.out.print("Enter message: ");
        String msg = input.nextLine();
        sentMessages.add(msg);
        messageIDs.add(UUID.randomUUID().toString());
        messageHashes.add(hashMessage(msg));
        System.out.println("Message sent successfully!");
    }

    static void disregardMessage(Scanner input) {
        System.out.print("Enter message to disregard: ");
        String msg = input.nextLine();
        disregardedMessages.add(msg);
        System.out.println("Message disregarded.");
    }

    static void storeMessage(Scanner input) {
        System.out.print("Enter message to store: ");
        String msg = input.nextLine();
        storedMessages.add(msg);
        messageIDs.add(UUID.randomUUID().toString());
        messageHashes.add(hashMessage(msg));
        System.out.println("Message stored successfully!");
    }

    static void storedMessagesMenu(Scanner input) {
        int option;
        do {
            System.out.println("\n--- Stored Messages Menu ---");
            System.out.println("1. Display all stored messages");
            System.out.println("2. Display longest stored message");
            System.out.println("3. Search by Message ID");
            System.out.println("4. Search by Recipient");
            System.out.println("5. Delete message by hash");
            System.out.println("6. Display report");
            System.out.println("7. Back to main menu");
            System.out.print("Enter option: ");
            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1 -> displayStoredMessages();
                case 2 -> displayLongestMessage();
                case 3 -> searchByMessageID(input);
                case 4 -> searchByRecipient(input);
                case 5 -> deleteByHash(input);
                case 6 -> displayReport();
                case 7 -> System.out.println("Returning...");
                default -> System.out.println("Invalid option!");
            }
        } while (option != 7);
    }

    static void displayStoredMessages() {
        System.out.println("\nStored Messages:");
        for (String msg : storedMessages) System.out.println(msg);
    }

    static void displayLongestMessage() {
        String longest = storedMessages.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("No messages stored.");
        System.out.println("Longest message: " + longest);
    }

    static void searchByMessageID(Scanner input) {
        System.out.print("Enter Message ID: ");
        String id = input.nextLine();
        int index = messageIDs.indexOf(id);
        if (index != -1)
            System.out.println("Message found: " + storedMessages.get(index));
        else
            System.out.println("Message ID not found.");
    }

    static void searchByRecipient(Scanner input) {
        System.out.print("Enter recipient number: ");
        String recipient = input.nextLine();
        System.out.println("Messages for " + recipient + ":");
        for (String msg : storedMessages)
            if (msg.contains(recipient)) System.out.println(msg);
    }

    static void deleteByHash(Scanner input) {
        System.out.print("Enter message hash to delete: ");
        String hash = input.nextLine();
        int index = messageHashes.indexOf(hash);
        if (index != -1) {
            System.out.println("Message deleted: " + storedMessages.remove(index));
            messageHashes.remove(index);
            messageIDs.remove(index);
        } else {
            System.out.println("Hash not found.");
        }
    }

    static void displayReport() {
        System.out.println("\n--- Stored Messages Report ---");
        for (int i = 0; i < storedMessages.size(); i++) {
            System.out.println("Message ID: " + messageIDs.get(i));
            System.out.println("Hash: " + messageHashes.get(i));
            System.out.println("Message: " + storedMessages.get(i));
            System.out.println("-----------------------------");
        }
    }

    static String hashMessage(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(message.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) hexString.append(String.format("%02x", b));
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Error generating hash";
        }
    }
}


    

