package model.player;


public class FractionCat
{
    private static FractionCat instanceOfFractionCat = new FractionCat();
    private Unit cat = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit tiger = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit housekeeper = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit jaguar = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit catWithHat = new Unit(2,2,2,2,2,2,2,2,2);



    public FractionCat (Unit cat, Unit tiger, Unit housekeeper, Unit jaguar, Unit catWithHat)
    {
        this.cat = cat;
        this.tiger = tiger;
        this.housekeeper = housekeeper;
        this.jaguar = jaguar;
        this.catWithHat = catWithHat;
    }


    public FractionCat ()
    {
        ;
    }


    // getter methods
    public Unit getCat ()
    {
        return cat;
    }


    public Unit getTiger ()
    {
        return tiger;
    }


    public Unit getHousekeeper ()
    {
        return housekeeper;
    }


    public Unit getJaguar ()
    {
        return jaguar;
    }


    public Unit getCatWithHat ()
    {
        return catWithHat;
    }


    public static FractionCat getInstanceOfFractionCat ()
    {
        return instanceOfFractionCat;
    }
}
