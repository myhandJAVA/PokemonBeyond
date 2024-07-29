package PokemonBeyond.MonsterBall.repository;

import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.Member.stream.MyObjectOutput;
import PokemonBeyond.MonsterBall.aggregate.MyPokemon;
import PokemonBeyond.MonsterBall.service.MonsterballService;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class MonsterBallRepository {

    private Map<String, ArrayList<MyPokemon>> allMebersPokemons;
    private static final String filePath = "src/main/java/PokemonBeyond/MonsterBall/db/allMebersPokemons.dat";
    private static final int MAX_POKEMON_PER_MEMBER = 6;
    private final MonsterballService monsterballService = new MonsterballService();

    public MonsterBallRepository() {
        this.allMebersPokemons = new HashMap<>();
        File file = new File(filePath);
        loadAllMembersPokemons(file);
    }

    //모든 회원 리스트 로드
    private void loadAllMembersPokemons(File file) {
        if(file.exists()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(file)
                        )
                );
                allMebersPokemons = (Map<String, ArrayList<MyPokemon>>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("파일 로딩 중 에러가 발생했습니다.");
                allMebersPokemons = new HashMap<>();
            } finally {
                if(ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        else {
            System.out.println("데이터 파일이 존재하지 않습니다. 새로운 리스트를 생성합니다.");
            allMebersPokemons = new HashMap<>();
        }
    }
    // 모든 회원 리스트 저장
    private int saveAllMembersPokemons(Map<String ,ArrayList<MyPokemon>> allMebersPokemons) {
        ObjectOutputStream oos = null;
        int result = 0;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath)
                    )
            );
            oos.writeObject(allMebersPokemons);
            System.out.println("모든 멤버의 포켓몬 정보를 저장했습니다.");
            result = 1;
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다.");
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }


    private boolean ensureMaxPokemonLimit(ArrayList<MyPokemon> memberPokemons) {
        if (memberPokemons.size() >= MAX_POKEMON_PER_MEMBER) {
            throw new IllegalStateException("멤버당 최대 " + MAX_POKEMON_PER_MEMBER + "마리의 포켓몬만 소유할 수 있습니다.");
        }
        return true;
    }

    // 포켓몬 리스트에 포켓몬 추가
    // 이미 공간이 충분하다는 메서드를 통과한 이후라고 가정하고 수정할 예정
    public int addPokemonToMember(String id, MyPokemon pokemon) {
        int result = 0;
        ArrayList<MyPokemon> memberPokemons = allMebersPokemons.get(id);
            memberPokemons.add(pokemon);
            saveAllMembersPokemons(allMebersPokemons);
            result = 1;

        return result;
    }

    // 삭제하길 원하는 포켓몬을 새 포켓몬으로 덮어씌우는 메서드
    // 이미 공간이 불충분하다는 메서드를 통과한 이후라고 가정하고 수정할 예정
    public int updatePokemonToMember(Member selectedMember, MyPokemon pokemon) {
        int result = 0;
        ArrayList<MyPokemon> memberPokemons = allMebersPokemons.get(selectedMember.getId());
        if(ensureMaxPokemonLimit(memberPokemons)) {
            int index = pickdeletePokemon(memberPokemons);
            memberPokemons.set(index, pokemon);
            result = 1;
        }
        return result;
    }

    private int pickdeletePokemon(ArrayList<MyPokemon> memberPokemons) {

    }

    // 새로운 회원 리스트 생성 후 등록
    public String creatnewMyPokemonList(String id) {
        String result = "";
        ArrayList<MyPokemon> newMemberPokemons = new ArrayList<>();
        MyObjectOutput moo = null;
        try {
            moo = new MyObjectOutput(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath, true)
                    )
            );
            moo.writeObject(newMemberPokemons);
            allMebersPokemons.put(id, newMemberPokemons);
            result = id;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(moo != null) {
                try {
                    moo.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }
    // 포켓몬 리스트 조회
    public ArrayList<MyPokemon> showMyPokemon(String memberId) {
        ArrayList<MyPokemon> memberPokemons = allMebersPokemons.get(memberId);
        return memberPokemons;
    }
}