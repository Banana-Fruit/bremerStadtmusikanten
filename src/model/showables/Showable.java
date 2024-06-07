package model.showables;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import resources.constants.scenes.Constants_Showable;


public class Showable
{
    private final Pane pane;
    private final Scene scene;
    
    
    public Showable ()
    {
        this.pane = new Pane();
        this.scene = new Scene(this.pane);
    }
    
    
    public Showable (Scene scene)
    {
        this.scene = scene;
        this.pane = new Pane(scene.getRoot());
        init();
    }
    
    
    public void addChildToPane (Node child)
    {
        getPane().getChildren().add(child);
    }
    
    
    private void init ()
    {
        scene.setRoot(pane);
        scene.fillProperty().set(Color.BLACK);
    }
    
    
    public void setBackground (String path)
    {
        pane.setBackground(new Background(new BackgroundImage(
                new Image(path),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(this.scene.getWidth(), this.scene.getHeight(),
                        Constants_Showable.BACKGROUND_AS_PERCENTAGE, Constants_Showable.BACKGROUND_AS_PERCENTAGE,
                        Constants_Showable.BACKGROUND_CONTAIN, Constants_Showable.BACKGROUND_COVER)
        )));
    }
    
    
    public Pane getPane ()
    {
        return pane;
    }
    
    
    public Scene getScene ()
    {
        return scene;
    }
    
    
    public Showable getShowable ()
    {
        return this;
    }
}
