package view;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import utility.Converter;


public class ImageDemonstrator
{
    public static ImageView getImageViewVanilla(Image image)
    {
        return new ImageView(image);
    }
    
    
    public static ImageView getImageViewUsingHeight(Image image, int imageHeight)
    {
        double aspectRatio = image.getWidth() / image.getHeight();
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(imageHeight);
        imageView.setFitWidth(imageHeight * aspectRatio);
        
        return imageView;
    }
    
    
    public static ImageView getImageViewUsingWidth(Image image, int imageWidth)
    {
        double aspectRatio = image.getWidth() / image.getHeight();
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(imageWidth);
        imageView.setFitHeight(imageWidth / aspectRatio);
        
        return imageView;
    }
    
    
    public static ImageView getImageViewScaledPaneSize(ImageView imageView, int scalingPercentage, Pane pane)
    {
        double scalingFactor = scalingPercentage / 100;
        // Adjust background size based on aspect ratios
        double paneAspectRatio = pane.getWidth() / pane.getHeight();
        double imageAspectRatio = imageView.getImage().getWidth() / imageView.getImage().getHeight();
        if (paneAspectRatio > imageAspectRatio)
        {
            // Map narrower than pane
            imageView.fitHeightProperty().set(pane.heightProperty().get() * scalingFactor);
            imageView.fitWidthProperty().set(Converter.getWidthOfAnImageBySettingHeight(pane.heightProperty().get(), imageView.getImage()) * scalingFactor);
        } else if (paneAspectRatio < imageAspectRatio)
        {
            // Map wider than pane
            imageView.fitWidthProperty().set(pane.widthProperty().get() * scalingFactor);
            imageView.fitHeightProperty().set(Converter.getHeightOfAnImageBySettingWidth(pane.widthProperty().get(), imageView.getImage()) * scalingFactor);
        } else
        {
            // Map has the same aspect ratio as pane
            imageView.fitHeightProperty().set(pane.heightProperty().get() * scalingFactor);
            imageView.fitWidthProperty().set(Converter.getWidthOfAnImageBySettingHeight(pane.heightProperty().get(), imageView.getImage()) * scalingFactor);
        }
        return imageView;
    }
    
    
    public static ImageView imageViewScaledPaneSize(ImageView imageView, int scalingPercentage, Pane pane)
    {
        // Calculate aspect ratios
        double paneAspectRatio = pane.getWidth() / pane.getHeight();
        double imageAspectRatio = imageView.getImage().getWidth() / imageView.getImage().getHeight();
        
        // Adjust size based on aspect ratios
        if (paneAspectRatio > imageAspectRatio)
        {
            // Pane is narrower than the image
            imageView.fitHeightProperty().bind(pane.heightProperty());
            imageView.fitWidthProperty().bind(pane.heightProperty().multiply(imageAspectRatio));
        } else if (paneAspectRatio < imageAspectRatio)
        {
            // Pane is wider than the image
            imageView.fitWidthProperty().bind(pane.widthProperty());
            imageView.fitHeightProperty().bind(pane.widthProperty().divide(imageAspectRatio));
        } else
        {
            // Pane and image have the same aspect ratio
            imageView.fitWidthProperty().bind(pane.widthProperty());
            imageView.fitHeightProperty().bind(pane.heightProperty());
        }
        
        // Apply scaling percentage
        double scalingFactor = scalingPercentage / 100.0;
        imageView.setFitWidth(imageView.getFitWidth() * scalingFactor);
        imageView.setFitHeight(imageView.getFitHeight() * scalingFactor);
        
        return imageView;
    }
    
    
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
