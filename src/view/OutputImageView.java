package view;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Coordinate;
import resources.constants.Constants_DefaultValues;


/**
 * ImageView class with added methods.
 *
 * @author Michael Markov
 */
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
    
    
    /**
     * Initializer for OutputImageView instances.
     *
     * @author Michael Markov
     */
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
    
    
    /**
     * Setter for coordinates of the image that is shown.
     *
     * @param coordinate Coordinate to be set for the ImageView
     */
    public void setCoordinates (Coordinate coordinate)
    {
        this.setX(coordinate.getPositionX());
        this.setY(coordinate.getPositionY());
    }
}
