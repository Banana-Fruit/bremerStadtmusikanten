package control;


import javafx.stage.Stage;
import model.showables.Game;


public class GameController
{
    private Game game;
    private Stage stage;
    
    public GameController(Game game, Stage stage)
    {
        this.game = game;
        this.stage = stage;
        init();
    }
    
    
    public void init()
    {
        KeyboardController.getInstance().setGame(this.game);
        MouseController.getInstance().setGame(this.game);
        ImageController.getInstance().setGame(this.game);
        SceneController.getInstance().setGame(this.game);
        SceneController.getInstance().setStage(this.stage);
        PlayerController.getInstance().setGame(this.game);
        
        new Thread(KeyboardController.getInstance()).start();
        new Thread(MouseController.getInstance()).start();
        new Thread(ImageController.getInstance()).start();
        new Thread(SceneController.getInstance()).start();
        
        this.stage.setTitle(game.getGameTitle());
        this.stage.show();
    }
}
