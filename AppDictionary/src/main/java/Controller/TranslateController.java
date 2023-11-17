package Controller;

import Base.App.TransLateApi;
import Base.App.Voice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import Base.App.Language;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    String languageFrom = "en-us";
    String languageTo = "vi-vn";
    Voice intance = new Voice();
    @FXML
    private ComboBox BoxLanguageFrom;
    @FXML
    private ComboBox BoxLanguageTo;
    @FXML
    private TextField From;
    @FXML
    private TextField To;

    @FXML
    private TextArea area1;
    @FXML
    private TextArea area2;

    private TransLateApi translateApi = new TransLateApi();

    @FXML
    void translate() {
        if (!Objects.equals(area1.getText(), "")) {

            area2.setText(translateApi.translate(languageFrom, languageTo, area1.getText()));
        } else {
            System.out.println("ko co tu dich");
        }
    }

    @FXML
    void playArea1() {
        intance.Speak(area1.getText(), languageFrom);
    }

    @FXML
    void playArea2() {

        intance.Speak(area2.getText(), languageTo);
    }

    @FXML
    private void SellectLangFrom() {
        languageFrom = Language.langCode.get(BoxLanguageFrom.getValue());
        From.setText((String) BoxLanguageFrom.getValue());
    }

    @FXML
    private void SellectLangTo() {
        languageTo = Language.langCode.get(BoxLanguageTo.getValue());
        To.setText((String) BoxLanguageTo.getValue());
    }

    @FXML
    private void Swap() {
        String tmp = (String) BoxLanguageTo.getValue();
        BoxLanguageTo.setValue(BoxLanguageFrom.getValue());
        BoxLanguageFrom.setValue(tmp);
        SellectLangFrom();
        SellectLangTo();
        area1.setText(area2.getText());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<String> languages = new ArrayList<String>();
        for (String tmp : Language.langCode.keySet()) {
            languages.add(tmp);
        }

        ObservableList<String> ListLang = FXCollections.observableArrayList(languages);
        BoxLanguageFrom.setItems(ListLang);
        BoxLanguageFrom.setValue("English");
        BoxLanguageTo.setItems(ListLang);
        BoxLanguageTo.setValue("Vietnamese");

    }
}
