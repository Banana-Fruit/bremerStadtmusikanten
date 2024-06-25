package model.player;

import resources.constants.Constants_Combat;
import control.game.UnitController;
import model.Unit;
import java.util.List;

public class FractionDonkey
{
    UnitController unitController = UnitController.getInstance();
    List<model.Unit> units = unitController.unitCreator();
    private static FractionDonkey instanceOfFractiondonkey = new FractionDonkey();
    private Unit rats = units.get(Constants_Combat.RAT);
    private Unit beetle = units.get(Constants_Combat.BEETLE);
    private Unit mosquitoes = units.get(Constants_Combat.MOSQUITOES);


    public FractionDonkey (Unit rats, Unit beetle, Unit mosquitoes)
    {
        this.rats = rats;
        this.beetle = beetle;
        this.mosquitoes = mosquitoes;
    }


    public FractionDonkey ()
    {
        ;
    }




    // getter methods
    public Unit getRats ()
    {
        return rats;
    }


    public Unit getBeetle ()
    {
        return beetle;
    }


    public Unit getMosquitoes ()
    {
        return mosquitoes;
    }


    public static FractionDonkey getInstanceOfFractiondonkey ()
    {
        return instanceOfFractiondonkey;
    }
}
