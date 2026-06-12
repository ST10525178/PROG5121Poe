import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickChatAppTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSentMessagesArray() {
        QuickChatApp.sentMessages.add("Did you get the cake?");
        QuickChatApp.sentMessages.add("It is dinner time!");
        assertEquals(2, QuickChatApp.sentMessages.size());
    }

    @Test
    public void testLongestMessage() {
        QuickChatApp.storedMessages.add("Where are you? You are late! I have asked you to be on time.");
        QuickChatApp.storedMessages.add("Ok, I am leaving without you.");
        String longest = QuickChatApp.storedMessages.stream()
                .max(java.util.Comparator.comparingInt(String::length))
                .orElse("");
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testDeleteByHash() {
        String msg = "Where are you? You are late!";
        String hash = QuickChatApp.hashMessage(msg);
        QuickChatApp.storedMessages.add(msg);
        QuickChatApp.messageHashes.add(hash);
        QuickChatApp.deleteByHash(new java.util.Scanner(hash));
        assertFalse(QuickChatApp.storedMessages.contains(msg));
    }

    @org.junit.Test
    public void testMain() {
    }
}


