package ro.utcn.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import ro.utcn.presentation.util.SceneController;

public class HomePage
{
    @FXML
    Button startButton;

    @FXML
    public void initialize()
    {
        startButton.setGraphic(new ImageView("start_button.png"));
        startButton.setPadding(Insets.EMPTY);
    }

    public void onStartButtonClicked(ActionEvent actionEvent)
    {
        System.out.println("Starting game...");

        SceneController.getInstance().changeScene("/question_page.fxml");
    }
}
