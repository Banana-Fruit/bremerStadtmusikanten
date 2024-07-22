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
 * The unit controller is responsible for the unit interactions during the combat.
 */
public class UnitController
{
    private static volatile UnitController instance = null;
    private final HashMap<Coordinate, Unit> unitPositions;
    
    
    private UnitController ()
    {
        unitPositions = new HashMap<>();
    }
    
    
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
    
    
    public ArrayList<Unit> unitCreator ()
    {
        ArrayList<Unit> units = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(Constants_Player_Units.FILE_READ_UNITS)))
        {
            
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(Constants_DefaultValues.SPLIT);
                String Name = values[Constants_IndexPropertyUnit.INDEX_NAME];
                int health = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_HEALTH]);
                int shield = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_SHIELD]);
                int mana = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_MANA]);
                int meeleDamage = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_MEELE_DAMAGE]);
                int rangedDamage = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_RANGE_DAMAGE]);
                int ammo = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_AMMO]);
                int dodge = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_DODGE]);
                int magicResistance = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_MAGIC_RESISTENCE]);
                int movementPoints = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_MOVEMENT_POINTS]);
                int initiative = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_INITIATIVE]);
                int magicDamage = Integer.valueOf(values[Constants_IndexPropertyUnit.MAGIC_DAMAGE]);
                int myAttack = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_MY_ATTACK]);
                double positionX = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_POSITION_X]);
                double positionY = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_POSITION_Y]);
                
                OutputImageView unitView = new OutputImageView(new Image(Constants_Map.UNIT_VIEW_STANDARD), Constants_Map.UNIT_SIZE);
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
    
    
    public List<Attack> AttackCreator ()
    {//WIP
        List<Attack> attacks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(Constants_Combat.ATTACK_LIST)))
        {
            
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(Constants_DefaultValues.SPLIT);
                int attackRange = Integer.valueOf(values[Constants_IndexPropertyEnemy.INDEX_ATTACK_RANGE]);
                boolean isMagic = Boolean.valueOf(values[Constants_IndexPropertyEnemy.INDEX_IS_MAGIC]);
                boolean isRanged = Boolean.valueOf(values[Constants_IndexPropertyEnemy.INDEX_IS_RANGED]);
                
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
    
    
    public static UnitController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    public void addUnitsMission1 ()
    {
        Platform.runLater(() ->
        {
            List<Unit> units = unitCreator(); // Erstelle Einheiten aus der CSV-Datei
            List<Coordinate> positions = getUnitPositionsForMission1(); // Bekomme vordefinierte oder zufällige Positionen
            
            for (int i = 0; i < units.size() && i < positions.size(); i++)
            {
                setUnitPosition(units.get(i), (int) positions.get(i).getPositionX(), (int) positions.get(i).getPositionY());
            }
        });
    }
    
    
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
    
    
    private void setUnitPosition (Unit unit, int tileX, int tileY)
    {
        Coordinate coordinate = new Coordinate(PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(), tileX, tileY));
        Map.getInstance().getPane().getChildren().add(unit.getUnitView());
        unit.getUnitView().setCoordinates(coordinate);
        unitPositions.put(coordinate, unit);
    }
    
    
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
}