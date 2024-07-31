package model.player;


import resources.constants.Constants_Combat;
import model.Unit;
import control.game.UnitController;

import java.util.List;


/**
 * The class FractionDog contains alle methods to create an instance of the fraction dog.
 *
 * @author Jule Degener
 */
public class FractionDog
{
    UnitController unitController = UnitController.getInstance();
    List<model.Unit> units = unitController.createUnit();
    private static FractionDog instanceOfFractionDog = new FractionDog();
    private Unit hunter = units.get(Constants_Combat.HUNTER);
    private Unit bulldog = units.get(Constants_Combat.MASTIFF);
    private Unit Hundini = units.get(Constants_Combat.HUNDINI);
    private Unit goldenRetriever = units.get(Constants_Combat.GOLDEN_RETRIEVER);
    private Unit germanShepherd = units.get(Constants_Combat.GERMAN_SHEPHERD);


    /**
     * Default constructor
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Instance of FractionDog is created without any parameters.
     */
    public FractionDog ()
    {
        ;
    }


    /**
     * Getter-method to access of the unit GoldenRetriever.
     *
     * @author Jule Degener
     * @return The unit GoldenRetriever is returned.
     * @precondition none
     * @postcondition Access of the unit GoldenRetriever.
     */
    public Unit getGoldenRetriever ()
    {
        return goldenRetriever;
    }


    /**
     * Getter-method to access of the unit GermanShepherd.
     *
     * @author Jule Degener
     * @return The unit GermanShepherd is returned.
     * @precondition none
     * @postcondition Access of the unit GermanShepherd.
     */
    public Unit getGermanShepherd ()
    {
        return germanShepherd;
    }


    /**
     * Getter-method to access of the unit Hunter.
     *
     * @author Jule Degener
     * @return The unit Hunter is returned.
     * @precondition none
     * @postcondition Access of the unit Hunter.
     */
    public Unit getHunter ()
    {
        return hunter;
    }


    /**
     * Getter-method to access of the unit Bulldog.
     *
     * @author Jule Degener
     * @return The unit Bulldog is returned.
     * @precondition none
     * @postcondition Access of the unit Bulldog.
     */
    public Unit getBulldog ()
    {
        return bulldog;
    }


    /**
     * Getter-method to access of the unit Hundini.
     *
     * @author Jule Degener
     * @return The unit Hundini is returned.
     * @precondition none
     * @postcondition Access of the unit Hundini.
     */
    public Unit getHundini ()
    {
        return Hundini;
    }


    /**
     * Getter-method to access of the instance of FractionDog.
     *
     * @author Jule Degener
     * @return The instance of FractionDog is returned.
     * @precondition none
     * @postcondition Access of the instance of FractionDog.
     */
    public static FractionDog getInstanceOfFractionDog ()
    {
        return instanceOfFractionDog;
    }
}
