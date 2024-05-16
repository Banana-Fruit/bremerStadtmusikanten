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
    int imageHeight = 100;
    
    
    @Override
    public void start(Stage stage)
    {
        Pane pane = new Pane();
        
        // Create a scene and add the pane to it
        Scene scene = new Scene(pane, 400, 400);
        scene.setFill(Color.BLACK);
        
        // Add a player to the map
        Player player = new Player(Constants_DefaultValues.DEFAULT_X_POSITION, Constants_DefaultValues.DEFAULT_Y_POSITION, Constants_DefaultValues.HITBOX_RADIUS, MyIO.getImageFromPath("/resources/player.jpg"));
        MapScene map = new MapScene(MyIO.getImageFromPath("/resources/map.jpg"));
        
        HashMap<ImageView, Integer> imageViewsWithSizePercentage = new HashMap<>();
        imageViewsWithSizePercentage.put(new ImageView(map.getMapImage()), 100);
        imageViewsWithSizePercentage.put(new ImageView(player.getPlayerImage()), 100);
        for(ImageView imageView : imageViewsWithSizePercentage.keySet())
        {
            pane.getChildren().add(imageView);
        }
        
        PlayerController playerController = new PlayerController(player);
        KeyboardController keyboardController = new KeyboardController(scene);
        ImageController imageController = new ImageController(imageViewsWithSizePercentage, pane);
        new Thread(keyboardController).start();
        new Thread(imageController).start();
        
        
        // Set the stage title and scene, then show the stage
        stage.setTitle("Image WASD Movement");
        stage.setScene(scene);
        stage.show();
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
}