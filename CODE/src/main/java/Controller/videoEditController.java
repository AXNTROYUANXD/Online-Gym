package Controller;

import DAO.VideoDAO;
import DAO.impl.VideoDAOIml;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vo.Video;

import java.io.*;

public class videoEditController {



    @FXML
    private TableColumn<?, ?> VideoNameCol;

    @FXML
    private TableColumn<?, ?> CategoryCol;

    @FXML
    private TableColumn<?, ?> CoachCol;

    @FXML
    private TableColumn<?, ?> MarkCol;

    @FXML
    private TableView<Video> table;

    @FXML
    private Button CoverButton;

    @FXML
    private Button VideoButton;

    @FXML
    private ImageView CoverImage;

    @FXML
    private Button AddButton;

    @FXML
    private TextField ImageUrlText;

    @FXML
    private TextField MarkText;

    @FXML
    private TextField VideoNameText;

    @FXML
    private TextField CategoryText;

    @FXML
    private TextField CoachText;

    @FXML
    private TextField VideoUrlText;

    @FXML
    private Button DeleteButton;




    @FXML
    public void initialize() throws IOException {
        LoadData();
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Video>() {
            @Override
            public void changed(ObservableValue<? extends Video> observable, Video oldValue, Video newValue) {
            DeleteButton.setDisable(false);
            System.out.println(table.getSelectionModel().getSelectedItem());
            System.out.println(table.getSelectionModel().getSelectedIndex());
            }
        });
        table.setTableMenuButtonVisible(true);
    }

    public void ShowPaneRefresh() throws IOException {
        FXMLLoader CoachFxmlLoader = new FXMLLoader(getClass().getResource("/FXML/video/videoShow.fxml"));
        Parent root = CoachFxmlLoader.load();
        videoShowController control = (videoShowController) CoachFxmlLoader.getController();
        control.videoShowRemove();
        control.videoShowInit();
    }
    public void LoadData() throws IOException {
        VideoDAO videoDAO=new VideoDAOIml();
        final ObservableList<Video> data = FXCollections.observableArrayList(videoDAO.GetVideosObject());
        VideoNameCol.setCellValueFactory(new PropertyValueFactory<>("videoName"));
        CoachCol.setCellValueFactory(new PropertyValueFactory<>("coach"));
        CategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        MarkCol.setCellValueFactory(new PropertyValueFactory<>("mark"));
        CoverImage.setImage(null);
        VideoNameText.setText(null);
        CoachText.setText(null);
        CategoryText.setText(null);
        MarkText.setText(null);
        ImageUrlText.setText(null);
        VideoUrlText.setText(null);
        table.setItems(data);

        DeleteButton.setDisable(true);
    }

    @FXML
    void ChooseCover(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your cover");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File fileChoose = fileChooser.showOpenDialog(stage);
        if (fileChoose != null) {
            Image image = new Image("file:///" + fileChoose.getAbsolutePath());
            CoverImage.setImage(image);
            String ImagePackage ="src/main/resources/Image/"+fileChoose.getName();
            ImageUrlText.setText("/Image/"+fileChoose.getName());
            VideoDAO dao=new VideoDAOIml();
            dao.CopyFile(fileChoose.getAbsolutePath(),ImagePackage);
        }
    }

    @FXML
    void ChooseVideo(ActionEvent event) throws IOException{
        Stage stage=new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your cover");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac", "*.mp4"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File fileChoose = fileChooser.showOpenDialog(stage);
        if (fileChoose!=null){
            String VideoPackage="src/main/resources/Media/"+fileChoose.getName();
            VideoUrlText.setText("/Media/"+fileChoose.getName());
            VideoDAO dao=new VideoDAOIml();
            dao.CopyFile(fileChoose.getAbsolutePath(),VideoPackage);
        }
    }


    @FXML
    void AddVideo(ActionEvent event) throws IOException {

        Video video = new Video();
        video.setVideoName(VideoNameText.getText());
        video.setCoach(CoachText.getText());
        video.setCategory(CategoryText.getText());
        video.setMark(MarkText.getText());
        video.setImageUrl(ImageUrlText.getText());
        video.setVideoUrl(VideoUrlText.getText());
        video.setCollect(false);
        VideoDAO videoDAO = new VideoDAOIml();
        videoDAO.JavaToJson(videoDAO.addToVideoFileObject(video));
        LoadData();//Refresh
        //ShowPaneRefresh();

    }


    @FXML
    void DeleteVideo(ActionEvent event) throws IOException {
        VideoDAO dao=new VideoDAOIml();
        dao.JavaToJson(dao.deleteVideoFileObject(table.getSelectionModel().getSelectedItem(),table.getSelectionModel().getSelectedIndex()));
        LoadData();
        ShowPaneRefresh();
    }

}






