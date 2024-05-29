package view;


import control.scenes.PanelController;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.panel.Panel;
import resources.constants.Constants_Panel;


public class PanelView
{
    public static Pane addTilesToPane (PanelController panelController, Panel panel, Pane pane)
    {
        for (int i = Constants_Panel.MIN_TILE_INDEX; i < panel.getTileArray().length; i++)
        {
            for (int j = Constants_Panel.MIN_TILE_INDEX; j < panel.getTileArray()[Constants_Panel.MIN_TILE_INDEX].length; j++)
            {
                ImageView currentImageView = new ImageView(panel.getTileArray()[i][j].getImage());
                currentImageView.setX(panelController.getPositionXFromTileIndex(i));
                currentImageView.setY(panelController.getPositionYFromTileIndex(j));
                pane.getChildren().add(currentImageView);
            }
        }
        return pane;
    }
}
