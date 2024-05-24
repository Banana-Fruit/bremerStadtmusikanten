package view;


import javafx.scene.image.ImageView;


public class PlayerView
{
    private ImageView playerImage;
    
    
    public PlayerView(ImageView playerImage)
    {
        this.playerImage = playerImage;
    }
    
    
    public void setPlayerImagePosition(double x, double y)
    {
        this.playerImage.setX(x);
        this.playerImage.setY(y);
    }
}
