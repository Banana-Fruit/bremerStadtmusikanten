package control.scenes;



import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.player.Inventory;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_Building;


/**
 * The GUI processes are handled by the display controller.
 */
public class DisplayController
{
    private static volatile DisplayController instance = null;

    private static Label numberOfGold;
    private static Label numberOfBrick;
    private static Label numberOfWood;
    private static Label numberOfBeer;
    private static Label numberOfEssence;


    // default constructor
    private DisplayController ()
    {
        ;
    }

    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new DisplayController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }


    
    public static GridPane createInventory()
    {
        GridPane gridpane = new GridPane();
        gridpane.setLayoutX(Constants_Building.GRIDPANE_LAYOUT_POSITION_X);
        gridpane.setLayoutY(Constants_Building.GRIDPANE_LAYOUT_POSITION_Y);



        // create labels
        Label inventory = new Label(Constants_Building.RESOURCES_INVENTORY);
        Label gold = new Label(Constants_Building.GOLD);
        Label brick = new Label(Constants_Building.BRICK);
        Label wood = new Label(Constants_Building.WOOD);
        Label beer = new Label(Constants_Building.BEER);
        Label essence = new Label(Constants_Building.ESSENCE);
        numberOfGold = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryGold()));
        numberOfBrick = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryBrick()));
        numberOfWood = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryWood()));
        numberOfBeer = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryBeer()));
        numberOfEssence = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryEssence()));

        // add labels to the gridPane
        gridpane.add(inventory, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_ZERO,
                Constants_Building.GRIDPANE_SIZE_TWO,Constants_Building.GRIDPANE_SIZE_TWO);
        gridpane.add(gold, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_THREE,
                Constants_Building.GRIDPANE_SIZE_ONE, Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(brick, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_FOUR,
                Constants_Building.GRIDPANE_SIZE_ONE, Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(wood, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_FIVE,
                Constants_Building.GRIDPANE_SIZE_ONE, Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(beer, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_SIX,
                Constants_Building.GRIDPANE_SIZE_ONE, Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(essence, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_SEVEN,
                Constants_Building.GRIDPANE_SIZE_ONE, Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(numberOfGold, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_THREE,
                Constants_Building.GRIDPANE_SIZE_ONE,Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(numberOfBrick, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_FOUR,
                Constants_Building.GRIDPANE_SIZE_ONE,Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(numberOfWood, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_FIVE,
                Constants_Building.GRIDPANE_SIZE_ONE,Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(numberOfBeer, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_SIX,
                Constants_Building.GRIDPANE_SIZE_ONE,Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(numberOfEssence, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_SEVEN,
                Constants_Building.GRIDPANE_SIZE_ONE,Constants_Building.GRIDPANE_SIZE_ONE);

        return gridpane;
    }
    
    
    public void updateInventory()
    {
        // Update the labels with current inventory values
        numberOfGold.setText(Integer.toString(Inventory.getInstanceOfInventory().getInventoryGold()));
        numberOfBrick.setText(Integer.toString(Inventory.getInstanceOfInventory().getInventoryBrick()));
        numberOfWood.setText(Integer.toString(Inventory.getInstanceOfInventory().getInventoryWood()));
        numberOfBeer.setText(Integer.toString(Inventory.getInstanceOfInventory().getInventoryBeer()));
        numberOfEssence.setText(Integer.toString(Inventory.getInstanceOfInventory().getInventoryEssence()));
    }
    


    public static DisplayController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }

}
