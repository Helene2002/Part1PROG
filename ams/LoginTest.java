import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // -------------------------------
    // Username Validation Tests
    // -------------------------------

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(Login.checkUserName("kyl_1"), "Username should be correctly formatted.");
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(Login.checkUserName("kyle!!!!!!!"), "Username should be incorrectly formatted.");
    }

    // -------------------------------
    // Password Validation Tests
    // -------------------------------

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        assertTrue(Login.checkPasswordComplexity("Ch&&sec@ke99!"), "Password should meet complexity requirements.");
    }

    @Test
    public void testPasswordFailsComplexityRequirements() {
        assertFalse(Login.checkPasswordComplexity("password"), "Password should fail complexity requirements.");
    }

    // -------------------------------
    // Cellphone Validation Tests
    // -------------------------------

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(Login.checkCellPhoneNumber("+27838968976"), "Phone number should be correctly formatted.");
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertFalse(Login.checkCellPhoneNumber("08966553"), "Phone number should be incorrectly formatted.");
    }

    // -------------------------------
    // Login Tests
    // -------------------------------

    @Test
    public void testLoginSuccessful() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!");
        login.setFirstName("John");
        login.setLastName("Doe");

        boolean loginSuccess = login.LoginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(loginSuccess, "Login should be successful.");

        String expectedMessage = "Welcome John, Doe it is great to see you again.";
        String actualMessage = login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testLoginFailsWithWrongCredentials() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!");
        login.setFirstName("John");
        login.setLastName("Doe");

        String result = login.returnLoginStatus("wrongUser", "wrongPass");
        assertEquals("Username or password incorrect, please try again.", result);
    }

    // -------------------------------
    // Registration Error Tests
    // -------------------------------

    @Test
    public void testRegisterUserReturnsAllErrors() {
        String result = Login.registerUser("kyle!!!!!!!", "08966553", "password");

        assertTrue(result.contains("username") &&
                   result.contains("Password") &&
                   result.contains("cell number"),
                   "Should return multiple error messages");
    }
}
