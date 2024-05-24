package control;


import javafx.stage.Stage;
import model.showables.Game;
import resources.Constants_Game;


public class SceneController implements Runnable
{
    private static volatile SceneController instance;
    private Game game;
    private Stage stage;
    
    
    private SceneController()
    {
    }
    
    
    public static SceneController getInstance()
    {
        if (instance == null)
        {
            synchronized (SceneController.class)
            {
                if (instance == null)
                {
                    instance = new SceneController();
                }
            }
        }
        return instance;
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
    
    
    public void setGame(Game game)
    {
        this.game = game;
    }
    
    
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
