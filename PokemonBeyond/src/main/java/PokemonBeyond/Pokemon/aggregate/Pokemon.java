package PokemonBeyond.Pokemon.aggregate;

import PokemonBeyond.Skill.aggregate.Skill;

import java.io.Serializable;
import java.util.ArrayList;


public class Pokemon implements Serializable {
    private int pokemonNo;
    private String pokemonName;
    private int pokemonPower;
    private ArrayList<Skill> poekmonSkill;

    public Pokemon() {
    }

    public Pokemon(int pokemonNo, String pokemonName, int pokemonPower, ArrayList<Skill> poekmonSkill) {
        this.pokemonNo = pokemonNo;
        this.pokemonName = pokemonName;
        this.pokemonPower = pokemonPower;
        this.poekmonSkill = poekmonSkill;
    }

    public int getPokemonNo() {
        return pokemonNo;
    }

    public void setPokemonNo(int pokemonNo) {
        this.pokemonNo = pokemonNo;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public int getPokemonPower() {
        return pokemonPower;
    }

    public void setPokemonPower(int pokemonPower) {
        this.pokemonPower = pokemonPower;
    }

    public ArrayList<Skill> getPoekmonSkill() {
        return poekmonSkill;
    }

    public void setPoekmonSkill(ArrayList<Skill> poekmonSkill) {
        this.poekmonSkill = poekmonSkill;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonNo=" + pokemonNo +
                ", pokemonName='" + pokemonName + '\'' +
                ", pokemonPower=" + pokemonPower +
                ", poekmonSkill=" + poekmonSkill +
                '}';
    }
}
