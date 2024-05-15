package utility;


import javafx.scene.image.Image;


public class Converter
{
    public static double getWidthOfAnImageBySettingHeight (double height, Image image)
    {
        double aspectRatio = image.getWidth() / image.getHeight();
        return (aspectRatio * height);
    }


    public static double getHeightOfAnImageBySettingWidth (double width, Image image)
    {
        double aspectRatio = image.getWidth() / image.getHeight();
        return (width / aspectRatio);
    }
}
