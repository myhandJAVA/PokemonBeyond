package PokemonBeyond.MonsterBall.aggregate;

import PokemonBeyond.Pokemon.aggregate.Pokemon;

import java.io.Serializable;
import java.util.Objects;

public class MyPokemon implements Serializable {
    private Pokemon pokemon;
    private String name;
    private String memId;

    public MyPokemon() {
    }

    public MyPokemon(Pokemon pokemon, String memId) {
        this.pokemon = pokemon;
        this.name = pokemon.getPokemonName();
        this.memId = memId;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public String getName() {
        return name;
    }

    public String getMemId() {
        return memId;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    @Override
    public String toString() {
        return "MyPokemon{" +
                "pokemon=" + pokemon +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPokemon myPokemon = (MyPokemon) o;
        return Objects.equals(pokemon, myPokemon.pokemon) && Objects.equals(name, myPokemon.name) && Objects.equals(memId, myPokemon.memId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemon, name, memId);
    }

    @Override
    public String toString() {
        return "MyPokemon{" +
                "pokemon=" + pokemon +
                ", name='" + name + '\'' +
                ", memId='" + memId + '\'' +
                '}';
    }
}
