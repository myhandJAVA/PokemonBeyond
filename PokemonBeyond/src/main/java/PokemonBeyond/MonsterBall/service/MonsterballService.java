package PokemonBeyond.MonsterBall.service;

import PokemonBeyond.MonsterBall.aggregate.MyPokemon;
import PokemonBeyond.MonsterBall.repository.MonsterBallRepository;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.service.PokemonService;

import java.util.ArrayList;

public class MonsterballService {

    private final MonsterBallRepository monsterBallRepository = new MonsterBallRepository();
    private final PokemonService pokemonService = new PokemonService();

    public MonsterballService() {
    }

    // 조회를 통해 내 포켓몬을 삭제할지, 이름을 변경할지 정할 수 있다.
//    public ArrayList<MyPokemon> selectMyPokemons(String memId) {
//        ArrayList<MyPokemon> oldlist = monsterBallRepository.showMyPokemon(memId);
//        int result = 0;
//        if (oldlist != null) {
//            ArrayList<MyPokemon> newList = new ArrayList<>();
//            result = monsterBallRepository.updatePokemonList(memId, newList);
//        }
//        if (result == 1) System.out.println("보유하신 ");
//        else System.out.println("보유하신 포켓몬이 없습니다.");
//
//        return null;
//    }

    /* 최초로 계정 생성하면 무작위 포켓몬 하나를 갖고 있는 리스트를 생성하는 메서드 */
    public void createNewMemberList(String memberId) {
        int result = 0;
        ArrayList<MyPokemon> newMemberList = new ArrayList<>();
        Pokemon FirstPokemon = pokemonService.meetRandomPokemon();
        MyPokemon startingPokemon = new MyPokemon(FirstPokemon, memberId);
        newMemberList.add(startingPokemon);
        result = monsterBallRepository.updatePokemonList(memberId, newMemberList);
    }
    // 덜 찼을 때에도 null값으로 되어있을 테니 거기다가 넣고
    // 꽉 찼을 때 새로운 포켓몬을 만났을 경우 특정 인덱스 포켓몬을 버리고 그 인덱스에 넣는 거에도 사용
    /* 새로운 포켓몬을 저장하는 메서드 */
    public int addnewPokemon(MyPokemon newPokemon, String memberId) {
        ArrayList<MyPokemon> currentList = monsterBallRepository.showMyPokemon(memberId);
        int result = 0;

        if (currentList.size() < 6) {
            currentList.add(newPokemon);
            result = monsterBallRepository.updatePokemonList(memberId, currentList);
        } else {
            System.out.println("포켓몬 리스트가 가득 찼습니다. 새 포켓몬을 추가할 수 없습니다.");
        }

        if (result == 1) {
            System.out.println(newPokemon.getName() + "이(가) 동료가 되었다!");
        }
        return result;
    }
    /* 오박사님께 보내는 메서드 */
    public void modifyPokemon(String memberId, int deleteIdx) {
        String abandonPokemon =
                monsterBallRepository.showMyPokemon(memberId).get(deleteIdx).getName();
        ArrayList<MyPokemon> oldList = monsterBallRepository.showMyPokemon(memberId);
        if(oldList.size() == 1) {
            System.out.println("포켓몬은 최소 1마리 이상 보유해야 합니다!");
            return;
        }
        ArrayList<MyPokemon> newList = new ArrayList<>();
        // 삭제 인덱스만 빼고 리스트 복사
        for (int i = 0; i < 6; i++) {
            if(i == deleteIdx) continue;
            newList.set(i, oldList.get(i));
        }
        int result = monsterBallRepository.updatePokemonList(memberId, newList);
        if(result == 1) {
            System.out.println("오박사님께 " + abandonPokemon + "을 성공적으로 보냈습니다!");
        }
        else System.out.println("오박사님께서 " + abandonPokemon + "을 거부하셨습니다..");
    }

    /* 포켓몬 이름 바꾸는 메서드 */
    public int changePokemonName(int pokemonIdx, String memberId, String name) {
        int result = 0;
        if(pokemonIdx < 0 || pokemonIdx >= 6) {
            System.out.println("잘못된 포켓몬 번호입니다");
            return result;
        }
        ArrayList<MyPokemon> currentList = monsterBallRepository.showMyPokemon(memberId);
        // 새로운 MyPokemon 인스턴스 생성 후 값 이름빼고 다 복사
        MyPokemon oldnamePokemon = currentList.get(pokemonIdx);
        MyPokemon newnamePokemon = new MyPokemon();
        newnamePokemon.setName(name);
        newnamePokemon.setPokemon(oldnamePokemon.getPokemon());
        newnamePokemon.setMemId(oldnamePokemon.getMemId());
        // 회원 MyPokemon 리스트 저장
        currentList.set(pokemonIdx, newnamePokemon);
        // 리스트 갱신
        result = monsterBallRepository.updatePokemonList(memberId, currentList);
        if(result == 1) System.out.println(oldnamePokemon.getName() + "이 " +
                name + "으로 바뀌었습니다!");
        else System.out.println("이름 변경에 실패했습니다..");
        return result;
    }
}
