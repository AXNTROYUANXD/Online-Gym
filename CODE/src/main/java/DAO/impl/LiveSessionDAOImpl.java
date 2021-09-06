package DAO.impl;

import java.io.*;
import java.util.ArrayList;

import DAO.LiveSessionDAO;
import vo.LiveSession;

/**
 * 
 * Title: LiveSessionDAOImpl.java
 * Description: This class is the implementation for the interface of Live Sessions data access objects.
 * Copyright: Copyright (c) 2021
 * @since March 30 2021
 * @author Jiayi Zhang
 *
 */


/*======================================================================================================*
 * 																										*
 *						   					Syntax for Files	    									*
 * 																										*
 *									  e.g. AvailableLiveSession.txt										*
 * 																										*
 * 			1001  *   Simon Smith	*	2021-04-01 08:00:00 	*     2hrs     *  Boxing - Basic		*
 *			 ↓		       ↓			 		↓						↓				↓				*
 * 		  coachID     coach's Name    one of the available slots	 duration		course name			*
 * 																										*
 *======================================================================================================*/

public class LiveSessionDAOImpl implements LiveSessionDAO {

	@Override
	public ArrayList<String> displayAllSessions() throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * This method is intent to extract all the information of the available live sessions.
		 * @return allAvailableSessions ArrayList<String>, containing the information in the form of "coachID*coach's name*start time of the session".
		 * @param fileName String type, the file of LiveSessionAvailable.txt directory.
		 */
		
		// All of the available sessions of all coaches.
		ArrayList<String> allAvailableSessions = new ArrayList<String>();
		
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/AvailableLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		
		try {
			/** 
			 * @param oneLine String type, the temporary storage for each line of the content.
			 */
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				// Read each line of the file then add them to the ArrayList.
				allAvailableSessions.add(oneLine);
				oneLine = bufferedReader.readLine();
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println("Errors occurred when try to read the file AvailableLiveSession.txt");
			System.out.println("Shutting Down...");
			e.printStackTrace();
			System.exit(1);
		}
		
		// FOR TEST ONLY.
		//System.out.println(allAvailableSessions);
		
		return allAvailableSessions;
	}

	@Override
	public boolean bookSession(LiveSession liveSession) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * This method is intent to book the session user selected, delete the corresponding record and save into user's live session file.
		 * @return flag boolean, to indicate if the operation is successful.
		 * @param fileName String type, the file of LiveSessionAvailable.txt directory.
		 * @param tempFileName String type, the file of temp.txt directory.
		 * @param userFileName String type, the file of user's live session information directory.
		 * @param flag1 boolean type, to determine if the operation about deleting record in LiveSessionAvailable.txt is successful.
		 * @param flag2 boolean type, to determine if the operation about adding record in user's live session information file is successful.
		 * @param flag boolean type, to determine if the operation about the whole method is successful.
		 * @param bookedSession String type, user's selection.
		 * @param userID integer, userID.
		 */
		
		// The determinant of the booking status.
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag = false;
		// Get the user's selection.
		String bookedSession = liveSession.getBookedSession();
		// Get the user's id.
		int userID = liveSession.getUserID();
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/AvailableLiveSession.txt";
		String tempFileName = "src/main/resources/LiveSession/temp.txt";
		String userFileName = "src/main/resources/LiveSession/" + userID +"BookedLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		try { // Delete record in the AvailableLiveSession.txt.
			/**
			 * @param fileCreateFlag Indication to tell if the file is successfully created.
			 */
			boolean fileCreateFlag = false;
			File file = new File(tempFileName);
			File oldFile = new File(fileName);
			try {
				if(file.exists()) {
					System.out.println("Deleted current existing temp.txt.");
					file.delete();
				}
				// Creating a temporary file and check status.
				fileCreateFlag = file.createNewFile();
			} catch (IOException e) {
				System.out.println("Errors occurred when try to create the file temp.txt");
				System.out.println("Shutting Down...");
				e.printStackTrace();
				System.exit(1);
			}
			System.out.println("Status of creating temp.txt file: " + fileCreateFlag);
			
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			FileWriter fileWriter = new FileWriter(tempFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				// FOR TEST ONLY.
				//System.out.println(oneLine);
				// FOR TEST ONLY.
				//System.out.println("Match or not: >>>>>>>>>>>>>>>>>>>>" + oneLine.equals(bookedSession));
				if(oneLine.equals(bookedSession)) {
					oneLine = bufferedReader.readLine();
					continue;
				}
				else {
					bufferedWriter.write(oneLine);
					bufferedWriter.newLine();
					oneLine = bufferedReader.readLine(); 
					continue;
				}
			}
			
			if(oldFile.exists()&&oldFile.isFile()) {
				file.renameTo(oldFile);
				flag1 = true;
			} else {
				flag1 = false;
			}
			
			bufferedWriter.flush();
			fileWriter.flush();
			
			bufferedReader.close();
			fileReader.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Errors occurred while trying to read or write.");
			e.printStackTrace();
		}
		
		
		try { // Add to personal account information of live sessions.
			/**
			 * @param fileCreateFlag Indication to tell if the file is successfully created.
			 */
			boolean fileCreateFlag = false;
			File file = new File(tempFileName);
			File oldFile = new File(userFileName);
			try {
				if(file.exists()) {
					System.out.println("Deleted current existing temp.txt.");
					file.delete();
				}
				// Creating a temporary file and check status.
				fileCreateFlag = file.createNewFile();
			} catch (IOException e) {
				System.out.println("Errors occurred when try to create the file temp.txt");
				System.out.println("Shutting Down...");
				e.printStackTrace();
				System.exit(1);
			}
			System.out.println("Status of creating temp.txt file: " + fileCreateFlag);
			
			FileReader fileReader = new FileReader(userFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			FileWriter fileWriter = new FileWriter(tempFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				// FOR TEST ONLY.
				//System.out.println(oneLine);
				bufferedWriter.write(oneLine);
				bufferedWriter.newLine();
				oneLine = bufferedReader.readLine(); 
			}
			
			bufferedWriter.write(bookedSession);
			bufferedWriter.newLine();
			
			if(oldFile.exists()&&oldFile.isFile()) {
				file.renameTo(oldFile);
				flag2 = true;
			} else {
				flag2 = false;
			}
			
			bufferedWriter.flush();
			fileWriter.flush();
			
			bufferedReader.close();
			fileReader.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Errors occurred while trying to read or write user's live session file.");
			e.printStackTrace();
		}
		
		if(flag1&&flag2)
			flag = true; 
		else 
			flag = false;
		
		// FOR TEST ONLY.
		//System.out.println(flag);
		
		return flag;
	}
	
	@Override
	public boolean cancelSession(LiveSession liveSession) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * This method is intent to cancel the session user selected, delete the corresponding record and return into LiveSessionAvailable.txt file.
		 * @return flag boolean, to indicate if the operation is successful.
		 * @param fileName String type, the file of LiveSessionAvailable.txt directory.
		 * @param tempFileName String type, the file of temp.txt directory.
		 * @param userFileName String type, the file of user's live session information directory.
		 * @param flag1 boolean type, to determine if the operation about adding record in user's live session information file is successful.
		 * @param flag2 boolean type, to determine if the operation about deleting record in LiveSessionAvailable.txt is successful.
		 * @param flag boolean type, to determine if the operation about the whole method is successful.
		 * @param bookedSession String type, user's selection.
		 * @param userID integer, userID.
		 */
		
		// The determinant of the cancellation status.
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag = false;
		// Get the user's selection.
		String cancelSession = liveSession.getCancelSession();
		// Get the user's id.
		int userID = liveSession.getUserID();
		
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/AvailableLiveSession.txt";
		String tempFileName = "src/main/resources/LiveSession/temp.txt";
		String userFileName = "src/main/resources/LiveSession/" + userID +"BookedLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		try { // Delete record in the user's file.
			/**
			 * @param fileCreateFlag Indication to tell if the file is successfully created.
			 */
			boolean fileCreateFlag = false;
			File file = new File(tempFileName);
			File oldFile = new File(userFileName);
			try {
				if(file.exists()) {
					System.out.println("Deleted current existing temp.txt.");
					file.delete();
				}
				// Creating a temporary file and check status.
				fileCreateFlag = file.createNewFile();
			} catch (IOException e) {
				System.out.println("Errors occurred when try to create the file temp.txt");
				System.out.println("Shutting Down...");
				e.printStackTrace();
				System.exit(1);
			}
			System.out.println("Status of creating temp.txt file: " + fileCreateFlag);
			
			FileReader fileReader = new FileReader(userFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			FileWriter fileWriter = new FileWriter(tempFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				// FOR TEST ONLY.
				//System.out.println(oneLine);
				// FOR TEST ONLY.
				//System.out.println("Match or not: >>>>>>>>>>>>>>>>>>>>" + oneLine.equals(bookedSession));
				if(oneLine.equals(cancelSession)) {
					oneLine = bufferedReader.readLine();
					continue;
				}
				else {
					bufferedWriter.write(oneLine);
					bufferedWriter.newLine();
					oneLine = bufferedReader.readLine(); 
					continue;
				}
			}
			
			if(oldFile.exists()&&oldFile.isFile()) {
				file.renameTo(oldFile);
				flag1 = true;
			} else {
				flag1 = false;
			}
			
			bufferedWriter.flush();
			fileWriter.flush();
			
			bufferedReader.close();
			fileReader.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Errors occurred while trying to read or write.");
			e.printStackTrace();
		}
		
		
		try { // Add to AvailableLiveSession.txt.
			/**
			 * @param fileCreateFlag Indication to tell if the file is successfully created.
			 */
			boolean fileCreateFlag = false;
			File file = new File(tempFileName);
			File oldFile = new File(fileName);
			try {
				if(file.exists()) {
					System.out.println("Deleted current existing temp.txt.");
					file.delete();
				}
				// Creating a temporary file and check status.
				fileCreateFlag = file.createNewFile();
			} catch (IOException e) {
				System.out.println("Errors occurred when try to create the file temp.txt");
				System.out.println("Shutting Down...");
				e.printStackTrace();
				System.exit(1);
			}
			System.out.println("Status of creating temp.txt file: " + fileCreateFlag);
			
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			FileWriter fileWriter = new FileWriter(tempFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				// FOR TEST ONLY.
				//System.out.println(oneLine);
				bufferedWriter.write(oneLine);
				bufferedWriter.newLine();
				oneLine = bufferedReader.readLine(); 
			}
			
			bufferedWriter.write(cancelSession);
			bufferedWriter.newLine();
			
			if(oldFile.exists()&&oldFile.isFile()) {
				file.renameTo(oldFile);
				flag2 = true;
			} else {
				flag2 = false;
			}
			
			bufferedWriter.flush();
			fileWriter.flush();
			
			bufferedReader.close();
			fileReader.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Errors occurred while trying to read or write AvailableLiveSession.txt file.");
			e.printStackTrace();
		}
		
		if(flag1&&flag2)
			flag = true; 
		else 
			flag = false;
		
		// FOR TEST ONLY.
		//System.out.println(flag);
		
		return flag;
	}

	@Override
	public ArrayList<String> displaySearchedSessions(LiveSession liveSession) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * This method is intent to extract all the information of the searched coach.
		 * @return searchResult ArrayList<String>, containing the information in the form of "coachID*coach's name*start time of the session".
		 * @param searchContent String type, contain the user's searching content.
		 * @param fileName String type, the file of LiveSessionAvailable.txt directory.
		 */
		
		// All of the search results.
		ArrayList<String> searchResult = new ArrayList<String>();
		// User's search content.
		String searchContent = liveSession.getSearchedCoach();
		
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/AvailableLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		
		
		try {
			/** 
			 * @param oneLine String type, the temporary storage for each line of the content.
			 */
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				if(oneLine.contains(searchContent)) {
					searchResult.add(oneLine);
					oneLine = bufferedReader.readLine();
				} else {
					oneLine = bufferedReader.readLine();
				}
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println("Errors occurred when try to read the file AvailableLiveSession.txt");
			System.out.println("Shutting Down...");
			e.printStackTrace();
			System.exit(1);
		}
		
		// FOR TEST ONLY.
		System.out.println(searchResult);
		
		return searchResult;
	}

	@Override
	public ArrayList<String> displaySearchedStartTime(LiveSession liveSession) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * This method is intent to extract all the information of the searched time-slot.
		 * @return searchResult ArrayList<String>, containing the information in the form of "coachID*coach's name*start time of the session".
		 * @param searchContent String type, contain the user's searching time-slot.
		 * @param fileName String type, the file of LiveSessionAvailable.txt directory.
		 */
		
		// All of the search results.
		ArrayList<String> searchResult = new ArrayList<String>();
		// User's search content.
		String searchContent = liveSession.getSearchedStartTime();
		//System.out.println(">>>>>>>>>>>>"+searchContent);
		
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/AvailableLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		
		
		try {
			/** 
			 * @param oneLine String type, the temporary storage for each line of the content.
			 */
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				if(oneLine.contains(searchContent)) {
					searchResult.add(oneLine);
					oneLine = bufferedReader.readLine();
				} else {
					oneLine = bufferedReader.readLine();
				}
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println("Errors occurred when try to read the file AvailableLiveSession.txt");
			System.out.println("Shutting Down...");
			e.printStackTrace();
			System.exit(1);
		}
		
		// FOR TEST ONLY.
		System.out.println(searchResult);
		
		return searchResult;
	}

	@Override
	public ArrayList<String> displayHistory(LiveSession liveSession) throws Exception {
		// TODO Auto-generated method stub

		/**
		 * This method is intent to extract all the information of the available live sessions.
		 * @return allAvailableSessions ArrayList<String>, containing the information in the form of "coachID*coach's name*start time of the session".
		 * @param fileName String type, the file of user's booked session directory.
		 * @param userID integer, userID.
		 */
		
		// All of the available sessions of all coaches.
		ArrayList<String> allHistory = new ArrayList<String>();
		int userID = liveSession.getUserID();
		
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/" + userID +"BookedLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		
		try {
			/** 
			 * @param oneLine String type, the temporary storage for each line of the content.
			 */
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				// Read each line of the file then add them to the ArrayList.
				allHistory.add(oneLine);
				oneLine = bufferedReader.readLine();
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println("Errors occurred when try to read the user's booked sessions file.");
			System.out.println("Shutting Down...");
			e.printStackTrace();
			System.exit(1);
		}
		
		// FOR TEST ONLY.
		System.out.println(allHistory);
		
		return allHistory;
	}

	@Override
	public ArrayList<String> displayHistorySearchedStartTime(LiveSession liveSession) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * This method is intent to extract all the information of the searched time-slot.
		 * @return searchResult ArrayList<String>, containing the information in the form of "coachID*coach's name*start time of the session".
		 * @param userID integer, userID.
		 * @param searchContent String type, contain the user's searching time-slot.
		 * @param fileName String type, the file of LiveSessionAvailable.txt directory.
		 */

		// All of the search results.
		ArrayList<String> searchResult = new ArrayList<String>();
		int userID = liveSession.getUserID();
		// User's search content.
		String searchContent = liveSession.getSearchedStartTime();

		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/" + userID +"BookedLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */


		try {
			/**
			 * @param oneLine String type, the temporary storage for each line of the content.
			 */
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				if(oneLine.contains(searchContent)) {
					searchResult.add(oneLine);
					oneLine = bufferedReader.readLine();
				} else {
					oneLine = bufferedReader.readLine();
				}
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println("Errors occurred when try to read the file AvailableLiveSession.txt");
			System.out.println("Shutting Down...");
			e.printStackTrace();
			System.exit(1);
		}

		// FOR TEST ONLY.
		System.out.println(searchResult);

		return searchResult;
	}

	@Override
	public ArrayList<String> displayHistorySearchedSessions(LiveSession liveSession) throws Exception {
		// TODO Auto-generated method stub

		/**
		 * This method is intent to extract all the information of the searched coach.
		 * @return searchResult ArrayList<String>, containing the information in the form of "coachID*coach's name*start time of the session".
		 * @param userID integer, userID.
		 * @param searchContent String type, contain the user's searching content.
		 * @param fileName String type, the file of LiveSessionAvailable.txt directory.
		 */

		// All of the search results.
		ArrayList<String> searchResult = new ArrayList<String>();
		int userID = liveSession.getUserID();
		// User's search content.
		String searchContent = liveSession.getSearchedCoach();
		System.out.println(">>>>>>>>"+searchContent);
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/" + userID +"BookedLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */


		try {
			/**
			 * @param oneLine String type, the temporary storage for each line of the content.
			 */
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				if(oneLine.contains(searchContent)) {
					searchResult.add(oneLine);
					oneLine = bufferedReader.readLine();
				} else {
					oneLine = bufferedReader.readLine();
				}
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println("Errors occurred when try to read the file AvailableLiveSession.txt");
			System.out.println("Shutting Down...");
			e.printStackTrace();
			System.exit(1);
		}

		// FOR TEST ONLY.
		System.out.println(searchResult);

		return searchResult;
	}

	@Override
	public boolean addToCoachRecord(LiveSession liveSession) throws Exception {
		/**
		 * This method is intent to cancel the session user selected, and save into coach's live session file.
		 * @return flag boolean, to indicate if the operation is successful.
		 * @param fileName String type, the file of LiveSessionAvailable.txt directory.
		 * @param tempFileName String type, the file of temp.txt directory.
		 * @param userFileName String type, the file of user's live session information directory.
		 * @param flag2 boolean type, to determine if the operation about adding record in coach's live session information file is successful.
		 * @param bookedSession String type, user's selection.
		 * @param info[] String array contains the corresponding info.
		 * @param coachID integer, coachID.
		 */

		// The determinant of the booking status.
		boolean flag2 = false;
		// Get the user's selection.
		String bookedSession = liveSession.getBookedSession();
		// Get the coach's id.
		String[] info = bookedSession.split("\\*");
		String coachID = info[0];

		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/AvailableLiveSession.txt";
		String tempFileName = "src/main/resources/LiveSession/temp.txt";
		String userFileName = "src/main/resources/LiveSession/" + coachID +"BookedLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

		try { // Add to coach's personal account information of live sessions.
			/**
			 * @param fileCreateFlag Indication to tell if the file is successfully created.
			 */
			boolean fileCreateFlag = false;
			File file = new File(tempFileName);
			File oldFile = new File(userFileName);
			try {
				if(file.exists()) {
					System.out.println("Deleted current existing temp.txt.");
					file.delete();
				}
				// Creating a temporary file and check status.
				fileCreateFlag = file.createNewFile();
			} catch (IOException e) {
				System.out.println("Errors occurred when try to create the file temp.txt");
				System.out.println("Shutting Down...");
				e.printStackTrace();
				System.exit(1);
			}
			System.out.println("Status of creating temp.txt file: " + fileCreateFlag);

			FileReader fileReader = new FileReader(userFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			FileWriter fileWriter = new FileWriter(tempFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				// FOR TEST ONLY.
				//System.out.println(oneLine);
				bufferedWriter.write(oneLine);
				bufferedWriter.newLine();
				oneLine = bufferedReader.readLine();
			}

			bufferedWriter.write(bookedSession+liveSession.getUserID()+"*");
			bufferedWriter.newLine();

			if(oldFile.exists()&&oldFile.isFile()) {
				file.renameTo(oldFile);
				flag2 = true;
			} else {
				flag2 = false;
			}

			bufferedWriter.flush();
			fileWriter.flush();

			bufferedReader.close();
			fileReader.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Errors occurred while trying to read or write user's live session file.");
			e.printStackTrace();
		}

		// FOR TEST ONLY.
		//System.out.println(flag);

		return flag2;
	}

	@Override
	public boolean cancelFromCoachRecord(LiveSession liveSession) throws Exception {

		/**
		 * This method is intent to book the session user selected, delete the corresponding record from coach's live session file.
		 * @return flag boolean, to indicate if the operation is successful.
		 * @param fileName String type, the file of LiveSessionAvailable.txt directory.
		 * @param tempFileName String type, the file of temp.txt directory.
		 * @param userFileName String type, the file of user's live session information directory.
		 * @param flag1 boolean type, to determine if the operation about deleting record in LiveSessionAvailable.txt is successful.
		 * @param bookedSession String type, user's selection.
		 * @param info[] String array contains the corresponding info.
		 * @param coachID integer, coachID.
		 */

		// The determinant of the booking status.
		boolean flag1 = false;
		// Get the user's selection.
		String bookedSession = liveSession.getCancelSession();
		// Get the coach's id.
		String[] info = bookedSession.split("\\*");
		String coachID = info[0];

		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String tempFileName = "src/main/resources/LiveSession/temp.txt";
		String userFileName = "src/main/resources/LiveSession/" + coachID +"BookedLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		try { // Delete record in the AvailableLiveSession.txt.
			/**
			 * @param fileCreateFlag Indication to tell if the file is successfully created.
			 */
			boolean fileCreateFlag = false;
			File file = new File(tempFileName);
			File oldFile = new File(userFileName);
			try {
				if(file.exists()) {
					System.out.println("Deleted current existing temp.txt.");
					file.delete();
				}
				// Creating a temporary file and check status.
				fileCreateFlag = file.createNewFile();
			} catch (IOException e) {
				System.out.println("Errors occurred when try to create the file temp.txt");
				System.out.println("Shutting Down...");
				e.printStackTrace();
				System.exit(1);
			}
			System.out.println("Status of creating temp.txt file: " + fileCreateFlag);

			FileReader fileReader = new FileReader(userFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			FileWriter fileWriter = new FileWriter(tempFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			String oneLine = bufferedReader.readLine();
			System.out.println("First Line: >>>>>>> "+oneLine);
			while (oneLine != null) {
				// FOR TEST ONLY.
				System.out.println(oneLine);
				// FOR TEST ONLY.
				System.out.println("Match or not: >>>>>>>>>>>>>>>>>>>>" + oneLine.equals(bookedSession+liveSession.getUserID()+"*"));
				if(oneLine.equals(bookedSession+liveSession.getUserID()+"*")) {
					oneLine = bufferedReader.readLine();
					continue;
				}
				else {
					bufferedWriter.write(oneLine);
					bufferedWriter.newLine();
					oneLine = bufferedReader.readLine();
					continue;
				}
			}

			if(oldFile.exists()&&oldFile.isFile()) {
				file.renameTo(oldFile);
				flag1 = true;
			} else {
				flag1 = false;
			}

			bufferedWriter.flush();
			fileWriter.flush();

			bufferedReader.close();
			fileReader.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Errors occurred while trying to read or write.");
			e.printStackTrace();
		}

		// FOR TEST ONLY.
		//System.out.println(flag);

		return flag1;
	}


	@Override
	public ArrayList<String> displayHistoryCoach(LiveSession liveSession) throws Exception {

		/**
		 * This method is intent to extract all the information of the available live sessions.
		 * @return allAvailableSessions ArrayList<String>, containing the information in the form of "coachID*coach's name*start time of the session".
		 * @param fileName String type, the file of user's booked session directory.
		 * @param userID integer, userID.
		 */

		// All of the available sessions of all coaches.
		ArrayList<String> allHistory = new ArrayList<String>();
		int userID = liveSession.getUserID();

		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/1001BookedLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

		try {
			/**
			 * @param oneLine String type, the temporary storage for each line of the content.
			 */
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				// Read each line of the file then add them to the ArrayList.
				allHistory.add(oneLine);
				oneLine = bufferedReader.readLine();
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println("Errors occurred when try to read the user's booked sessions file.");
			System.out.println("Shutting Down...");
			e.printStackTrace();
			System.exit(1);
		}

		// FOR TEST ONLY.
		System.out.println(allHistory);

		return allHistory;
	}

	@Override
	public ArrayList<String> displayHistorySearchedStartTimeCoach(LiveSession liveSession) throws Exception {
		/**
		 * This method is intent to extract all the information of the searched time-slot.
		 * @return searchResult ArrayList<String>, containing the information in the form of "coachID*coach's name*start time of the session".
		 * @param userID integer, userID.
		 * @param searchContent String type, contain the user's searching time-slot.
		 * @param fileName String type, the file of LiveSessionAvailable.txt directory.
		 */

		// All of the search results.
		ArrayList<String> searchResult = new ArrayList<String>();
		int userID = liveSession.getUserID();
		// User's search content.
		String searchContent = liveSession.getSearchedStartTime();

		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/1001BookedLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */


		try {
			/**
			 * @param oneLine String type, the temporary storage for each line of the content.
			 */
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				if(oneLine.contains(searchContent)) {
					searchResult.add(oneLine);
					oneLine = bufferedReader.readLine();
				} else {
					oneLine = bufferedReader.readLine();
				}
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println("Errors occurred when try to read the file AvailableLiveSession.txt");
			System.out.println("Shutting Down...");
			e.printStackTrace();
			System.exit(1);
		}

		// FOR TEST ONLY.
		System.out.println(searchResult);

		return searchResult;
	}

	@Override
	public ArrayList<String> displayHistorySearchedSessionsCoach(LiveSession liveSession) throws Exception {

		/**
		 * This method is intent to extract all the information of the searched coach.
		 * @return searchResult ArrayList<String>, containing the information in the form of "coachID*coach's name*start time of the session".
		 * @param userID integer, userID.
		 * @param searchContent String type, contain the user's searching content.
		 * @param fileName String type, the file of LiveSessionAvailable.txt directory.
		 */

		// All of the search results.
		ArrayList<String> searchResult = new ArrayList<String>();
		int userID = liveSession.getUserID();
		// User's search content.
		String searchContent = liveSession.getSearchedCoach();
		System.out.println(">>>>>>>>"+searchContent);
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
		// The file of LiveSessionAvailable.txt directory.
		String fileName = "src/main/resources/LiveSession/1001BookedLiveSession.txt";
		/* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICATION REQUIRED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */


		try {
			/**
			 * @param oneLine String type, the temporary storage for each line of the content.
			 */
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String oneLine = bufferedReader.readLine();
			while (oneLine != null) {
				if(oneLine.contains(searchContent)) {
					searchResult.add(oneLine);
					oneLine = bufferedReader.readLine();
				} else {
					oneLine = bufferedReader.readLine();
				}
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println("Errors occurred when try to read the file AvailableLiveSession.txt");
			System.out.println("Shutting Down...");
			e.printStackTrace();
			System.exit(1);
		}

		// FOR TEST ONLY.
		System.out.println(searchResult);

		return searchResult;
	}


}
