package control;


import control.game.SavegameController;
import control.scenes.GUIController;
import control.scenes.MapController;
import control.scenes.PanelController;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Coordinate;
import model.buildings.BaseCamp;
import model.buildings.Building;
import model.buildings.Forge;
import model.buildings.FractionCamp;
import model.panel.Panel;
import model.player.Inventory;
import model.userInterface.TransparentButton;
import model.userInterface.showables.MainMenu;
import model.userInterface.showables.Map;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_Building;


public class BuildingController
{
    private static BuildingController instance = null;


    // default controller
    private BuildingController ()
    {
        ;
    }
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new BuildingController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }


    // Method to retrieve the Singleton instance without parameters
    public static BuildingController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }


    public static boolean checkIfBuildingIsUnlock (Building building)
    {
        Inventory inventory = Inventory.getInstanceOfInventory();

        if ((inventory.getInventoryGold()>= building.numberOfGold) &&
                (inventory.getInventoryBrick() >= building.numberOfBrick) &&
                (inventory.getInventoryWood() >= building.numberOfWood) &&
                (inventory.getInventoryBeer() >= building.numberOfBeer) &&
                (inventory.getInventoryEssence() >= building.numberOfEssence))
        {
            System.out.println(Constants_Building.BUILDING_UNLOCKED);
            Inventory.payWithTheInventoryForABuilding(building);
            GUIController.getInstance().updateInventory();
            building.setUnlocked(true);
            return true;
        }
        else
        {
            System.out.println(Constants_Building.BUILDING_LOCKED);
            return false;
        }
    }

    public void addButtons()
    {
        Map.getInstance().getPane().getChildren().add(createBuildingButton("BC", () -> GUIController.showPriceOfBuilding(BaseCamp.getInstanceOfBasecamp()),48,48, BaseCamp.getInstanceOfBasecamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("FO", () -> GUIController.showPriceOfBuilding(Forge.getInstanceOfForge()),80,64, Forge.getInstanceOfForge().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("FC", () -> GUIController.showPriceOfBuilding(FractionCamp.getInstanceOfFractioncamp()),80,48, FractionCamp.getInstanceOfFractioncamp().getPositionUpperLeft()));
    }

    public TransparentButton createBuildingButton (String name, Runnable action, int itemWidth, int itemHeight, Coordinate coordinate)
    {
        Coordinate buildingCoordinate = PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(),(int) coordinate.getPositionX(),(int) coordinate.getPositionY()-1);
        TransparentButton button = new TransparentButton(name, () -> { action.run();},
                itemWidth, itemHeight, 0.0, 0.1);
        button.setTranslateX(buildingCoordinate.getPositionX());
        System.out.println(coordinate.getPositionX());
        System.out.println(coordinate.getPositionY());
        button.setTranslateY(buildingCoordinate.getPositionY());
        return button;
    }

}
