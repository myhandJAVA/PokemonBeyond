package PokemonBeyond.MonsterBall.service;

import PokemonBeyond.MonsterBall.aggregate.MyPokemon;
import PokemonBeyond.MonsterBall.repository.MonsterBallRepository;
import PokemonBeyond.Pokemon.aggregate.Pokemon;

import java.util.ArrayList;

public class MonsterballService {

    private final MonsterBallRepository monsterBallRepository = new MonsterBallRepository();

    public MonsterballService() {
    }

    // 조회, 추가, 수정

    public ArrayList<MyPokemon> showMyPokemons(String id) {
        ArrayList<MyPokemon> myPokemons = monsterBallRepository.showMyPokemon(id);

        if(myPokemons != null) {
            System.out.println("보유하신 포켓몬 리스트: " + myPokemons);
        }
        else System.out.println("보유하신 포켓몬이 없습니다.");

        return myPokemons;
    }

    public void addmyPokemon(MyPokemon myPokemon, String id) {
        ArrayList<MyPokemon> myPokemons = monsterBallRepository.showMyPokemon(id);
        int result = 0;
        if(myPokemons != null) {
            ArrayList<MyPokemon> newMyPokemons = showMyPokemons(monsterBallRepository.creatnewMyPokemonList(id));
            result = monsterBallRepository.addPokemonToMember(id, myPokemon);
        }
    }
}
