package DAO.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vo.LiveSession;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LiveSessionDAOImplTest {

    private static LiveSession liveSession = new LiveSession();
    private static LiveSessionDAOImpl dao = new LiveSessionDAOImpl();

    @Before
    public void setUp() throws Exception {
        System.out.println("<<<<<<<<<<<< Test Started >>>>>>>>>>>>");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("<<<<<<<<<<<< Test Ended >>>>>>>>>>>>");
    }

    @Test
    public void displayAllSessions() {
        try {
            dao.displayAllSessions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bookSession() {
        liveSession.setBookedSession("test");
        liveSession.setUserID(123);
        try {
            assertTrue(dao.bookSession(liveSession));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cancelSession() {
        liveSession.setCancelSession("test");
        liveSession.setUserID(123);
        try {
            assertTrue(dao.cancelSession(liveSession));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void displaySearchedSessions() {
        ArrayList<String> str = new ArrayList<>();
        liveSession.setSearchedCoach("1001");
        try {
            str.addAll(dao.displaySearchedSessions(liveSession));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<str.size();i++){
            assertTrue(str.get(i).contains("1001"));
        }
    }

    @Test
    public void displaySearchedStartTime() {
        ArrayList<String> str = new ArrayList<>();
        liveSession.setSearchedStartTime("2021-04-01");
        try {
            str.addAll(dao.displaySearchedStartTime(liveSession));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<str.size();i++){
            assertTrue(str.get(i).contains("2021-04-01"));
        }
    }

    @Test
    public void displayHistory() {
        liveSession.setUserID(123);
        try {
            dao.displayHistory(liveSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void displayHistorySearchedStartTime() {
        ArrayList<String> str = new ArrayList<>();
        liveSession.setSearchedStartTime("2021-04-01");
        try {
            str.addAll(dao.displayHistorySearchedStartTime(liveSession));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<str.size();i++){
            assertTrue(str.get(i).contains("2021-04-01"));
        }
    }

    @Test
    public void displayHistorySearchedSessions() {
        ArrayList<String> str = new ArrayList<>();
        liveSession.setSearchedCoach("1003");
        try {
            str.addAll(dao.displayHistorySearchedSessions(liveSession));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<str.size();i++){
            assertTrue(str.get(i).contains("1003"));
        }
    }

    @Test
    public void addToCoachRecord() {
        liveSession.setBookedSession("1001*....test*");
        try {
            assertTrue(dao.addToCoachRecord(liveSession));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cancelFromCoachRecord() {
        liveSession.setCancelSession("1001*....test*");
        try {
            assertTrue(dao.cancelFromCoachRecord(liveSession));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void displayHistoryCoach() {
        ArrayList<String> str = new ArrayList<>();
        liveSession.setCoachID(1001);
        try {
            str.addAll(dao.displayHistoryCoach(liveSession));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<str.size();i++){
            assertTrue(str.get(i).contains("1001"));
        }
    }

    @Test
    public void displayHistorySearchedStartTimeCoach() {
        ArrayList<String> str = new ArrayList<>();
        liveSession.setCoachID(1001);
        liveSession.setSearchedStartTime("2021-04-07");
        try {
            str.addAll(dao.displayHistorySearchedStartTimeCoach(liveSession));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<str.size();i++){
            assertTrue(str.get(i).contains("2021-04-07"));
        }
    }

    @Test
    public void displayHistorySearchedSessionsCoach() {
        ArrayList<String> str = new ArrayList<>();
        liveSession.setSearchedCoach("2hrs");
        liveSession.setCoachID(1001);
        try {
            str.addAll(dao.displayHistorySearchedSessionsCoach(liveSession));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<str.size();i++){
            assertTrue(str.get(i).contains("2hrs"));
        }
    }
}