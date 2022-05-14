import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class PokemonsParser {

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
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;

        } finally {
            is.close();
        }
    }

    /*** Get basic information about the pokemon ***/
    public static void GetPokeInfo(Pokemons pok, String PokeIDorName) throws IOException, JSONException {

        JSONObject json = readJsonFromUrl("https://pokeapi.co/api/v2/pokemon/"+PokeIDorName+"/");
        pok.setPokeID(json.getInt("id"));
        pok.setPokeName(json.getString("name"));
        pok.setPokeType(json.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name"));
        pok.setPokeAbilityName(json.getJSONArray("abilities").getJSONObject(0).getJSONObject("ability").getString("name"));


        json = readJsonFromUrl("https://pokeapi.co/api/v2/ability/"+pok.getPokeID()+"/");
        String text = json.getJSONArray("effect_entries").getJSONObject(1).getString("effect");
        text = text.replaceAll("\n", "");
        pok.setPokeAbilityDesricption(text);

        /*json = readJsonFromUrl("https://pokeapi.co/api/v2/evolution-chain/"+pok.getPokeID()+"/");
        pok.FirstEvolution = json.getJSONObject("chain").getJSONArray("evolves_to").getJSONObject(0).getJSONObject("species").getString("name");
        pok.SecondEvolution = json.getJSONObject("chain").getJSONArray("evolves_to").getJSONObject(0).getJSONArray("evolves_to").getJSONObject(0).getJSONObject("species").getString("name");
        */
    }
}
