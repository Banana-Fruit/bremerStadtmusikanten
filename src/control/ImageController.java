package control;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.showables.Game;
import resources.Constants_Game;
import view.ImageDemonstrator;

import java.util.*;


public class ImageController implements Runnable
{
    private Game game;
    private view.ImageView imageView = new view.ImageView();
    
    
    public ImageController(Game game)
    {
        this.game = game;
    }
    
    
    @Override
    public void run()
    {
        while (true)
        {
            for (Map.Entry<javafx.scene.image.ImageView, Integer> entry : this.game.getCurrentShowable().getImageViewsWithSizePercentage().entrySet())
            {
                try
                {
                    Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                view.ImageView.scaleImageViewToBackgroundByPercentage(entry.getKey(), this.game.getCurrentShowable().getBackground(), entry.getValue());
            }
        }
    }
    
    
    public static void changeImagePosition(Image image, int x, int y)
    {
        for (ImageView imageView : images.keySet())
        {
            if (imageView.getImage().equals(image))
            {
                imageView.setLayoutX(x);
                imageView.setLayoutY(y);
                break;
            }
        }
    }
}
