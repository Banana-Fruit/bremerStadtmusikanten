package model.player;


import resources.constants.Constants_Combat;
import control.game.UnitController;
import model.Unit;

import java.util.List;


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
