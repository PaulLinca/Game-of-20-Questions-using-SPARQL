package ro.utcn.presentation;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
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
    public ProgressIndicator loadingIndicator;

    @FXML
    public void initialize()
    {
        questionNumberLabel.setText(String.valueOf("Question " + questionNumber));
        questionLabel.setText(question);

        yesButton.setGraphic(new ImageView("yes_button.png"));
        yesButton.setPadding(Insets.EMPTY);
        noButton.setGraphic(new ImageView("no_button.png"));
        noButton.setPadding(Insets.EMPTY);
    }

    public void onYesButtonClicked(ActionEvent actionEvent)
    {
        System.out.println("Yes");

        setControlEnabled(true);
        Task task = new Task<Void>() {
            @Override
            public Void call()
            {
                Game.getInstance().generateNextQuestion(true);
                return null;
            }
        };
        task.setOnSucceeded(taskFinishEvent -> {
            goToNextQuestion();
            setControlEnabled(false);
        });
        new Thread(task).start();
    }

    public void onNoButtonClicked(ActionEvent actionEvent)
    {
        System.out.println("No");

        setControlEnabled(true);
        Task task = new Task<Void>() {
            @Override
            public Void call()
            {
                Game.getInstance().generateNextQuestion(false);
                return null;
            }
        };
        task.setOnSucceeded(taskFinishEvent -> {
            goToNextQuestion();
            setControlEnabled(false);
        });
        new Thread(task).start();
    }

    public void goToNextQuestion()
    {
        if(Game.getInstance().isGameFinished)
        {
            SceneController.getInstance().changeScene("/game_over_page.fxml");
        }
        else
        {
            SceneController.getInstance().changeScene("/question_page.fxml");
        }
    }

    private void setControlEnabled(boolean shouldBeEnabled)
    {
        loadingIndicator.setVisible(shouldBeEnabled);
        yesButton.setDisable(shouldBeEnabled);
        noButton.setDisable(shouldBeEnabled);
    }
}
