package control;


import control.events.KeyboardController;
import control.events.MouseController;
import control.scenes.ImageController;
import control.scenes.SceneController;
import javafx.stage.Stage;
import model.userInterface.Game;


public class GameController
{
    private static Game game;
    private Stage stage;
    
    
    public GameController (Game game, Stage stage)
    {
        this.game = game;
        this.stage = stage;
        init();
    }
    
    
    public void init ()
    {
        KeyboardController.initialize(this.game);
        MouseController.initialize(this.game);
        ImageController.initialize(this.game);
        SceneController.initialize(this.game, this.stage);
        SceneController.initialize(this.game, this.stage);
        
        new Thread(KeyboardController.getInstance()).start();
        new Thread(MouseController.getInstance()).start();
        new Thread(ImageController.getInstance()).start();
        new Thread(SceneController.getInstance()).start();
        
        this.stage.setTitle(game.getGameTitle());
        this.stage.show();
    }
}
