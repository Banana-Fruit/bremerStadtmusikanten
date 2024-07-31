package control.scenes;


import control.BuildingController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.buildings.*;
import model.userInterface.Game;
import model.userInterface.showables.Map;
import model.userInterface.showables.Showable;
import utility.GameMenuBar;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_City;
import resources.constants.scenes.Constants_Showable;


/**
 * This controller is responsible for scene switching and other scene related functions.
 *
 * @author Michael Markov
 */
public class SceneController
{
    private static SceneController instance = null;
    private final Stage stage;
    private boolean dialogShown = false;
    private Showable previousShowable = null; // Showable to be tracked
    
    
    private SceneController (Stage stage)
    {
        this.stage = stage;
    }
    
    
    public static synchronized void initialize (Stage stage)
    {
        if (instance == null)
        {
            instance = new SceneController(stage);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }


    /**
     * Method to build a default scene for the inside of a building. Click on the respective building to open
     * the corresponding scene.
     *
     * @author Jule Degener
     * @param building Building that wants to be visited.
     * @precondition none
     * @postcondition The corresponding scene of the inside of the building is shown.
     */
    public static void buildSceneBuildingInside (Building building)
    {
        GridPane gridpane = new GridPane();
        Button goBack = new Button(Constants_City.INSIDE_BUTTON_GO_BACK);
        Label nameOfBuilding = new Label(building.getName());
        nameOfBuilding.setFont(new Font(Constants_City.INSIDE_NAME_FONT, Constants_City.FONT_NAME_BUILDING));
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(Constants_City.GRIDPANE_HGAP_INSIDE);
        gridpane.setVgap(Constants_City.GRIDPANE_VGAP_INSIDE);
        gridpane.add(goBack, Constants_City.GRIDPANE_ROW_SEVEN, Constants_City.GRIDPANE_COLUMN_ONE);
        gridpane.add(nameOfBuilding, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_ONE);
        closeBuildingInside(goBack);
        
        chooseTheRightBuildingInside(building, gridpane);
        
        Scene scene = new Scene(gridpane);
        SceneController.instance.stage.setScene(scene);
        SceneController.instance.stage.setFullScreen(true);
        SceneController.instance.stage.show();
    }


    /**
     * Method to assign an event to the button. The event is to close the scene inside from a building.
     *
     * @author Jule Degener
     * @param button Button to be assigned to the event.
     * @precondition An instance of SceneController and MapController must exist.
     * @postcondition Scene inside from a building can be closed.
     */
    private static void closeBuildingInside (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                SceneController.getInstance().switchShowable(Map.getInstance());
            }
        });
    }


    /**
     * Method to choose the right scene inside from a building to the clicked building.
     *
     * @author Jule Degener
     * @param building Building that is to be displayed.
     * @param gridpane GridPane on which the elements of the scene is built.
     * @precondition none
     * @postcondition The scene inside from the clicked building is shown.
     */
    private static void chooseTheRightBuildingInside (Building building, GridPane gridpane)
    {
        if (building == BaseCamp.getInstanceOfBasecamp())
        {
            BuildingController.getInsideBaseCamp(gridpane);
        } else if (building == MagicAmplifier.getInstanceOfMagicamplifier())
        {
            BuildingController.getInsideMagicAmplifier(gridpane);
        } else if (building == Headquarter.getInstanceOfHeadquarter())
        {
            BuildingController.getInsideHeadquarter(gridpane);
        } else if (building == TrainingArea.getInstanceOfTrainingarea())
        {
            BuildingController.getInsideTrainingsArea(gridpane);
        } else if (building == Pub.getInstanceOfPub())
        {
            BuildingController.getInsidePub(gridpane);
        } else if (building == FractionCampDog.getInstanceOfFractionDogCamp())
        {
            BuildingController.getInsideFractionCampDog(gridpane);
        } else if (building == FractionCampCat.getInstanceOfFractionCatCamp())
        {
            BuildingController.getInsideFractionCampCat(gridpane);
        } else if (building == FractionCampChicken.getInstanceOfFractionChickenCamp())
        {
            BuildingController.getInsideFractionCampChicken(gridpane);
        } else if (building == Forge.getInstanceOfForge())
        {
            BuildingController.getInsideForge(gridpane);
        } else if (building == Marketplace.getInstanceOfMarketplace())
        {
            BuildingController.getInsideMarketplace(gridpane);
        }
    }
    
    
    /**
     * Creates a base pane with the menu bar.
     *
     * @return Base Pane only with the MenuBar.
     * @author Michael Markov
     */
    public Pane getBasePane ()
    {
        Pane pane = new Pane();
        
        // creates a Menu bar with two points (game and settings) and add two menuItems to the point game
        pane.getChildren().add(GameMenuBar.createMenuBar());
        
        return pane;
    }
    
    
    /**
     * Switches the showable (scene) to another one.
     *
     * @param showable Showable to be switched to.
     * @author Michael Markov
     */
    public void switchShowable (Showable showable)
    {
        // Tracks previous showable in case there is a back button
        this.previousShowable = Game.getInstance().getCurrentShowable();
        Game.getInstance().setCurrentShowable(showable); // Switches showable
        
        // Makes sure scene properties are aligned among all scenes
        this.stage.setFullScreen(true);
        this.stage.setFullScreenExitHint(Constants_Showable.HIDE_EXIT_HINT_STRING);
        this.stage.setScene(showable.getScene());
    }
    
    
    /**
     * The previous showable is always tracked. Meaning you can always return back to the previous option.
     *
     * @author Michael Markov
     */
    public void switchBackShowable ()
    {
        // Tracks previous showable in case there is a back button
        Showable previousShowable = Game.getInstance().getCurrentShowable();
        Game.getInstance().setCurrentShowable(this.previousShowable); // Switches showable
        
        // Makes sure scene properties are aligned among all scenes
        this.stage.setFullScreen(true);
        this.stage.setFullScreenExitHint(Constants_Showable.HIDE_EXIT_HINT_STRING);
        this.stage.setScene(Game.getInstance().getCurrentShowable().getScene());
    }
    
    
    public boolean isDialogShown ()
    {
        return dialogShown;
    }


    /**
     * Getter-method to get the instance of the SceneController
     *
     * @author Michael Markov, Jule Degener
     * @return Instance of the SceneController
     * @precondition none
     * @postcondition One instance of SceneController exist in the program.
     */
    public static SceneController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}