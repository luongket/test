//package Controller;
//
//import dao.vocabularyDAO;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//import model.testmysql.words;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class addWordController implements Initializable {
//    @FXML
//    private TextField idText;
//
//    @FXML
//    private TextField meaningText;
//
//    @FXML
//    private TextField noteText;
//
//    @FXML
//    private TextField wordText;
//
//    @FXML
//    void clean() {
//        idText.clear();
//        wordText.clear();
//        meaningText.clear();
//        noteText.clear();
//    }
//
//    @FXML
//    void save(ActionEvent event) {
//        int id = Integer.parseInt(idText.getText());
//        String word = wordText.getText();
//        String meaning = meaningText.getText();
//        String note = noteText.getText();
//        words wordsObj = new words(id, word, meaning, note);
//        if(vocabularyDAO.getInstance().seclectById(wordsObj) == null) {
//            vocabularyDAO.getInstance().insert(wordsObj);
//        } else {
//            vocabularyDAO.getInstance().update(wordsObj);
//        }
//        clean();
//    }
//    public void setWordToEdit(words word) {
//        idText.setText(String.valueOf(word.getId()));
//        wordText.setText(word.getWord());
//        meaningText.setText(word.getMeaning());
//        noteText.setText(word.getNote());
//    }
//
//    @FXML
//    public void close(MouseEvent event) {
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.close();
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }
//
//}
