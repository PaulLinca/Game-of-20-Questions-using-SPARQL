package ro.utcn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application
{
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home_page.fxml"));
        Parent root = loader.load();
        stage.setTitle("20 Questions");
        stage.setScene(new Scene(root, 800, 600));
        stage.setResizable(false);
        stage.show();
    }
}
