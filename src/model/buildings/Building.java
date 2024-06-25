package model.buildings;


import model.Coordinate;

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



    // constructor
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


    // default constructor
    public Building ()
    {
        ;
    }


    public abstract void unlockBuilding ();

    public void setUnlocked (boolean unlocked)
    {
        isUnlocked = unlocked;
    }

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


    public Coordinate getPositionUpperLeft ()
    {
        return positionUpperLeft;
    }


}
