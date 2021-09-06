package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class videoController {

    @FXML
    private Label VideoCategory;

    @FXML
    private Label VideoName;

    @FXML
    private Label VideoCoach;

    @FXML
    private Label VideoMark;

    @FXML
    private ImageView VideoPhoto;

    public Label getVideoCategory() {
        return VideoCategory;
    }

    public void setVideoCategory(String str) {
        VideoCategory.setText(str);
    }

    public Label getVideoName() {
        return VideoName;
    }

    public void setVideoName(String str) {
        VideoName.setText(str);
    }

    public Label getVideoCoach() {
        return VideoCoach;
    }

    public void setVideoCoach(String str) {
        VideoCoach.setText(str);
    }

    public Label getVideoMark() {
        return VideoMark;
    }

    public void setVideoMark(String str) {
        VideoMark.setText(str);
    }

    public ImageView getVideoPhoto(){return VideoPhoto;}

    public void setVideoPhoto(ImageView image){VideoPhoto=image;}



}

