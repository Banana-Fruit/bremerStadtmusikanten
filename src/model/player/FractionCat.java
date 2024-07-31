package model.player;


import control.game.UnitController;
import model.Unit;
import resources.constants.Constants_Combat;

import java.util.List;


/**
 * The class FractionCat contains alle methods to create an instance of the fraction cat.
 *
 * @author Jule Degener
 */
public class FractionCat
{
    UnitController unitController = UnitController.getInstance();
    List<Unit> units = unitController.createUnit();
    private static FractionCat instanceOfFractionCat = new FractionCat();
    private Unit housekeeper = units.get(Constants_Combat.HOUSEKEEPER);
    private Unit jaguar = units.get(Constants_Combat.JAGUAR);
    private Unit Bingus = units.get(Constants_Combat.BINGUS);
    private Unit cat = units.get(Constants_Combat.CAT);
    private Unit tiger = units.get(Constants_Combat.TIGER);


    /**
     * Default constructor
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Instance of FractionCat is created without any parameters.
     */
    public FractionCat ()
    {
    
    }


    /**
     * Getter-method to access of the unit cat.
     *
     * @author Jule Degener
     * @return The unit cat is returned.
     * @precondition none
     * @postcondition Access of the unit cat.
     */
    public Unit getCat ()
    {
        return cat;
    }


    /**
     * Getter-method to access of the unit tiger.
     *
     * @author Jule Degener
     * @return The unit tiger is returned.
     * @precondition none
     * @postcondition Access of the unit tiger.
     */
    public Unit getTiger ()
    {
        return tiger;
    }


    /**
     * Getter-method to access of the unit housekeeper.
     *
     * @author Jule Degener
     * @return The unit housekeeper is returned.
     * @precondition none
     * @postcondition Access of the unit housekeeper.
     */
    public Unit getHousekeeper ()
    {
        return housekeeper;
    }


    /**
     * Getter-method to access of the unit jaguar.
     *
     * @author Jule Degener
     * @return The unit jaguar is returned.
     * @precondition none
     * @postcondition Access of the unit jaguar.
     */
    public Unit getJaguar ()
    {
        return jaguar;
    }


    /**
     * Getter-method to access of the unit bingus.
     *
     * @author Jule Degener
     * @return The unit bingus is returned.
     * @precondition none
     * @postcondition Access of the unit bingus.
     */
    public Unit getBingus ()
    {
        return Bingus;
    }


    /**
     * Getter-method to access of the instance of FractionCat.
     *
     * @author Jule Degener
     * @return The instance of FractionCat is returned.
     * @precondition none
     * @postcondition Access of the instance of FractionCat.
     */
    public static FractionCat getInstanceOfFractionCat ()
    {
        return instanceOfFractionCat;
    }
}
