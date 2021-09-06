package vo;

import javafx.beans.property.SimpleStringProperty;



public class HistoryDisplayData {
    private SimpleStringProperty coachName;
    private SimpleStringProperty coachID;
    private SimpleStringProperty startTime;
    private SimpleStringProperty duration;
    private SimpleStringProperty courseName;

    public HistoryDisplayData(String coachName, String coachID, String startTime, String duration, String courseName) {
        this.coachName = new SimpleStringProperty(coachName);
        this.coachID = new SimpleStringProperty(coachID);
        this.startTime = new SimpleStringProperty(startTime);
        this.duration = new SimpleStringProperty(duration);
        this.courseName = new SimpleStringProperty(courseName);
    }

    public String getCoachName() {
        return coachName.get();
    }

    public SimpleStringProperty coachNameProperty() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName.set(coachName);
    }

    public String getCoachID() {
        return coachID.get();
    }

    public SimpleStringProperty coachIDProperty() {
        return coachID;
    }

    public void setCoachID(String coachID) {
        this.coachID.set(coachID);
    }

    public String getStartTime() {
        return startTime.get();
    }

    public SimpleStringProperty startTimeProperty() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public String getDuration() {
        return duration.get();
    }

    public SimpleStringProperty durationProperty() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public String getCourseName() {
        return courseName.get();
    }

    public SimpleStringProperty courseNameProperty() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }
}