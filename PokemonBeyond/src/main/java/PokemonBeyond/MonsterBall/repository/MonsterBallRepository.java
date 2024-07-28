package PokemonBeyond.MonsterBall.repository;

import PokemonBeyond.MonsterBall.aggregate.MyPokemon;

import java.io.*;
import java.util.ArrayList;

public class MonsterBallRepository {

    private ArrayList<Member> memberList;
    private final String filePath = "src/main/java/PokemonBeyond/Member/db/";

    public MonsterBallRepository() {
        this.allMebersPokemons = new ArrayList<>();
        loadAllMembersPokemons();
    }

    private void loadAllMembersPokemons() {
        File file = new File(filePath);
        if(file.exists()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(file)
                        )
                );
            } catch (IOException e) {
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
    }
    private static saveAllMembersPokemons() {

    }
}
