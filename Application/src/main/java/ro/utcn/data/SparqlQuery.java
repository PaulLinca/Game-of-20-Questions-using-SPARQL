package ro.utcn.data;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class SparqlQuery {
    private final String queryBeginning =
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                    "PREFIX dbr: <http://dbpedia.org/resource/>\n" +
                    "PREFIX dct: <http://purl.org/dc/terms/>\n" +
                    "PREFIX dbc: <http://dbpedia.org/resource/Category:>\n" +
                    "PREFIX dbp: <http://dbpedia.org/property/>\n" +
                    "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                    "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT DISTINCT (STR(?characterLabel) as ?characterName)\n" +
                    "WHERE {\n" +
                    "     ?character rdfs:label ?characterLabel.\n" +
                    "     ?character rdf:type dbo:ComicsCharacter.\n";
    private final String queryEnd =
            "     FILTER (LANG(?characterLabel) = \"en\")\n" +
                    "}LIMIT 100\n";
    private List<String> filters = new ArrayList<>();

    public SparqlQuery() {
    }

    public void addFilter(String filter) {
        filters.add(filter);
    }

    private String buildQuery() {
        String allFilters = "";
        for (String filter : filters) {
            allFilters += filter;
        }
        return queryBeginning + allFilters + queryEnd;
    }

    public ResultSet executeQuery() {
        String queryToExecute = buildQuery();
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", queryToExecute);
        return queryExecution.execSelect();
    }

    public void displayQueryResult() {
        ResultSet queryResult = executeQuery();
        System.out.println("Possible results so far: ");
        while (queryResult.hasNext()) {
            System.out.println(queryResult.next().get("characterName").toString());
        }
    }
}
