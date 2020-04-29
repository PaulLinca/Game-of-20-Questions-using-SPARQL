package ro.utcn.data;

public class Game
{
    public static Game instance = null;

    public String currentQuestion = "Is your character a female?";
    public int currentQuestionNumber = 1;

    private Game()
    {
    }

    public static Game getInstance()
    {
        if (instance == null)
        {
            instance = new Game();
        }

        return instance;
    }

    public void generateNextQuestion()
    {
        currentQuestionNumber++;
        currentQuestion = "A " + currentQuestionNumber + "a intrebare";
    }
}
