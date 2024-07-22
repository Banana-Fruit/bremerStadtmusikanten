package view;


import control.scenes.PanelController;
import javafx.scene.layout.Pane;
import model.Coordinate;
import model.panel.Panel;
import resources.constants.Constants_Panel;
import utility.ValueConversion;


/**
 * PanelView handles the output to the user of the panel.
 *
 * @author Michael Markov
 */
public class PanelView
{
    /**
     * Adds tiles to given pane.
     *
     * @param panel
     * @param pane
     * @author Michael Marov
     */
    public static void addTilesToPane (Panel panel, Pane pane)
    {
        for (int row = Constants_Panel.MIN_TILE_INDEX; row < panel.getTileArray().length; row++)
        {
            for (int column = Constants_Panel.MIN_TILE_INDEX; column < panel.getTileArray()[Constants_Panel.MIN_TILE_INDEX].length; column++)
            {
                if (panel.getTileArray()[row][column].getBackgroundImage() == null) continue;
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
