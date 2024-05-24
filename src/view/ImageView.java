package view;


import javafx.scene.image.Image;
import javafx.scene.layout.Background;


public class ImageView
{
    public static javafx.scene.image.ImageView getImageViewVanilla(Image image)
    {
        return new javafx.scene.image.ImageView(image);
    }
    
    
    public static javafx.scene.image.ImageView getImageViewUsingHeight(Image image, int imageHeight)
    {
        double aspectRatio = image.getWidth() / image.getHeight();
        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(image);
        imageView.setFitHeight(imageHeight);
        imageView.setFitWidth(imageHeight * aspectRatio);
        
        return imageView;
    }
    
    
    public static javafx.scene.image.ImageView getImageViewUsingWidth(Image image, int imageWidth)
    {
        double aspectRatio = image.getWidth() / image.getHeight();
        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(image);
        imageView.setFitWidth(imageWidth);
        imageView.setFitHeight(imageWidth / aspectRatio);
        
        return imageView;
    }
    
    
    public static void scaleImageViewToBackgroundByPercentage(javafx.scene.image.ImageView imageViewToScale, Background imageViewAsReference, int scalingPercentage)
    { // TODO: Fix scaling method
        /*double referenceHeight = imageViewAsReference.fitHeightProperty().get();
        double referenceWidth = imageViewAsReference.fitWidthProperty().get();
        double referenceAspectRatio = referenceWidth / referenceHeight;
        
        double imageAspectRatio = imageViewToScale.getImage().getWidth() / imageViewToScale.getImage().getHeight();
        double newImageHeight;
        double newImageWidth;
        
        
        if (referenceAspectRatio > imageAspectRatio) {
            // Pane is wider than the image
            newImageHeight = referenceHeight;
            newImageWidth = referenceHeight * imageAspectRatio;
        } else if (referenceAspectRatio < imageAspectRatio) {
            // Pane is narrower than the image
            newImageWidth = referenceWidth;
            newImageHeight = referenceWidth / imageAspectRatio;
        } else {
            // Pane and image have the same aspect ratio
            newImageHeight = referenceHeight;
            newImageWidth = referenceWidth;
        }
        
        
        // Apply the scaling percentage
        double scalingFactor = scalingPercentage / 100.0;
        newImageHeight *= scalingFactor;
        newImageWidth *= scalingFactor;
        
        // Set the new dimensions to the ImageView
        imageViewToScale.fitHeightProperty().set(newImageHeight);
        imageViewToScale.fitWidthProperty().set(newImageWidth);*/
    }
    
    
    public void changeImageViewPosition(Image image, int x, int y)
    {
        for (javafx.scene.image.ImageView imageView : images.keySet())
        {
            if (imageView.getImage().equals(image))
            {
                imageView.setLayoutX(x);
                imageView.setLayoutY(y);
                break;
            }
        }
    }
}
