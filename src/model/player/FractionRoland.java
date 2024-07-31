package model.player;


import resources.constants.Constants_Combat;
import control.game.UnitController;
import model.Unit;

import java.util.List;


/**
 * The class FractionRoland contains alle methods to create an instance of the fraction roland.
 *
 * @author Jule Degener
 */
public class FractionRoland
{
    UnitController unitController = UnitController.getInstance();
    List<model.Unit> units = unitController.createUnit();
    private static FractionRoland instanceOfFractionRoland = new FractionRoland();
    private Unit citizen = units.get(Constants_Combat.CITIZEN);
    private Unit statueOfBremerStadtmusikanten = units.get(Constants_Combat.STATUE_OF_BREMERSTADTMUSIKANTEN);
    private Unit citizenWithHat = units.get(Constants_Combat.CITIZEN_WITH_HAT);
    private Unit theSevenLazyOnes = units.get(Constants_Combat.SEVEN_LAZY_ONES);
    private Unit roland = units.get(Constants_Combat.ROLAND);


    /**
     * Default constructor
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Instance of FractionRoland is created without any parameters.
     */
    public FractionRoland ()
    {
    
    }


    /**
     * Getter-method to access of the unit TheSevenLazyOnes.
     *
     * @author Jule Degener
     * @return The unit TheSevenLazyOnes is returned.
     * @precondition none
     * @postcondition Access of the unit TheSevenLazyOnes.
     */
    public Unit getTheSevenLazyOnes ()
    {
        return theSevenLazyOnes;
    }


    /**
     * Getter-method to access of the unit Roland.
     *
     * @author Jule Degener
     * @return The unit Roland is returned.
     * @precondition none
     * @postcondition Access of the unit Roland.
     */
    public Unit getRoland ()
    {
        return roland;
    }


    /**
     * Getter-method to access of the unit Citizen.
     *
     * @author Jule Degener
     * @return The unit Citizen is returned.
     * @precondition none
     * @postcondition Access of the unit Citizen.
     */
    public Unit getCitizen ()
    {
        return citizen;
    }


    /**
     * Getter-method to access of the unit StatueOfBremerStadtmusikanten.
     *
     * @author Jule Degener
     * @return The unit StatueOfBremerStadtmusikanten is returned.
     * @precondition none
     * @postcondition Access of the unit StatueOfBremerStadtmusikanten.
     */
    public Unit getStatueOfBremerStadtmusikanten ()
    {
        return statueOfBremerStadtmusikanten;
    }


    /**
     * Getter-method to access of the unit CitizenWithHat.
     *
     * @author Jule Degener
     * @return The unit CitizenWithHat is returned.
     * @precondition none
     * @postcondition Access of the unit CitizenWithHat.
     */
    public Unit getCitizenWithHat ()
    {
        return citizenWithHat;
    }


    /**
     * Getter-method to access of the instance of FractionRoland.
     *
     * @author Jule Degener
     * @return The instance of FractionRoland is returned.
     * @precondition none
     * @postcondition Access of the instance of FractionRoland.
     */
    public static FractionRoland getInstanceOfFractionRoland ()
    {
        return instanceOfFractionRoland;
    }
}
