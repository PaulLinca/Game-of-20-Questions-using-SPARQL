package ro.utcn.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ro.utcn.presentation.util.SceneController;

public class HomePage
{
    @FXML
    Button startButton;

    public void onStartButtonClicked(ActionEvent actionEvent)
    {
        System.out.println("Starting game...");

        SceneController.getInstance().changeScene("/question_page.fxml");
    }
}
