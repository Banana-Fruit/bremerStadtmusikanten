package view;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class OutputImageView extends ImageView
{
    public OutputImageView (Image image)
    {
        super(image);
    }
    
    
    public void updatePosition (double x, double y)
    {
        this.setX(x);
        this.setY(y);
    }
}
