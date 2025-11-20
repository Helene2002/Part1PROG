
// reference : JUnit 5 User Guide – JUnit Team. Available at: https://junit.org/junit5/docs/current/user-guide/
package org.example;

public class Account {
    private String Username;
    private String Password;
    private String cellNumbers;
    private String firstName;
    private String lastName;
    // Constructor
    public Account(String Username, String Password, String cellNumbers, String firstName, String lastName) {

        this.Username = Username;
        this.Password = Password;
        this.cellNumbers = cellNumbers;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    // --- Username check ---
    public static boolean checkUserName(String Username) {
        return Username.contains("_") && Username.length() <= 5;
    }

    // --- Password check ---
    public static boolean checkPasswordComplexity(String Password) {
        boolean hasUpper = !Password.equals(Password.toLowerCase());
        boolean hasDigit = Password.matches(".*[0-9].*");
        boolean hasSpecial = Password.matches(".*[&*()^%$£,./<>{}@!¬';?#].*");
        boolean isLongEnough = Password.length() >= 8;
        return hasUpper && hasDigit && hasSpecial && isLongEnough;
    }

    // --- Cell number check ---
    public static boolean checkCellNumbers(String cellNumbers) {
        return cellNumbers.startsWith("+27") && cellNumbers.length() == 12;
    }

    // --- Register user ---
    public String registerUser(String Username, String Password, String cellNumbers) {
        StringBuilder errorMsg = new StringBuilder();

        if (!checkUserName(Username)) {
            errorMsg.append("Username is not correctly formatted. Please ensure it contains an underscore and is no more than five characters long.\n");
        }

        if (!checkPasswordComplexity(Password)) {
            errorMsg.append("Password is not correctly formatted. It must contain at least 8 characters, an uppercase letter, a number, and a special character.\n");
        }

        if (!checkCellNumbers(cellNumbers)) {
            errorMsg.append("Cell number incorrectly formatted. It must start with +27 and contain 12 digits.\n");
        }

        if (errorMsg.length() > 0) {
            return errorMsg.toString();
        }

        // store final valid credentials
        this.Username = Username;
        this.Password = Password;
        this.cellNumbers = cellNumbers;

        return "User successfully registered.";
    }

    // --- Login verification ---
    public boolean loginUser(String inputUsername, String inputPassword) {
        return this.Username.equals(inputUsername) && this.Password.equals(inputPassword);
    }

    // --- Login status message ---
    public String returnLoginStatus(String inputUsername, String inputPassword) {
        if (loginUser(inputUsername, inputPassword)) {
            return "Welcome " + firstName + " " + lastName + "! It is great to see you again.";
        } else {
            return "Username or Password incorrect. Please try again.";
        }
    }
}
