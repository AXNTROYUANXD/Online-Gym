/**
 *
 * Title: MemberPageController.java
 * Description: This class is the control class for methods in LiveSessionDAOImpl.class and the GUI.
 * Copyright: Copyright (c) 2021
 * @since May 7 2021
 * @author Jiayi Zhang
 *
 */
package Controller;

import Com.Main;
import DAO.LiveSessionDAO;
import DAO.impl.LiveSessionDAOImpl;
import DAO.impl.VideoDAOIml;
import DAO.impl.userDAOImpl;
import DAO.userDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import vo.HistoryDisplayData;
import vo.LiveSession;
import vo.Video;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class MemberPageController {
////Timetable
    //The variables and methods with 1 behind correspond to the 'Reserve' page, and those without 1 are belonged to 'My Page' page.

    //The variables and methods with 1 behind correspond to the 'Reserve' page, and those without 1 are belonged to 'My Page' page.

    @FXML // fx:id="PortraiteButton"
    private Button PortraiteButton; // Value injected by FXMLLoader

    @FXML // fx:id="startTimePicker"
    private DatePicker startTimePicker; // Value injected by FXMLLoader

    @FXML // fx:id="myPageDateReturn"
    private TableView<HistoryDisplayData> myPageDateReturn; // Value injected by FXMLLoader

    @FXML // fx:id="coachName"
    private TableColumn<HistoryDisplayData,String> coachName; // Value injected by FXMLLoader

    @FXML // fx:id="coachID"
    private TableColumn<HistoryDisplayData,String> coachID; // Value injected by FXMLLoader

    @FXML // fx:id="startTime"
    private TableColumn<HistoryDisplayData,String> startTime; // Value injected by FXMLLoader

    @FXML // fx:id="duration"
    private TableColumn<HistoryDisplayData,String> duration; // Value injected by FXMLLoader

    @FXML // fx:id="courseName"
    private TableColumn<HistoryDisplayData,String> courseName; // Value injected by FXMLLoader

    @FXML // fx:id="searchedContent"
    private TextField searchedContent; // Value injected by FXMLLoader

    @FXML // fx:id="search"
    private Button search; // Value injected by FXMLLoader

    @FXML // fx:id="displayAllButton"
    private Button displayAllButton; // Value injected by FXMLLoader

    @FXML // fx:id="cancelBookButton"
    private Button cancelBookButton; // Value injected by FXMLLoader

    @FXML // fx:id="searchedContent1"
    private TextField searchedContent1; // Value injected by FXMLLoader

    @FXML // fx:id="search1"
    private Button search1; // Value injected by FXMLLoader

    @FXML // fx:id="startTimePicker1"
    private DatePicker startTimePicker1; // Value injected by FXMLLoader

    @FXML // fx:id="reserveDate"
    private TableView<HistoryDisplayData> reserveDate; // Value injected by FXMLLoader

    @FXML // fx:id="coachName1"
    private TableColumn<HistoryDisplayData,String> coachName1; // Value injected by FXMLLoader

    @FXML // fx:id="coachID1"
    private TableColumn<HistoryDisplayData,String> coachID1; // Value injected by FXMLLoader

    @FXML // fx:id="startTime1"
    private TableColumn<HistoryDisplayData,String> startTime1; // Value injected by FXMLLoader

    @FXML // fx:id="duration1"
    private TableColumn<HistoryDisplayData,String> duration1; // Value injected by FXMLLoader

    @FXML // fx:id="courseName1"
    private TableColumn<HistoryDisplayData,String> courseName1; // Value injected by FXMLLoader

    @FXML // fx:id="displayAllButton1"
    private Button displayAllButton1; // Value injected by FXMLLoader

    @FXML // fx:id="bookButton"
    private Button bookButton; // Value injected by FXMLLoader


    @FXML
    void clik(ActionEvent event) {

    }


    @FXML
    void dateAction(ActionEvent event) {

        /**
         * This method is to search the booked content by using users' input date value.
         * @param str String Temporary string for the results.
         * @param info String[] It's temporarily for the corresponding results.
         * @param coachIDTemp String Temporary string for coachID.
         * @param coachNameTemp String Temporary string for coachName.
         * @param startTimeTemp String Temporary string for startTime.
         * @param durationTemp String Temporary string for duration.
         * @param courseNameTemp String Temporary string for courseName.
         */
        cancelBookButton.setDisable(true);
        LocalDate searchedDate = startTimePicker.getValue(); // Get date.
        //System.out.println(searchedDate.toString());
        LiveSessionDAO dao = new LiveSessionDAOImpl();
        LiveSession liveSession = new LiveSession();

        liveSession.setUserID(123);
        liveSession.setSearchedStartTime(searchedDate.toString()); // Transfer to String and use setter.

        ArrayList<String> searchedStartTimeResult = new ArrayList<String>();

        try {
            searchedStartTimeResult.addAll(dao.displayHistorySearchedStartTime(liveSession)); // Implement method.
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Errors occurred when try to implement dateAction(ActionEvent) method.");
        }
        //System.out.println(searchedStartTimeResult);

        String str = null;

        final ObservableList<HistoryDisplayData> dataList = FXCollections.observableArrayList();

        for(int i=0; i<searchedStartTimeResult.size(); i++) {
            str = searchedStartTimeResult.get(i);
            String[] info = str.split("\\*");
            String coachIDTemp = info[0];
            String coachNameTemp = info[1];
            String startTimeTemp = info[2];
            String durationTemp = info[3];
            String courseNameTemp = info[4];
            //System.out.println(">>>>>>>>>>>>>>"+coachNameTemp+"<<<<<<<<<<<<<<<");

            // Display.
            dataList.add(new HistoryDisplayData(coachNameTemp,coachIDTemp,startTimeTemp,durationTemp,courseNameTemp));
            coachName.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData,String>("coachName"));
            coachID.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData,String>("coachID"));
            startTime.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData,String>("startTime"));
            duration.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData,String>("duration"));
            courseName.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData,String>("courseName"));
            myPageDateReturn.setItems(dataList);
        }
    }


    @FXML
    void dateAction1(ActionEvent event) {

        /**
         * This method is to search all available content by using users' input date value.
         * @param str String Temporary string for the results.
         * @param info String[] It's temporarily for the corresponding results.
         * @param coachIDTemp String Temporary string for coachID.
         * @param coachNameTemp String Temporary string for coachName.
         * @param startTimeTemp String Temporary string for startTime.
         * @param durationTemp String Temporary string for duration.
         * @param courseNameTemp String Temporary string for courseName.
         */
        bookButton.setDisable(true);
        LocalDate searchedDate = startTimePicker1.getValue();
        //System.out.println(searchedDate.toString());
        LiveSessionDAO dao = new LiveSessionDAOImpl();
        LiveSession liveSession = new LiveSession();

        liveSession.setUserID(123);
        liveSession.setSearchedStartTime(searchedDate.toString());

        ArrayList<String> searchedStartTimeResult = new ArrayList<String>();

        try {
            searchedStartTimeResult.addAll(dao.displaySearchedStartTime(liveSession));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Errors occurred when try to implement dateAction1(ActionEvent) method.");
        }
        System.out.println(searchedStartTimeResult);

        String str = null;

        final ObservableList<HistoryDisplayData> dataList = FXCollections.observableArrayList();

        for (int i = 0; i < searchedStartTimeResult.size(); i++) {
            str = searchedStartTimeResult.get(i);
            String[] info = str.split("\\*");
            String coachIDTemp = info[0];
            String coachNameTemp = info[1];
            String startTimeTemp = info[2];
            String durationTemp = info[3];
            String courseNameTemp = info[4];
            //System.out.println(">>>>>>>>>>>>>>"+coachNameTemp+"<<<<<<<<<<<<<<<");

            dataList.add(new HistoryDisplayData(coachNameTemp, coachIDTemp, startTimeTemp, durationTemp, courseNameTemp));
            coachName1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("coachName"));
            coachID1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("coachID"));
            startTime1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("startTime"));
            duration1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("duration"));
            courseName1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("courseName"));
            reserveDate.setItems(dataList);
        }
    }

    @FXML
    void searchButtonAction(ActionEvent event) {
        /**
         * This method is to search the booked content by using users' input string.
         * @param str String Temporary string for the results.
         * @param info String[] It's temporarily for the corresponding results.
         * @param coachIDTemp String Temporary string for coachID.
         * @param coachNameTemp String Temporary string for coachName.
         * @param startTimeTemp String Temporary string for startTime.
         * @param durationTemp String Temporary string for duration.
         * @param courseNameTemp String Temporary string for courseName.
         */
        cancelBookButton.setDisable(true);
        LiveSessionDAO dao = new LiveSessionDAOImpl();
        LiveSession liveSession = new LiveSession();

        liveSession.setUserID(123);
        liveSession.setSearchedCoach(searchedContent.getText());

        ArrayList<String> searchedResult = new ArrayList<String>();

        try {
            searchedResult.addAll(dao.displayHistorySearchedSessions(liveSession));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Errors occurred when try to implement searchButtonAction(ActionEvent) method.");
        }
        System.out.println(searchedResult);

        String str = null;

        final ObservableList<HistoryDisplayData> dataList = FXCollections.observableArrayList();

        for (int i = 0; i < searchedResult.size(); i++) {
            str = searchedResult.get(i);
            String[] info = str.split("\\*");
            String coachIDTemp = info[0];
            String coachNameTemp = info[1];
            String startTimeTemp = info[2];
            String durationTemp = info[3];
            String courseNameTemp = info[4];
            //System.out.println(">>>>>>>>>>>>>>"+coachNameTemp+"<<<<<<<<<<<<<<<");

            dataList.add(new HistoryDisplayData(coachNameTemp, coachIDTemp, startTimeTemp, durationTemp, courseNameTemp));
            coachName.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("coachName"));
            coachID.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("coachID"));
            startTime.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("startTime"));
            duration.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("duration"));
            courseName.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("courseName"));
            myPageDateReturn.setItems(dataList);
        }
    }

    @FXML
    void searchButtonAction1(ActionEvent event) {
        /**
         * This method is to search all available content by using users' input string.
         * @param str String Temporary string for the results.
         * @param info String[] It's temporarily for the corresponding results.
         * @param coachIDTemp String Temporary string for coachID.
         * @param coachNameTemp String Temporary string for coachName.
         * @param startTimeTemp String Temporary string for startTime.
         * @param durationTemp String Temporary string for duration.
         * @param courseNameTemp String Temporary string for courseName.
         */
        bookButton.setDisable(true);
        LiveSessionDAO dao = new LiveSessionDAOImpl();
        LiveSession liveSession = new LiveSession();

        liveSession.setUserID(123);
        liveSession.setSearchedCoach(searchedContent1.getText());

        ArrayList<String> searchedResult = new ArrayList<String>();

        try {
            searchedResult.addAll(dao.displaySearchedSessions(liveSession));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Errors occurred when try to implement searchButtonAction1(ActionEvent) method.");
        }
        System.out.println(searchedResult);

        String str = null;

        final ObservableList<HistoryDisplayData> dataList = FXCollections.observableArrayList();

        for (int i = 0; i < searchedResult.size(); i++) {
            str = searchedResult.get(i);
            String[] info = str.split("\\*");
            String coachIDTemp = info[0];
            String coachNameTemp = info[1];
            String startTimeTemp = info[2];
            String durationTemp = info[3];
            String courseNameTemp = info[4];
            //System.out.println(">>>>>>>>>>>>>>"+coachNameTemp+"<<<<<<<<<<<<<<<");

            dataList.add(new HistoryDisplayData(coachNameTemp, coachIDTemp, startTimeTemp, durationTemp, courseNameTemp));
            coachName1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("coachName"));
            coachID1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("coachID"));
            startTime1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("startTime"));
            duration1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("duration"));
            courseName1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("courseName"));
            reserveDate.setItems(dataList);
        }
    }

    @FXML
    void displayAllAction(ActionEvent event) {
        /**
         * This method is to display all booked contents.
         * @param str String Temporary string for the results.
         * @param info String[] It's temporarily for the corresponding results.
         * @param coachIDTemp String Temporary string for coachID.
         * @param coachNameTemp String Temporary string for coachName.
         * @param startTimeTemp String Temporary string for startTime.
         * @param durationTemp String Temporary string for duration.
         * @param courseNameTemp String Temporary string for courseName.
         */
        cancelBookButton.setDisable(true);
        LiveSessionDAO dao = new LiveSessionDAOImpl();
        LiveSession liveSession = new LiveSession();

        liveSession.setUserID(123);

        ArrayList<String> searchedResult = new ArrayList<String>();

        try {
            searchedResult.addAll(dao.displayHistory(liveSession));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Errors occurred when try to implement searchButtonAction(ActionEvent) method.");
        }
        System.out.println(searchedResult);

        String str = null;

        final ObservableList<HistoryDisplayData> dataList = FXCollections.observableArrayList();

        for (int i = 0; i < searchedResult.size(); i++) {
            str = searchedResult.get(i);
            String[] info = str.split("\\*");
            String coachIDTemp = info[0];
            String coachNameTemp = info[1];
            String startTimeTemp = info[2];
            String durationTemp = info[3];
            String courseNameTemp = info[4];
            //System.out.println(">>>>>>>>>>>>>>"+coachNameTemp+"<<<<<<<<<<<<<<<");

            dataList.add(new HistoryDisplayData(coachNameTemp, coachIDTemp, startTimeTemp, durationTemp, courseNameTemp));
            coachName.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("coachName"));
            coachID.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("coachID"));
            startTime.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("startTime"));
            duration.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("duration"));
            courseName.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("courseName"));
            myPageDateReturn.setItems(dataList);
        }
    }

    @FXML
    void displayAllAction1(ActionEvent event) {
        /**
         * This method is to display all available contents.
         * @param str String Temporary string for the results.
         * @param info String[] It's temporarily for the corresponding results.
         * @param coachIDTemp String Temporary string for coachID.
         * @param coachNameTemp String Temporary string for coachName.
         * @param startTimeTemp String Temporary string for startTime.
         * @param durationTemp String Temporary string for duration.
         * @param courseNameTemp String Temporary string for courseName.
         */
        bookButton.setDisable(true);
        LiveSessionDAO dao = new LiveSessionDAOImpl();
        LiveSession liveSession = new LiveSession();

        ArrayList<String> searchedResult = new ArrayList<String>();

        try {
            searchedResult.addAll(dao.displayAllSessions());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Errors occurred when try to implement searchButtonAction1(ActionEvent) method.");
        }
        System.out.println(searchedResult);

        String str = null;

        final ObservableList<HistoryDisplayData> dataList = FXCollections.observableArrayList();

        for (int i = 0; i < searchedResult.size(); i++) {
            str = searchedResult.get(i);
            String[] info = str.split("\\*");
            String coachIDTemp = info[0];
            String coachNameTemp = info[1];
            String startTimeTemp = info[2];
            String durationTemp = info[3];
            String courseNameTemp = info[4];
            //System.out.println(">>>>>>>>>>>>>>"+coachNameTemp+"<<<<<<<<<<<<<<<");

            dataList.add(new HistoryDisplayData(coachNameTemp, coachIDTemp, startTimeTemp, durationTemp, courseNameTemp));
            coachName1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("coachName"));
            coachID1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("coachID"));
            startTime1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("startTime"));
            duration1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("duration"));
            courseName1.setCellValueFactory(new PropertyValueFactory<HistoryDisplayData, String>("courseName"));
            reserveDate.setItems(dataList);
        }
    }

    @FXML
    void bookAction(ActionEvent event) {
        /**
         * This method is to book a Live Session by implementing the existing method is LiveSessionDAOImpl.class.
         * @since Iteration 4
         * @param flag boolean Status indicator for the method.
         */
        HistoryDisplayData bookedSession = reserveDate.getSelectionModel().getSelectedItem();

        LiveSessionDAO dao = new LiveSessionDAOImpl();
        LiveSession liveSession = new LiveSession();

        liveSession.setUserID(123);
        liveSession.setBookedSession(bookedSession.getCoachID()+"*"+bookedSession.getCoachName()+"*"+bookedSession.getStartTime()+"*"+bookedSession.getDuration()+"*"+bookedSession.getCourseName()+"*");

        boolean flag = false;
        try {
            flag = dao.bookSession(liveSession);
            System.out.println("The status of bookSession(LiveSession) is: " + flag);
            flag = dao.addToCoachRecord(liveSession);
            System.out.println("The status of addToCoachRecord(LiveSession) is: " + flag);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Errors occurred when try to implement bookAction(ActionEvent) method.");
        }
        if(flag==true){
            displayAllAction1(event);
            System.out.println("REFRESHED!!!!!!!!");
        }
    }

    @FXML
    void cancelBookAction(ActionEvent event) {
        /**
         * This method is to cancel a Live Session by implementing the existing method is LiveSessionDAOImpl.class.
         * @since Iteration 4
         * @param flag boolean Status indicator for the method.
         */
        HistoryDisplayData canceledSession = myPageDateReturn.getSelectionModel().getSelectedItem();

        LiveSessionDAO dao = new LiveSessionDAOImpl();
        LiveSession liveSession = new LiveSession();
        liveSession.setUserID(123);
        liveSession.setCancelSession(canceledSession.getCoachID()+"*"+canceledSession.getCoachName()+"*"+canceledSession.getStartTime()+"*"+canceledSession.getDuration()+"*"+canceledSession.getCourseName()+"*");

        boolean flag = false;
        try {
            flag = dao.cancelSession(liveSession);
            System.out.println("The status of cancelSession(LiveSession) is: " + flag);
            flag = dao.cancelFromCoachRecord(liveSession);
            System.out.println("The status of cancelFromCoachRecord(LiveSession) is: " + flag);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Errors occurred when try to implement cancelBookAction(ActionEvent)/cancelFromCoachRecord(ActionEvent) method.");
        }
        if(flag==true){
            displayAllAction(event);
            System.out.println("REFRESHED!!!!!!!!");
        }
    }

    @FXML
    void detectAction(MouseEvent event) {
        /**
         * This method is to detect if there is one session has been selected.
         * @since Iteration 4
         */
        HistoryDisplayData canceledSession = myPageDateReturn.getSelectionModel().getSelectedItem();
        if(canceledSession!=null){
            cancelBookButton.setDisable(false);
        }
    }

    @FXML
    void detectAction1(MouseEvent event) {
        /**
         * This method is to detect if there is one session has been selected.
         * @since Iteration 4
         */
        HistoryDisplayData bookedSession = reserveDate.getSelectionModel().getSelectedItem();
        if(bookedSession!=null){
            bookButton.setDisable(false);
        }
    }
    ////TimeTable

    ////MENU
    @FXML
    private Button MyPageButton;

    @FXML
    private Button ReserveButton;

    @FXML
    private Button VideoButton;

    @FXML
    private TabPane tabPane;

    @FXML
    private Label balanceText;

    @FXML
    private Label membershipLevelText;


    @FXML
    private TextArea descriptionText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField genderText;


    @FXML
    private Label MemberRights1;

    @FXML
    private Label MemberRights2;

    @FXML
    private Label MemberRights3;

    @FXML
    private TextField ageText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField categoryText;

    @FXML
    private AnchorPane videoShowPane;

    @FXML
    private AnchorPane MarkedVideoPane;


    @FXML
    void MyPageButtonClick(ActionEvent event) {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(0);
    }

    @FXML
    void ReserveButtonClick(ActionEvent event) throws IOException {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(1);
        markedVideoShowInit();

    }

    @FXML
    void VideoButtonClick(ActionEvent event) throws IOException {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(2);
        videoShowInit();

    }
    private ObservableList<String> apiList=FXCollections.observableArrayList();

    ///
    @FXML
    public void initialize() throws IOException {
        videoShowInit();
        markedVideoShowInit();
        infoInitialize();
        memberImageInit();
        }

    @FXML
    void Edit(ActionEvent event) {
        emailText.setEditable(true);
        addressText.setEditable(true);
        phoneText.setEditable(true);
        categoryText.setEditable(true);
        descriptionText.setEditable(true);

    }
    @FXML
    void Save(ActionEvent event) {
        userDAO userdao = new userDAOImpl();
        String[] info = new String[11];
        info = userdao.getInfo("123");
        info[2] = addressText.getText();
        info[4] = phoneText.getText();
        info[6] = categoryText.getText();
        info[8] = descriptionText.getText();
        if(emailText.getText().matches( "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){
            info[7] = emailText.getText();
            emailText.setEditable(false);
        }else{
            emailText.setText("Please Enter the correct value" );
        }

        if(phoneText.getText().matches( "^[1]+[3,8]+\\d{9}$")){
            info[4] = phoneText.getText();
            phoneText.setEditable(false);
        }else{
            phoneText.setText("Please Enter the correct value" );
        }
        addressText.setEditable(false);
        //phoneText.setEditable(false);
        categoryText.setEditable(false);
        descriptionText.setEditable(false);
        userdao.Save(info);
    }

    public void infoInitialize(){
        userDAO userdao = new userDAOImpl();
        String[] info = new String[11];
        String[] Rights = new String[5];
        String temp = "";
        info = userdao.getInfo("123");
        nameText.setText(info[1]);
        addressText.setText(info[2]);
        genderText.setText(info[3]);
        phoneText.setText(info[4]);
        ageText.setText(info[5]);
        categoryText.setText(info[6]);
        emailText.setText(info[7]);
        descriptionText.setText(info[8]);
        balanceText.setText(info[9]);
        switch (info[10]){
            case "1":
                membershipLevelText.setText("Senior Member");
                MemberRights1.setText("Videos Access Right");
                MemberRights2.setText("Access to Live Session");
                MemberRights3.setText("Free Instruction");
                break;
            case "2":
                membershipLevelText.setText("Premium Member");
                MemberRights1.setText("More VIP Free Videos Access Right");
                MemberRights2.setText("More Available Live Session Choices");
                MemberRights3.setText("Free Additional Instruction");
                break;
        }
    }

    @FXML
    void Recharge(ActionEvent event) {
        TextInputDialog in = new TextInputDialog();
        in.setTitle("Recharge");
        in.setHeaderText("How much do you want");
        in.show();
        Button ok = (Button)in.getDialogPane().lookupButton(ButtonType.OK);
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                userDAO userdao = new userDAOImpl();
                String[] userInfo = new String[11];
                userInfo = userdao.getInfo("123");
                userInfo[9] = valueOf(Integer.valueOf(userInfo[9])+Integer.valueOf(in.getEditor().getText()));
                userdao.Save(userInfo);
                infoInitialize();
            }
        });

    }

    @FXML
    void Upgrade(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Upgrade");
        alert.setHeaderText("Confirm the upgrade?");
        alert.show();
        Button ok = (Button)alert.getDialogPane().lookupButton(ButtonType.OK);
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userDAO userdao = new userDAOImpl();
                String[] userInfo = new String[11];
                userInfo = userdao.getInfo("123");
                if(Integer.valueOf(userInfo[9])<1000){
                    Alert Warning1 = new Alert(Alert.AlertType.WARNING);
                    Warning1.setHeaderText("Please first top-up");
                    Warning1.show();
                }else{
                    if(userInfo[10].equals("2")){
                        Alert Warning = new Alert(Alert.AlertType.WARNING);
                        Warning.setHeaderText("You're already a Premium Member");
                        Warning.show();
                    }else{
                        userInfo[10] = "2";
                        userInfo[9] = valueOf(Integer.valueOf(userInfo[9])-1000);
                        userdao.Save(userInfo);
                        infoInitialize();
                    }
                }
            }
        });
    }


    public void videoShowInit() throws IOException{
        FXMLLoader videoShowLoader = new FXMLLoader();
        videoShowLoader.setLocation(getClass().getResource("/FXML/video/videoMemberShow.fxml"));
        AnchorPane videoShowRoot= videoShowLoader.load();
        videoShowPane.getChildren().add(videoShowRoot);
    }

    public void markedVideoShowInit() throws IOException{
        FXMLLoader videoShowLoader = new FXMLLoader();
        videoShowLoader.setLocation(getClass().getResource("/FXML/video/videoMark.fxml"));
        AnchorPane videoShowRoot= videoShowLoader.load();
        MarkedVideoPane.getChildren().add(videoShowRoot);
    }

    @FXML
    private ImageView memberImage;

    public void memberImageInit() throws IOException {
        Image image = new Image("/Image/0.JPG");
        memberImage.setImage(image);
    }

    @FXML
    private Button backButton;

    @FXML
    void backAction(ActionEvent event) {
        Platform.runLater(()-> {
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.hide();
            try {
                new Main().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
