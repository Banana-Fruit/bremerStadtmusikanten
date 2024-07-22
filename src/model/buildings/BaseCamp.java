package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.player.FractionDonkey;
import model.Unit;
import model.player.Player;
import resources.constants.Constants_Player_Units;
import resources.constants.scenes.Constants_Building;

import java.util.ArrayList;
import java.util.List;


public class BaseCamp extends Building
{
    private static final BaseCamp INSTANCE_OF_BASECAMP = new BaseCamp(Constants_Building.NAME_BASECAMP_NAME,
            Constants_Building.BASECAMP_GOLD, Constants_Building.BASECAMP_BRICK, Constants_Building.BASECAMP_WOOD,
            Constants_Building.BASECAMP_BEER, Constants_Building.BASECAMP_ESSENCE, false,
            new Coordinate(Constants_Building.BASECAMP_POSITION_X, Constants_Building.BASECAMP_POSITION_Y));
    
    
    // TODO: später woanders initialisieren
    private FractionDonkey fractionDonkey = FractionDonkey.getInstanceOfFractiondonkey();
    private List<Unit> listOfFractions;
    
    
    // constructor
    private BaseCamp (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                      int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                      Coordinate positionUpperLeft)
    {
        super(name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }
    
    
    //TODO: buttons in den verschiedenen FraktionsCamps zum rekrutieren jeder einheit  (einheiten zu sehen in Constants_Combat oder  Einheiten.CSV)
    // + Esel fraktions Camp ist BaseCamp(?)
    private void recruitUnits (Unit unit)
    {
        ArrayList<Unit> team = Player.getInstance().getTeamMembers();
        
        int i;
        for (i = Constants_Player_Units.ZERO; i < team.size(); i++)
        {
            if (team.get(i) == null)
            {
                team.add(unit);
            } else
            {
                if (!(team.get(Constants_Player_Units.LAST_INDEX_NUMBER_OF_TEAM) == null))
                {
                    System.out.println(Constants_Building.TEAM_FULL);
                } else
                {
                    i++;
                }
            }
        }
    }
    
    
    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfBasecamp());
    }
    
    
    // ist später unnötig: unlock button besser
    public static FractionDonkey unlockFractionDonkey (FractionDonkey fractionDonkey)
    {
        Unit rats = recruitRatsFromFractionDonkey(fractionDonkey);
        Unit beetles = recruitBeetlesFromFractionDonkey(fractionDonkey);
        Unit mosquitoes = recruitMosquitoesFromFractionDonkey(fractionDonkey);
        
        
        return fractionDonkey;
    }
    
    
    private static Unit recruitRatsFromFractionDonkey (FractionDonkey fractionDonkey)
    {
        return fractionDonkey.getRats(); // TODO: später Einlesen
    }
    
    
    private static Unit recruitBeetlesFromFractionDonkey (FractionDonkey fractionDonkey)
    {
        return fractionDonkey.getBeetle(); // TODO: später Einlesen
    }
    
    
    private static Unit recruitMosquitoesFromFractionDonkey (FractionDonkey fractionDonkey)
    {
        return fractionDonkey.getMosquitoes(); // TODO: später Einlesen
    }
    
    
    public static void addUnitsToList (FractionDonkey fractionDonkey)
    {
        List<Unit> list = INSTANCE_OF_BASECAMP.getListOfFractions();
        list.add(fractionDonkey.getRats());
        list.add(fractionDonkey.getBeetle());
        list.add(fractionDonkey.getMosquitoes());
    }
    
    
    public static BaseCamp getInstanceOfBasecamp ()
    {
        return INSTANCE_OF_BASECAMP;
    }
    
    
    public List<Unit> getListOfFractions ()
    {
        return listOfFractions;
    }
}
