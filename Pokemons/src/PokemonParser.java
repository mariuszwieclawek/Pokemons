import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class PokemonParser {

    static Logger log = Logger.getLogger(PokemonParser.class.getName());
    static boolean DataIsOk;

    /*** Return JSON text from buffer ***/
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /*** Get JSON data format from URL ***/
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = null;
        JSONObject json = null;

        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            json = new JSONObject(jsonText);
            DataIsOk = true;
            return json;
        }
        catch (Exception e){
            DataIsOk = false;
        }
        finally {
            if(DataIsOk)
                is.close();
        }
        return json;
    }

    /*** Get pokemon name ***/
    public static String GetPokeName(String PokeID) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://pokeapi.co/api/v2/pokemon/"+PokeID+"/");
        log.info("User search pokemon by ID: " + PokeID);
        if (DataIsOk) {
            log.info("User enter a properly ID of pokemon: " + PokeID);
            String PokeName;
            PokeName = json.getString("name");
            return PokeName;

        }else{
            log.error("Cannot find this pokemon ID in database: " + PokeID);
            return "ERROR";
        }
    }

    /*** Get basic information about the pokemon ***/
    public static void GetPokeInfo(Pokemon pok, String PokeIDorName) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://pokeapi.co/api/v2/pokemon/"+PokeIDorName+"/");
        log.info("User try to search Pokemon/ID: " + PokeIDorName);
        if (DataIsOk) {
            log.info("User enter a properly name of pokemon: " + PokeIDorName);
            pok.setPokeID(json.getInt("id"));
            pok.setPokeName(json.getString("name"));
            pok.setPokeHeight(json.getInt("height"));
            pok.setPokeWeight(json.getInt("weight"));
            pok.setPokeType(json.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name"));
            pok.setPokeAbilityName(json.getJSONArray("abilities").getJSONObject(0).getJSONObject("ability").getString("name"));
            pok.setPokeMove(json.getJSONArray("moves").getJSONObject(0).getJSONObject("move").getString("name"));
            //pok.PrintAll();
        }else{
            log.error("Cannot find this pokemon in database, Pokemon/ID: " + PokeIDorName);
            pok.setPokeName(PokeIDorName);
        }

    }
}
