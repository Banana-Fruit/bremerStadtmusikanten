package model.player;


public class FractionRoland
{
    private static FractionRoland instanceOfFractionRoland = new FractionRoland();
    private Unit theSevenLazyOnes = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit roland = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit citizen = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit statueOfBremenTownMusicians = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit citizenWithHat = new Unit(2,2,2,2,2,2,2,2,2);


    public FractionRoland (Unit theSevenLazyOnes, Unit roland, Unit citizen, Unit statueOfBremenTownMusicians, Unit citizenWithHat)
    {
        this.theSevenLazyOnes = theSevenLazyOnes;
        this.roland = roland;
        this.citizen = citizen;
        this. statueOfBremenTownMusicians = statueOfBremenTownMusicians;
        this.citizenWithHat = citizenWithHat;
    }



    public FractionRoland ()
    {

    }


    // getter methods
    public Unit getTheSevenLazyOnes ()
    {
        return theSevenLazyOnes;
    }


    public Unit getRoland ()
    {
        return  roland;
    }


    public Unit getCitizen ()
    {
        return citizen;
    }


    public Unit getStatueOfBremenTownMusicians ()
    {
        return statueOfBremenTownMusicians;
    }


    public Unit getCitizenWithHat ()
    {
        return citizenWithHat;
    }


    public static FractionRoland getInstanceOfFractionRoland ()
    {
        return instanceOfFractionRoland;
    }
}
