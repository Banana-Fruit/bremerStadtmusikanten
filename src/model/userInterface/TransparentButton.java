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
import resources.constants.Constants_MainMenu;


public class TransparentButton extends StackPane
{
    // attributes
    private static final LinearGradient gradient = createGradient(Constants_MainMenu.LINEAR_GRADIENT_OPACITY);
    private static final LinearGradient wGradient = createGradient(Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W);
    
    
    // constructor
    public TransparentButton (String name, Runnable action, int rcwidth, int rcheight)
    {
        Rectangle bg = new Rectangle(rcwidth, rcheight, gradient);
        Text text = new Text(name);
        text.setFont(Font.font(Constants_MainMenu.TEXT_FONT));
        text.fillProperty().bind(Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.GRAY));
        setOnMouseClicked(e -> action.run());
        setOnMousePressed(e -> bg.setFill(wGradient));
        setOnMouseReleased(e -> bg.setFill(gradient));
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
