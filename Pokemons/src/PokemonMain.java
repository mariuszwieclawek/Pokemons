import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class PokemonMain {

    public static void main(String args[]) throws IOException {
        Pokemons pok = new Pokemons();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz nazwe pokemona lub ID pokemona: ");
        String PokeIDorName;
        PokeIDorName = scanner.nextLine();

        PokemonsParser pokpars = new PokemonsParser();
        pokpars.GetPokeInfo(pok,PokeIDorName);

        pok.PrintAll();

        PokemonGUI PokGUI = new PokemonGUI();
        PokGUI.createAndShowGUI();
    }
}
