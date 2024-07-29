package PokemonBeyond.Encyclopedia.aggregate;

import java.io.Serializable;
import java.util.ArrayList;

public class Encyclopedia implements Serializable {
    private String memberId;
    private ArrayList<Integer> pokemonNoInEncyclopedia;

    public Encyclopedia() {
    }

    public Encyclopedia(String memberId, ArrayList<Integer> pokemonNoInEncyclopedia) {
        this.memberId = memberId;
        this.pokemonNoInEncyclopedia = pokemonNoInEncyclopedia;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public ArrayList<Integer> getPokemonNoInEncyclopedia() {
        return pokemonNoInEncyclopedia;
    }

    public void setPokemonNoInEncyclopedia(ArrayList<Integer> pokemonNoInEncyclopedia) {
        this.pokemonNoInEncyclopedia = pokemonNoInEncyclopedia;
    }

    @Override
    public String toString() {
        return "Encyclopedia{" +
                "memberId=" + memberId +
                ", pokemonNoInEncyclopedia=" + pokemonNoInEncyclopedia +
                '}';
    }
}
