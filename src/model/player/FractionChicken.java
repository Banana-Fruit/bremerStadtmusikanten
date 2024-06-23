package model.player;


public class FractionChicken
{
    private static FractionChicken instanceOfFractionChicken = new FractionChicken();
    private Unit chick = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit fightingChicken = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit chef = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit turkey = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit chickenWithHat = new Unit(2,2,2,2,2,2,2,2,2);


    public FractionChicken (Unit chick, Unit fightingChicken, Unit chef, Unit turkey, Unit chickenWithHat)
    {
        this.chick = chick;
        this.fightingChicken = fightingChicken;
        this.chef = chef;
        this.turkey = turkey;
        this.chickenWithHat = chickenWithHat;
    }


    public FractionChicken ()
    {
        ;
    }


    // getter Methods
    public Unit getChick ()
    {
        return chick;
    }


    public Unit getFightingChicken ()
    {
        return fightingChicken;
    }


    public Unit getChef ()
    {
        return chef;
    }


    public Unit getTurkey ()
    {
        return turkey;
    }


    public Unit getChickenWithHat ()
    {
        return chickenWithHat;
    }


    public static FractionChicken getInstanceOfFractionChicken ()
    {
        return instanceOfFractionChicken;
    }
}
