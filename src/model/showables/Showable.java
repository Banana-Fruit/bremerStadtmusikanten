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
    private Background background;
    
    
    public Showable(Scene scene, String backgroundPath)
    {
        this.scene = scene;
        this.pane = new Pane(scene.getRoot());
        this.imageViewsWithSizePercentage = new HashMap<>();
        this.background = new Background(
                new BackgroundImage(
                        new Image(backgroundPath),
                        BackgroundRepeat.NO_REPEAT, // Do not repeat the image
                        BackgroundRepeat.NO_REPEAT, // Do not repeat the image
                        BackgroundPosition.CENTER, // Center the image
                        new BackgroundSize(
                                100, // Width percentage (100% of the pane's width)
                                100, // Height percentage (100% of the pane's height)
                                true, // Width is a percentage
                                true, // Height is a percentage
                                true, // Preserve aspect ratio
                                false // Do not cover the whole area (preserve aspect ratio)
                        )
                )
        );
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
    
    
    public Background getBackground()
    {
        return background;
    }
}
