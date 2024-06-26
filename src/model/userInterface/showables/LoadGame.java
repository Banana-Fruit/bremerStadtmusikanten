package model.userInterface.showables;


import control.scenes.SceneController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import model.userInterface.TransparentButton;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_MainMenu;
import resources.constants.scenes.Constants_Showable;


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
    
    
    private void init ()
    {
        setBackground(Constants_MainMenu.PATH_BACKGROUND_IMAGE);
        
        GridPane gridPane = createGridPane(Constants_MainMenu.GRIDPANE_WIDTH, Constants_MainMenu.GRIDPANE_HEIGHT,
                Constants_MainMenu.GRIDPANE_TRANSLATE_Y, Constants_MainMenu.GRIDPANE_GAP);
        createMenuItems(gridPane);
        
        TransparentButton backButton = new TransparentButton(Constants_MainMenu.BACK_BUTTON_NAME, () ->
        {
            SceneController.getInstance().switchShowable(MainMenu.getInstance());
        }, Constants_MainMenu.VBOX_ITEM_WIDTH, Constants_MainMenu.VBOX_ITEM_HEIGHT, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W);
        
        gridPane.add(backButton, 0, 2);
        getPane().getChildren().add(gridPane);
    }
    
    
    private GridPane createGridPane (int width, int height, int translateY, int gap)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(width, height);
        gridPane.setTranslateY(translateY);
        gridPane.setHgap(gap);
        gridPane.setVgap(gap);
        
        gridPane.layoutXProperty().bind(getScene().widthProperty().subtract(
                gridPane.widthProperty()).divide(Constants_Showable.CENTER_VAR));
        gridPane.layoutYProperty().bind(getScene().heightProperty().subtract(
                gridPane.heightProperty()).divide(Constants_Showable.CENTER_VAR));
        
        return gridPane;
    }
    
    
    private void createMenuItems (GridPane gridPane)
    {
        TransparentButton[] saveGameItems = createGameLoadsItems(Constants_MainMenu.NUMBER_OF_GAMES);
        addItemsToGridpane(saveGameItems, gridPane);
    }
    
    
    private TransparentButton[] createGameLoadsItems(int numberOfGames)
    {
        TransparentButton[] saveGameItems = new TransparentButton[numberOfGames];
        
        //Todo: Namensanpassung der Spielstände, sodass statt Spielstand 1 dort ein Eigenname steht
        for (int i = Constants_MainMenu.START_LOOP; i < numberOfGames; i++)
        {
            String saveGameName = Constants_MainMenu.SAVE_GAMES + (i + Constants_MainMenu.ONE); // name of the game load
            TransparentButton saveGameItem = new TransparentButton(saveGameName, () ->
            {
            }, Constants_MainMenu.GAME_LOAD_ITEM_WIDTH,
                    Constants_MainMenu.GAME_LOAD_ITEM_HEIGHT, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W);
            
            saveGameItems[i] = saveGameItem;
        }
        
        return saveGameItems;
    }
    
    
    private void addItemsToGridpane (TransparentButton[] saveGameItems, GridPane gridPane)
    {
        for (int i = Constants_MainMenu.START_LOOP; i < Constants_MainMenu.NUMBER_OF_GAMES; i++)
        {
            GridPane.setConstraints(saveGameItems[i], i % Constants_MainMenu.TWO, i / Constants_MainMenu.TWO); // set position on the GridPane
            gridPane.getChildren().add(saveGameItems[i]);
        }
    }
    
    
    /*private void createTilePaneToGoBack (Pane root, GridPane gridPane)
    {
        // org.example.bremen.Tile-Pane for the GoBack-Button
        TilePane goBackPane = new TilePane();
        
        // defines position and size of the tilePane
        defineTilePane(goBackPane);
        
        // creates goBack button
        TransparentButton backButton = new TransparentButton(Constants_MainMenu.BACK_BUTTON_NAME, () ->
        {
            // Define the action for the goBack button
            try {
                // Assuming MainMenuController has a method to start the main menu scene
                MainMenuController.getInstance().addButtons();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }, Constants_MainMenu.BACK_BUTTON_WIDTH, Constants_MainMenu.BACK_BUTTON_HEIGHT, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W);
        //adds button to the TilePane
        goBackPane.getChildren().addAll(backButton);
        //adds TilePane and GridPane to the Pane root
        root.getChildren().addAll(gridPane, goBackPane);
    }*/
    
    
    private void defineTilePane(TilePane tilePane)
    {
        tilePane.setAlignment(Pos.BOTTOM_CENTER);
        tilePane.setTranslateY(Constants_MainMenu.TILEPANE_TRANSLATE_Y);
        tilePane.setPrefSize(Constants_MainMenu.TILEPANE_WIDTH, Constants_MainMenu.TILEPANE_HEIGHT);
    }
}