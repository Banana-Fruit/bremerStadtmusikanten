package model;


import java.util.List;


public class Map
{
    private String directory;
    private List obstacles;
    private List collectables;


    public Map(String mapJSON)
    {
        readJSON(mapJSON);
    }


    private void readJSON (String path)
    {

    }
}
