package PokemonBeyond.Encyclopedia.aggregate;

import java.io.Serializable;
import java.util.ArrayList;

public class Encyclopedia implements Serializable {
    private int memberNo;
    private ArrayList<Integer> pokemonNoInEncyclopedia;

    public Encyclopedia() {
    }

    public Encyclopedia(int memberNo, ArrayList<Integer> pokemonNoInEncyclopedia) {
        this.memberNo = memberNo;
        this.pokemonNoInEncyclopedia = pokemonNoInEncyclopedia;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
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
                "memberNo=" + memberNo +
                ", pokemonNoInEncyclopedia=" + pokemonNoInEncyclopedia +
                '}';
    }
}
