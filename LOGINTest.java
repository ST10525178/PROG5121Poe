import org.junit.Test;
import static org.junit.Assert.*;

public class LOGINTest {

    @Test
    public void testValidUsername() {
        LOGIN login = new LOGIN("M_L", "Qw8@Rachelp99!", "+278346208976");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testInvalidUsername() {
        LOGIN login = new LOGIN("Michael", "Qw8@Rachelp99!", "+278346208976");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testValidPassword() {
        LOGIN login = new LOGIN("M_L", "Qw8@Rachelp99!", "+278346208976");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testInvalidPassword() {
        LOGIN login = new LOGIN("M_L", "password", "+278346208976");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testValidCellPhoneNumber() {
        LOGIN login = new LOGIN("M_L", "Qw8@Rachelp99!", "+278346208976");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testInvalidCellPhoneNumber() {
        LOGIN login = new LOGIN("M_L", "Qw8@Rachelp99!", "08346208976");
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testRegisterUserSuccess() {
        LOGIN login = new LOGIN("M_L", "Qw8@Rachelp99!", "+278346208976");
        assertEquals("User registered successfully", login.registerUser());
    }

    @Test
    public void testRegisterUserFailure() {
        LOGIN login = new LOGIN("Kyle", "password", "08346208976");
        assertEquals("Registration failed", login.registerUser());
    }

    @Test
    public void testLoginUserSuccess() {
        LOGIN login = new LOGIN("M_L", "Qw8@Rachelp99!", "+278346208976");
        assertTrue(login.loginUser("M_L", "Qw8@Rachelp99!"));
    }

    @Test
    public void testLoginUserFailure() {
        LOGIN login = new LOGIN("M_L", "Qw8@Rachelp99!", "+278346208976");
        assertFalse(login.loginUser("M_L", "wrongPassword"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        LOGIN login = new LOGIN("M_L", "Qw8@Rachelp99!", "+278346208976");
        assertEquals("Login successful", login.returnLoginStatus("M_L", "Qw8@Rachelp99!"));
    }

    @Test
    public void testReturnLoginStatusFailure() {
        LOGIN login = new LOGIN("M_L", "Qw8@Rachelp99!", "+278346208976");
        assertEquals("Login failed", login.returnLoginStatus("M_L", "wrongPassword"));
    }
}
