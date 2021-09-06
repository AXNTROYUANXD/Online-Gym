package Com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vo.Video;

public class Coach extends Application {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Coach/Coach.fxml"));
        Scene scene= new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Coach Page");
        stage.setScene(scene);
        stage.show();}


}

