package Com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Controller.MemberPageController;

import java.io.IOException;

public class Member extends Application {
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Member/MemberPage.fxml"));
        Scene scene= new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Member Page");
        stage.setScene(scene);

        //MemberPageController controller=new MemberPageController();
        //controller.Init();
        stage.show();
    }
}

