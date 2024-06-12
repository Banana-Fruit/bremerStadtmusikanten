package model.buildings;


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


    // constructor
    public Building (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer,
    int numberOfEssence, boolean isUnlocked)
    {
        this.name = name;
        this.numberOfGold = numberOfGold;
        this.numberOfBrick = numberOfBrick;
        this.numberOfWood = numberOfWood;
        this.numberOfBeer = numberOfBeer;
        this.numberOfEssence = numberOfEssence;
        this.isUnlocked = isUnlocked;
    }


    // default constructor
    public Building ()
    {
        ;
    }


    public abstract void unlockBuilding ();



    // getter methods
    public String getName ()
    {
        return name;
    }


    public int getNumberOfGold ()
    {
        return numberOfGold;
    }


    public int getNumberOfBrick ()
    {
        return numberOfBrick;
    }


    public int getNumberOfWood ()
    {
        return numberOfWood;
    }


    public int getNumberOfBeer ()
    {
         return numberOfBeer;
    }


    public int getNumberOfEssence ()
    {
        return numberOfEssence;
    }


    public boolean getIsUnlocked ()
    {
        return isUnlocked;
    }

}
