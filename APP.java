import java.util.Scanner;

public class APP {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cell phone number (+27...): ");
        String phone = input.nextLine();

        LOGIN login = new LOGIN(username, password, phone);

        String registrationResult = login.registerUser();
        System.out.println(registrationResult);

        if (registrationResult.equals("User registered successfully")) {
            System.out.print("Enter login username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter login password: ");
            String loginPassword = input.nextLine();

            String loginStatus = login.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(loginStatus);
        } else {
            System.out.println("Please try registering again with valid details.");
        }

        input.close();
    }
}
