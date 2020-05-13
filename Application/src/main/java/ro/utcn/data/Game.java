package ro.utcn.data;

import java.util.*;

public class Game
{
    public static Game instance = null;

    List<Filter> filters;

    public SparqlQuery characterQuery = new SparqlQuery();
    public Filter currentFilter;
    public String currentQuestion;
    public int currentQuestionNumber;
    public Map<Integer, String> questionsMap = new HashMap<>();

    private Game()
    {
        filters = new ArrayList<>();
        filters.add(new Filter("rdf:type", "dbo:Person"));
        questionsMap.put(0, "a person");
        filters.add(new Filter("dbo:ground", "dbr:Camp_Nou"));
        questionsMap.put(1, " camp nou");
        filters.add(new Filter("foaf:gender", "\"female\"@en"));
        questionsMap.put(2, "a female");
        filters.add(new Filter("dct:subject", "dbc:Marvel_Comics_characters_who_use_magic"));
        questionsMap.put(3, "a magic user");
        filters.add(new Filter("next question", "next question"));
        questionsMap.put(4, "next question");

        currentQuestionNumber = 0;
        currentFilter = filters.get(currentQuestionNumber);
        currentQuestion = "Is your character " + questionsMap.get(currentQuestionNumber) + " ?";
    }

    public static Game getInstance()
    {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    private void updateGameParameters()
    {
        currentQuestionNumber++;
        currentFilter = filters.get(currentQuestionNumber);
        currentQuestion = "Is your character " + questionsMap.get(currentQuestionNumber) + " ?";
    }

    private void addFilterToQuery(boolean isPositive)
    {
        if (isPositive)
        {
            characterQuery.addFilter(currentFilter.positiveFilter());
        }
        else
        {
            characterQuery.addFilter(currentFilter.negativeFilter());
        }
    }

    private void findNextQuestion()
    {
        List<String> queryResultPages = characterQuery.getQueryResultPages();
        while(true)
        {
            boolean isNextQuestionApplicable = false;
            int listIterator = 0;
            while(!isNextQuestionApplicable && listIterator < queryResultPages.size())
            {
                isNextQuestionApplicable = SparqlQuery.executeBooleanQuery(queryResultPages.get(listIterator), currentFilter.attribute, currentFilter.value);
                listIterator++;
            }

            if(!isNextQuestionApplicable)
            {
                updateGameParameters();
            }
            else
            {
                break;
            }
        }
    }

    public void generateNextQuestion(boolean answer)
    {
        addFilterToQuery(answer);
        characterQuery.displayQueryResult();
        updateGameParameters();
        findNextQuestion();
    }
}
