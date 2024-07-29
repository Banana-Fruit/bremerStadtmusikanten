package model.userInterface.showables;


import control.scenes.SceneController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import model.userInterface.TransparentButton;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_LoadGame;
import resources.constants.scenes.Constants_MainMenu;
import resources.constants.scenes.Constants_Showable;


/**
 * LoadGame class contains the scene of the class.
 *
 * @author Michael Markov
 */
public class LoadGame extends Showable
{
    private static volatile LoadGame instance;
    
    
    private LoadGame (Scene scene)
    {
        super(scene);
        init();
    }
    
    
    public static synchronized void initialize (Scene scene)
    {
        if (instance == null)
        {
            instance = new LoadGame(scene);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public static LoadGame getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    /**
     * Initializer or the LoadGame Showable.
     *
     * @author Michael Markov
     */
    private void init ()
    {
        setBackground(Constants_MainMenu.PATH_BACKGROUND_IMAGE); // Background image
        
        GridPane gridPane = createGridPane(Constants_MainMenu.GRIDPANE_WIDTH, Constants_MainMenu.GRIDPANE_HEIGHT,
                Constants_MainMenu.GRIDPANE_TRANSLATE_Y, Constants_MainMenu.GRIDPANE_GAP); // Four loading buttons in a gridpane
        createMenuItems(gridPane);
        
        // Back button, which puts you to the origin scene
        TransparentButton backButton = new TransparentButton(Constants_MainMenu.BACK_BUTTON_NAME, () ->
        {
            SceneController.getInstance().switchBackShowable();
        }, Constants_MainMenu.VBOX_ITEM_WIDTH, Constants_MainMenu.VBOX_ITEM_HEIGHT, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W);
        
        // Add buttons to pane
        gridPane.add(backButton, Constants_LoadGame.BACKBUTTON_PosX, Constants_LoadGame.BACKBUTTON_PosY);
        getPane().getChildren().add(gridPane);
    }
    
    
    /**
     * Creates formatted gridpane to contain the buttons.
     *
     * @param width Width of the GridPane.
     * @param height Height of the GridPane.
     * @param translateY TranslateY value of the GridPane.
     * @param gap Gap size between the elements of the GridPane.
     * @return GridPane created from the parameters.
     * @author Michael Markov, Jonas Helfer
     */
    private GridPane createGridPane (int width, int height, int translateY, int gap)
    {
        GridPane gridPane = new GridPane();
        
        // Formatting
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(width, height);
        gridPane.setTranslateY(translateY);
        gridPane.setHgap(gap);
        gridPane.setVgap(gap);
        
        // Center the gridPane
        gridPane.layoutXProperty().bind(getScene().widthProperty().subtract(
                gridPane.widthProperty()).divide(Constants_Showable.CENTER_VAR));
        gridPane.layoutYProperty().bind(getScene().heightProperty().subtract(
                gridPane.heightProperty()).divide(Constants_Showable.CENTER_VAR));
        
        return gridPane;
    }
    
    
    /**
     * Creates buttons with functions and adds them to the gridPane.
     *
     * @param gridPane GridPane that will receive the MenuItems.
     * @author Jonas Helfer
     */
    private void createMenuItems (GridPane gridPane)
    {
        // Assigning function to the buttons
        TransparentButton[] saveGameItems = createGameLoadsItems(Constants_MainMenu.NUMBER_OF_GAMES);
        addItemsToGridpane(saveGameItems, gridPane);
    }
    
    
    /**
     * Creates buttons with functions.
     *
     * @param numberOfGames Number of games that determine the amount of transparent buttons in the array.
     * @return TransparentButton Array based on the number of games.
     * @author Michael Markov, Jonas Helfer
     */
    private TransparentButton[] createGameLoadsItems (int numberOfGames)
    {
        TransparentButton[] saveGameItems = new TransparentButton[numberOfGames];
        
        for (int i = Constants_MainMenu.START_LOOP; i < numberOfGames; i++)
        {
            String saveGameName = Constants_MainMenu.SAVE_GAMES + (i + Constants_MainMenu.ONE); // name of the game load
            TransparentButton saveGameItem = new TransparentButton(saveGameName, () ->
            {
            }, Constants_MainMenu.GAME_LOAD_ITEM_WIDTH,
                    Constants_MainMenu.GAME_LOAD_ITEM_HEIGHT, Constants_MainMenu.LINEAR_GRADIENT_OPACITY,
                    Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W);
            
            saveGameItems[i] = saveGameItem;
        }
        
        return saveGameItems;
    }
    
    
    /**
     * Adds items to a gridPane.
     *
     * @param saveGameItems Transparent button array with the save game items.
     * @param gridPane GridPane that the buttons have to be added to.
     * @author Michael Markov, Jonas Helfer
     */
    private void addItemsToGridpane (TransparentButton[] saveGameItems, GridPane gridPane)
    {
        for (int i = Constants_MainMenu.START_LOOP; i < Constants_MainMenu.NUMBER_OF_GAMES; i++)
        {
            GridPane.setConstraints(saveGameItems[i], i % Constants_MainMenu.TWO, i / Constants_MainMenu.TWO); // set position on the GridPane
            gridPane.getChildren().add(saveGameItems[i]);
        }
    }
}