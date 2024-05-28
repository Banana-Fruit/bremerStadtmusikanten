package view;


public class SceneView
{
    // TODO: Is it actually needed?
    /*public static void scaleImageViewToSceneSize (ImageView imageView, int percentageValue, Scene scene)
    {
        double referenceHeight = scene.heightProperty().get();
        double referenceWidth = scene.widthProperty().get();
        double referenceAspectRatio = referenceWidth / referenceHeight;
        
        double imageAspectRatio = imageView.fitWidthProperty().get() / imageView.fitHeightProperty().get();
        double newImageHeight;
        double newImageWidth;
        
        
        if (referenceAspectRatio > imageAspectRatio)
        {
            // Pane is wider than the image
            newImageHeight = referenceHeight;
            newImageWidth = referenceHeight * imageAspectRatio;
        } else if (referenceAspectRatio < imageAspectRatio)
        {
            // Pane is narrower than the image
            newImageWidth = referenceWidth;
            newImageHeight = referenceWidth / imageAspectRatio;
        } else
        {
            // Pane and image have the same aspect ratio
            newImageHeight = referenceHeight;
            newImageWidth = referenceWidth;
        }
        
        
        // Apply the scaling percentage
        double scalingFactor = percentageValue / Constants_DefaultValues.PERCENTAGE_NUMBER;
        newImageHeight *= scalingFactor;
        newImageWidth *= scalingFactor;
        
        // Set the new dimensions to the ImageView
        imageView.fitHeightProperty().set(newImageHeight);
        imageView.fitWidthProperty().set(newImageWidth);
    }*/
    
    
    /*public static void scaleImageViewToBackgroundByPercentage(javafx.scene.image.ImageView imageViewToScale, Background imageViewAsReference, int scalingPercentage)
    {
        double referenceHeight = imageViewAsReference.fitHeightProperty().get();
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
        imageViewToScale.fitWidthProperty().set(newImageWidth);
    }*/
}
