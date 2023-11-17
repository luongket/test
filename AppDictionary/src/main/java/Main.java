import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load((getClass().getResource("View/main.fxml")));
        Scene scene = new Scene(root, 876, 600);
        stage.setTitle("Dirictory");
        stage.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("CSS.css")).toExternalForm());
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}