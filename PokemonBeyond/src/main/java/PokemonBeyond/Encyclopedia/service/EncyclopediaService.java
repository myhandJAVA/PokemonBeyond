package PokemonBeyond.Encyclopedia.service;

import PokemonBeyond.Encyclopedia.aggregate.Encyclopedia;
import PokemonBeyond.Encyclopedia.repository.EncyclopediaRepository;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.repository.PokemonRepository;

import java.util.ArrayList;

public class EncyclopediaService {
    private static final EncyclopediaRepository encyclopediaRepository = new EncyclopediaRepository();
    private static final PokemonRepository pokemonRepository = new PokemonRepository();

    public void findAllEncyclopedia(){
        ArrayList<Encyclopedia> allEncyclopedia =  encyclopediaRepository.selectAllEncyclopedia();
        for( Encyclopedia encyclopedia : allEncyclopedia){
            System.out.println("회원 번호: " + encyclopedia.getMemberNo() +
                    ", 도감에 등록된 포켓몬 수: " + encyclopedia.getPokemonNoInEncyclopedia().size());
        }
    }

    public void findEncyclopedia(int memberNo){
       Encyclopedia encyclopedia =  encyclopediaRepository.selectEncyclopedia(memberNo);
        System.out.println(encyclopedia.getMemberNo()+"님의 도감에 등록된 포켓몬 목록입니다.");
        for(int i=0; i<encyclopedia.getPokemonNoInEncyclopedia().size(); i++){
            Pokemon pokemon = pokemonRepository.selectPokemon(encyclopedia.getPokemonNoInEncyclopedia().get(i));
            System.out.println("번호: " + pokemon.getPokemonNo() +
                    ", 이름: " + pokemon.getPokemonName() +
                    ", 공격력: " + pokemon.getPokemonPower());
        }
    }

    public void addPokemonToEncylopedia(int memberNo, int pokemonNo){
        Encyclopedia encyclopedia = encyclopediaRepository.selectEncyclopedia(memberNo);
        Encyclopedia copiedEncyclopedia = new Encyclopedia();
        copiedEncyclopedia.setMemberNo(memberNo);
        copiedEncyclopedia.setPokemonNoInEncyclopedia(
                (ArrayList<Integer>) encyclopedia.getPokemonNoInEncyclopedia().clone());
        copiedEncyclopedia.getPokemonNoInEncyclopedia().add(pokemonNo);

    }
}
