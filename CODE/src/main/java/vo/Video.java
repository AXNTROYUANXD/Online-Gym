package vo;

public class Video {

    String videoName;
    String coach;
    String category;
    String mark;
    String imageUrl;
    String videoUrl;
    boolean collectFlag;
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String url) {
        this.videoUrl = url;
    }



    @Override
    public String toString() {
        return "Video{" +
                "videoName='" + videoName + '\'' +
                ", coach='" + coach + '\'' +
                ", category='" + category + '\'' +
                ", mark='" + mark + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", collect='" +collectFlag+'\''+
                ", videoUrl='" + videoUrl + '\''+
                '}';
    }
    public Video(String videoName, String coach, String category, String mark,String imageUrl,String videoUrl, Boolean collectFlag) {
        this.videoName = videoName;
        this.coach = coach;
        this.category = category;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.mark = mark;
        this.collectFlag=collectFlag;
    }

    public Video(){

    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setCollect(boolean collect){
        this.collectFlag=collect;
    }
    public boolean getCollect(){
        return collectFlag;
    }

}
