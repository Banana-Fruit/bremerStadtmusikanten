package model.player;


import resources.constants.Constants_Combat;
import control.game.UnitController;
import model.Unit;

import java.util.List;


/**
 * The class FractionDonkey contains alle methods to create an instance of the fraction donkey.
 *
 * @author Jule Degener
 */
public class FractionDonkey
{
    UnitController unitController = UnitController.getInstance();
    List<model.Unit> units = unitController.createUnit();
    private static FractionDonkey instanceOfFractiondonkey = new FractionDonkey();
    private Unit rats = units.get(Constants_Combat.RAT);
    private Unit beetle = units.get(Constants_Combat.BEETLE);
    private Unit mosquitoes = units.get(Constants_Combat.MOSQUITOES);


    /**
     * Default constructor
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Instance of FractionDonkey is created without any parameters.
     */
    public FractionDonkey ()
    {
        ;
    }


    /**
     * Getter-method to access of the unit rat.
     *
     * @author Jule Degener
     * @return The unit rat is returned.
     * @precondition none
     * @postcondition Access of the unit rat.
     */
    public Unit getRats ()
    {
        return rats;
    }


    /**
     * Getter-method to access of the unit beetle.
     *
     * @author Jule Degener
     * @return The unit beetle is returned.
     * @precondition none
     * @postcondition Access of the unit beetle.
     */
    public Unit getBeetle ()
    {
        return beetle;
    }


    /**
     * Getter-method to access of the unit mosquitoes.
     *
     * @author Jule Degener
     * @return The unit mosquitoes is returned.
     * @precondition none
     * @postcondition Access of the unit mosquitoes.
     */
    public Unit getMosquitoes ()
    {
        return mosquitoes;
    }


    /**
     * Getter-method to access of the instance of FractionDonkey.
     *
     * @author Jule Degener
     * @return The instance of FractionDonkey is returned.
     * @precondition none
     * @postcondition Access of the instance of FractionDonkey.
     */
    public static FractionDonkey getInstanceOfFractiondonkey ()
    {
        return instanceOfFractiondonkey;
    }
}
