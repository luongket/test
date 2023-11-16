package Controller;

import dao.dictionaryDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.testmysql.word;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class searchViewController implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField searchBar;

    @FXML
    private WebView definitionView;

    @FXML
    private FontAwesomeIconView clickSave;

    private ArrayList<word> wordList;

    @FXML
    void switchSceneMenu(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void updateListView() {
        String searchContent = searchBar.getText().toLowerCase(); // Lấy nội dung từ searchBar và chuyển thành chữ thường
        listView.getItems().clear(); // Xóa danh sách hiện tại

        for (word word : wordList) {
            if (word.getWord().toLowerCase().startsWith(searchContent)) {
                listView.getItems().add(word.getWord());
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Khởi tạo danh sách từ từ cơ sở dữ liệu
        wordList = dictionaryDAO.getInstance().selectAll();

        // Hiển thị danh sách từ trong ListView
        for (word word : wordList) {
            listView.getItems().add(word.getWord());
        }

        // Thiết lập sự kiện lắng nghe khi chọn một từ từ danh sách
        listView.setOnMouseClicked(event -> {
            String selectedWord = listView.getSelectionModel().getSelectedItem();
            displayDefinition(selectedWord);
        });

        // Sự kiện lắng nghe khi nội dung searchBar thay đổi
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            updateListView();
        });
    }

    // Hiển thị nghĩa của từ trong WebView
    private void displayDefinition(String word) {
        WebEngine webEngine = definitionView.getEngine();
        // Tìm từ trong danh sách
        for (word w : wordList) {
            if (w.getWord().equals(word)) {
                webEngine.loadContent(w.getDefinition());
                break;
            }
        }
    }
}
