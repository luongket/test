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

public class DictionaryController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private AnchorPane addWord;

    @FXML
    private TextArea def;

    @FXML
    private ListView<String> listView;

    @FXML
    private WebView definitionView;

    @FXML
    private TextArea editArea;

    @FXML
    private AnchorPane editTab;

    @FXML
    private TextField eng;

    @FXML
    private Button search;

    @FXML
    private Button delete;

    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private Button save;

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;

    @FXML
    private Button canc;

    @FXML
    private Button note;

    private ArrayList<Word> wordList = DictionaryManagement.getInstance().selectAll("dictionary.dictionary");
    private ArrayList<Word> savedList = DictionaryManagement.getInstance().selectAll("dictionary.saveword");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editTab.setVisible(false);
        addWord.setVisible(false);
        note.setVisible(false);
        for (Word word : wordList) {
            listView.getItems().add(word.getWord());
        }
        listView.setOnMouseClicked(event -> {
            String selectedWord = listView.getSelectionModel().getSelectedItem();
            displayDefinition(selectedWord);
            note.setText("");
            note.setVisible(true);
            int k = Item.binarySearch(savedList, selectedWord);
            if (k == -1) {
                note.setText("Note");
            } else {
                note.setText("Noted");
            }
            note.setOnMouseClicked(e -> {
                int i = Item.binarySearch(savedList, selectedWord);
                if (i == -1) {
                    int j = Item.binarySearch(wordList, selectedWord);
                    Word word = wordList.get(j);
                    DictionaryManagement.insert("dictionary.saveword", word);
                    savedList.add(word);
                    Item.sort(savedList, 0, savedList.size() - 1);
                    note.setText("Noted");
                } else {
                    int j = Item.binarySearch(savedList, selectedWord);
                    DictionaryManagement.delete("dictionary.saveword", selectedWord);
                    savedList.remove(j);
                    note.setText("Note");
                }
            });
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

        for (Word word : wordList) {
            if (word.getWord().toLowerCase().startsWith(searchContent)) {
                listView.getItems().add(word.getWord());
            }
        }
    }

    private void displayDefinition(String word) {
        WebEngine webEngine = definitionView.getEngine();
        for (Word w : wordList) {
            if (w.getWord().equals(word)) {
                webEngine.loadContent(w.getDefinition());
                break;
            }
        }
    }

    private void Search() {
        String searchContent = searchField.getText().toLowerCase();
        listView.getItems().clear();
        int i = Item.binarySearch(wordList, searchContent);
        if (i != -1) {
            listView.getItems().add(wordList.get(i).getWord());
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
            DictionaryManagement.delete("dictionary.dictionary", selectedWord);
            int selectedId = listView.getSelectionModel().getSelectedIndex();
            int newSelectedId = (selectedId == listView.getItems().size() - 1) ? selectedId - 1 : selectedId;
            int i = Item.binarySearch(wordList, selectedWord);
            wordList.remove(i);
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
            int i = Item.binarySearch(wordList, selectedWord);
            Word word = wordList.get(i);
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
        } else if (Item.binarySearch(wordList, engWord) != -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Từ này đã tồn tại!");
            alert.showAndWait();
        } else {
            Word word = new Word(engWord, defWord);
            wordList.add(word);
            Item.sort(wordList, 0, wordList.size() - 1);
            DictionaryManagement.insert("dictionary.dictionary", word);
            addWord.setVisible(false);
            eng.clear();
            def.clear();
        }
    }

}
