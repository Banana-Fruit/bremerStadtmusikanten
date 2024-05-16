package control;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import view.ImageDemonstrator;

import java.util.*;


public class ImageController implements Runnable
{
    private Pane pane; // Assuming pane is a Pane or similar object
    private static HashMap<ImageView, Integer> images;
    
    
    public ImageController(HashMap<ImageView, Integer> images, Pane pane)
    {
        this.pane = pane;
        this.images = images;
    }
    
    
    @Override
    public void run()
    {
        while (true)
        {
            for (Map.Entry<ImageView, Integer> entry : images.entrySet())
            {
                ImageDemonstrator.getImageViewScaledPaneSize(entry.getKey(), entry.getValue(), pane);
            }
        }
    }
    
    
    public static void changeImageViewPosition(Image image, int x, int y)
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
    
    
    public static HashMap<ImageView, Integer> getMapOfScaledImageViewsToPaneSize(HashMap<ImageView, Integer> images, Pane pane)
    {
        HashMap scaledImages = new HashMap();
        for (Map.Entry<ImageView, Integer> entry : images.entrySet())
        {
            scaledImages.put(ImageDemonstrator.getImageViewScaledPaneSize(entry.getKey(), entry.getValue(), pane), entry.getValue());
        }
        return scaledImages;
    }
}
