package PokemonBeyond.MonsterBall.service;

import PokemonBeyond.MonsterBall.aggregate.MyPokemon;
import PokemonBeyond.MonsterBall.repository.MonsterBallRepository;

import java.util.ArrayList;

public class MonsterballService {

    private final MonsterBallRepository monsterBallRepository = new MonsterBallRepository();

    public MonsterballService() {
    }

    // 조회를 통해 내 포켓몬을 삭제할지, 이름을 변경할지 정할 수 있다.
    public ArrayList<MyPokemon> selectMyPokemons(String memId) {
        ArrayList<MyPokemon> oldlist = monsterBallRepository.showMyPokemon(memId);
        if (oldlist != null) {
            ArrayList<MyPokemon> newList = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                newList.set(i, oldlist.get(i));
            }
            return newList;
        }
        else System.out.println("보유하신 포켓몬이 없습니다.");

        return null;
    }

    public int addmyPokemon(MyPokemon myPokemon, String memberId) {
        ArrayList<MyPokemon> myPokemons = monsterBallRepository.showMyPokemon(memberId);
        int result = 0;
        if(myPokemons != null) {
            ArrayList<MyPokemon> newMyPokemons =
                    selectMyPokemons(monsterBallRepository.creatnewMyPokemonList(memberId));
            result = monsterBallRepository.addPokemonToMember(memberId, myPokemon);
        }
        return result;
    }
    // 꽉 찼을 때 보내는 메서드
    public void modifyPokemon(String memberId, int deleteIdx) {
        String abandonPokemon =
                monsterBallRepository.showMyPokemon(memberId).get(deleteIdx).getName();
        int result = monsterBallRepository.abandonPokemon(deleteIdx, memberId);
        if(result == 1) {
            System.out.println("오박사님께 " + abandonPokemon + "을 성공적으로 보냈습니다!");
        }
        else System.out.println("오박사님께서 " + abandonPokemon + "을 거부하셨습니다..");
    }

    // 포켓몬 이름 바꾸기
    public int changePokemonName(MyPokemon myPokemon, String memberId, String name) {
        int result = 0;
        MyPokemon newnamePokemon =
                new MyPokemon(myPokemon.getPokemon(), name, myPokemon.getMemId());
        result = monsterBallRepository.updatePokemonToMember(newnamePokemon, memberId, );
    }

    // 포켓몬 버리기
    public int abandonPokemon(int myPokemonIdx, String memberId) {
        int result = 0;
        if()
        return result;
    }
}
