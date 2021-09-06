package DAO;

import javafx.scene.Parent;
import vo.Video;
import vo.VideoFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public interface VideoDAO {
    public ArrayList<Video> GetVideosObject() throws IOException;
    public int GetVideosNum() throws IOException;


    void JavaToJson(VideoFile addVideo) throws IOException;

    public String GetJsonString() throws IOException;
    public VideoFile addToVideoFileObject(Video video) throws IOException;
    public void CopyFile(String ori,String des) throws IOException;
    public VideoFile deleteVideoFileObject(Video video,int index) throws IOException;
}
