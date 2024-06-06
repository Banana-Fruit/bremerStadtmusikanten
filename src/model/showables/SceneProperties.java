package model.showables;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class SceneProperties extends Scene
{
    public SceneProperties (Scene scene)
    {
        super(new Pane(), scene.getWidth(), scene.getHeight());
    }
}
