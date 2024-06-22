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
        Map.getInstance().getPane().getChildren().add(createBuildingButton("BC", () -> GUIController.showPriceOfBuilding(BaseCamp.getInstanceOfBasecamp()),Constants_Building.BUTTON_WIDTH_BASECAMP,Constants_Building.BUTTON_HEIGHT_BASECAMP, BaseCamp.getInstanceOfBasecamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("FO", () -> GUIController.showPriceOfBuilding(Forge.getInstanceOfForge()),Constants_Building.BUTTON_WIDTH_FORGE,Constants_Building.BUTTON_HEIGHT_FORGE, Forge.getInstanceOfForge().getPositionUpperLeft()));
        //Map.getInstance().getPane().getChildren().add(createBuildingButton("F1", () -> GUIController.showPriceOfBuilding(FractionCamp.createFractionCamp(Constants_Building.NAME_FRACTIONCAMP_DOG)),Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_DOG,Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_DOG, FractionCamp.getDogFractionCamp().getPositionUpperLeft()));
        //Map.getInstance().getPane().getChildren().add(createBuildingButton("F2", () -> GUIController.showPriceOfBuilding(FractionCamp.createFractionCamp(Constants_Building.NAME_FRACTIONCAMP_CAT)),Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_CAT,Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_CAT, FractionCamp.getCatFractionCamp().getPositionUpperLeft()));
        //Map.getInstance().getPane().getChildren().add(createBuildingButton("F3", () -> GUIController.showPriceOfBuilding(FractionCamp.createFractionCamp(Constants_Building.NAME_FRACTIONCAMP_CHICKEN)),Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_CHICKEN,Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_CHICKEN, FractionCamp.getChickenFractionCamp().getPositionUpperLeft()));

        Map.getInstance().getPane().getChildren().add(createBuildingButton("F1", () -> GUIController.showPriceOfBuilding(FractionCampDog.getInstanceOfFractionDogcamp()),Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_DOG,Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_DOG, FractionCampDog.getInstanceOfFractionDogcamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("F2", () -> GUIController.showPriceOfBuilding(FractionCampCat.getInstanceOfFractionCatCamp()),Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_CAT,Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_CAT, FractionCampCat.getInstanceOfFractionCatCamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("F3", () -> GUIController.showPriceOfBuilding(FractionCampChicken.getInstanceOfFractionChickenCamp()),Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_CHICKEN,Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_CHICKEN, FractionCampChicken.getInstanceOfFractionChickenCamp().getPositionUpperLeft()));


        Map.getInstance().getPane().getChildren().add(createBuildingButton("HQ", () -> GUIController.showPriceOfBuilding(Headquarter.getInstanceOfHeadquarter()),Constants_Building.BUTTON_WIDTH_HEADQUARTER,Constants_Building.BUTTON_HEIGHT_HEADQUARTER, Headquarter.getInstanceOfHeadquarter().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("MA", () -> GUIController.showPriceOfBuilding(MagicAmplifier.getInstanceOfMagicamplifier()),Constants_Building.BUTTON_WIDTH_MAGICAMPLIFIER,Constants_Building.BUTTON_HEIGHT_MAGICAMPLIFIER, MagicAmplifier.getInstanceOfMagicamplifier().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("MP", () -> GUIController.showPriceOfBuilding(Marketplace.getInstanceOfMarketplace()),Constants_Building.BUTTON_WIDTH_MARKETPLACE, Constants_Building.BUTTON_HEIGHT_MARKETPLACE, Marketplace.getInstanceOfMarketplace().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("PU", () -> GUIController.showPriceOfBuilding(Pub.getInstanceOfPub()),Constants_Building.BUTTON_WIDTH_PUB,Constants_Building.BUTTON_HEIGHT_PUB, Pub.getInstanceOfPub().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton("TA", () -> GUIController.showPriceOfBuilding(TrainingArea.getInstanceOfTrainingarea()),Constants_Building.BUTTON_WIDTH_TRAINING_AREA,Constants_Building.BUTTON_HEIGHT_TRAINING_AREA, TrainingArea.getInstanceOfTrainingarea().getPositionUpperLeft()));
    }

    public TransparentButton createBuildingButton (String name, Runnable action, int itemWidth, int itemHeight, Coordinate coordinate)
    {
        Coordinate buildingCoordinate = PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(),(int) coordinate.getPositionX(),(int) coordinate.getPositionY()-1);
        TransparentButton button = new TransparentButton(name, () -> { action.run();},
                itemWidth, itemHeight, Constants_Building.BUILDING_BUTTON_OPACITY_RELEASED, Constants_Building.BUILDING_BUTTON_OPACITY_PRESSED);
        button.setTranslateX(buildingCoordinate.getPositionX());
        button.setTranslateY(buildingCoordinate.getPositionY());
        return button;
    }

}
