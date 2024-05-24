package control;


import javafx.stage.Stage;
import model.showables.Game;
import resources.Constants_Game;


public class SceneController implements Runnable
{
    private Game game;
    private Stage stage;
    
    
    public SceneController(Game game, Stage stage)
    {
        this.game = game;
        this.stage = stage;
    }
    
    
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            
            this.stage.setScene(game.getCurrentShowable().getScene());
        }
    }
}
