package control;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    @Override
    public void start(Stage primaryStage) {
        // Create a Pane
        Pane root = new Pane();
        // Create a Rectangle
        Rectangle square = new Rectangle(50, 50, 50, 50);
        square.setFill(Color.BLUE);
        // Add the square to the Pane
        root.getChildren().add(square);

        // Handle key releases
        root.setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.W) upPressed = false;
            if (code == KeyCode.S) downPressed = false;
            if (code == KeyCode.A) leftPressed = false;
            if (code == KeyCode.D) rightPressed = false;
        });

        // Create an AnimationTimer to handle movement
        AnimationTimer timer = new AnimationTimer() {
            double speed = 3; // Movement speed

            @Override
            public void handle(long now) {
                // Move the square based on key presses
                if (upPressed && !downPressed) square.setY(square.getY() - speed);
                if (downPressed && !upPressed) square.setY(square.getY() + speed);
                if (leftPressed && !rightPressed) square.setX(square.getX() - speed);
                if (rightPressed && !leftPressed) square.setX(square.getX() + speed);
            }
        };

        // Start the animation
        timer.start();

        // Create the Scene
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Square Movement");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}