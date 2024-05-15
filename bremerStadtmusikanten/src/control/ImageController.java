package control;


import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import view.ImageDemonstrator;

import java.util.*;


public class ImageController implements Runnable
{
    private Pane pane; // Assuming pane is a Pane or similar object
    HashMap<ImageView, Integer> images;
    
    
    public ImageController(HashMap<ImageView, Integer> images, Pane pane)
    {
        this.pane = pane;
        this.images = images;
    }
    
    
    @Override
    public void run()
    {
        while(true)
        {
            for(Map.Entry<ImageView, Integer> entry: images.entrySet())
            {
                ImageDemonstrator.getImageViewScaledPaneSize(entry.getKey(), entry.getValue(), pane);
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
