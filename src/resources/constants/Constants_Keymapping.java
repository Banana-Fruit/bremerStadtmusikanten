package resources.constants;


import javafx.scene.input.KeyCode;

import java.util.List;


public interface Constants_Keymapping
{
    // Movement
    KeyCode moveUP = KeyCode.W;
    KeyCode moveDOWN = KeyCode.S;
    KeyCode moveLEFT = KeyCode.A;
    KeyCode moveRIGHT = KeyCode.D;
    
    // GUI
    KeyCode EXIT = KeyCode.ESCAPE;
    KeyCode INTERACT = KeyCode.E;
    KeyCode MAP = KeyCode.M;
    KeyCode INVENTORY = KeyCode.I;
    
    // Lists
    List<KeyCode> PLAYER_KEYS = List.of(moveUP, moveDOWN, moveLEFT, moveRIGHT);
}