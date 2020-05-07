package ro.utcn.data;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    public static Game instance = null;

    List<Filter> filters;

    public SparqlQuery characterQuery = new SparqlQuery();
    public Filter currentFilter;
    public String currentQuestion;
    public int currentQuestionNumber;

    private Game()
    {
        filters = new ArrayList<>();
        filters.add(new Filter("rdf:type", "dbo:Person"));
        filters.add(new Filter("foaf:gender", "\"female\"@en"));
        filters.add(new Filter("dct:subject", "dbc:Marvel_Comics_characters_who_use_magic"));
        filters.add(new Filter("asta e de umplutura", "ca sa nu puste"));

        currentQuestionNumber = 0;
        currentFilter = filters.get(currentQuestionNumber);
        currentQuestion = currentFilter.value + "?";
    }

    public static Game getInstance()
    {
        if (instance == null)
        {
            instance = new Game();
        }

        return instance;
    }

    public void generateNextQuestion(boolean answer)
    {
        if(answer)
        {
            characterQuery.addFilter(currentFilter.positiveFilter());
        }
        else
        {
            characterQuery.addFilter(currentFilter.negativeFilter());
        }
        characterQuery.displayQueryResult();

        currentQuestionNumber++;
        currentFilter = filters.get(currentQuestionNumber);
        currentQuestion = currentFilter.value + "?";
    }
}
