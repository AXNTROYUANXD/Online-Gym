package Controller;

import Com.Coach;
import Com.Member;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class FrontPageController {

    @FXML
    private Button CoachButton;

    @FXML
    private Button MemberButton;


    @FXML
    void ClickMemberButton(ActionEvent event) {

    }
    @FXML
    public void ClickCoachButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Platform.runLater(()-> {
            Stage primaryStage = (Stage) CoachButton.getScene().getWindow();
            primaryStage.hide();
                try {
                    new Coach().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }

    public void ClickMemberButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Platform.runLater(()-> {
            Stage primaryStage = (Stage) MemberButton.getScene().getWindow();
            primaryStage.hide();

                try {
                    new Member().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } );
    }
}


