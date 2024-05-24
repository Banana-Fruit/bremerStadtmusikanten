package model.showables;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Collectable;
import model.Obstacle;
import model.Player;
import resources.Constants_DefaultValues;

import java.util.List;


public class Map extends Showable
{
    private List<Obstacle> obstacles;
    private List<Collectable> collectables;
    private Image mapImage;
    private Player player;
    
    
    public Map(Scene scene, String backgroundPath)
    {
        super(scene, backgroundPath);
        init();
    }
    
    
    public void init()
    {
        this.player = new Player(
                Constants_DefaultValues.DEFAULT_X_POSITION,
                Constants_DefaultValues.DEFAULT_Y_POSITION,
                Constants_DefaultValues.HITBOX_RADIUS,
                new Image("/resources/player.jpg")
        );
        
        addImageView(new ImageView(player.getPlayerImage()), 10);
    }
}
