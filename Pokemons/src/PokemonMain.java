import org.apache.log4j.Logger;

import java.io.IOException;

public class PokemonMain {

    static Logger log = Logger.getLogger(PokemonMain.class.getName());

    public static void main(String args[]) throws IOException {
        log.info("Start application");
        PokemonGUI PokGUI = new PokemonGUI();
        PokGUI.createAndShowGUI();
    }
}
