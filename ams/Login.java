public class Login {
   // Reference:
    //*OpenAI, 2025. ChatGPT (September 14 version) [online].Available at:https://chat.openai.com/[Accessed 12 Sep.2025].
    //declare string variables
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String cellPhoneNumber;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // Username validation
    public static boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            System.out.println("Username successfully captured.");
            return true;
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
            return false;
        }
    }
     // Check password complexity
     // Regex logic generated with the assistance of ChatGPT (OpenAI,2025)
     // Password validation
    public static boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";
        if (password != null && password.matches(regex)) {
            System.out.println("Password successfully captured.");
            return true;
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
        }
    }
     // Check phone number format: +27
     // Regex logic generated with the assistance of ChatGPT(OpenAI,2025)
     // Cellphone validation
    public static boolean checkCellPhoneNumber(String cellphone) {
        String regex = "^\\+27\\d{9}$";
        if (cellphone != null && cellphone.matches(regex)) {
            System.out.println("Cell phone number successfully captured.");
            return true;
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            return false;
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static String registerUser(String username, String cellphone, String password) {
        StringBuilder errors = new StringBuilder();

        if (!checkUserName(username)) {
            errors.append("The username does not contain an _ and or should be five characters or less long.\n");
        }

        if (!checkPasswordComplexity(password)) {
            errors.append("The password does not meet the complexity requirements. Password should be 8 characters long, have a capital letter, a number and a special character");
        }

        if (!checkCellPhoneNumber(cellphone)) {
            errors.append("The cell number is incorrectly formatted.The cell number should be 10 numbers long and should start with +27\n");
        }

        if (errors.length() > 0) {
            return errors.toString().trim();
        } else {
            return "Account registerd successfully.";
        }
    }

    // ✅ Fixed login method
    public boolean LoginUser(String usernameAuthentication, String PasswordAuthentication) {
        return this.getUsername().equals(usernameAuthentication) && this.getPassword().equals(PasswordAuthentication);
    }

    public String returnLoginStatus(String username, String password) {
        if (username.equals(getUsername()) && password.equals(getPassword())) {
            return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
        } else
            return "Username or password incorrect, please try again.";
    }

    // Getters
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }
}
