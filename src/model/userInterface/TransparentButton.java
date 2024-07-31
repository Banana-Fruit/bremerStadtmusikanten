package model.userInterface;


import javafx.beans.binding.Bindings;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import resources.constants.scenes.Constants_MainMenu;


public class TransparentButton extends StackPane
{
    
    
    // constructor
    public TransparentButton (String name, Runnable action, int rcwidth, int rcheight, double opacityReleased,
                              double opacityPressed)
    {
        LinearGradient gradientReleased = createGradient(opacityReleased);
        LinearGradient gradientPressed = createGradient(opacityPressed);
        Rectangle bg = new Rectangle(rcwidth, rcheight, gradientReleased);
        Text text = new Text(name);
        text.setFont(Font.font(Constants_MainMenu.TEXT_FONT));
        text.fillProperty().bind(Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.GRAY));
        setOnMouseClicked(e -> action.run());
        setOnMousePressed(e -> bg.setFill(gradientPressed));
        setOnMouseReleased(e -> bg.setFill(gradientReleased));
        getChildren().addAll(bg, text);
    }
    
    
    public TransparentButton (String name, int rcwidth, int rcheight, double opacityReleased, double opacityPressed,
                              double xPos, double yPos)
    {
        LinearGradient gradientReleased = createGradient(opacityReleased);
        LinearGradient gradientPressed = createGradient(opacityPressed);
        Rectangle bg = new Rectangle(rcwidth, rcheight, gradientReleased);
        Text text = new Text(name);
        text.setFont(Font.font(Constants_MainMenu.TEXT_FONT));
        text.fillProperty().bind(Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.GRAY));
        setOnMousePressed(e -> bg.setFill(gradientPressed));
        setOnMouseReleased(e -> bg.setFill(gradientReleased));
        
        setLayoutX(xPos);
        setLayoutY(yPos);
        
        getChildren().addAll(bg, text);
    }
    
    
    /**
     * Method for creating a linear gradient with a specific opacity
     *
     * @param opacity Opacity
     * @return A new LinearGradient with the specific opacity
     * @author Jonas Helfer
     */
    private static LinearGradient createGradient (double opacity)
    {
        return new LinearGradient(Constants_MainMenu.LINEAR_GRADIENT_V, Constants_MainMenu.LINEAR_GRADIENT_V1,
                Constants_MainMenu.LINEAR_GRADIENT_V2, Constants_MainMenu.LINEAR_GRADIENT_V3, true,
                CycleMethod.NO_CYCLE, new Stop(Constants_MainMenu.LINEAR_GRADIENT_STOP_V,
                Color.web(Constants_MainMenu.LINEAR_GRADIENT_COLOR, opacity)));
    }
}
