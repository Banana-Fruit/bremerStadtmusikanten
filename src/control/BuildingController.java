package control;


import control.scenes.GUIController;
import control.scenes.PanelController;
import model.Coordinate;
import model.buildings.*;
import model.player.Inventory;
import model.userInterface.TransparentButton;
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
        Map.getInstance().getPane().getChildren().add(createBuildingButton("FO", () -> GUIController.showPriceOfBuilding(Forge.getInstanceOfForge()),128,48, Forge.getInstanceOfForge().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("F1", () -> GUIController.showPriceOfBuilding(FractionCamp.createFractionCamp("Hund")),48,48, FractionCamp.getDogFractionCamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("F2", () -> GUIController.showPriceOfBuilding(FractionCamp.createFractionCamp("Katze")),48,48, FractionCamp.getCatFractionCamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("F3", () -> GUIController.showPriceOfBuilding(FractionCamp.createFractionCamp("Huhn")),48,48, FractionCamp.getChickenFractionCamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("HQ", () -> GUIController.showPriceOfBuilding(Headquarter.getInstanceOfHeadquarter()),96,80, Headquarter.getInstanceOfHeadquarter().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("MA", () -> GUIController.showPriceOfBuilding(MagicAmplifier.getInstanceOfMagicamplifier()),48,64, MagicAmplifier.getInstanceOfMagicamplifier().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("MP", () -> GUIController.showPriceOfBuilding(Marketplace.getInstanceOfMarketplace()),144, 80, Marketplace.getInstanceOfMarketplace().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("PU", () -> GUIController.showPriceOfBuilding(Pub.getInstanceOfPub()),192,64, Pub.getInstanceOfPub().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("TA", () -> GUIController.showPriceOfBuilding(TrainingArea.getInstanceOfTrainingarea()),112,64, TrainingArea.getInstanceOfTrainingarea().getPositionUpperLeft()));
    }

    public TransparentButton createBuildingButton (String name, Runnable action, int itemWidth, int itemHeight, Coordinate coordinate)
    {
        Coordinate buildingCoordinate = PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(),(int) coordinate.getPositionX(),(int) coordinate.getPositionY()-1);
        TransparentButton button = new TransparentButton(name, () -> { action.run();},
                itemWidth, itemHeight, 0.0, 0.1);
        button.setTranslateX(buildingCoordinate.getPositionX());
        button.setTranslateY(buildingCoordinate.getPositionY());
        return button;
    }

}
