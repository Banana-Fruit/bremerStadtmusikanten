package control.game;


import model.Attack;
import model.Coordinate;
import model.Unit;
import resources.constants.*;
import model.player.Player;

import java.util.*;


/**
 * The combat controller handles everything related to the combat.
 *
 * @author Michael Markov
 */
public class CombatController
{
    private static CombatController instance = null;
    
    
    public static CombatController getInstance ()
    {
        if (instance == null)
        {
            instance = new CombatController();
        }
        return instance;
    }
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new CombatController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    private CombatController ()
    {
        ;
    }
    
    
    //TODO: Anpassen Koordinaten (weiß nicht wie die Koordinaten im kampf berechenet werden / wo welche ist
    private void initializeCombatPositions (List<Unit> myUnits, List<Unit> enemies)
    {
        for (int i = Constants_DefaultValues.START_FOR_LOOP; i < myUnits.size(); i++)
        {
            myUnits.get(i).setPositionX(Constants_Combat.UNIT_POSITION_X);
            myUnits.get(i).setPositionY(i);
        }
        for (int i = Constants_DefaultValues.START_FOR_LOOP; i < enemies.size(); i++)
        {
            enemies.get(i).setPositionX(Constants_Combat.UNIT_POSITION_X);
            enemies.get(i).setPositionY(i);
        }
    }
    
    
    //TODO: Wie bekommen wir die liste der gegner? Wie bewegen wir die units?
    public void Combat (ArrayList<Unit> enemies)
    {
        
        ArrayList<Unit> myTeam = Player.getInstance().getTeamMembers();
        List<Unit> myUnits = Convert(myTeam);
        
        initializeCombatPositions(myUnits, enemies);
        
        ArrayList<Unit> participants = new ArrayList<>();
        participants.addAll(myUnits);
        participants.addAll(enemies);
        
        
        UnitController unitController = UnitController.getInstance();
        List<Attack> attacks = unitController.AttackCreator();
        Collections.sort(participants, new Comparator<Unit>()
        {
            @Override
            public int compare (Unit o1, Unit o2)
            {
                if (Integer.compare(o2.getInitiative(), o1.getInitiative()) > Constants_Sorting.ZERO)
                {
                    return (Constants_Sorting.POSITIVE);
                } else if (Integer.compare(o2.getInitiative(), o1.getInitiative()) == Constants_Sorting.ZERO)
                {
                    return (Constants_Sorting.ZERO);
                } else
                {
                    return (Constants_Sorting.NEGATIVE);
                }
            }
        });
        
        for (int n = Constants_DefaultValues.START_FOR_LOOP; n <= participants.size(); n++)
        {//WIP
            if (participants.get(n).getHealth() > Constants_Combat.DEATH)
            {
                AttackUnit(participants.get(n), choiceOfFoe(enemies), attacks.get(participants.get(n).getMyAttack()));
            } else
            {
                ArrayList<Unit> newParticipants = Die(participants.indexOf(participants.get(n)), participants);
                participants = newParticipants;
                enemies.contains(participants.get(n));
            }
        }
    }
    
    
    private static Unit choiceOfFoe (List<Unit> ListofFoes)
    {
        int indexOfChosenEnemy = Constants_Combat.INDEX_CHOOSEN_ENEMY;//Choose();
        Unit choice = ListofFoes.get(indexOfChosenEnemy);
        return choice;
    }
    
    
    public void AttackUnit (Unit attacker, Unit defender, Attack attackused)
    {
        
        boolean isMagic = attackused.getMagic();
        boolean isRanged = attackused.getRanged();
        int atkRange = attackused.getAtkRange();
        
        int shield = defender.getShield();
        int magicResist = defender.getMagicresist();
        int dodge = defender.getDodge();
        
        int meleeDamage = attacker.getMeele();
        int rangedDamage = attacker.getRanged();
        int magicDamage = attacker.getMagicDamage();
        int currentMana = attacker.getMana();
        int currentAmmo = attacker.getAmmo();
        
        float damageBlocked;
        int rawDamage;
        boolean isCloseEnough;
        int newMana;
        int newAmmo;
        
        isCloseEnough = amIcloseEnough(defender, attacker, atkRange);
        
        if (isCloseEnough == true)
        {
            if (isMagic)
            {
                if (currentMana > Constants_DefaultValues.ZERO)
                {
                    damageBlocked = magicDamage * magicResist / Constants_DefaultValues.PERCENTAGE_NUMBER;
                    rawDamage = magicDamage;
                    newMana = currentMana - Constants_DefaultValues.ONE;
                    attacker.setMana(newMana);
                    
                    if (RollDice(dodge) == true)
                    {
                        DoDamage(damageBlocked, defender, rawDamage);
                        System.out.print(defender.getHealth() + Constants_Combat.NEW_HP + defender.getName()); //nur für Debugging
                    } else
                    {
                        System.out.println(Constants_Combat.DODGED);
                    }
                }
            } else if (isRanged)
            {
                
                if (currentAmmo > Constants_DefaultValues.ZERO)
                {
                    damageBlocked = rangedDamage * shield / Constants_DefaultValues.PERCENTAGE_NUMBER;
                    rawDamage = rangedDamage;
                    newAmmo = currentAmmo - Constants_DefaultValues.ONE;
                    attacker.setAmmo(newAmmo);
                    if (RollDice(dodge) == true)
                    {
                        DoDamage(damageBlocked, defender, rawDamage);
                        System.out.print(defender.getHealth() + Constants_Combat.NEW_HP + defender.getName()); //nur für Debugging
                    } else
                    {
                        System.out.println(Constants_Combat.DODGED);
                    }
                }
            } else
            {
                
                damageBlocked = meleeDamage * shield / Constants_DefaultValues.PERCENTAGE_NUMBER;
                rawDamage = meleeDamage;
                if (RollDice(dodge) == true)
                {
                    DoDamage(damageBlocked, defender, rawDamage);
                    System.out.print(defender.getHealth() + Constants_Combat.NEW_HP + defender.getName()); //nur für Debugging
                } else
                {
                    System.out.println(Constants_Combat.DODGED);
                }
            }
        } else
        {
            System.out.println(Constants_Combat.NOT_CLOSE);
        }
    }
    
    
    public void DoDamage (float DmgBlocked, Unit Defender, int rawDamage)
    {
        int curHP = Defender.getHealth();
        int roundDamageDealt;
        int newHP;
        
        roundDamageDealt = Math.round(rawDamage - DmgBlocked);
        newHP = curHP - roundDamageDealt;
        Defender.setHealth(newHP);
    }
    
    
    public boolean RollDice (int chance)
    {
        Random random = new Random();
        boolean didDodge;
        int roll = random.nextInt();
        if (roll <= chance)
        {
            didDodge = true;
            return didDodge;
        } else
        {
            didDodge = false;
            return didDodge;
        }
    }
    
    
    public boolean amIcloseEnough (Unit defender, Unit attacker, int atkRange)
    {
        boolean isCloseEnough;
        double distanceX = defender.getPositionX() - attacker.getPositionX();
        double distanceY = defender.getPositionY() - attacker.getPositionY();
        double distance = distanceX + distanceY;
        if (distance <= atkRange)
        {
            isCloseEnough = true;
        } else
        {
            isCloseEnough = false;
        }
        return isCloseEnough;
    }
    
    
    public List<Unit> Convert (ArrayList<Unit> Gegner)
    {
        List<Unit> units = new ArrayList<>();
        
        for (int i = Constants_Player_Units.ZERO; i < Gegner.size(); i++)
        {
            units.add(Gegner.get(i));
        }
        return units;
    }
    
    
    public ArrayList<Unit> Die (int IndexofDeadUnit, ArrayList<Unit> participants)
    {
        participants.remove(IndexofDeadUnit);
        return participants;
    }
    
    
    public void move (Unit unit, Coordinate newUnitPosition)
    {
        Coordinate oldPosition = new Coordinate(unit.getPositionX(), unit.getPositionY());
        unit.setPositionX(newUnitPosition.getPositionX());
        unit.setPositionY(newUnitPosition.getPositionY());
    }


//DEBUG
	/*public static void TestCombat()
	{
		UnitController UC = UnitController.getInstance();
		CombatController CC = CombatController.getInstance();
		CC.Combat(UC.unitCreator());
	}*/
}
