package model.showables;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class Showable
{
    private Pane pane;
    private Scene scene;
    
    
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
