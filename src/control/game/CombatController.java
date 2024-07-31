package control.game;

import model.Attack;
import model.Coordinate;
import model.Unit;
import resources.constants.*;
import model.player.Player;

import java.util.*;

/**
 * The CombatController class handles all operations related to combat.
 * It is implemented as a singleton to ensure only one instance manages the combat state.
 *
 * @author Jonas Stamminger
 */
public class CombatController {
    private static CombatController instance = null;

    /**
     * Retrieves the single instance of CombatController.
     *
     * @author Jonas Stamminger
     * @return The instance of CombatController.
     */
    public static CombatController getInstance() {
        if (instance == null) {
            instance = new CombatController();
        }
        return instance;
    }

    /**
     * Private constructor to prevent instantiation.
     * @author Jonas Stamminger
     */
    private CombatController() {
        ;
    }

    /**
     * Initializes the positions of units in combat.
     *
     * @author Jonas Stamminger
     * @param myUnits List of player's units.
     * @param enemies List of enemy units.
     */
    private void initializeCombatPositions(List<Unit> myUnits, List<Unit> enemies) {
        for (int i = Constants_DefaultValues.START_FOR_LOOP; i < myUnits.size(); i++) {
            myUnits.get(i).setPositionX(Constants_Combat.UNIT_POSITION_X);
            myUnits.get(i).setPositionY(i);
        }
        for (int i = Constants_DefaultValues.START_FOR_LOOP; i < enemies.size(); i++) {
            enemies.get(i).setPositionX(Constants_Combat.UNIT_POSITION_X);
            enemies.get(i).setPositionY(i);
        }
    }

    /**
     * Initializes the CombatController instance.
     *
     * @author Jonas Stamminger
     * @throws IllegalStateException if the instance is already initialized.
     */
    public static synchronized void initialize() {
        //Check if CombatController is already initilized
        if (instance == null) {
            instance = new CombatController();
        } else {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }

    /**
     * Manages the combat process between player's units and enemy units.
     *
     * @author Jonas Stamminger
     * @param enemies List of enemy units.
     */
    public void Combat(ArrayList<Unit> enemies) {
        //Retrieving the player built team of units
        ArrayList<Unit> myTeam = Player.getInstance().getTeamMembers();

        //Conversion from ArrayList to List becourse of incompatibility
        List<Unit> myUnits = Convert(myTeam);

        //Putting all units present in the fight on to their starting position
        initializeCombatPositions(myUnits, enemies);

        //Combining hostile and friendly units to be able to determine attack order
        ArrayList<Unit> participants = new ArrayList<>();
        participants.addAll(myUnits);
        participants.addAll(enemies);

        UnitController unitController = UnitController.getInstance();
        List<Attack> attacks = unitController.AttackCreator();

        //Sorting all participating units by initiative stat
        Collections.sort(participants, new Comparator<Unit>() {
            @Override
            public int compare(Unit o1, Unit o2) {
                if (Integer.compare(o2.getInitiative(), o1.getInitiative()) > Constants_Sorting.ZERO) {
                    return Constants_Sorting.POSITIVE;
                } else if (Integer.compare(o2.getInitiative(), o1.getInitiative()) == Constants_Sorting.ZERO) {
                    return Constants_Sorting.ZERO;
                } else {
                    return Constants_Sorting.NEGATIVE;
                }
            }
        });

        //Loop iterating through the sorted participant list
        for (int n = Constants_DefaultValues.START_FOR_LOOP; n <= participants.size(); n++) {
            //check if given unit is alive and acting accordingly
            if (participants.get(n).getHealth() > Constants_Combat.DEATH) {
                //Automatically attacking as this is unfinished
                AttackUnit(participants.get(n), choiceOfFoe(enemies), attacks.get(participants.get(n).getMyAttack()));
            } else {
                ArrayList<Unit> newParticipants = Die(participants.indexOf(participants.get(n)), participants);
                participants = newParticipants;
                enemies.contains(participants.get(n));
            }
        }
    }

    /**
     * Chooses a foe from the list of enemies.
     *
     * @author Jonas Stamminger
     * @param ListofFoes List of enemy units.
     * @return The chosen enemy unit.
     */
    private static Unit choiceOfFoe(List<Unit> ListofFoes) {
        //Placeholder, should determine what unit was clicked
        int indexOfChosenEnemy = Constants_Combat.INDEX_CHOOSEN_ENEMY;
        Unit choice = ListofFoes.get(indexOfChosenEnemy);
        return choice;
    }

    /**
     * Executes an attack from one unit to another.
     *
     * @author Jonas Stamminger
     * @param attacker The attacking unit.
     * @param defender The defending unit.
     * @param attackused The attack being used.
     */
    public void AttackUnit(Unit attacker, Unit defender, Attack attackused) {
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

        //Check if units are close enough to hit each other
        if (isCloseEnough == true) {
            //Check type of attack used
            if (isMagic) {
                //Check if there is enough mana left
                if (currentMana > Constants_DefaultValues.ZERO) {
                    //Amount of damage that is blocked by Magic Resistance
                    damageBlocked = magicDamage * magicResist / Constants_DefaultValues.PERCENTAGE_NUMBER;
                    rawDamage = magicDamage;
                    //Reducing the amount of mana left for the given unit
                    newMana = currentMana - Constants_DefaultValues.ONE;
                    attacker.setMana(newMana);
                    //Check if the attack hits its target
                    if (RollDice(dodge) == true) {
                        //Calculating how much Damage is done to the Target
                        DoDamage(damageBlocked, defender, rawDamage);
                        //Output for Testing
                        System.out.print(defender.getHealth() + Constants_Combat.NEW_HP + defender.getName());
                    } else {
                        //Output for Testing
                        System.out.println(Constants_Combat.DODGED);
                    }
                }
            } else if (isRanged) {
                //Check if there is enough mana left
                if (currentAmmo > Constants_DefaultValues.ZERO) {
                    //Amount of damage that is blocked by armor
                    damageBlocked = rangedDamage * shield / Constants_DefaultValues.PERCENTAGE_NUMBER;
                    rawDamage = rangedDamage;
                    //Reducing the amount of ammunition left for the given unit
                    newAmmo = currentAmmo - Constants_DefaultValues.ONE;
                    attacker.setAmmo(newAmmo);
                    //Check if the attack hits its target
                    if (RollDice(dodge) == true) {
                        //Calculating how much Damage is done to the Target
                        DoDamage(damageBlocked, defender, rawDamage);
                        //Output for Testing
                        System.out.print(defender.getHealth() + Constants_Combat.NEW_HP + defender.getName());
                    } else {
                        //Output for Testing
                        System.out.println(Constants_Combat.DODGED);
                    }
                }
            } else {
                //Amount of damage that is blocked by armor
                damageBlocked = meleeDamage * shield / Constants_DefaultValues.PERCENTAGE_NUMBER;
                rawDamage = meleeDamage;
                if (RollDice(dodge) == true) {
                    //Calculating how much Damage is done to the Target
                    DoDamage(damageBlocked, defender, rawDamage);
                    //Output for Testing
                    System.out.print(defender.getHealth() + Constants_Combat.NEW_HP + defender.getName());
                } else {
                    //Output for Testing
                    System.out.println(Constants_Combat.DODGED);
                }
            }
        } else {
            //Output for Testing
            System.out.println(Constants_Combat.NOT_CLOSE);
        }
    }

    /**
     * Applies damage to the defender.
     *
     * @author Jonas Stamminger
     * @param DmgBlocked The amount of damage blocked.
     * @param Defender The defending unit.
     * @param rawDamage The raw damage dealt.
     */
    public void DoDamage(float DmgBlocked, Unit Defender, int rawDamage) {
        int curHP = Defender.getHealth();
        int roundDamageDealt;
        int newHP;

        roundDamageDealt = Math.round(rawDamage - DmgBlocked);
        newHP = curHP - roundDamageDealt;
        Defender.setHealth(newHP);
    }

    /**
     * Rolls a dice to determine if the defender dodges the attack.
     *
     * @author Jonas Stamminger
     * @param chance The chance to dodge.
     * @return True if dodged, false otherwise.
     */
    public boolean RollDice(int chance) {
        Random random = new Random();
        boolean didDodge;
        int roll = random.nextInt();
        //Check if dodge roll is successful
        if (roll <= chance) {
            didDodge = true;
            return didDodge;
        } else {
            didDodge = false;
            return didDodge;
        }
    }

    /**
     * Determines if the attacker is close enough to the defender to attack.
     *
     * @author Jonas Stamminger
     * @param defender The defending unit.
     * @param attacker The attacking unit.
     * @param atkRange The attack range.
     * @return True if the attacker is close enough, false otherwise.
     */
    public boolean amIcloseEnough(Unit defender, Unit attacker, int atkRange) {
        boolean isCloseEnough;
        double distanceX = defender.getPositionX() - attacker.getPositionX();
        double distanceY = defender.getPositionY() - attacker.getPositionY();
        double distance = distanceX + distanceY;
        //Check if one unit is close enough to another to hit it
        if (distance <= atkRange) {
            isCloseEnough = true;
        } else {
            isCloseEnough = false;
        }
        return isCloseEnough;
    }

    /**
     * Converts an ArrayList of units to a List of units.
     *
     * @author Jonas Stamminger
     * @param Gegner The ArrayList of units.
     * @return A List of units.
     */
    public List<Unit> Convert(ArrayList<Unit> Gegner) {
        List<Unit> units = new ArrayList<>();

        for (int i = Constants_Player_Units.ZERO; i < Gegner.size(); i++) {
            units.add(Gegner.get(i));
        }
        return units;
    }

    /**
     * Removes a unit that has died from the participants list.
     *
     * @author Jonas Stamminger
     * @param IndexofDeadUnit The index of the dead unit.
     * @param participants The list of participants.
     * @return The updated list of participants.
     */
    public ArrayList<Unit> Die(int IndexofDeadUnit, ArrayList<Unit> participants) {
        participants.remove(IndexofDeadUnit);
        return participants;
    }

    /**
     * Moves a unit to a new position.
     *
     * @author Jonas Stamminger
     * @param unit The unit to move.
     * @param newUnitPosition The new position of the unit.
     */
    public void move(Unit unit, Coordinate newUnitPosition) {
        Coordinate oldPosition = new Coordinate(unit.getPositionX(), unit.getPositionY());
        unit.setPositionX(newUnitPosition.getPositionX());
        unit.setPositionY(newUnitPosition.getPositionY());
    }

}
