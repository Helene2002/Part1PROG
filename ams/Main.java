import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Declarations
        String name;
        String password1;
        String firstName;
        String lastName;
        String cellPhoneNumber;
        String usernameAuthentication;
        String passwordAuthentication;
        String registrationStatus;

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Registration ---");

        // First Name
        System.out.print("Enter first name: ");
        firstName = scanner.nextLine();

        // Last Name
        System.out.print("Enter last name: ");
        lastName = scanner.nextLine();

        // Username
        System.out.print("Enter username: ");
        name = scanner.nextLine();

        // Password
        System.out.print("Enter password: ");
        password1 = scanner.nextLine();

        // Cell phone number
        System.out.print("Enter cell phone number: ");
        cellPhoneNumber = scanner.nextLine();

        // Register the user
        registrationStatus = Login.registerUser(name, cellPhoneNumber, password1);
        System.out.println(registrationStatus);
        if (!registrationStatus.equals("Account registerd successfully.")) {
            return;
        }

        // Create Login object
        Login user2 = new Login(name, password1);
        user2.setFirstName(firstName);
        user2.setLastName(lastName);
        user2.setCellPhoneNumber(cellPhoneNumber);

        // Login
        System.out.println("\n----- Login -----");
        System.out.print("Enter your username: ");
        usernameAuthentication = scanner.nextLine();

        System.out.print("Enter password: ");
        passwordAuthentication = scanner.nextLine();

        if (user2.LoginUser(usernameAuthentication, passwordAuthentication)) {
            String status = user2.returnLoginStatus(usernameAuthentication, passwordAuthentication);
            System.out.println(status);
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }

        scanner.close();
    }
}
