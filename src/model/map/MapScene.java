package model.map;


import java.util.List;

import javafx.scene.image.Image;


/**
 * A map consists of obstacles, collectables, buildings and an image where they are placed on
 */
public class MapScene implements Runnable
{
    private List obstacles;
    private List collectables;
    Image mapImage;
    
    
    public MapScene(Image mapImage)
    {
        this.mapImage = mapImage;
    }
    
    
    public Image getMapImage()
    {
        return mapImage;
    }
    
    
    public void centerMap()
    {
    
    }
    
    
    public void adjustMapToSceneSize()
    {
    
    }
    
    
    @Override
    public void run()
    {
    
    }
}
