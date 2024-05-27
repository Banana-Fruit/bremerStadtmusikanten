package control.scenes;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.userInterface.Game;
import resources.Constants_ExceptionMessages;
import resources.Constants_MenuSetting;
import src.resources.GameMenuBar;
import model.userInterface.TransparentButton;


public class MainMenuController extends Application implements GameMenuBar
{
    private static volatile MainMenuController instance;
    private Game game;
    private Stage stage;
    
    
    private MainMenuController ()
    {
        this.game = game;
        this.stage = stage;
    }
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new MainMenuController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    // Method to retrieve the Singleton instance without parameters
    public static MainMenuController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    @Override
    public void start (Stage stage) throws Exception
    {
        Pane root = new Pane();
        Scene scene = new Scene(root, Constants_MenuSetting.SCENE_WIDTH, Constants_MenuSetting.SCENE_HEIGHT);
        
        // create a background
        // TODO: Idea 1 (is not working)
        //Background background = createABackground(ConstantMenuSetting.PATH_BACKGROUND_IMAGE,
        //ConstantMenuSetting.BACKGROUND_WIDTH, ConstantMenuSetting.BACKGROUND_HEIGHT, MenuController.class.getClassLoader());
        //TODO: Idea 2 (is not working)
        //Background background = testBackground(ConstantMenuSetting.PATH_BACKGROUND_IMAGE);
        
        // set background to the pane
        // root.setBackground(background);
        
        // creates a Menu bar with two points (game and settings) and add two menuItems to the point game
        MenuBar menuBar = GameMenuBar.createMenuBarWithTwoPoints(stage, root, Constants_MenuSetting.MENUBAR_GAME,
                Constants_MenuSetting.MENUBAR_SETTING, Constants_MenuSetting.MENUBAR_CLOSE, Constants_MenuSetting.MENUBAR_LOAD);
        
        // creates a Menu with six menuItems
        VBox box = createMenuInVBox(stage, root, Constants_MenuSetting.VBOX_ITEM_WIDTH, Constants_MenuSetting.VBOX_ITEM_HEIGHT,
                Constants_MenuSetting.VBOX_XPOSITION, Constants_MenuSetting.VBOX_YPOSITION);
        
        // add menu bar and vertical menu to the pane
        root.getChildren().addAll(menuBar, box);
        stage.setScene(scene);
        stage.setTitle(Constants_MenuSetting.TITLE_MENU_STAGE);
        stage.show();
    }
    
    
    //todo. IS NOT WORKING (Idea1)
    private static Background createABackground (String path, int imageWidth, int imageHeight, ClassLoader classLoader)
    {
        Image imgBackground = new Image(classLoader.getResourceAsStream(path), imageWidth, imageHeight, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(
                imgBackground,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }
    
    
    //TODO: is also not working (Idea2)
    private static Background testBackground (String path) throws IllegalArgumentException
    {
        Image imgBackground = new Image(path);
        return new Background(new BackgroundImage(
                imgBackground,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));
    }
    
    
    /**
     * Methode zum Öffnen der Einstellungen des Spiels
     *
     * @param stage Die Bühne, auf der die Szene angezeigt werden soll
     * @author Jonas Helfer
     */
    
    private static void openSettings (Stage stage, Pane root)
    {
        Scene scene = new Scene(root, Constants_MenuSetting.SCENE_WIDTH, Constants_MenuSetting.SCENE_HEIGHT);
        
        //TODO: set background
        //root.setBackground(background);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    public static VBox createMenuInVBox (Stage stage, Pane root, int itemWidth, int itemHeight, int xPositionVBox, int yPositionVBox)
    {
        VBox box = new VBox(Constants_MenuSetting.VBOX_V,
                new TransparentButton(Constants_MenuSetting.MENU_CONTINUE_GAME, () ->
                {
                }, itemWidth, itemHeight), // -> continueGame(), 200, 30),
                new TransparentButton(Constants_MenuSetting.MENU_NEW_GAME, () ->
                {
                }, itemWidth, itemHeight),
                new TransparentButton(Constants_MenuSetting.MENU_LOAD_GAME, () ->
                {
                }, itemWidth, itemHeight), // -> loadGame(stage), 200, 30),
                new TransparentButton(Constants_MenuSetting.MENU_MULTIPLAYER, () ->
                {
                }, itemWidth, itemHeight),//openSettings(stage, root), itemWidth, itemHeight),
                new TransparentButton(Constants_MenuSetting.MENU_SETTINGS, () ->
                {
                }, itemWidth, itemHeight),
                new TransparentButton(Constants_MenuSetting.MENU_CLOSE_GAME, () -> GameMenuBar.closeGame(stage), itemWidth, itemHeight));
        //X-Position des vertikalen Feldes
        box.setTranslateX(xPositionVBox);
        //Y-Position des vertikalen Feldes
        box.setTranslateY(yPositionVBox);
        return box;
    }
    
    //TODO: Program only likes GamePanel in the start() method (needs runnable)
    /*private void continueGame ()
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }*/
}
