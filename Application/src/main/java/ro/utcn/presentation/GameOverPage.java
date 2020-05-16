package ro.utcn.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ro.utcn.data.Game;

public class GameOverPage
{
    @FXML
    public Label infoLabel;

    @FXML
    public void initialize()
    {
        infoLabel.setText(Game.getInstance().finalGuess);
    }
}
