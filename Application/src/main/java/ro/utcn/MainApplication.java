package ro.utcn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MainApplication extends Application {
    public static Stage mainStage;

    public void start(Stage stage) throws Exception {
        mainStage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home_page.fxml"));
        Parent root = loader.load();
        mainStage.setTitle("20 Questions");
        mainStage.setScene(new Scene(root, 800, 600));
        mainStage.setResizable(false);
        mainStage.show();

        //Queries
        org.apache.log4j.BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.OFF);

        //Query pentru comicbook characters
        String queryString1 =
                "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                        "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
                        "PREFIX dbp: <http://dbpedia.org/property/>\n" +
                        "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                        "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                        "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n" +
                        "SELECT DISTINCT (STR(?characterLabel) as ?characterName)\n" +
                        "WHERE {\n" +
                        "     ?character dbp:type \"character\"^^rdf:langString.\n" +
                        "     ?character rdf:type dbo:ComicsCharacter." +
                        "     ?character foaf:gender ?gender.\n" +
                        "     ?character rdfs:label ?characterLabel.\n" +
                        "     ?character dbp:publisher ?publisher.\n" +
                        "     ?publisher rdfs:label ?publisherLabel.\n" +
                        "     FILTER(STR(?gender) = \"%s\")\n" +
                        "     FILTER (LANG(?characterLabel) = \"en\")\n" +
                        "     FILTER(STR(?publisherLabel) = \"%s\")\n" +
                        "     FILTER (LANG(?publisherLabel) = \"en\")\n" +
                        "}LIMIT %d\n";
        //Set values in query
        String gender1 = "male";
        String publisher = "DC Comics";
        int resultsLimit1 = 100;
        queryString1 = String.format(queryString1, gender1, publisher, resultsLimit1);

        //Query pentru filme/seriale characters
        String queryString2 =
                "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                        "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
                        "PREFIX dbp: <http://dbpedia.org/property/>\n" +
                        "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                        "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                        "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n" +
                        "SELECT DISTINCT (STR(?characterLabel) as ?characterName)\n" +
                        "WHERE {\n" +
                        "     ?character rdf:type dbo:FictionalCharacter.\n" +
                        "     ?character dbo:portrayer ?portrayer.\n" +
                        "     ?character foaf:gender ?gender.\n" +
                        "     ?character rdfs:label ?characterLabel.\n" +
                        "     ?character dbo:series ?series.\n" +
                        "     ?series rdfs:label ?seriesLabel.\n" +
                        "     FILTER(STR(?gender) = \"%s\")\n" +
                        "     FILTER (LANG(?characterLabel) = \"en\")\n" +
                        "     FILTER(STR(?seriesLabel) = \"%s\")\n" +
                        "     FILTER (LANG(?seriesLabel) = \"en\")\n" +
                        "}LIMIT %d\n";
        //Set values in query
        String gender2 = "male";
        String series = "Star Wars";
        int resultsLimit2 = 100;
        queryString2 = String.format(queryString2, gender2, series, resultsLimit2);

        //Preview the query
        System.out.println(queryString2);

        //Execute the query
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", queryString2);
        ResultSet resultSet = queryExecution.execSelect();

        //Display results
        //ResultSetFormatter.out(resultSet);
        while (resultSet.hasNext()) {
            System.out.println(resultSet.next().get("characterName").toString());
        }
    }
}
