package Controller;

import Base.Item;
import Base.Word;
import database.DictionaryManagement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class NoteController implements Initializable {
    @FXML
    private Button add;

    @FXML
    private Button cancel;

    @FXML
    private AnchorPane addWord;

    @FXML
    private TextArea editArea;

    @FXML
    private TextField searchField;

    @FXML
    private TextArea def;

    @FXML
    private Button edit;

    @FXML
    private Button save;

    @FXML
    private Button delete;

    @FXML
    private Button confirm;

    @FXML
    private Button search;

    @FXML
    private ListView<String> listView;

    @FXML
    private WebView definitionView;

    @FXML
    private AnchorPane editTab;

    @FXML
    private Button canc;

    @FXML
    private TextField eng;

    private ArrayList<Word> savedList = DictionaryManagement.getInstance().selectAll("dictionary.saveword");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editTab.setVisible(false);
        addWord.setVisible(false);

        for (Word word : savedList) {
            listView.getItems().add(word.getWord());
        }

        listView.setOnMouseClicked(event -> {
            String selectedWord = listView.getSelectionModel().getSelectedItem();
            displayDefinition(selectedWord);
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateListView();
        });

        search.setOnMouseClicked(event -> {
            Search();
        });

        delete.setOnMouseClicked(event -> {
            String selectedWord = listView.getSelectionModel().getSelectedItem();
            Delete(selectedWord);
        });

        edit.setOnMouseClicked(event -> {
            String selectedWord = listView.getSelectionModel().getSelectedItem();
            Edit(selectedWord);
        });

        save.setOnMouseClicked(event -> {
            Save();
        });

        cancel.setOnMouseClicked(event -> {
            editTab.setVisible(false);
            editArea.clear();
        });

        add.setOnMouseClicked(event -> {
            addWord.setVisible(true);
        });

        canc.setOnMouseClicked(event -> {
            addWord.setVisible(false);
            eng.clear();
            def.clear();
        });
        confirm.setOnMouseClicked(event -> {
            Confirm();
        });
    }

    private void updateListView() {
        String searchContent = searchField.getText().toLowerCase();
        listView.getItems().clear();

        for (Word word : savedList) {
            if (word.getWord().toLowerCase().startsWith(searchContent)) {
                listView.getItems().add(word.getWord());
            }
        }
    }

    private void displayDefinition(String word) {
        WebEngine webEngine = definitionView.getEngine();
        for (Word w : savedList) {
            if (w.getWord().equals(word)) {
                webEngine.loadContent(w.getDefinition());
                break;
            }
        }
    }

    private void Search() {
        String searchContent = searchField.getText().toLowerCase();
        listView.getItems().clear();
        int i = Item.binarySearch(savedList, searchContent);
        if (i != -1) {
            listView.getItems().add(savedList.get(i).getWord());
            displayDefinition(searchContent);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Không tìm thấy từ đã nhập!");
            alert.showAndWait();
        }
    }

    private void Delete(String selectedWord) {
        if (selectedWord != null) {
            DictionaryManagement.delete("dictionary.saveword", selectedWord);
            int selectedId = listView.getSelectionModel().getSelectedIndex();
            int newSelectedId = (selectedId == listView.getItems().size() - 1) ? selectedId - 1 : selectedId;
            int i = Item.binarySearch(savedList, selectedWord);
            savedList.remove(i);
            listView.getItems().remove(selectedId);
            listView.getSelectionModel().select(newSelectedId);
            if (newSelectedId != -1) {
                String newSelected = listView.getSelectionModel().getSelectedItem();
                displayDefinition(newSelected);
            } else {
                displayDefinition(null);
            }
        }
    }

    private void Edit(String selectedWord) {
        if (selectedWord != null) {
            editTab.setVisible(true);
        }
    }

    private void Save() {
        String descriptionEdited = editArea.getText().toLowerCase().trim();
        if (!descriptionEdited.equals("")) {
            String selectedWord = listView.getSelectionModel().getSelectedItem();
            int i = Item.binarySearch(savedList, selectedWord);
            Word word = savedList.get(i);
            word.setDefinition("<html> " + descriptionEdited + " <html>");
            DictionaryManagement.update(word);
            editTab.setVisible(false);
            displayDefinition(selectedWord);
            editArea.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập chưa đủ dữ liệu!");
            alert.showAndWait();
        }
    }

    private void Confirm() {
        String engWord = eng.getText().toLowerCase().trim();
        String defWord = "<html> " + def.getText().toLowerCase().trim() + " <html>";
        if (engWord.equals("") || defWord.equals("<html>  <html>")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập chưa đủ dữ liệu!");
            alert.showAndWait();
        } else if (Item.binarySearch(savedList, engWord) != -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Từ này đã tồn tại!");
            alert.showAndWait();
        } else {
            Word word = new Word(engWord, defWord);
            savedList.add(word);
            Item.sort(savedList, 0, savedList.size() - 1);
            DictionaryManagement.insert("dictionary.saveword", word);
            updateListView();
            addWord.setVisible(false);
            eng.clear();
            def.clear();
        }
    }
}
