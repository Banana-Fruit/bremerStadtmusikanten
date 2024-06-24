package model.player;

import resources.constants.Constants_Combat;
import control.game.UnitController;
import model.Unit;
import java.util.List;

public class FractionRoland
{
    UnitController unitController = UnitController.getInstance();
    List<model.Unit> units = unitController.UnitCreator();
    private static FractionRoland instanceOfFractionRoland = new FractionRoland();
    private Unit citizen = units.get(Constants_Combat.CITIZEN);
    private Unit statueOfBremerStadtmusikanten = units.get(Constants_Combat.STATUE_OF_BREMERSTADTMUSIKANTEN);
    private Unit citizenWithHat = units.get(Constants_Combat.CITIZEN_WITH_HAT);
    private Unit theSevenLazyOnes = units.get(Constants_Combat.SEVEN_LAZY_ONES);
    private Unit roland = units.get(Constants_Combat.ROLAND);


    public FractionRoland (Unit theSevenLazyOnes, Unit roland, Unit citizen, Unit statueOfBremerStadtmusikanten, Unit citizenWithHat)
    {
        this.theSevenLazyOnes = theSevenLazyOnes;
        this.roland = roland;
        this.citizen = citizen;
        this.statueOfBremerStadtmusikanten = statueOfBremerStadtmusikanten;
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


    public Unit getStatueOfBremerStadtmusikanten()
    {
        return statueOfBremerStadtmusikanten;
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
