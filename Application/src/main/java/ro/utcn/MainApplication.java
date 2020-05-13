package ro.utcn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MainApplication extends Application {
    public static Stage mainStage;

    public void start(Stage stage) throws Exception {
        mainStage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home_page.fxml"));
        Parent root = loader.load();
        mainStage.setTitle("20 Questions");
        mainStage.setScene(new Scene(root, 790, 590));
        mainStage.setResizable(false);
        mainStage.show();

        //Initialize something
        org.apache.log4j.BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.OFF);

        String queryString =
                "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                        "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
                        "PREFIX dbp: <http://dbpedia.org/property/>\n" +
                        "ask where {dbr:%s dbp:publisher dbr:%s}\n" ;
        //Set values in query
        queryString = String.format(queryString, "Iron_Man", "Marvel_Comics");
        //Execute the query
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", queryString);
        System.out.println(queryExecution.execAsk());
    }
}
