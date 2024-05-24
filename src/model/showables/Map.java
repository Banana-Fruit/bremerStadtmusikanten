package model.showables;


import javafx.scene.Scene;
import resources.Constants_Scenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Map extends Showable
{
    public Map(Scene scene)
    {
        super(scene, Constants_Scenes.IDENTIFIER_MAP);
    }
}
