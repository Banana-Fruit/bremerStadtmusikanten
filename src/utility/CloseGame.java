package utility;


import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import model.userInterface.Game;


public class CloseGame implements Popup
{
    private String closingMessage;
    private int textToButtonsSpacing;
    private int itemHeight;
    private int itemWidth;
    private Color backgroundColor;
    private Node popup;
    
    
    public CloseGame (String closingMessage, int textToButtonsSpacing, int itemWidth, int itemHeight, Color backgroundColor)
    {
        this.closingMessage = closingMessage;
        this.textToButtonsSpacing = textToButtonsSpacing;
        this.itemHeight = itemHeight;
        this.itemWidth = itemWidth;
        this.backgroundColor = backgroundColor;
        init();
    }
    
    
    private void init ()
    {
        this.popup = createPopup(closingMessage, textToButtonsSpacing, itemWidth, itemHeight, backgroundColor);
        addPopup();
    }
    
    
    @Override
    public void addPopup ()
    {
        Game.getInstance().getCurrentShowable().getPane().getChildren().add(popup);
    }
    
    
    @Override
    public void removePopup ()
    {
        Game.getInstance().getCurrentShowable().getPane().getChildren().remove(popup);
    }
    
    
    public void onNO ()
    {
        removePopup();
    }
    
    
    public void onYES ()
    {
        Platform.exit();
        removePopup();
    }
}