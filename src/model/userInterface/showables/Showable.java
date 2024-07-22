package model.userInterface.showables;


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
        scene.fillProperty().set(Color.BLACK);
        this.pane = new Pane(scene.getRoot());
        init();
    }
    
    
    public void addChildToPane (Node child)
    {
        getPane().getChildren().add(child);
    }
    
    
    private void init ()
    {
        this.scene.fillProperty().set(Color.BLACK);
        this.scene.setRoot(pane);
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    }
    
    
    public void setBackground (Background background)
    {
        getPane().setBackground(background);
    }
    
    
    public void setBackground (String path)
    {
        pane.setBackground(new Background(new BackgroundImage(
                new Image(path),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
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
