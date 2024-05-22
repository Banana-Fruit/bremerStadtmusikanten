package bremen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;


/**
 * Die Klasse HelloApplication enthält Methoden zum Erstellen des Startbildschirms.
 *
 * @author  Jonas Helfer
 * @version 1.0
 */
public class HelloApplication extends Application
{
    //Hintergrund aus dem JPG erzeugen
    Image imgBackground = new Image(getClass().getResource("/Bremen.jpg").toExternalForm(), 800, 500,false, true);
    BackgroundImage backgroundImage = new BackgroundImage(
            imgBackground,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    Background background = new Background(backgroundImage);

    @Override
    public void start (Stage stage)
    {

        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 500);
        root.setBackground(background);

        //Menueleiste zur Startseite hinzufügen
        MenuBar menuBar = new MenuBar();
        //Unterpunkte der Menueleiste definieren
        Menu menuGame = new Menu("Spiel");
        Menu menuSettings = new Menu("Einstellungen");
        //Unterpunkte der Menueleiste hinzufügen
        menuBar.getMenus().addAll(menuGame, menuSettings);

        MenuItem beenden = new MenuItem("Spiel Beenden");
        MenuItem laden = new MenuItem("Spiel Laden");
        //Methode zum Behandeln der Unterpunkte
        beenden.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                closeGame();
            }
        });
        laden.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                loadGame(stage);
            }
        });
        //Unterpunkte dem Menü hinzufügen
        menuGame.getItems().addAll(laden, beenden);

        //Vertikales Feld mit mehreren Items einrichten

        VBox box = new VBox(10,
                new menuItem("Fortfahren",() -> continueGame(), 200, 30),
                new menuItem("Neues Spiel",() -> {}, 200, 30),
                new menuItem("Spiel Laden", () -> loadGame(stage), 200, 30),
                new menuItem("Multiplayer",() -> openSettings(stage), 200, 30),
                new menuItem("Einstellungen", () -> {}, 200, 30),
                new menuItem("Spiel Beenden", () -> closeGame(), 200, 30));
        //X-Position des vertikalen Feldes
        box.setTranslateX(300);
        //Y-Position des vertikalen Feldes
        box.setTranslateY(100);
        //Menueleiste und vertikales Feld zur Root Pane hinzufügen
        root.getChildren().addAll(menuBar, box);
        stage.setScene(scene);
        //Name des Fenstertitels festlegen
        stage.setTitle("Bremer Stadtmusikanten");
        //Fenster anzeigen
        stage.show();
    }

    private void continueGame ()
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }


    /**
     * Methode zum Abfragen und Beenden des Spiels
     * @author  Jonas Helfer
     */
    private void closeGame ()
    {
        //Neue Bühne erstellen
        Stage dialogStage = new Stage();

        //Dialogfenster erstellen
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.setTitle("Spiel Beenden");
        //Vertikales Feld erzeugen
        VBox dialogVbox = new VBox(20);
        //Position des vertikalen Feldes festlegen
        dialogVbox.setAlignment(Pos.CENTER);
        //Text, ob das Spiel beendet werden soll
        Text message = new Text("Möchten Sie das Spiel wirklich beenden?");
        //Farbe der Textnachricht
        message.setFill(Color.WHITE);

        //Ja-Button erzeugen und Event behandeln
        menuItem jaButton = new menuItem("Ja", () ->
        {
            Platform.exit();
            dialogStage.close();
        }, 50,30);
        //Nein-Button erzeugen und Event behandeln
        menuItem neinButton = new menuItem("Nein", () -> dialogStage.close(),50,30);

        //Horizontales Feld erzeugen
        HBox buttonBox = new HBox(30);
        //Position des Horizontalen Feldes festlegen
        buttonBox.setAlignment(Pos.CENTER);
        //Ja- und Nein-Button in das horizontale Feld hinzufügen
        buttonBox.getChildren().addAll(jaButton, neinButton);
        //Frage, ob das Spiel beendet werden soll und Buttons in das horizontale Feld hinzufügen
        dialogVbox.getChildren().addAll(message, buttonBox);
        //Todo: Hintergrund ändern
        //Hintergrund des Dialogs festlegen
        dialogVbox.setBackground(background);

        Scene dialogScene = new Scene(dialogVbox, 300, 150);

        dialogScene.setFill(Color.TRANSPARENT);
        dialogStage.setScene(dialogScene);
        //Dialogfenster anzeigen
        dialogStage.show();
    }


    /**
     * Methode zum Öffnen der Einstellungen des Spiels
     * @author      Jonas Helfer
     * @param stage Die Bühne, auf der die Szene angezeigt werden soll
     */

    private void openSettings (Stage stage)
    {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 500);
        root.setBackground(background);

        stage.setScene(scene);
        //Fenstertitel setzen
        stage.setTitle("Einstellungen");
        //Fenster anzeigen
        stage.show();
    }

    /**
     * Methode zum Laden des Spiels
     * @author      Jonas Helfer
     * @param stage Die Bühne, auf der die Szene angezeigt werden soll
     */
    private void loadGame (Stage stage)
    {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 500);
        root.setBackground(background);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(800, 400);
        gridPane.setTranslateY(50);
        gridPane.setHgap(40);
        gridPane.setVgap(40);


        // Array für die Spielstandsmenüpunkte
        menuItem[] saveGameItems = new menuItem[4];

        // Schleife zur Erstellung der Spielstandsmenüpunkte
        //Todo: Namensanpassung der Spielstände, sodass statt Spielstand 1 dort ein Eigenname steht
        for (int i = 0; i < 4; i++)
        {
            String saveGameName = "Spielstand " + (i + 1); // Name des Spielstands
            menuItem saveGameItem = new menuItem(saveGameName, () -> {}, 250, 150); // Menüpunkt erstellen
            saveGameItems[i] = saveGameItem; // Menüpunkt zum Array hinzufügen
        }

        // Spielstandsmenüpunkte zum GridPane hinzufügen
        for (int i = 0; i < 4; i++)
        {
            GridPane.setConstraints(saveGameItems[i], i % 2, i / 2); // Position im GridPane festlegen
            gridPane.getChildren().add(saveGameItems[i]); // Menüpunkt dem GridPane hinzufügen
        }

        //org.example.bremen.Tile-Pane für den Zurück-Button erstellen
        TilePane zurueckPane = new TilePane();
        //Positionierung des TilePanes
        zurueckPane.setAlignment(Pos.BOTTOM_CENTER);
        //Y-Position des TilePanes
        zurueckPane.setTranslateY(410);
        //Größe der TilePane festlegen
        zurueckPane.setPrefSize(800, 70);
        //Erstellen des Zurück-Buttons
        menuItem backButton = new menuItem("Zurück", () -> start(stage), 150, 30);
        //Zurück-Button zur TilePane hinzufügen
        zurueckPane.getChildren().addAll(backButton);
        //ZurückPane und GridPane der Spielstände zur Root-Pane hinzufügen
        root.getChildren().addAll(gridPane, zurueckPane);

        stage.setScene(scene);
        //Fenstertitel festlegen
        stage.setTitle("Spiel Laden");
        //Bühne anzeigen
        stage.show();
    }

    //Todo: menuItem aus HelloApplication entfernen

    /**
     * Klasse für die Menü-Items
     * @author  Jonas Helfer
     * @version 1.0
     */
    private static class menuItem extends StackPane
    {
        private static final LinearGradient gradient = createGradient(0.7);
        private static final LinearGradient wGradient = createGradient(0.35);

        menuItem(String name, Runnable action, int rcwidth, int rcheight)
        {
            Rectangle bg = new Rectangle(rcwidth, rcheight, gradient);
            Text text = new Text(name);
            text.setFont(Font.font(18.0));
            text.fillProperty().bind(Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.GRAY));
            setOnMouseClicked(e -> action.run());
            setOnMousePressed(e -> bg.setFill(wGradient));
            setOnMouseReleased(e -> bg.setFill(gradient));
            getChildren().addAll(bg, text);
        }

        /**
         * Methode zur Erstellung eines Lineargradienten mit einer bestimmten Durchsichtigkeit
         * @author          Jonas Helfer
         * @param opacity   Durchsichtigkeit
         * @return          Ein neuer LinearGradient mit der bestimmter Durchsichtigkeit
         */
        private static LinearGradient createGradient(double opacity)
        {
            return new LinearGradient(0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0.1, Color.web("black", opacity)));
        }
    }

    public static void main (String[] args)
    {
        launch(args);
    }
}