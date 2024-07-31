package control.game;


import control.scenes.PanelController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import model.Attack;
import model.Coordinate;
import model.Unit;
import model.userInterface.showables.Map;
import resources.constants.*;
import resources.constants.scenes.Constants_Map;
import view.OutputImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The UnitController class handles the creation and management of units and their positions.
 * It is implemented as a singleton to ensure only one instance manages the unit state.
 *
 * @author Jonas Stamminger
 */

public class UnitController
{
    private static volatile UnitController instance = null;
    private final HashMap<Coordinate, Unit> unitPositions;


    /**
     * @author Jonas Stamminger
     * Private constructor to prevent instantiation.
     */
    private UnitController ()
    {
        unitPositions = new HashMap<>();
    }

    /**
     * Initializes the UnitController instance.
     *
     * @author Jonas Stamminger
     * @throws IllegalStateException if the instance is already initialized.
     */
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new UnitController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }

    /**
     * Creates units by reading unit data from a CSV file.
     *
     * @author Jonas Stamminger, Jonas Helfer
     * @return A list of created units.
     */

    public ArrayList<Unit> createUnit ()
    {
        //Reading the CSV file
        ArrayList<Unit> units = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(Constants_Player_Units.FILE_READ_UNITS)))
        {

            String line;
            //Creation of Units
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(Constants_DefaultValues.SPLIT);
                String Name = values[Constants_IndexPropertyUnit.INDEX_NAME];
                int health = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_HEALTH]);
                int shield = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_SHIELD]);
                int mana = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_MANA]);
                int meeleDamage = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_MEELE_DAMAGE]);
                int rangedDamage = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_RANGE_DAMAGE]);
                int ammo = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_AMMO]);
                int dodge = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_DODGE]);
                int magicResistance = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_MAGIC_RESISTENCE]);
                int movementPoints = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_MOVEMENT_POINTS]);
                int initiative = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_INITIATIVE]);
                int magicDamage = Integer.parseInt(values[Constants_IndexPropertyUnit.MAGIC_DAMAGE]);
                int myAttack = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_MY_ATTACK]);
                double positionX = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_POSITION_X]);
                double positionY = Integer.parseInt(values[Constants_IndexPropertyUnit.INDEX_POSITION_Y]);
                OutputImageView unitView = new OutputImageView(new Image(Constants_Resources.UNIT_VIEW_STANDARD), Constants_Map.UNIT_SIZE);
                //Collecting the unts into arraylist
                units.add(new Unit(Name, health, shield, mana, meeleDamage, rangedDamage, ammo, dodge,
                        magicResistance, movementPoints, initiative, magicDamage, myAttack, positionX, positionY, unitView));
            }
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return units;
    }

    /**
     * Creates a list of attacks by reading attack data from a CSV file.
     *
     * @author Jonas Stamminger
     * @return A list of created attacks.
     */
    public List<Attack> AttackCreator ()
    {//Reading CSV file
        List<Attack> attacks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(Constants_Combat.ATTACK_LIST)))
        {
            
            String line;
            //Creating an attack
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(Constants_DefaultValues.SPLIT);
                int attackRange = Integer.valueOf(values[Constants_IndexPropertyEnemy.INDEX_ATTACK_RANGE]);
                boolean isMagic = Boolean.valueOf(values[Constants_IndexPropertyEnemy.INDEX_IS_MAGIC]);
                boolean isRanged = Boolean.valueOf(values[Constants_IndexPropertyEnemy.INDEX_IS_RANGED]);

                //Collecting them into a list
                attacks.add(new Attack(attackRange, isMagic, isRanged));
            }
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return attacks;
    }

    /**
     * @author Jonas Helfer
     * Adds units to the mission 1 by placing them at predefined positions.
     */
    public void addUnitsMission1 ()
    {
        Platform.runLater(() ->
        {
            List<Unit> units = createUnit(); // Erstelle Einheiten aus der CSV-Datei
            List<Coordinate> positions = getUnitPositionsForMission1(); // Bekommen vordefinierte oder zufällige Positionen

            for (int i = 0; i < units.size() && i < positions.size(); i++)
            {
                setUnitPosition(units.get(i), (int) positions.get(i).getPositionX(), (int) positions.get(i).getPositionY());
            }
        });
    }

    /**
     * Gets the predefined unit positions for mission 1.
     *
     * @author Jonas Helfer
     * @return A list of coordinates for unit positions.
     */
    private List<Coordinate> getUnitPositionsForMission1 ()
    {
        List<Coordinate> positions = new ArrayList<>();
        
        positions.add(new Coordinate(Constants_Player_Units.UNIT_1_POSITION_X, Constants_Player_Units.UNIT_1_POSITION_Y));
        positions.add(new Coordinate(Constants_Player_Units.UNIT_2_POSITION_X, Constants_Player_Units.UNIT_2_POSITION_Y));
        positions.add(new Coordinate(Constants_Player_Units.UNIT_3_POSITION_X, Constants_Player_Units.UNIT_3_POSITION_Y));
        positions.add(new Coordinate(Constants_Player_Units.UNIT_4_POSITION_X, Constants_Player_Units.UNIT_4_POSITION_Y));
        positions.add(new Coordinate(Constants_Player_Units.UNIT_5_POSITION_X, Constants_Player_Units.UNIT_5_POSITION_Y));
        
        return positions;
    }


    /**
     * Sets the position of a unit on the map.
     *
     * @author Jonas Helfer
     * @param unit The unit to be positioned.
     * @param tileX The x-coordinate of the tile.
     * @param tileY The y-coordinate of the tile.
     */

    private void setUnitPosition (Unit unit, int tileX, int tileY)
    {
        Coordinate coordinate = new Coordinate(PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(), tileX, tileY));
        Map.getInstance().getPane().getChildren().add(unit.getUnitView());
        unit.getUnitView().setCoordinates(coordinate);
        unitPositions.put(coordinate, unit);
    }

    /**
     * Removes a unit from the specified coordinate on the map.
     *
     * @author Jonas Helfer
     * @param coordinate The coordinate of the unit to be removed.
     */
    public void removeUnit (Coordinate coordinate)
    {
        Platform.runLater(() ->
        {
            Map.getInstance().getPane().getChildren().remove(unitPositions.get(coordinate).getUnitView());
            unitPositions.remove(coordinate);
        });
    }
    
    
    public HashMap<Coordinate, Unit> getUnitPositions ()
    {
        return unitPositions;
    }


    /**
     * Getter-method to get the instance of the UnitController
     *
     * @author Michael Markov, Jule Degener
     * @return Instance of the UnitController
     * @precondition none
     * @postcondition One instance of UnitController exist in the program.
     */
    public static UnitController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}