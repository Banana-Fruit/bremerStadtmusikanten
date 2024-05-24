package control;


import javafx.stage.Stage;
import model.showables.Game;


public class GameController
{
    private Game game;
    private Stage stage;
    private KeyboardController keyboardController;
    private MouseController mouseController;
    private PlayerController playerController;
    private ImageController imageController;
    private SceneController sceneController;
    
    public GameController(Game game, Stage stage)
    {
        this.game = game;
        this.stage = stage;
        
        keyboardController = new KeyboardController(this.game);
        mouseController = new MouseController(this.game);
        imageController = new ImageController(this.game);
        sceneController = new SceneController(this.game, stage);
        playerController = new PlayerController(this.game);
        
        init();
    }
    
    
    public void init()
    {
        new Thread(keyboardController).start();
        new Thread(mouseController).start();
        new Thread(imageController).start();
        new Thread(sceneController).start();
        
        this.stage.setTitle(game.getGameTitle());
        this.stage.show();
    }
}
