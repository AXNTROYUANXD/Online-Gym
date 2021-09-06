package Controller;

import DAO.impl.VideoDAOIml;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import vo.Video;

import java.io.IOException;

public class videoShowController {

    @FXML
    private FlowPane videoShowPane;

    public void initialize() throws IOException {
        videoShowRemove();
        videoShowInit();
    }


    public void videoShowInit() throws IOException {
        VideoDAOIml videoDAOIml = new VideoDAOIml();
        for (Video video : videoDAOIml.GetVideosObject()) {
            FXMLLoader videoFxmlLoader = new FXMLLoader(getClass().getResource("/FXML/video/video.fxml"));
            Parent root = videoFxmlLoader.load();
            videoController control = (videoController) videoFxmlLoader.getController();
            control.setVideoCoach(video.getCoach());
            control.setVideoCategory(video.getCategory());
            control.setVideoMark(video.getMark());
            control.setVideoName(video.getVideoName());
            root.setStyle("-fx-border-color: black;");
            FlowPane.setMargin(root, new Insets(10));
            control.getVideoPhoto().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    videoDAOIml.ShowMediaVideo(video);
                }
            });
            Image image = new Image(video.getImageUrl());
            control.getVideoPhoto().setImage(image);
            videoShowPane.getChildren().add(root);
        }
    }
    public void videoShowRemove(){
        videoShowPane.getChildren().removeAll();
    }

}
