package view;


import control.scenes.PanelController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.Coordinate;
import model.panel.Panel;
import resources.constants.Constants_Panel;
import utility.ValueConversion;


public class PanelView
{
    public static void addTilesToPane (Panel panel, Pane pane)
    {
        for (int row = Constants_Panel.MIN_TILE_INDEX; row < panel.getTileArray().length; row++)
        {
            for (int column = Constants_Panel.MIN_TILE_INDEX; column < panel.getTileArray()[Constants_Panel.MIN_TILE_INDEX].length; column++)
            {
                if(panel.getTileArray()[row][column].getBackgroundImage() == null) continue;
                OutputImageView currentOutputImageView = new OutputImageView(
                        panel.getTileArray()[row][column].getBackgroundImage(),
                        ValueConversion.getDiagonalSizeFromSquareLength(panel.getTileSize()));
                
                Coordinate imageCoordinate = PanelController.getInstance().getCoordinateFromPanelTile(panel, row, column);
                currentOutputImageView.setX(imageCoordinate.getPositionX());
                currentOutputImageView.setY(imageCoordinate.getPositionY());
                
                pane.getChildren().add(currentOutputImageView);
            }
        }
    }
}
