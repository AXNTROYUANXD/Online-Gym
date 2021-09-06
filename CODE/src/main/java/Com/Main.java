package Com;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Frontpage.fxml"));
        Scene scene= new Scene(root);
        stage.setTitle("Front Page");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
