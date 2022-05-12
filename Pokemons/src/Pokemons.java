import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


public class Pokemons {
    int PokeID;
    String PokeName;
    String PokeType;
    String FirstEvolution;
    String SecondEvolution;
    String PokeAbilityName;
    String PokeAbilityDesricption;

    public void PrintAll() {
        System.out.println("ID: " + PokeID + "\nName: " + PokeName + "\nType: " + PokeType + "\nFirst evolution: "+ FirstEvolution
                + "\nSecond evolution: "+ SecondEvolution +"\nAbility: " + PokeAbilityName + "\nAbility description: " + PokeAbilityDesricption);
    }
}
