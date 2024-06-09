package view;


import control.scenes.PanelController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.panel.Panel;
import resources.constants.Constants_Panel;


public class PanelView
{
    public static Pane addTilesToPane (Panel panel, Pane pane)
    {
        int k = 0;
        for (int i = Constants_Panel.MIN_TILE_INDEX; i < panel.getTileArray().length; i++)
        {
            for (int j = Constants_Panel.MIN_TILE_INDEX; j < panel.getTileArray()[Constants_Panel.MIN_TILE_INDEX].length; j++)
            {
                if(panel.getTileArray()[i][j].getBackgroundImage() == null) continue;
                OutputImageView currentOutputImageView = new OutputImageView(
                        panel.getTileArray()[i][j].getBackgroundImage(), panel.getTileSize());
                currentOutputImageView.setX(PanelController.getInstance().getPositionXFromTileIndex(panel, i));
                currentOutputImageView.setY(PanelController.getInstance().getPositionYFromTileIndex(panel, j));
                pane.getChildren().add(currentOutputImageView);
            }
        }
        return pane;
    }
}
