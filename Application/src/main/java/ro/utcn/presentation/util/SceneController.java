package ro.utcn.presentation.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import ro.utcn.MainApplication;

import java.io.IOException;

public class SceneController
{
    private static SceneController instance = null;

    private SceneController()
    {
    }

    public static SceneController getInstance()
    {
        if (instance == null)
        {
            instance = new SceneController();
        }

        return instance;
    }

    public void changeScene(String fxml)
    {
        try
        {
            MainApplication.mainStage.setScene(new Scene(FXMLLoader.load(getClass().getResource( fxml ))));
        }
        catch (IOException e)
        {
            System.out.println("Error while changing scenes.");
            e.printStackTrace();
        }
    }
}
