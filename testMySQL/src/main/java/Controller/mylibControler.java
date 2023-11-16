//package Controller;
//
//import dao.vocabularyDAO;
//import database.libralyData;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableCell;
//import javafx.scene.input.MouseEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.HBox;
//import javafx.stage.Stage;
//import model.testmysql.words;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Objects;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class mylibControler implements Initializable {
//    @FXML
//    private Stage stage;
//    private Stage addWordStage;
//    @FXML
//    private Scene scene;
//    @FXML
//    private Parent root;
//
//    @FXML
//    private TableColumn<words, String> editCol;
//
//    @FXML
//    private TableColumn<words, Integer> idCol;
//
//    @FXML
//    private TableColumn<words, String> meaningCol;
//
//    @FXML
//    private TableColumn<words, String> noteCol;
//
//    @FXML
//    private TableView<words> tableWord;
//
//    @FXML
//    private TableColumn<words, String> wordCol;
//
//    @FXML
//    private ObservableList<words> wordsList;
//
//    String query = null;
//    ResultSet resultSet = null ;
//    PreparedStatement preparedStatement = null ;
//    Connection connection = null ;
//
//    @FXML
//    public void switchToScene(MouseEvent event) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
//        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ArrayList<words> wordsArrayList = vocabularyDAO.getInstance().selectAll();
//        wordsList = FXCollections.observableArrayList();
//        for (words newWord : wordsArrayList) {
//            wordsList.add(new words(newWord.getId(), newWord.getWord(), newWord.getMeaning()
//                    , newWord.getNote()));
//        }
//        idCol.setCellValueFactory(new PropertyValueFactory<words, Integer>("id"));
//        wordCol.setCellValueFactory(new PropertyValueFactory<words, String>("word"));
//        meaningCol.setCellValueFactory(new PropertyValueFactory<words, String>("meaning"));
//        noteCol.setCellValueFactory(new PropertyValueFactory<words, String>("note"));
//        tableWord.setItems(wordsList);
//
//        // Tạo một cột tùy chỉnh để chứa nút "Edit" và "Delete"
//        editCol.setCellFactory(param -> new TableCell<words, String>() {
//            final Button editButton = new Button();
//            final Button deleteButton = new Button();
//
//            {
//                FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
//                editIcon.setStyle("-fx-cursor: hand ;" + "-glyph-size: 20px;" + "-fx-fill: #00E676;");
//                editButton.setGraphic(editIcon);
//
//                FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
//                deleteIcon.setStyle("-fx-cursor: hand ;" + "-glyph-size: 20px;" + "-fx-fill: #ff1744;");
//                deleteButton.setGraphic(deleteIcon);
//
//                editButton.setOnAction(event -> {
//                    words word = getTableView().getItems().get(getIndex());
//                    try {
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("addWord.fxml"));
//                        Parent root = loader.load();
//
//                        Stage editStage = new Stage();
//                        editStage.setTitle("Chỉnh sửa từ");
//                        editStage.setScene(new Scene(root));
//
//                        addWordController addWordController = loader.getController();
//                        addWordController.setWordToEdit(word);
//
//                        editStage.show();
//
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//
//                deleteButton.setOnAction(event -> {
//                    words word = getTableView().getItems().get(getIndex());
//                    vocabularyDAO.getInstance().delete(word);
//                    refreshTable();
//                });
//            }
//
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    HBox buttons = new HBox(editButton, deleteButton);
//                    setGraphic(buttons);
//                }
//            }
//        });
//    }
//
//    @FXML
//    private void refreshTable() {
//        try {
//            wordsList.clear();
//            connection = libralyData.getConnection();
//            query = "SELECT * FROM `vocabularys`";
//            preparedStatement = connection.prepareStatement(query);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                wordsList.add(new words(
//                    resultSet.getInt("id"),
//                    resultSet.getString("words"),
//                    resultSet.getString("meaning"),
//                    resultSet.getString("note")));
//                tableWord.setItems(wordsList);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);        }
//    }
//
//    @FXML
//    private void insertWord() {
//        if (addWordStage == null) {
//            try {
//                // Tạo một FXMLLoader để tải cửa sổ "Thêm từ mới"
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("addWord.fxml"));
//                Parent root = loader.load();
//
//                // Tạo một cửa sổ cho cửa sổ "Thêm từ mới"
//                addWordStage = new Stage();
//                addWordStage.setTitle("Thêm từ mới");
//                addWordStage.setScene(new Scene(root));
//
//                // Xử lý sự kiện đóng cửa sổ
//                addWordStage.setOnHidden(event -> {
//                    addWordStage = null; // Đặt biến cửa sổ thành null khi cửa sổ được đóng
//                });
//
//                // Hiển thị cửa sổ "Thêm từ mới"
//                addWordStage.showAndWait();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
