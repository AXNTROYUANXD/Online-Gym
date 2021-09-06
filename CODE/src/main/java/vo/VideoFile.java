package vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

public class VideoFile {
    private ArrayList<Video> data;

    public ArrayList<Video> getData() {
        return data;
    }

    public void setData(ArrayList<Video> data) {
        this.data = data;
    }

    @JSONField(serialize = false, deserialize = false)
    public int getNum(){
        return data.size();
    }

}
