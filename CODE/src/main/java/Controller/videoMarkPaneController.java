package Controller;

import Com.Coach;
import DAO.VideoDAO;
import DAO.impl.VideoDAOIml;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import vo.Video;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class videoMarkPaneController {

    @FXML
    private FlowPane VideoMarkPane;



    public void initialize() throws IOException {
        int i = 0;
        VideoDAOIml videoDAOIml = new VideoDAOIml();
        VideoMarkPane.getChildren().clear();
        for (Video video : videoDAOIml.GetVideosObject()) {
            FXMLLoader videoFxmlLoader = new FXMLLoader(getClass().getResource("/FXML/video/videoWithMark.fxml"));
            Parent root = videoFxmlLoader.load();
            videoMarkController control = (videoMarkController) videoFxmlLoader.getController();
            control.setVideoCoach(video.getCoach());
            control.setVideoCategory(video.getCategory());
            control.setVideoMark(video.getMark());
            control.setVideoName(video.getVideoName());
            Image collectImage = new Image("/Image/Button/marked.png");
            Image unCollectImage = new Image("/Image/Button/Unmarked.png");
            if (video.getCollect()==false) {
                control.getCollectImage().setImage(unCollectImage);
            }else if (video.getCollect()==true){
                control.getCollectImage().setImage(collectImage);
            }
            int finalI = i;

            //Collection
            control.getCollectImage().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
//                        control.getCollectImage().setImage(unCollectImage);
                        video.setCollect(false);
                        try {
                            videoDAOIml.JavaToJson(videoDAOIml.changeCollectState(video, finalI));
                            System.out.println();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    try {
                        initialize();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });


            root.setStyle("-fx-border-color: black;");
            FlowPane.setMargin(root, new Insets(10));
            Image videoImage = new Image(video.getImageUrl());
            control.getVideoPhoto().setImage(videoImage);

            //MediaView
            control.getVideoPhoto().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    videoDAOIml.ShowMediaVideo(video);
                }
            });

            if (video.getCollect()==true) {
                VideoMarkPane.getChildren().add(root);
            }
        i++;
        }
    }
}
