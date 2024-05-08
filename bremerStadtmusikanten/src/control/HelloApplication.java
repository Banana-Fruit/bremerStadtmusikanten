package control;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;


public class HelloApplication extends Application
{
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    int imageHeight = 200;


    @Override
    public void start (Stage stage)
    {
        // Create a rectangle
        Image image = new Image("/resources/player.jpg");

        ImageView playerImageView = new ImageView(image);

        double aspectRatio = image.getWidth() / image.getHeight();
        playerImageView.setFitHeight(imageHeight);
        playerImageView.setFitWidth(imageHeight * aspectRatio);



        // Create a pane to hold the image
        Pane pane = new Pane();
        pane.getChildren().add(playerImageView);

        new Thread(new Runnable()
        {
            @Override
            public void run ()
            {
                while (true)
                {
                    Image mapImage = new Image("resources/map.jpg", stage.getWidth(), stage.getHeight(), true, true);
                    BackgroundImage backgroundImage = new BackgroundImage(mapImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                    pane.setBackground(new Background(backgroundImage));
                }
            }
        }).start();

        // Create a scene and add the pane to it
        Scene scene = new Scene(pane, 400, 400);

        // Add event listeners for key presses and releases
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle (KeyEvent keyEvent)
            {
                pressedKeys.add(keyEvent.getCode());
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle (KeyEvent keyEvent)
            {
                pressedKeys.remove(keyEvent.getCode());
            }
        });

        // Create a game loop to handle continuous movement
        new Thread(new Runnable()
        {
            @Override
            public void run ()
            {
                while (true)
                {
                    try
                    {
                        // Everytime a key press is recognised, the application moves the rectangle.
                        // Making the movement thread sleep for a period, will restrict the amount of key presses
                        // recognised at a certain time period
                        Thread.sleep(15); // TODO: FRAGE: Thread schlafen vs Geschwindigkeit selbstständig anpassen
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    Platform.runLater(new Runnable()
                    {
                        @Override
                        public void run ()
                        {
                            handleKeyPressesImage(playerImageView);
                        }
                    });
                }
            }
        }).start();

        // Set the stage title and scene, then show the stage
        stage.setTitle("Image WASD Movement");
        stage.setScene(scene);
        stage.show();
    }


    private void handleKeyPressesImage (ImageView imageView)
    {
        double deltaX = 0;
        double deltaY = 0;

        if (pressedKeys.contains(KeyCode.W))
            deltaY -= 1;
        if (pressedKeys.contains(KeyCode.A))
            deltaX -= 1;
        if (pressedKeys.contains(KeyCode.S))
            deltaY += 1;
        if (pressedKeys.contains(KeyCode.D))
            deltaX += 1;

        // Makes diagonal movement go at same speed as horizontal and vertical
        if (deltaX != 0 && deltaY != 0)
        {
            // Movement of one pixel horizontally
            deltaX *= Math.sqrt(2) / 2;
            deltaY *= Math.sqrt(2) / 2;
        }

        if (pressedKeys.contains(KeyCode.SHIFT))
        {
            deltaX *= 3;
            deltaY *= 3;
        }

        // Move the rectangle
        imageView.setX(imageView.getX() + deltaX * 5); // Adjust speed by changing the multiplier
        imageView.setY(imageView.getY() + deltaY * 5); // Adjust speed by changing the multiplier
    }

}