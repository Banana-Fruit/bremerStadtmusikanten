package model.player;


import control.game.UnitController;
import model.Unit;
import resources.constants.Constants_Combat;

import java.util.List;


public class FractionCat
{
    UnitController unitController = UnitController.getInstance();
    List<Unit> units = unitController.unitCreator();
    private static FractionCat instanceOfFractionCat = new FractionCat();
    private Unit housekeeper = units.get(Constants_Combat.HOUSEKEEPER);
    private Unit jaguar = units.get(Constants_Combat.JAGUAR);
    private Unit Bingus = units.get(Constants_Combat.BINGUS);
    private Unit cat = units.get(Constants_Combat.CAT);
    private Unit tiger = units.get(Constants_Combat.TIGER);
    
    
    public FractionCat (Unit cat, Unit tiger, Unit housekeeper, Unit jaguar, Unit Bingus)
    {
        this.cat = cat;
        this.tiger = tiger;
        this.housekeeper = housekeeper;
        this.jaguar = jaguar;
        this.Bingus = Bingus;
    }
    
    
    public FractionCat ()
    {
    
    }
    
    
    // getter methods
    public Unit getCat ()
    {
        return cat;
    }
    
    
    public Unit getTiger ()
    {
        return tiger;
    }
    
    
    public Unit getHousekeeper ()
    {
        return housekeeper;
    }
    
    
    public Unit getJaguar ()
    {
        return jaguar;
    }
    
    
    public Unit getBingus ()
    {
        return Bingus;
    }
    
    
    public static FractionCat getInstanceOfFractionCat ()
    {
        return instanceOfFractionCat;
    }
}
