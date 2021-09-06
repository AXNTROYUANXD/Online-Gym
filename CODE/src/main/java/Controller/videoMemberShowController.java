package Controller;

import DAO.impl.VideoDAOIml;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import vo.Video;

import java.io.IOException;

public class videoMemberShowController {

    @FXML
    private FlowPane videoShowPane;

    public void initialize() throws IOException {
            VideoDAOIml videoDAOIml = new VideoDAOIml();
            int i=0;
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
                control.getCollectImage().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (video.getCollect()){
                            control.getCollectImage().setImage(unCollectImage);
                            video.setCollect(false);
                            try {
                                videoDAOIml.JavaToJson(videoDAOIml.changeCollectState(video, finalI));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }else if (!video.getCollect()){
                            control.getCollectImage().setImage(collectImage);
                            video.setCollect(true);
                            try {
                                videoDAOIml.JavaToJson(videoDAOIml.changeCollectState(video, finalI));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                control.getVideoPhoto().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        videoDAOIml.ShowMediaVideo(video);
                    }
                });
                root.setStyle("-fx-border-color: black;");
                FlowPane.setMargin(root, new Insets(10));
                Image videoImage = new Image(video.getImageUrl());
                control.getVideoPhoto().setImage(videoImage);
                videoShowPane.getChildren().add(root);
                i++;
        }
    }



}
