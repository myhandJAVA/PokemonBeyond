package PokemonBeyond.MonsterBall.aggregate;

import PokemonBeyond.Pokemon.aggregate.Pokemon;

import java.io.Serializable;
import java.util.Objects;

public class MyPokemon implements Serializable {
    private MonsterBall monsterBall;
    private Pokemon pokemon;
    private String name = pokemon.getPokemonName();

    public MonsterBall getMonsterBall() {
        return monsterBall;
    }

    public void setMonsterBall(MonsterBall monsterBall) {
        this.monsterBall = monsterBall;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPokemon myPokemon = (MyPokemon) o;
        return Objects.equals(monsterBall, myPokemon.monsterBall) && Objects.equals(pokemon, myPokemon.pokemon) && Objects.equals(name, myPokemon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monsterBall, pokemon, name);
    }
}
