package PokemonBeyond.MonsterBall.repository;

import PokemonBeyond.Member.stream.MyObjectOutput;
import PokemonBeyond.MonsterBall.aggregate.MyPokemon;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MonsterBallRepository {

    private Map<String, ArrayList<MyPokemon>> allMebersPokemons;
    private static final String filePath = "src/main/java/PokemonBeyond/MonsterBall/db/allMebersPokemons.dat";

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
    // 갱신된 리스트 저장 확인
    private int saveAllMembersPokemons(File file, Map<String ,ArrayList<MyPokemon>> allMebersPokemons) {
        ObjectOutputStream oos = null;
        int result = 0;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)
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


//    private boolean ensureMaxPokemonLimit(ArrayList<MyPokemon> memberPokemons) {
//        if (memberPokemons.size() >= MAX_POKEMON_PER_MEMBER) {
//            throw new IllegalStateException("멤버당 최대 " + MAX_POKEMON_PER_MEMBER + "마리의 포켓몬만 소유할 수 있습니다.");
//        }
//        return true;
//    }


    /* 포켓몬 리스트에 변경사항이 있으면 반영하는 메서드 */
    public int updatePokemonList(String memberId, ArrayList<MyPokemon> updatedList) {
        allMebersPokemons.put(memberId, updatedList);
        File file = new File(filePath);
        int result = saveAllMembersPokemons(file, allMebersPokemons);
        return result;
    }

    /* 새로운 회원 리스트 생성 후 등록 */
    public ArrayList<MyPokemon> addNewMyPokemonList(String memberId, ArrayList<MyPokemon> newList) {
        MyObjectOutput moo = null;
        try {
            moo = new MyObjectOutput(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath, true)
                    )
            );
            moo.writeObject(newList);
            allMebersPokemons.put(memberId, newList);
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
        return newList;
    }
    /* 포켓몬 조회하는 메서드 */
    public ArrayList<MyPokemon> selectMyPokemon(String memberId) {
        ArrayList<MyPokemon> memberPokemons = allMebersPokemons.get(memberId);
        return memberPokemons;
    }
}