package vo;

import javafx.beans.property.SimpleStringProperty;

public class VideoDisplayData {
    private final SimpleStringProperty videoName;



    private final SimpleStringProperty coach;
    private final SimpleStringProperty category;
    private final SimpleStringProperty mark;

    public VideoDisplayData(SimpleStringProperty videoName, SimpleStringProperty coach, SimpleStringProperty category, SimpleStringProperty mark) {
        this.videoName = videoName;
        this.coach = coach;
        this.category = category;
        this.mark = mark;
    }

    public String getVideoName() {
        return videoName.get();
    }

    public SimpleStringProperty videoNameProperty() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName.set(videoName);
    }

    public String getCoach() {
        return coach.get();
    }

    public SimpleStringProperty coachProperty() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach.set(coach);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getMark() {
        return mark.get();
    }

    public SimpleStringProperty markProperty() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark.set(mark);
    }
}
