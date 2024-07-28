package PokemonBeyond.MonsterBall.repository;

import PokemonBeyond.MonsterBall.aggregate.MyPokemon;

import java.io.*;
import java.lang.reflect.Member;
import java.util.ArrayList;

public class MonsterBallRepository {

    private ArrayList<ArrayList<MyPokemon>> allMebersPokemons;
    private static final String filePath = "src/main/java/PokemonBeyond/MonsterBall/db/allMebersPokemons.dat";
    private static final int MAX_POKEMON_PER_MEMBER = 6;

    public MonsterBallRepository() {
        this.allMebersPokemons = new ArrayList<>();
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
                allMebersPokemons = (ArrayList<ArrayList<MyPokemon>>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("파일 로딩 중 에러가 발생했습니다.");
                allMebersPokemons = new ArrayList<>();
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
            allMebersPokemons = new ArrayList<>();
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

    public int addPokemonToMember(Member selectedMember, MyPokemon pokemon) {
        int result = 0;
        ArrayList<MyPokemon> memberPokemons = allMebersPokemons.get(selectedMember.getMemNo());
        if(ensureMaxPokemonLimit(memberPokemons)) {
            memberPokemons.add(pokemon);
            saveAllMembersPokemons(allMebersPokemons);
            result = 1;
        }
        return result;
    }

    public int updatePokemonToMember(Member selectedMember, MyPokemon pokemon) {
        int result = 0;
        ArrayList<MyPokemon> memberPokemons = allMebersPokemons.get(selectedMember.getMemNo);

    }
}