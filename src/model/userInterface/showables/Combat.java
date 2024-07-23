package model.userInterface.showables;


import control.scenes.PanelController;
import javafx.scene.Scene;
import model.Coordinate;
import model.panel.Panel;
import model.userInterface.TransparentButton;
import resources.constants.Constants_Combat;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Resources;


/**
 * Combat class contains the scene of the combat.
 *
 * @author Michael Markov
 */
public class Combat extends Showable
{
    private static volatile Combat instance;
    private Panel panel;
    
    
    private Combat (Scene scene)
    {
        super(scene);
        init();
    }
    
    
    private void init ()
    {
        setBackground(Constants_Resources.COMBAT_BACKGROUND_PATH);
    }
    
    
    public static synchronized void initialize (Scene scene)
    {
        if (instance == null)
        {
            instance = new Combat(scene);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public static Combat getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    public void addTileButtons ()
    {
        for (int row = Constants_Combat.MIN_ROW; row < Constants_Combat.MAX_ROWS; row++) // Runs through all rows till max rows
        {
            for (int column = Constants_Combat.MIN_COLUMN; column < Constants_Combat.MAX_COLUMNS; column++) // Runs through all columns till max columns
            {
                // Each button is placed above the tiles of the panel aligned by size and position
                Coordinate buttonCoordinate = PanelController.getInstance().getCoordinateFromPanelTile(panel, row, column);
                // The buttons are transparent until pressed
                TransparentButton transparentButton = new TransparentButton(Constants_Combat.BUTTON_TEXT,
                        Constants_Combat.TILE_SIZE, Constants_Combat.TILE_SIZE, Constants_Combat.OPACITY_PRESSED,
                        Constants_Combat.OPACITY_RELEASED, buttonCoordinate.getPositionX(), buttonCoordinate.getPositionY());
                
                // Adding button
                getPane().getChildren().add(transparentButton);
            }
        }
    }
    
    
    public void setPanel (Panel panel)
    {
        this.panel = panel;
    }
}
