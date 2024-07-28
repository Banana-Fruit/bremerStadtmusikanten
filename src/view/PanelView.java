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
     * @param panel Panel to be shown.
     * @param pane Pane to show the panel on.
     * @author Michael Marov
     */
    public static void addTilesToPane (Panel panel, Pane pane)
    {
        // Runs through all rows until the limit is reached
        for (int row = Constants_Panel.MIN_TILE_INDEX; row <= panel.getMaxRows(); row++)
        {
            // Runs through all columns until the limit is reached
            for (int column = Constants_Panel.MIN_TILE_INDEX; column <= panel.getMaxColumns(); column++)
            {
                // When no image is at current position, skip the iteration
                if (panel.getTileArray()[row][column].getImage() == null) continue;
                
                // Creates new instance of OutputImageView for the current iteration
                OutputImageView currentOutputImageView = new OutputImageView(
                        panel.getTileArray()[row][column].getImage(),
                        ValueConversion.getDiagonalSizeFromSquareLength(panel.getTileSize())
                );
                
                // Determines the position for the current image
                Coordinate imageCoordinate = PanelController.getInstance().getCoordinateFromPanelTile(panel, row, column);
                currentOutputImageView.setX(imageCoordinate.getPositionX());
                currentOutputImageView.setY(imageCoordinate.getPositionY());
                
                // Adds the image to the pane
                pane.getChildren().add(currentOutputImageView);
            }
        }
    }
}
