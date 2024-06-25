package control.scenes;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.buildings.*;
import model.userInterface.Game;
import model.userInterface.showables.Map;
import model.userInterface.showables.Showable;
import resources.GameMenuBar;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_MainMenu;
import resources.constants.scenes.Constants_City;


/**
 * This controller is responsible for scene switching
 */
public class SceneController
{
    private static SceneController instance = null;
    private final Stage stage;
    
    
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
        closeBuildingInside(goBack, SceneController.instance.stage);

        chooseTheRightBuildingInside(building, gridpane);

        Scene scene = new Scene(gridpane);
        SceneController.instance.stage.setScene(scene);
        SceneController.instance.stage.setFullScreen(true);
        SceneController.instance.stage.show();
    }


    private static void closeBuildingInside (Button button, Stage stage)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                SceneController.getInstance().switchShowable(Map.getInstance());
            }
        });
    }


    private static void chooseTheRightBuildingInside (Building building, GridPane gridpane)
    {
        if (building == BaseCamp.getInstanceOfBasecamp())
        {
            GUIController.getInsideBaseCamp(gridpane);
        }
        else if (building == MagicAmplifier.getInstanceOfMagicamplifier())
        {
            GUIController.getInsideMagicAmplifier(gridpane);
        }
        else if (building == Headquarter.getInstanceOfHeadquarter())
        {
            GUIController.getInsideHeadquarter(gridpane);
        }
        else if (building == TrainingArea.getInstanceOfTrainingarea())
        {
            GUIController.getInsideTrainingsArea(gridpane);
        }
        else if (building == Pub.getInstanceOfPub())
        {
            GUIController.getInsidePub(gridpane);
        }
        else if (building == FractionCampDog.getInstanceOfFractionDogcamp())
        {
            GUIController.getInsideFractionCampDog(gridpane);
        }

    }
    
    
    public Pane getBasePane ()
    {
        Pane pane = new Pane();
        // creates a Menu bar with two points (game and settings) and add two menuItems to the point game
        pane.getChildren().add(GameMenuBar.createMenuBarWithTwoPoints(stage, Constants_MainMenu.MENUBAR_GAME,
                Constants_MainMenu.MENUBAR_SETTING, Constants_MainMenu.MENUBAR_CLOSE,
                Constants_MainMenu.MENUBAR_LOAD));
        return pane;
    }
    
    
    public void switchShowable(Showable showable)
    {
        Game.getInstance().setCurrentShowable(showable);
        this.stage.setFullScreen(true);
        this.stage.setFullScreenExitHint("");
        this.stage.setScene(showable.getScene());
    }


    // Method to retrieve the Singleton instance without parameters
    public static SceneController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}