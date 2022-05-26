
public class Pokemon {
    private int PokeID;
    private String PokeName;
    private int PokeHeight;
    private int PokeWeight;
    private String PokeType;
    private String PokeAbilityName;
    private String PokeMove;


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

    public int getPokeHeight(){
        return PokeHeight;
    }
    public void setPokeHeight(int pokeheight){
        PokeHeight = pokeheight;
    }

    public int getPokeWeight(){
        return PokeWeight;
    }
    public void setPokeWeight(int pokeweight){
        PokeWeight = pokeweight;
    }

    public String getPokeType(){
        return PokeType;
    }
    public void setPokeType(String poketype){ PokeType = poketype; }

    public String getPokeAbilityName(){ return PokeAbilityName; }
    public void setPokeAbilityName(String pokeabilityname){
        PokeAbilityName = pokeabilityname;
    }

    public String getPokeMove(){
        return PokeMove;
    }
    public void setPokeMove(String pokemove){ PokeMove = pokemove; }

    public void PrintAll() {
        System.out.println("ID: " + PokeID + "\nName: " + PokeName + "\nHeight: " + PokeHeight + "\nWeight: " + PokeWeight
                + "\nType: " + PokeType + "\nAbility: " + PokeAbilityName + "\nMove: " + PokeMove);
    }
}
