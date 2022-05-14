
public class Pokemons {
    private int PokeID;
    private String PokeName;
    private String PokeType;
    private String PokeAbilityName;
    private String PokeAbilityDesricption;

    public int getPokeID(){
        return PokeID;
    }
    public void setPokeID(int pokeid){
        PokeID = pokeid;
    }

    public String getPokeName(){
        return PokeName;
    }
    public void setPokeName(String pokename){
        PokeName = pokename;
    }

    public String getPokeType(){
        return PokeType;
    }
    public void setPokeType(String poketype){
        PokeType = poketype;
    }

    public String getPokeAbilityName(){
        return PokeAbilityName;
    }
    public void setPokeAbilityName(String pokeabilityname){
        PokeAbilityName = pokeabilityname;
    }

    public String getPokeAbilityDesricption(){
        return PokeAbilityDesricption;
    }
    public void setPokeAbilityDesricption(String pokeabilitydesc){
        PokeAbilityDesricption = pokeabilitydesc;
    }

    public void PrintAll() {
        System.out.println("ID: " + PokeID + "\nName: " + PokeName + "\nType: " + PokeType + "\nAbility: "
                + PokeAbilityName + "\nAbility description: " + PokeAbilityDesricption);
    }
}
