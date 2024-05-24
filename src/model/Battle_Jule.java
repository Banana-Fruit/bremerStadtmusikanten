package model;


import src.resources.Constants_BattleJule;


public class Battle_Jule
{
    // attributes of a person
    private final String NAME;
    private int health;
    private int attack;
    private boolean isAlive;


    //constructor
    public Battle_Jule (String name, int health, int attack, boolean isAlive)
    {
        this.NAME = name;
        this.health = health;
        this.attack = attack;
        this.isAlive = isAlive;
    }


    public void tellMeAboutYou (Battle_Jule person)
    {
        System.out.printf(Constants_BattleJule.INTRODUCTION_PERSON, person.getNAME(), person.getHealth());
    }


    public static void attackPerson (Battle_Jule bully, Battle_Jule victim)
    {
        // for the EA: more attributes than only health
        System.out.printf(Constants_BattleJule.ATTACK, bully.getNAME(), victim.getNAME(), bully.getAttack());
        victim.setHealth(victim.getHealth() - bully.getAttack());
        System.out.printf(Constants_BattleJule.DAMAGE, victim.getNAME(), victim.getHealth());
    }


    public static void checkIfPersonIsDead (Battle_Jule person)
    {
        // for the EA: implements as a thread that checks every second if an object is dead
        if (person.getHealth() <= Constants_BattleJule.LIMIT_HEALTH)
        {
            person.isAlive = false;
            person.lockDeadPerson(person);
            System.out.printf(Constants_BattleJule.PERSON_IS_DEAD, person.getNAME());
        }
        else
        {
            System.out.printf(Constants_BattleJule.PERSON_IS_ALIVE, person.getNAME());
        }
    }


    private void lockDeadPerson (Battle_Jule person)
    {
            if (!person.isAlive)
            {
                System.out.printf(Constants_BattleJule.PERSON_IS_LOCK, person.getNAME());
                // for the EA: Object person is lock and disappears from the GUI
            }
    }


    public String getNAME ()
    {
        return NAME;
    }


    public int getHealth ()
    {
        return health;
    }


    public int getAttack ()
    {
        return attack;
    }

    public void setHealth (int health)
    {
        this.health = health;
    }

    public static void main (String[] args)
    {
        // creates two objects of person
        Battle_Jule hans = new Battle_Jule("Hans", 80, 20, true);
        Battle_Jule juergen = new Battle_Jule("Juergen", 40, 50, true);


        // Introduction of the persons
        hans.tellMeAboutYou(hans);
        juergen.tellMeAboutYou(juergen);

        // first attack
        attackPerson(hans, juergen);
        checkIfPersonIsDead(juergen);

        // attack back
        attackPerson(juergen, hans);
        checkIfPersonIsDead(hans);

        // second attack
        attackPerson(hans, juergen);
        checkIfPersonIsDead(juergen);


        //Ideen zum Erweitern:
        // Zufallszahlen für Attacke
        // verschiedene Attacken implementieren (Nah- und Fernangriff (boolean isNear))
    }
}
