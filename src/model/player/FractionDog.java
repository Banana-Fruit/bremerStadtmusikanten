package model.player;


public class FractionDog
{
    private static FractionDog instanceOfFractionDog = new FractionDog();
    private Unit goldenRetriever = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit germanShepherd = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit hunter = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit bulldog = new Unit(2,2,2,2,2,2,2,2,2);
    private Unit dogWithHat = new Unit(2,2,2,2,2,2,2,2,2);


    public FractionDog (Unit goldenRetriever, Unit germanShepherd, Unit hunter, Unit bulldog, Unit dogWithHat)
    {
        this.goldenRetriever = goldenRetriever;
        this.germanShepherd = germanShepherd;
        this.hunter = hunter;
        this.bulldog = bulldog;
        this.dogWithHat = dogWithHat;
    }


    public FractionDog ()
    {
        ;
    }


    // getter methods
    public Unit getGoldenRetriever ()
    {
        return goldenRetriever;
    }


    public Unit getGermanShepherd ()
    {
        return germanShepherd;
    }


    public Unit getHunter ()
    {
        return hunter;
    }


    public Unit getBulldog ()
    {
        return bulldog;
    }


    public Unit getDogWithHat ()
    {
        return dogWithHat;
    }


    public static FractionDog getInstanceOfFractionDog ()
    {
        return instanceOfFractionDog;
    }
}
