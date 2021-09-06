package Controller;

import Com.Main;
import DAO.LiveSessionDAO;
import DAO.VideoDAO;
import DAO.coachDAO;
import DAO.impl.LiveSessionDAOImpl;
import DAO.impl.VideoDAOIml;
import DAO.impl.coachDAOImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import vo.HistoryDisplayData;
import vo.LiveSession;
import vo.Video;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

public class CoachMyPageController {

    @FXML
    private AnchorPane videoShowPane;

    @FXML
    private Button PortraiteButton;

    @FXML
    private TextField addressText;

    @FXML
    private TextField genderText;

    @FXML
    private TextField markText;

    @FXML
    private Button Edit;

    @FXML
    private TextField emailText;

    @FXML
    private TextArea descriptionText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField categoryText;

    @FXML
    private Button VideoButton;

    @FXML
    private TextField phoneText;

    @FXML
    private Button MyPageButton;

    @FXML
    private Label name;

    @FXML
    private TextField ageText;

    @FXML
    private Button Save;

    @FXML
    private Button ManagementButton;

    @FXML
    private TabPane tabPane;

    @FXML
    private AnchorPane videoEditPane;


    @FXML
    public void initialize() throws IOException {
        this.videoShowInit();
        this.videoEditInit();
        infoInitialize();
        coachImageInit();
    }

    @FXML
    void Edit(ActionEvent event) {
        addressText.setEditable(true);
        phoneText.setEditable(true);
        emailText.setEditable(true);
        categoryText.setEditable(true);
        descriptionText.setEditable(true);
    }

    @FXML
    void Save(ActionEvent event) {
        coachDAO coachdao = new coachDAOImpl();
        String coachname = nameText.getText();
        String gender = genderText.getText();
        String address = addressText.getText();
        String moblienum = phoneText.getText();
        String age = ageText.getText();
        String mark = markText.getText();
        String email = emailText.getText();
        String category = categoryText.getText();
        String description = descriptionText.getText();
        addressText.setEditable(false);
        phoneText.setEditable(false);
        emailText.setEditable(false);
        categoryText.setEditable(false);
        descriptionText.setEditable(false);
        coachdao.editCoachInfo(coachname, address,gender,moblienum,age,mark,email,category,description);
    }

    public void infoInitialize(){
        coachDAO coachdao = new coachDAOImpl();
        String[] info = new String[9];
        info = coachdao.getInfo("123");
        nameText.setText(info[1]);
        addressText.setText(info[2]);
        genderText.setText(info[3]);
        phoneText.setText(info[4]);
        ageText.setText(info[5]);
        markText.setText(info[6]);
        emailText.setText(info[7]);
        categoryText.setText(info[8]);
        descriptionText.setText(info[9]);
    }

    public void videoShowRemove(){
        videoShowPane.getChildren().removeAll();
    }

    public void videoShowInit() throws IOException{
        FXMLLoader videoShowLoader = new FXMLLoader();
        videoShowLoader.setLocation(getClass().getResource("/FXML/video/videoShow.fxml"));
        AnchorPane videoShowRoot= videoShowLoader.load();
        videoShowPane.getChildren().add(videoShowRoot);
        }


    public void videoEditInit() throws IOException {
        FXMLLoader videoEditLoader = new FXMLLoader();
        videoEditLoader.setLocation(getClass().getResource("/FXML/video/videoEdit.fxml"));
        AnchorPane videoEditRoot= videoEditLoader.load();
        videoEditPane.getChildren().add(videoEditRoot);
    }

        @FXML
        void ClickMyPageButton(ActionEvent event) {
            SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
            selectionModel.select(0);
            }

        @FXML
        void ClickManagementButton(ActionEvent event) {
            SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
            selectionModel.select(1);

        }

        @FXML
        void ClickVideoButton(ActionEvent event) throws IOException {
            SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
            selectionModel.select(2);
            this.videoShowInit();

        }


    @FXML // fx:id="Text"
    private TextField Text; // Value injected by FXMLLoader

    @FXML // fx:id="startTimePicker"
    private DatePicker startTimePicker; // Value injected by FXMLLoader

    @FXML // fx:id="searchedContent"
    private TextField searchedContent; // Value injected by FXMLLoader

    @FXML // fx:id="search"
    private Button search; // Value injected by FXMLLoader

    @FXML // fx:id="displayAllButton"
    private Button displayAllButton; // Value injected by FXMLLoader

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

    @FXML // fx:id="startTimePicker1"
    private DatePicker startTimePicker1; // Value injected by FXMLLoader

    @FXML // fx:id="searchedContent1"
    private TextField searchedContent1; // Value injected by FXMLLoader

    @FXML // fx:id="search1"
    private Button search1; // Value injected by FXMLLoader

    @FXML // fx:id="displayAllButton1"
    private Button displayAllButton1; // Value injected by FXMLLoader

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
        LocalDate searchedDate = startTimePicker.getValue(); // Get date.
        //System.out.println(searchedDate.toString());
        LiveSessionDAO dao = new LiveSessionDAOImpl();
        LiveSession liveSession = new LiveSession();

        liveSession.setUserID(123);
        liveSession.setSearchedStartTime(searchedDate.toString()); // Transfer to String and use setter.

        ArrayList<String> searchedStartTimeResult = new ArrayList<String>();

        try {
            searchedStartTimeResult.addAll(dao.displayHistorySearchedStartTimeCoach(liveSession)); // Implement method.
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
            dataList.add(new HistoryDisplayData(coachNameTemp,"123",startTimeTemp,durationTemp,courseNameTemp));
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
        LiveSessionDAO dao = new LiveSessionDAOImpl();
        LiveSession liveSession = new LiveSession();

        liveSession.setUserID(123);

        ArrayList<String> searchedResult = new ArrayList<String>();

        try {
            searchedResult.addAll(dao.displayHistoryCoach(liveSession));
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

            dataList.add(new HistoryDisplayData(coachNameTemp, "123", startTimeTemp, durationTemp, courseNameTemp));
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
        LiveSessionDAO dao = new LiveSessionDAOImpl();
        LiveSession liveSession = new LiveSession();

        liveSession.setUserID(123);
        liveSession.setSearchedCoach(searchedContent.getText());

        ArrayList<String> searchedResult = new ArrayList<String>();

        try {
            searchedResult.addAll(dao.displayHistorySearchedSessionsCoach(liveSession));
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

            dataList.add(new HistoryDisplayData(coachNameTemp, "123", startTimeTemp, durationTemp, courseNameTemp));
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
    private ImageView coachImage;

    public void coachImageInit() throws IOException {

        Image image = new Image("/Image/1.JPG");
        coachImage.setImage(image);

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