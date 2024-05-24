package model.showables;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;


public class Showable
{
    private Pane pane;
    private Scene scene;
    private HashMap<ImageView, Integer> imageViewsWithSizePercentage;
    
    
    public Showable(Scene scene)
    {
        this.scene = scene;
        this.pane = new Pane(scene.getRoot());
        this.imageViewsWithSizePercentage = new HashMap<>();
        init();
    }
    
    
    private void init()
    {
        scene.setRoot(pane);
        scene.fillProperty().set(Color.BLACK);
    }
    
    
    public void addImageView(ImageView imageviewKey, Integer percentageValue)
    {
        this.imageViewsWithSizePercentage.put(imageviewKey, percentageValue);
        this.pane.getChildren().add(imageviewKey);
    }
    
    
    public void removeImageView(ImageView imageviewKey)
    {
        this.imageViewsWithSizePercentage.remove(imageviewKey);
        this.pane.getChildren().remove(imageviewKey);
    }
    
    
    public HashMap<ImageView, Integer> getImageViewsWithSizePercentage()
    {
        return imageViewsWithSizePercentage;
    }
    
    
    public Pane getPane()
    {
        return pane;
    }
    
    
    public Scene getScene()
    {
        return scene;
    }
}
