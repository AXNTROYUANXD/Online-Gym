package DAO.impl;

import Controller.videoController;
import Controller.videoMarkController;
import DAO.VideoDAO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import vo.Video;
import vo.VideoFile;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class VideoDAOIml implements VideoDAO {

    @Override
    public ArrayList<Video> GetVideosObject() throws IOException {
        String jsonString = GetJsonString();
        VideoFile videoFile = JSON.parseObject(jsonString, VideoFile.class);
        System.out.println();
        ArrayList<Video> videos = new ArrayList<Video>();

        for (Video video : videoFile.getData()) {
            videos.add(video);
        }
        return videos;
    }


    public VideoFile addToVideoFileObject(Video video) throws IOException {
        String jsonString = GetJsonString();
        VideoFile videoFile = JSON.parseObject(jsonString, VideoFile.class);
        videoFile.getData().add(video);
        return videoFile;
    }

    public VideoFile deleteVideoFileObject(Video video, int index) throws IOException {
        String jsonString = GetJsonString();
        VideoFile videoFile = JSON.parseObject(jsonString, VideoFile.class);
        videoFile.getData().remove(index);
        File ImageFile = new File("src/main/resources" + video.getImageUrl());
        File MediaFile = new File("src/main/resources" + video.getVideoUrl());
        MediaFile.delete();
        ImageFile.delete();
        return videoFile;
    }

    public VideoFile changeCollectState(Video video, int index) throws IOException {
        String jsonString = GetJsonString();
        VideoFile videoFile = JSON.parseObject(jsonString, VideoFile.class);
        videoFile.getData().get(index).setCollect(video.getCollect());
        return videoFile;
    }


    @Override
    public int GetVideosNum() throws IOException {
        String jsonString = GetJsonString();
        VideoFile videoFile = JSON.parseObject(jsonString, VideoFile.class);
        return videoFile.getNum();
    }

    @Override
    public void JavaToJson(VideoFile addVideo) throws IOException {
        String jsonStr = JSON.toJSONString(addVideo);
        System.out.println(jsonStr);
        FileUtils.writeStringToFile(new File("src/main/resources/JSON/videoData.json"), jsonStr, "UTF-8");

    }

    @Override
    public String GetJsonString() throws IOException {
        String result = null;
        result = FileUtils.readFileToString(new File("src/main/resources/JSON/videoData.json"), "UTF-8");
        return result;
    }

    public void CopyFile(String ori, String des) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(ori);
        FileOutputStream outputStream = new FileOutputStream(des);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        outputStream.close();
        fileInputStream.close();
    }

    public void ShowMediaVideo(Video video){
        Stage stage = new Stage();
        URL url = this.getClass().getResource(video.getVideoUrl());
        //??????????????????
        Media media=new Media(url.toExternalForm());
        //?????????????????????
        MediaPlayer mPlayer=new MediaPlayer(media);
        //????????????????????????
        MediaView mView=new MediaView(mPlayer);
        //??????????????????????????????????????????
        mView.setFitWidth(1350);
        mView.setFitHeight(720);
        //????????????
        Button pBut=new Button(">");
        //??????????????????????????????
        pBut.setOnAction(e->
        {
            if(pBut.getText().equals(">"))
            {
                //??????????????????????????????????????????
                mPlayer.play();
                pBut.setText("||");
            }
            else
            {
                //??????????????????????????????????????????
                mPlayer.pause();
                pBut.setText(">");
            }
        });
        //????????????????????????
        Button rBut=new Button("<<");
        //?????????????????????
        rBut.setOnAction(e->mPlayer.seek(Duration.ZERO));
        //???????????????
        Slider sVol=new Slider();
        //?????????????????????????????????????????????
        sVol.setMinWidth(30);
        sVol.setPrefWidth(150);
        sVol.setValue(50);
        //??????????????????100??????????????????????????????????????????????????????????????????????????????
        mPlayer.volumeProperty().bind(sVol.valueProperty().divide(100));
        HBox hB=new HBox(10);
        hB.setAlignment(Pos.CENTER);
        Label vol=new Label("??????");
        hB.getChildren().addAll(pBut,rBut,vol,sVol);
        BorderPane bPane=new BorderPane();
        bPane.setCenter(mView);
        bPane.setBottom(hB);
        Scene scene=new Scene(bPane);
        stage.setTitle("MediaPlayer");
        stage.setScene(scene);
        stage.show();
    }
}


