package ro.utcn.data;

import java.util.*;

public class Game {
    public static Game instance = null;

    List<Filter> filters;

    public SparqlQuery characterQuery = new SparqlQuery();
    public Filter currentFilter;
    public String currentQuestion;
    public int currentQuestionNumber;
    public Map<Integer, String> questionsMap = new HashMap<>();

    private Game() {
        filters = new ArrayList<>();
        filters.add(new Filter("rdf:type", "dbo:Person"));
        questionsMap.put(0, "a person");
        filters.add(new Filter("foaf:gender", "\"female\"@en"));
        questionsMap.put(1, "a female");
        filters.add(new Filter("dct:subject", "dbc:Marvel_Comics_characters_who_use_magic"));
        questionsMap.put(2, "a magic user");
        filters.add(new Filter("next question", "next question"));
        questionsMap.put(3, "next question");

        currentQuestionNumber = 0;
        currentFilter = filters.get(currentQuestionNumber);
        currentQuestion = "Is your character " + questionsMap.get(currentQuestionNumber) + " ?";
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    public void generateNextQuestion(boolean answer) {
        if (answer) {
            characterQuery.addFilter(currentFilter.positiveFilter());
        } else {
            characterQuery.addFilter(currentFilter.negativeFilter());
        }

        if (currentQuestionNumber == filters.size() - 2) {
            characterQuery.displayQueryResult();
        }

        currentQuestionNumber++;
        currentFilter = filters.get(currentQuestionNumber);
        currentQuestion = "Is your character " + questionsMap.get(currentQuestionNumber) + " ?";
    }
}
