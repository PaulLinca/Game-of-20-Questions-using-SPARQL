package ro.utcn.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ro.utcn.data.Game;
import ro.utcn.presentation.util.SceneController;

public class GameOverPage
{
    @FXML
    public Label infoLabel;
    @FXML
    public Label guessLabel;
    @FXML
    public Button homeButton;

    @FXML
    public void initialize()
    {
        homeButton.setGraphic(new ImageView("home_button.png"));
        homeButton.setPadding(Insets.EMPTY);

        infoLabel.setText("The character is");
        guessLabel.setText(Game.getInstance().finalGuess);
    }

    public void onHomeButtonClicked(ActionEvent actionEvent)
    {
        Game.reset();
        SceneController.getInstance().changeScene("/home_page.fxml");
    }
}
