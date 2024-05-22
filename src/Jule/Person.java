package Battle;


public class Person
{
    // attributes of a person
    private final String NAME;
    private int health;
    private int attack;
    private boolean isAlive;


    //constructor
    public Person (String name, int health, int attack, boolean isAlive)
    {
        this.NAME = name;
        this.health = health;
        this.attack = attack;
        this.isAlive = isAlive;
    }


    public void tellMeAboutYou (Person person)
    {
        System.out.printf(Constants.INTRODUCTION_PERSON, person.getNAME(), person.getHealth());
    }


    public static void attackPerson (Person bully, Person victim)
    {
        // for the EA: more attributes than only health
        System.out.printf(Constants.ATTACK, bully.getNAME(), victim.getNAME(), bully.getAttack());
        victim.setHealth(victim.getHealth() - bully.getAttack());
        System.out.printf(Constants.DAMAGE, victim.getNAME(), victim.getHealth());
    }


    public static void checkIfPersonIsDead (Person person)
    {
        // for the EA: implements as a thread that checks every second if an object is dead
        if (person.getHealth() <= Constants.LIMIT_HEALTH)
        {
            person.isAlive = false;
            person.lockDeadPerson(person);
            System.out.printf(Constants.PERSON_IS_DEAD, person.getNAME());
        }
        else
        {
            System.out.printf(Constants.PERSON_IS_ALIVE, person.getNAME());
        }
    }


    private void lockDeadPerson (Person person)
    {
            if (!person.isAlive)
            {
                System.out.printf(Constants.PERSON_IS_LOCK, person.getNAME());
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
}
