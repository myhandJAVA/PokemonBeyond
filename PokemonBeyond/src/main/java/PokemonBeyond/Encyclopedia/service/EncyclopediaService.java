package PokemonBeyond.Encyclopedia.service;

import PokemonBeyond.Encyclopedia.aggregate.Encyclopedia;
import PokemonBeyond.Encyclopedia.repository.EncyclopediaRepository;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.service.PokemonService;

import java.util.ArrayList;

public class EncyclopediaService {
     public static final EncyclopediaRepository encyclopediaRepository = new EncyclopediaRepository();
    public static final PokemonService pokemonService = new PokemonService();

    public void findAllEncyclopedia(){
        ArrayList<Encyclopedia> allEncyclopedia =  encyclopediaRepository.selectAllEncyclopedia();
        for( Encyclopedia encyclopedia : allEncyclopedia){
            System.out.println("회원 아이디: " + encyclopedia.getMemberId() +
                    ", 도감에 등록된 포켓몬 수: " + encyclopedia.getPokemonNoInEncyclopedia().size());
        }
    }

    public void findEncyclopedia(String memberId){
       Encyclopedia encyclopedia =  encyclopediaRepository.selectEncyclopedia(memberId);
       try{
        System.out.println(encyclopedia.getMemberId()+"님의 도감에 등록된 포켓몬 목록입니다.");
        for(int i=0; i<encyclopedia.getPokemonNoInEncyclopedia().size(); i++){
            Pokemon pokemon = pokemonService.findPokemon(encyclopedia.getPokemonNoInEncyclopedia().get(i));
            System.out.println("No. " + pokemon.getPokemonNo() +
                    " " + pokemon.getPokemonName() +
                    " | 공격력: " + pokemon.getPokemonPower());
            }
        } catch (NullPointerException e){
           System.out.println("해당 번호의 도감이 존재하지 않습니다.");

       }
    }

    public void addPokemonToEncylopedia(String memberId, int pokemonNo){
        Encyclopedia encyclopedia = encyclopediaRepository.selectEncyclopedia(memberId);
        if (encyclopedia == null) return;
        if (pokemonNo > pokemonService.findAllPokemon().size() | pokemonNo<1) {
            System.out.println("해당 번호의 포켓몬이 존재하지 않습니다.");
            return;
        }
        Encyclopedia copiedEncyclopedia = new Encyclopedia();
        copiedEncyclopedia.setMemberNo(memberId);
        copiedEncyclopedia.setPokemonNoInEncyclopedia(
                (ArrayList<Integer>) encyclopedia.getPokemonNoInEncyclopedia().clone());
        copiedEncyclopedia.getPokemonNoInEncyclopedia().add(pokemonNo);
        int result = 1;
        result = encyclopediaRepository.deleteEncyclopedia(memberId);
        result = encyclopediaRepository.insertEncyclopedia(copiedEncyclopedia);
        if(result == 1){
            System.out.println("포켓몬이 도감에 추가되었습니다.");
        } else System.out.println("오류가 생겨 포켓몬이 도감에 추가되지 않았습니다.");
    }
    public void addEncyclopedia(String memberId,Pokemon pokemon){
        ArrayList<Integer> pokemonNoList = new ArrayList<>();
        pokemonNoList.add(pokemon.getPokemonNo());
        encyclopediaRepository.insertEncyclopedia(new Encyclopedia(memberId,pokemonNoList));
    }
    public void removeEncyclopedia(String memberId){
       int result = 1;
       result = encyclopediaRepository.deleteEncyclopedia(memberId);
       if(result == 1){
           System.out.println("도감이 정상적으로 삭제되었습니다.");
       } else System.out.println("오류가 생겨 도감이 삭제되지 않았습니다.");
    }

}
