package PokemonBeyond.MonsterBall.aggregate;

public class MonsterBall {
    private int probability = 100;
    private String name = "몬스터볼";

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public String getName() {
        return name;
    }

    public int getProbability() {
        return probability;
    }

    private Pokemon pokemon;

    public MonsterBall() {
    }


}
