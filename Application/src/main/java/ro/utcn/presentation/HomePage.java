package ro.utcn.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomePage
{
    @FXML
    Button startButton;

    public void onStartButtonClicked(ActionEvent actionEvent)
    {
        System.out.println("Starting game...");
    }
}
