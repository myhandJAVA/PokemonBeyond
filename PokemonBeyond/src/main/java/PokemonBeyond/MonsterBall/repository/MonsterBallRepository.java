package PokemonBeyond.MonsterBall.repository;

import PokemonBeyond.Member.stream.MyObjectOutput;
import PokemonBeyond.MonsterBall.aggregate.MyPokemon;
import PokemonBeyond.MonsterBall.service.MonsterballService;

import java.io.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.IntStream;

public class MonsterBallRepository {

    private Map<String, ArrayList<MyPokemon>> allMebersPokemons;
    private static final String filePath = "src/main/java/PokemonBeyond/MonsterBall/db/allMebersPokemons.dat";
    private static final int MAX_POKEMON_PER_MEMBER = 6;
    private final MonsterballService monsterballService = new MonsterballService();

    public MonsterBallRepository() {
        this.allMebersPokemons = new Map<>;
        File file = new File(filePath);
        loadAllMembersPokemons(file);
    }

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
                allMebersPokemons = (Map<String, ArrayList<MyPokemon>>) new ArrayList<>();
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
            allMebersPokemons = (Map<String, ArrayList<MyPokemon>>) new ArrayList<>();
        }
    }
    private int saveAllMembersPokemons(ArrayList<ArrayList<MyPokemon>> allMebersPokemons) {
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

    // 이미 공간이 충분하다는 메서드를 통과한 이후라고 가정하고 수정할 예정
    public int addPokemonToMember(Member selectedMember, MyPokemon pokemon) {
        int result = 0;
        ArrayList<MyPokemon> memberPokemons = allMebersPokemons.get();
        if(ensureMaxPokemonLimit(memberPokemons)) {
            memberPokemons.add(pokemon);
            saveAllMembersPokemons((ArrayList<ArrayList<MyPokemon>>) allMebersPokemons);
            result = 1;
        }
        return result;
    }

    // 이미 공간이 불충분하다는 메서드를 통과한 이후라고 가정하고 수정할 예정
    public int updatePokemonToMember(Member selectedMember, MyPokemon pokemon) {
        int result = 0;
        ArrayList<MyPokemon> memberPokemons = allMebersPokemons.get(selectedMember.);
        /* 우선 기존 갖고 있는 포켓몬 중 어떤 걸 없앨지 정하는 메서드에 memberPokemons 배열을 파라미터로 넣어서
        *  인덱스를 return 받음. 그 포켓몬을 지우고 pokemon 객체를 그 자리에 넣는다. */
        int removeIdx = monsterballService.// 어떤 포켓몬을 없앨지 결정하는 method?


    }

    public int insertnewMyPokemonList(ArrayList<MyPokemon> newmemberPokemons) {
        int result = 0;
        MyObjectOutput moo = null;
        try {
            moo = new MyObjectOutput(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath, true)
                    )
            );
            moo.writeObject(newmemberPokemons);
            allMebersPokemons.add(newmemberPokemons);
            result = 1;
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
}