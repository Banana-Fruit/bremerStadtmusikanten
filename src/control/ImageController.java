package control;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.userInterface.Game;
import resources.Constants_ExceptionMessages;
import resources.Constants_Game;
import view.OutputImageView;

import java.util.*;


public class ImageController implements Runnable
{
    private static volatile ImageController instance;
    private static Game game;
    
    
    private ImageController (Game game)
    {
        this.game = game;
    }
    
    
    public static synchronized void initialize (Game game)
    {
        if (instance == null)
        {
            instance = new ImageController(game);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    // Method to retrieve the Singleton instance without parameters
    public static ImageController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    @Override
    public void run ()
    {
        while (true)
        {
            for (Map.Entry<ImageView, Integer> entry : this.game.getCurrentShowable().getImageViewsWithSizePercentage().entrySet())
            {
                try
                {
                    Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                OutputImageView.scaleImageViewToSceneSize(entry.getKey(), entry.getValue(), this.game.getCurrentShowable().getScene());
            }
        }
    }
    
    
    public void changeImagePosition (Image image, int x, int y)
    {
        //TODO: OutputImageView.setImagePosition( , x, y);
    }
}
