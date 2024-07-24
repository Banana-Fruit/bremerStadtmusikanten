package model.userInterface.showables;


import control.scenes.SceneController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.userInterface.TransparentButton;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Popup;
import resources.constants.scenes.Constants_Settings;
import resources.constants.scenes.Constants_Showable;


public class Settings extends Showable
{
    private static volatile Settings instance;
    
    
    private Settings (Scene scene)
    {
        super(scene);
        init();
    }
    
    
    public static synchronized void initialize (Scene scene)
    {
        if (instance == null)
        {
            instance = new Settings(scene);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public static Settings getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    /**
     * Initializer for the Settings Showable, that gives the Showable its buttons and background.
     */
    private void init()
    {
        setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, null)));
        
        VBox button = new VBox();
        button.getChildren().add(new TransparentButton(Constants_Settings.BACK_BUTTON, () ->
        {
            SceneController.getInstance().switchBackShowable();
        }, Constants_Settings.BUTTON_WIDTH, Constants_Settings.BUTTON_HEIGHT, Constants_Settings.LINEAR_GRADIENT_OPACITY, Constants_Settings.LINEAR_GRADIENT_OPACITY_W));
        button.setAlignment(Pos.CENTER);
        
        // Center the button
        button.layoutXProperty().bind(getScene().widthProperty().subtract(
                button.widthProperty()).divide(Constants_Settings.CENTER_VAR));
        button.layoutYProperty().bind(getScene().heightProperty().subtract(
                button.heightProperty()).divide(Constants_Settings.CENTER_VAR));
        
        getPane().getChildren().add(button);
    }
}
