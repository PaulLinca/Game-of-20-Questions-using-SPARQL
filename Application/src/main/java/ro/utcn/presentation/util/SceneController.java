package ro.utcn.presentation.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            MainApplication.mainStage.setScene(new Scene(root, 790, 590));
        }
        catch (IOException e)
        {
            System.out.println("Error while changing scenes.");
            e.printStackTrace();
        }
    }
}
