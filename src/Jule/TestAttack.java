package Battle;


import static Battle.Person.attackPerson;
import static Battle.Person.checkIfPersonIsDead;


public class TestAttack
{


    public static void main (String[] args)
    {
        // creates two objects of person
        Person hans = new Person("Hans", 80, 20, true);
        Person juergen = new Person("Juergen", 40, 50, true);


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
