package PokemonBeyond.MonsterBall.aggregate;

import java.util.ArrayList;

public class MyPokemon {

    private ArrayList<MonsterBall> ownPokemons = new ArrayList<MonsterBall>();
    private int MemberId;

    public MyPokemon() {
    }

    public int getMemberId() {
        return MemberId;
    }

    public void setMemberId(int memberId) {
        MemberId = memberId;
    }

    public ArrayList<MonsterBall> getOwnPokemons() {
        return ownPokemons;
    }

    public void setOwnPokemons(ArrayList<MonsterBall> ownPokemons) {
        this.ownPokemons = ownPokemons;
    }
}
