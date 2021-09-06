package DAO;

import java.util.ArrayList;

import vo.LiveSession;

/**
 * 
 * Title: LiveSessionDAO.java
 * Description: This interface is for Live Sessions data access objects.
 * Copyright: Copyright (c) 2021
 * @since March 30 2021
 * @author Jiayi Zhang
 *
 */

public interface LiveSessionDAO {
	
	/**
	 * @since Iteration 1
	 * @return ArrayList All the information of each Live Session.
	 * @throws Exception Any exception.
	 */
	public ArrayList<String> displayAllSessions() throws Exception;
	
	/**
	 * @since Iteration 1
	 * @param liveSession LiveSession Object.
	 * @return boolean The booking status.
	 * @throws Exception Any exception.
	 */
	public boolean bookSession(LiveSession liveSession) throws Exception;
	
	/**
	 * @since Iteration 2
	 * @param liveSession LiveSession Object.
	 * @return boolean The cancellation status.
	 * @throws Exception Any exception.
	 */
	public boolean cancelSession(LiveSession liveSession) throws Exception;
	
	/**
	 * @since Iteration 2
	 * @param liveSession LiveSession Object.
	 * @return ArrayList All corresponding information of searching result searched by coach.
	 * @throws Exception Any exception.
	 */
	public ArrayList<String> displaySearchedSessions(LiveSession liveSession) throws Exception;
	
	/**
	 * @since Iteration 2
	 * @param liveSession LiveSession Object.
	 * @return ArrayList All corresponding information of searching result searched by start time.
	 * @throws Exception Any exception.
	 */
	public ArrayList<String> displaySearchedStartTime(LiveSession liveSession) throws Exception;
	
	/**
	 * @since Iteration 2
	 * @param liveSession LiveSession Object.
	 * @return ArrayList All corresponding information of user's booked sessions history.
	 * @throws Exception Any exception.
	 */
	public ArrayList<String> displayHistory(LiveSession liveSession) throws Exception;

	/**
	 * @since Iteration 3
	 * @param liveSession LiveSession Object.
	 * @return ArrayList All corresponding information of searching result searched by start time.
	 * @throws Exception Any exception.
	 */
	public ArrayList<String> displayHistorySearchedStartTime(LiveSession liveSession) throws Exception;

	/**
	 * @since Iteration 3
	 * @param liveSession LiveSession Object.
	 * @return ArrayList All corresponding information of searching result searched by start time.
	 * @throws Exception Any exception.
	 */
	public ArrayList<String> displayHistorySearchedSessions(LiveSession liveSession) throws Exception;

	/**
	 * @since Iteration 4
	 * @param liveSession LiveSession Object.
	 * @return boolean The adding operation status.
	 * @throws Exception Any exception.
	 */
	public boolean addToCoachRecord(LiveSession liveSession) throws Exception;

	/**
	 * @since Iteration 4
	 * @param liveSession LiveSession Object.
	 * @return boolean The cancellation operation status.
	 * @throws Exception Any exception.
	 */
	public boolean cancelFromCoachRecord(LiveSession liveSession) throws Exception;


	/**
	 * @since Iteration 4
	 * @param liveSession LiveSession Object.
	 * @return ArrayList All corresponding information of user's booked sessions history.
	 * @throws Exception Any exception.
	 */
	public ArrayList<String> displayHistoryCoach(LiveSession liveSession) throws Exception;

	/**
	 * @since Iteration 4
	 * @param liveSession LiveSession Object.
	 * @return ArrayList All corresponding information of searching result searched by start time.
	 * @throws Exception Any exception.
	 */
	public ArrayList<String> displayHistorySearchedStartTimeCoach(LiveSession liveSession) throws Exception;

	/**
	 * @since Iteration 3
	 * @param liveSession LiveSession Object.
	 * @return ArrayList All corresponding information of searching result searched by start time.
	 * @throws Exception Any exception.
	 */
	public ArrayList<String> displayHistorySearchedSessionsCoach(LiveSession liveSession) throws Exception;
}
