package ro.utcn.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ro.utcn.data.Game;
import ro.utcn.presentation.util.SceneController;

public class QuestionPage
{
    public String question = Game.getInstance().currentQuestion;
    public int questionNumber = Game.getInstance().currentQuestionNumber;

    @FXML
    public Button yesButton;
    @FXML
    public Button noButton;
    @FXML
    public Label questionLabel;
    @FXML
    public Label questionNumberLabel;

    @FXML
    public void initialize()
    {
        questionLabel.setText(question);
        questionNumberLabel.setText(String.valueOf(questionNumber));
    }

    public void onYesButtonClicked(ActionEvent actionEvent)
    {
        System.out.println("Yes");
        //TODO: Interpreteaza raspunsul
        Game.getInstance().generateNextQuestion();
        goToNextQuestion();
    }

    public void onNoButtonClicked(ActionEvent actionEvent)
    {
        System.out.println("No");
        //TODO: Interpreteaza raspunsul
        Game.getInstance().generateNextQuestion();
        goToNextQuestion();
    }

    public void goToNextQuestion()
    {
        SceneController.getInstance().changeScene("/question_page.fxml");
    }
}
