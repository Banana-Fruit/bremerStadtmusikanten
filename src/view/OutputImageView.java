package view;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Coordinates;


public class OutputImageView extends ImageView
{
    private int diagonalSize;
    private Image image;
    
    
    public OutputImageView (Image image, int diagonalSize)
    {
        super(image);
        this.image = image;
        this.diagonalSize = diagonalSize;
        init();
    }
    
    
    private void init() {
        // Compute the current diagonal size of the image
        double currentDiagonalSize = Math.sqrt(Math.pow(this.image.getHeight(), 2) +
                Math.pow(this.image.getWidth(), 2));
        
        // Compute the scaling factor to adjust the image to the desired diagonal size
        double scalingFactor = this.diagonalSize / currentDiagonalSize;
        
        // Compute the new height and width maintaining the aspect ratio
        double actualHeight = this.image.getHeight() * scalingFactor;
        double actualWidth = this.image.getWidth() * scalingFactor;
        
        // Set the new dimensions to the ImageView
        setFitWidth(actualWidth);
        setFitHeight(actualHeight);
    }
    
    
    
    public void setCoordinates (Coordinates coordinates)
    {
        this.setX(coordinates.getPositionX());
        this.setY(coordinates.getPositionY());
    }
    
    
    public Coordinates getCoordinates ()
    {
        return new Coordinates(this.getX(), this.getY());
    }
}
