package model.player;


public class FractionDonkey
{
    private static FractionDonkey instanceOfFractiondonkey = new FractionDonkey();
    private Unit rats = new Unit(2,2,2,2,2,2,2,2,2 * Player.getInstance().getMagicSkill());
    private Unit beetle = new Unit(2,2,2,2,2,2,2,2,2 * Player.getInstance().getMagicSkill());;
    private Unit mosquitoes = new Unit(2,2,2,2,2,2,2,2,2 * Player.getInstance().getMagicSkill());;


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
