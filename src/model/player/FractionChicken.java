package model.player;


import resources.constants.Constants_Combat;
import control.game.UnitController;
import model.Unit;

import java.util.List;


/**
 * The class FractionChicken contains alle methods to create an instance of the fraction chicken.
 *
 * @author Jule Degener
 */
public class FractionChicken
{
    UnitController unitController = UnitController.getInstance();
    List<model.Unit> units = unitController.createUnit();
    private static FractionChicken instanceOfFractionChicken = new FractionChicken();
    private Unit chef = units.get(Constants_Combat.CHEF);
    private Unit turkey = units.get(Constants_Combat.TURKEY);
    private Unit chickenWithHat = units.get(Constants_Combat.CHICKEN_WITH_HAT);
    private Unit chick = units.get(Constants_Combat.CHICK);
    private Unit fightingChicken = units.get(Constants_Combat.FIGHTING_CHICKEN);


    /**
     * Default constructor
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Instance of FractionChicken is created without any parameters.
     */
    public FractionChicken ()
    {
        ;
    }


    /**
     * Getter-method to access of the unit chick.
     *
     * @author Jule Degener
     * @return The unit chick is returned.
     * @precondition none
     * @postcondition Access of the unit chick.
     */
    public Unit getChick ()
    {
        return chick;
    }


    /**
     * Getter-method to access of the unit fightingChicken.
     *
     * @author Jule Degener
     * @return The unit fightingChicken is returned.
     * @precondition none
     * @postcondition Access of the unit fightingChicken.
     */
    public Unit getFightingChicken ()
    {
        return fightingChicken;
    }


    /**
     * Getter-method to access of the unit chef.
     *
     * @author Jule Degener
     * @return The unit chef is returned.
     * @precondition none
     * @postcondition Access of the unit chef.
     */
    public Unit getChef ()
    {
        return chef;
    }


    /**
     * Getter-method to access of the unit turkey.
     *
     * @author Jule Degener
     * @return The unit turkey is returned.
     * @precondition none
     * @postcondition Access of the unit turkey.
     */
    public Unit getTurkey ()
    {
        return turkey;
    }


    /**
     * Getter-method to access of the unit ChickenWithHat.
     *
     * @author Jule Degener
     * @return The unit ChickenWithHat is returned.
     * @precondition none
     * @postcondition Access of the unit ChickenWithHat.
     */
    public Unit getChickenWithHat ()
    {
        return chickenWithHat;
    }


    /**
     * Getter-method to access of the instance of FractionChicken.
     *
     * @author Jule Degener
     * @return The instance of FractionChicken is returned.
     * @precondition none
     * @postcondition Access of the instance of FractionChicken.
     */
    public static FractionChicken getInstanceOfFractionChicken ()
    {
        return instanceOfFractionChicken;
    }
}
