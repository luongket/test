package Controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    private ImageView dictionary;

    @FXML
    private ImageView home;

    @FXML
    private ImageView stadiaController;

    @FXML
    private ImageView note;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private ImageView menu;

    @FXML
    private AnchorPane sliderNav;

    private boolean flip = true;

    private void setMainContent(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            AnchorPane component = (AnchorPane) loader.load();
            mainContent.getChildren().clear();
            mainContent.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showHome();

        menu.setOnMouseClicked(event -> {
            flip = !flip;
            TranslateTransition anim = new TranslateTransition(Duration.seconds(0.5), sliderNav);
            if (!flip) {
                anim.setToX(-200);
                anim.play();
            } else {
                anim.setToX(0);
                anim.play();
            }
        });
        mainContent.setOnMouseClicked(event -> {
            flip = false;
            TranslateTransition anim = new TranslateTransition(Duration.seconds(0.5), sliderNav);
            anim.setToX(-200);
            anim.play();
        });


        stadiaController.setOnMouseClicked(event -> {
            showGameView();
        });

        dictionary.setOnMouseClicked(event -> {
            showDictionary();
        });
        home.setOnMouseClicked(event -> {
            showHome();
        });
        note.setOnMouseClicked(event -> {
            showNoteView();
        });
    }

    @FXML
    public void showGameView() {
        setMainContent("/View/Game.fxml");
    }

    @FXML
    public void showDictionary() {
        setMainContent("/View/Dictionary.fxml");
    }

    @FXML
    public void showHome() {
        setMainContent("/View/translateView.fxml");
    }

    @FXML
    public void showNoteView() {
        setMainContent("/View/Note.fxml");
    }
}
