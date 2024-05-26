package control;


import javafx.application.Application;
import javafx.stage.Stage;
import model.userInterface.Game;


/**
 * This is the heart of the program. The program is initiated here.
 */
public class Main extends Application
{
    @Override
    public void start(Stage stage)
    {
        GameController gameController = new GameController(new Game(), stage);
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
}