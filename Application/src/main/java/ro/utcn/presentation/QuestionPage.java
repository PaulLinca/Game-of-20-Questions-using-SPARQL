package ro.utcn.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class QuestionPage
{
    @FXML
    public Button yesButton;
    @FXML
    public Button noButton;
    @FXML
    public Label questionLabel;

    public void onYesButtonClicked(ActionEvent actionEvent)
    {
        System.out.println("Yes");
    }

    public void onNoButtonClicked(ActionEvent actionEvent)
    {
        System.out.println("No");
    }
}
