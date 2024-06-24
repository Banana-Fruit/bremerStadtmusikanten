package model.player;

import resources.constants.Constants_Combat;
import model.Unit;
import control.game.UnitController;

import java.util.List;

public class FractionDog
{
    UnitController unitController = UnitController.getInstance();
    List<model.Unit> units = unitController.UnitCreator();
    private static FractionDog instanceOfFractionDog = new FractionDog();
    private Unit hunter = units.get(Constants_Combat.HUNTER);
    private Unit bulldog = units.get(Constants_Combat.MASTIFF);
    private Unit Hundini = units.get(Constants_Combat.HUNDINI);
    private Unit goldenRetriever = units.get(Constants_Combat.GOLDEN_RETRIEVER);
    private Unit germanShepherd = units.get(Constants_Combat.GERMAN_SHEPHERD);


    public FractionDog (Unit goldenRetriever, Unit germanShepherd, Unit hunter, Unit bulldog, Unit Hundini)
    {
        this.goldenRetriever = goldenRetriever;
        this.germanShepherd = germanShepherd;
        this.hunter = hunter;
        this.bulldog = bulldog;
        this.Hundini = Hundini;
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


    public Unit getHundini()
    {
        return Hundini;
    }


    public static FractionDog getInstanceOfFractionDog ()
    {
        return instanceOfFractionDog;
    }
}
