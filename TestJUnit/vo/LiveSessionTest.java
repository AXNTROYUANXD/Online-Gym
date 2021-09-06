package vo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LiveSessionTest {

    private static LiveSession liveSession = new LiveSession();

    @Before
    public void setUp() throws Exception {
        System.out.println("<<<<<<<<<<<< Test Started >>>>>>>>>>>>");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("<<<<<<<<<<<< Test Ended >>>>>>>>>>>>");
    }

    @Test
    public void getUserID() {
        liveSession.setUserID(123);
        assertEquals(123,liveSession.getUserID());
    }

    @Test
    public void isAccessAuthorization() {
        liveSession.setAccessAuthorization(true);
        assertTrue(liveSession.isAccessAuthorization());
        liveSession.setAccessAuthorization(false);
        assertFalse(liveSession.isAccessAuthorization());
    }

    @Test
    public void getBookedSession() {
        liveSession.setBookedSession("test");
        assertEquals("test",liveSession.getBookedSession());
    }

    @Test
    public void getSearchedCoach() {
        liveSession.setSearchedCoach("Smith");
        assertEquals("Smith",liveSession.getSearchedCoach());
    }

    @Test
    public void getSearchedStartTime() {
        liveSession.setSearchedStartTime("2021-05-26");
        assertEquals("2021-05-26",liveSession.getSearchedStartTime());
    }

    @Test
    public void getCoachID() {
        liveSession.setCoachID(1001);
        assertEquals(1001,liveSession.getCoachID());
    }

    @Test
    public void getCancelSession() {
        liveSession.setCancelSession("test");
        assertEquals("test",liveSession.getCancelSession());
    }
}