package miles;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import miles.ui.MainWindow;

/**
 * A GUI for Miles using FXML.
 */
public class Main extends Application {
    private Miles miles = new Miles("./data/miles.txt");

    @Override
    public void start(Stage stage) {
        stage.setTitle("Miles");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMiles(miles);
            // render the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
