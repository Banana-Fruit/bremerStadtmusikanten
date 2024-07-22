package utility.popup;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.userInterface.TransparentButton;
import resources.constants.Constants_Popup;


/**
 * Creates a popup that overlays over the current scene graph without actually needing its own scene. The popup consists
 * of a message and two buttons. The text, spacing between the buttons, size and color can be customized to the liking.
 *
 * @author Michael Markov
 */
public interface Popup
{
    /**
     * Base functionality of the popup. Highly customizable allowing for different messages, colour options and size
     * options.
     *
     * @param currentPane
     * @param message
     * @param onOption1
     * @param onOption2
     * @param option1Name
     * @param option2Name
     * @param textToButtonsSpacing
     * @param width
     * @param height
     * @param backgroundColor
     */
    static void createPopupWithAction (Pane currentPane, String message, Runnable onOption1, Runnable onOption2,
                                       String option1Name, String option2Name, int textToButtonsSpacing,
                                       int width, int height, Color backgroundColor)
    {
        StackPane popupRoot = new StackPane();
        popupRoot.setPrefSize(width, height);
        
        // Create Yes and No buttons
        TransparentButton option1Button = new TransparentButton(option1Name, () ->
        {
            onOption1.run();
            removePopup(currentPane, popupRoot);
        }, Constants_Popup.ITEM_WIDTH, Constants_Popup.ITEM_HEIGHT, Constants_Popup.LINEAR_GRADIENT_OPACITY, Constants_Popup.LINEAR_GRADIENT_OPACITYW);
        
        TransparentButton option2Button = new TransparentButton(option2Name, () ->
        {
            onOption2.run();
            removePopup(currentPane, popupRoot);
        }, Constants_Popup.ITEM_WIDTH, Constants_Popup.ITEM_HEIGHT, Constants_Popup.LINEAR_GRADIENT_OPACITY, Constants_Popup.LINEAR_GRADIENT_OPACITYW);
        
        // Place buttons in an HBox
        HBox hbox = new HBox(Constants_Popup.HBOX_H, option1Button, option2Button);
        hbox.setAlignment(Pos.CENTER);
        
        // Create layout and add nodes
        Background background = new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY));
        popupRoot.setBackground(background);
        
        Text textMessage = new Text(message);
        textMessage.setFill(Color.WHITE);
        
        // Center the textMessage and hbox
        VBox vbox = new VBox(textToButtonsSpacing, textMessage, hbox);
        vbox.setAlignment(Pos.CENTER);
        StackPane.setAlignment(vbox, Pos.CENTER);
        
        popupRoot.getChildren().add(vbox);
        
        // Center the popup in its parent Pane
        popupRoot.layoutXProperty().bind(currentPane.widthProperty().subtract(popupRoot.widthProperty()).divide(Constants_Popup.CENTER_POPUP_VAR));
        popupRoot.layoutYProperty().bind(currentPane.heightProperty().subtract(popupRoot.heightProperty()).divide(Constants_Popup.CENTER_POPUP_VAR));
        
        addPopup(currentPane, popupRoot);
    }
    
    
    /**
     * Adds popup to the current pane.
     *
     * @param currentPane
     * @param popup
     */
    private static void addPopup (Pane currentPane, StackPane popup)
    {
        currentPane.getChildren().add(popup);
    }
    
    /**
     * Removes popup from the current pane.
     *
     * @param currentPane
     * @param popup
     */
    private static void removePopup (Pane currentPane, StackPane popup)
    {
        currentPane.getChildren().remove(popup);
    }
}
