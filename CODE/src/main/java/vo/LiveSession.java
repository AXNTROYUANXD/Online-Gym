package vo;

/**
 * 
 * Title: LiveSession.java
 * Description: This class is for Live Sessions value objects and their getters and setters.
 * Copyright: Copyright (c) 2021
 * @since March 30 2021
 * @author Jiayi Zhang
 *
 */

public class LiveSession {
	
	/**
	 * @since Iteration 1
	 * userID int type for user's ID.
	 * accessAuthorization boolean type for determining the member if he or she has the authorization for entering the Live Session system. Default value is {@code false}.}
	 * bookedSession String type, the serial numbers of user's booked session, every session lasts 2 hours. (coachID + start time, e.g.100120210101190000)
	 * bookedSession String type, the live session user want to cancel.
	 * searchedCoach String type, the user's searched coach's name/ID.
	 * searchedStartTime String type, user filter for the specific date and time.
	 */
	private int userID;
	private boolean accessAuthorization = false;
	private String bookedSession;
	private String cancelSession;
	private String searchedCoach;
	private String searchedStartTime;
	/**
	 * @since Iteration 2
	 * coachID int type for coach's ID.
	 */
	private int coachID;
	
	public int getUserID() {
		/**
		 * @since Iteration 1
		 * Getter for userID.
		 * @return userID for the current user.
		 */
		return userID;
	}

	public boolean isAccessAuthorization() {
		/**
		 * @since Iteration 1
		 * Getter for accessAuthorization.
		 * @return accessAuthorization for the current user.
		 */
		return accessAuthorization;
	}
	
	public String getBookedSession() {
		/**
		 * @since Iteration 1
		 * Getter for bookedSession.
		 * @return bookedSession for the current user's selection.
		 */
		return bookedSession;
	}
	
	public String getSearchedCoach() {
		/**
		 * @since Iteration 1
		 * Getter for searchedCoach.
		 * @return searchedCoach for the current user's searching.
		 */
		return searchedCoach;
	}
	public String getSearchedStartTime() {
		/**
		 * @since Iteration 1
		 * Getter for searchedStartTime.
		 * @return searchedCoach for the current user's selection.
		 */
		return searchedStartTime;
	}
	
	public int getCoachID() {
		/**
		 * @since Iteration 2
		 * Getter for coachID.
		 * @return coachID for the current coach's ID.
		 */
		return coachID;
	}
	
	public void setUserID(int userID) {
		/**
		 * @since Iteration 1
		 * Setter for userID.
		 */
		this.userID = userID;
	}

	public void setAccessAuthorization(boolean accessAuthorization) {
		/**
		 * @since Iteration 1
		 * Setter for accessAuthorization.
		 */
		this.accessAuthorization = accessAuthorization;
	}

	public void setBookedSession(String bookedSession) {
		/**
		 * @since Iteration 1
		 * Setter for bookedSession.
		 */
		this.bookedSession = bookedSession;
	}

	public void setSearchedCoach(String searchedCoach) {
		/**
		 * @since Iteration 1
		 * Setter for searchedCoach.
		 */
		this.searchedCoach = searchedCoach;
	}

	public void setSearchedStartTime(String searchedStartTime) {
		/**
		 * @since Iteration 1
		 * Setter for searchedStartTime.
		 */
		this.searchedStartTime = searchedStartTime;
	}

	public void setCoachID(int coachID) {
		/**
		 * @since Iteration 2
		 * Setter for coachID.
		 */
		this.coachID = coachID;
	}

	public String getCancelSession() {
		/**
		 * @since Iteration 1
		 * Getter for cancelSession.
		 * @return cancelSession for the current user's selection.
		 */
		return cancelSession;
	}

	public void setCancelSession(String cancelSession) {
		/**
		 * @since Iteration 1
		 * Setter for cancelSession.
		 */
		this.cancelSession = cancelSession;
	}
	

}
