package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class questionController implements Initializable {
    @FXML
    private ToggleGroup Choice;

    @FXML
    private Label lblQuestion;

    @FXML
    private RadioButton rdoA;

    @FXML
    private RadioButton rdoB;

    @FXML
    private RadioButton rdoC;

    private List<Question> questions;
    private int currentQuestionIndex = 0;

    @FXML
    void checkYourAnswer(ActionEvent event) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        RadioButton selectedRadioButton = (RadioButton) Choice.getSelectedToggle();
        String selectedAnswer = selectedRadioButton.getText();

        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            // Correct answer
            System.out.println("Correct!");
            nextAnswer(event);
        } else {
            // Incorrect answer
            System.out.println("Incorrect!");
            nextAnswer(event);
        }
    }

    @FXML
    void nextAnswer(ActionEvent event) {
        currentQuestionIndex++;

        if (currentQuestionIndex < questions.size()) {
            showQuestion();
        } else {
            // Optionally, handle end of questions
            System.out.println("End of questions.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load questions from file
        try {
            questions = QuestionLoader.loadQuestions("C:\\OOP_IN2\\testMySQL\\src\\main\\java\\Controller\\question.txt");
            showQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Set question text
        lblQuestion.setText(currentQuestion.getQuestion());

        // Ensure that choices list is initialized and has at least 3 elements
        List<String> choices = currentQuestion.getChoices();

        if (choices != null && choices.size() >= 3) {
            // Set choices in your radio buttons
            rdoA.setText(choices.get(0));
            rdoB.setText(choices.get(1));
            rdoC.setText(choices.get(2));
        } else {
            // Handle the case where choices are not properly initialized
            System.err.println("Error: Choices list does not have enough elements.");
            // You may want to add default choices or handle this case in another way
        }
    }
}
