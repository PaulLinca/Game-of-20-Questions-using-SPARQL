package ro.utcn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MainApplication extends Application
{
    public static Stage mainStage;

    public void start(Stage stage) throws Exception
    {
        mainStage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home_page.fxml"));
        Parent root = loader.load();
        mainStage.setTitle("20 Questions");
        mainStage.setScene(new Scene(root, 800, 600));
        mainStage.setResizable(false);
        mainStage.show();

        //Initialize something
        org.apache.log4j.BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.OFF);
    }
}
