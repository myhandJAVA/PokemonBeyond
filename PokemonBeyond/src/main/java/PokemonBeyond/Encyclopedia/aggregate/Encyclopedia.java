package PokemonBeyond.Encyclopedia.aggregate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

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

    public void setMemberNo(String memberId) {
        this.memberId = memberId;
    }

    public ArrayList<Integer> getPokemonNoInEncyclopedia() {
        return pokemonNoInEncyclopedia;
    }

    public void setPokemonNoInEncyclopedia(ArrayList<Integer> pokemonNoInEncyclopedia) {
        this.pokemonNoInEncyclopedia = pokemonNoInEncyclopedia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Encyclopedia that)) return false;
        return Objects.equals(memberId, that.memberId) && Objects.equals(pokemonNoInEncyclopedia, that.pokemonNoInEncyclopedia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, pokemonNoInEncyclopedia);
    }

    @Override
    public String toString() {
        return "Encyclopedia{" +
                "memberId=" + memberId +
                ", pokemonNoInEncyclopedia=" + pokemonNoInEncyclopedia +
                '}';
    }
}
