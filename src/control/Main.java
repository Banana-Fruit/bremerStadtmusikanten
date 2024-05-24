package control;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.MapScene;
import model.Player;
import model.showables.Game;
import resources.Constants_DefaultValues;
import utility.Converter;
import utility.MyIO;
import view.ImageDemonstrator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Main extends Application
{
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    @Override
    public void start(Stage stage)
    {
        Game game = new Game();
        GameController gameController = new GameController(game, stage);
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
}