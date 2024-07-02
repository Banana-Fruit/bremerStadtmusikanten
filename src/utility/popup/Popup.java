package utility.popup;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.userInterface.TransparentButton;
import resources.constants.Constants_Popup;


public interface Popup
{
    static void createPopupWithAction (Pane currentPane, String message, Runnable onOption1, Runnable onOption2,
                                              String option1Name, String option2Name, int textToButtonsSpacing,
                                              int width, int height, Color backgroundColor)
    {
        StackPane popupRoot = new StackPane();
        popupRoot.setPrefSize(width, height);
        
        // Create Yes and No buttons
        TransparentButton yesButton = new TransparentButton(option1Name, () ->
        {
            onOption1.run();
            removePopup(currentPane, popupRoot);
        }, Constants_Popup.ITEM_WIDTH, Constants_Popup.ITEM_HEIGHT, Constants_Popup.LINEAR_GRADIENT_OPACITY, Constants_Popup.LINEAR_GRADIENT_OPACITYW);
        
        TransparentButton noButton = new TransparentButton(option2Name, () ->
        {
            onOption2.run();
            removePopup(currentPane, popupRoot);
        }, Constants_Popup.ITEM_WIDTH, Constants_Popup.ITEM_HEIGHT, Constants_Popup.LINEAR_GRADIENT_OPACITY, Constants_Popup.LINEAR_GRADIENT_OPACITYW);
        
        // Place buttons in an HBox
        HBox hbox = new HBox(Constants_Popup.HBOX_H, noButton, yesButton);
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
    
    
    private static void addPopup (Pane currentPane, StackPane popup)
    {
        currentPane.getChildren().add(popup);
    }
    
    
    private static void removePopup (Pane currentPane, StackPane popup)
    {
        currentPane.getChildren().remove(popup);
    }
}
