package model.buildings;


import model.Coordinate;


/**
 * The abstract class Building is a template to create buildings.
 *
 * @author Jule Degener
 */
public abstract class Building
{
    // attributes
    public String name;
    public int numberOfGold;
    public int numberOfBrick;
    public int numberOfWood;
    public int numberOfBeer;
    public int numberOfEssence;
    public boolean isUnlocked;
    public Coordinate positionUpperLeft;


    /**
     * Constructor to create an instance of a building
     *
     * @author Jule Degener
     * @param name Name of the building
     * @param numberOfGold gold-price of the building
     * @param numberOfBrick brick-price of the building
     * @param numberOfWood wood-price of the building
     * @param numberOfBeer beer-price of the building
     * @param numberOfEssence essence-price of the building
     * @param isUnlocked boolean-value to see if a building is unlocked
     * @param positionUpperLeft Coordinate from the building
     * @precondition none
     * @postcondition An instance of a building is created.
     */
    public Building (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer,
                     int numberOfEssence, boolean isUnlocked, Coordinate positionUpperLeft)
    {
        this.name = name;
        this.numberOfGold = numberOfGold;
        this.numberOfBrick = numberOfBrick;
        this.numberOfWood = numberOfWood;
        this.numberOfBeer = numberOfBeer;
        this.numberOfEssence = numberOfEssence;
        this.isUnlocked = isUnlocked;
        this.positionUpperLeft = positionUpperLeft;
    }


    /**
     * Default constructor
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition An instance of a building is created without any parameters.
     */
    public Building ()
    {
        ;
    }


    /**
     * Abstract method to unlock a building.
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition none
     */
    public abstract void unlockBuilding ();


    /**
     * Setter-method to set the value of the boolean attribute isUnlocked.
     *
     * @author Jonas Helfer
     * @param unlocked Boolean value
     * @precondition none
     * @postcondition Parameter unlocked is the current value of the attribute isUnlocked.
     */
    public void setUnlocked (boolean unlocked)
    {
        isUnlocked = unlocked;
    }


    /**
     * Getter-method to have access of the attribute name.
     *
     * @author Jule Degener
     * @return The attribute name is returned.
     * @precondition none
     * @postcondition Access of the attribute name
     */
    public String getName ()
    {
        return name;
    }


    /**
     * Getter-method to have access of the attribute numberOfGold.
     *
     * @author Jule Degener
     * @return The attribute numberOfGold is returned.
     * @precondition none
     * @postcondition Access of the attribute numberOfGold
     */
    public int getNumberOfGold ()
    {
        return numberOfGold;
    }


    /**
     * Getter-method to have access of the attribute numberOfBrick.
     *
     * @author Jule Degener
     * @return The attribute numberOfBrick is returned.
     * @precondition none
     * @postcondition Access of the attribute numberOfBrick
     */
    public int getNumberOfBrick ()
    {
        return numberOfBrick;
    }


    /**
     * Getter-method to have access of the attribute numberOfWood.
     *
     * @author Jule Degener
     * @return The attribute numberOfWood is returned.
     * @precondition none
     * @postcondition Access of the attribute numberOfWood
     */
    public int getNumberOfWood ()
    {
        return numberOfWood;
    }


    /**
     * Getter-method to have access of the attribute numberOfBeer.
     *
     * @author Jule Degener
     * @return The attribute numberOfBeer is returned.
     * @precondition none
     * @postcondition Access of the attribute numberOfBeer
     */
    public int getNumberOfBeer ()
    {
        return numberOfBeer;
    }


    /**
     * Getter-method to have access of the attribute numberOfEssence.
     *
     * @author Jule Degener
     * @return The attribute numberOfEssence is returned.
     * @precondition none
     * @postcondition Access of the attribute numberOfEssence
     */
    public int getNumberOfEssence ()
    {
        return numberOfEssence;
    }


    /**
     * Getter-method to have access of the attribute isUnlocked.
     *
     * @author Jule Degener
     * @return The attribute isUnlocked is returned.
     * @precondition none
     * @postcondition Access of the attribute isUnlocked
     */
    public boolean getIsUnlocked ()
    {
        return isUnlocked;
    }


    /**
     * Getter-method to have access of the attribute positionUpperLeft.
     *
     * @author Jonas Helfer
     * @return The attribute positionUpperLeft is returned.
     * @precondition none
     * @postcondition Access of the attribute positionUpperLeft
     */
    public Coordinate getPositionUpperLeft ()
    {
        return positionUpperLeft;
    }
}
