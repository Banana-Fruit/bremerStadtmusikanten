package view;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Coordinate;
import resources.constants.Constants_DefaultValues;


public class OutputImageView extends ImageView
{
    private double diagonalSize;
    private Image image;
    
    
    public OutputImageView (Image image, double diagonalSize)
    {
        super(image);
        this.image = image;
        this.diagonalSize = diagonalSize;
        init();
    }
    
    
    private void init ()
    {
        // Compute the current diagonal size of the image
        double currentDiagonalSize = Math.sqrt(Math.pow(this.image.getHeight(), Constants_DefaultValues.SQUARE_EXPONENT) +
                Math.pow(this.image.getWidth(), Constants_DefaultValues.SQUARE_EXPONENT));
        
        // Compute the scaling factor to adjust the image to the desired diagonal size
        double scalingFactor = this.diagonalSize / currentDiagonalSize;
        
        // Compute the new height and width maintaining the aspect ratio
        double actualHeight = this.image.getHeight() * scalingFactor;
        double actualWidth = this.image.getWidth() * scalingFactor;
        
        // Set the new dimensions to the ImageView
        setFitWidth(actualWidth);
        setFitHeight(actualHeight);
    }
    
    
    public void setCoordinates (Coordinate coordinate)
    {
        this.setX(coordinate.getPositionX());
        this.setY(coordinate.getPositionY());
    }
    
    
    public Coordinate getCoordinates ()
    {
        return new Coordinate(this.getX(), this.getY());
    }
}
