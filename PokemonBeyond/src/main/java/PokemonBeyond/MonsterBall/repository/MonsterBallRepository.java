package PokemonBeyond.MonsterBall.repository;

import PokemonBeyond.MonsterBall.aggregate.MyPokemon;

import java.io.*;
import java.util.ArrayList;

public class MonsterBallRepository {

    ArrayList<MyPokemon> allMyPokemons = new ArrayList<>();

    public MonsterBallRepository() {
    }

    public void loadAllMyPokemons(File file) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream(file)
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void updateAllMyPokemons(ArrayList<ArrayList<MyPokemon>> allMembersPokemon, Member changedMem) {
        /* 설명. Member의 idx를 allMembersPokemon의 idx로 사용합니다. */
        int idx = changedMem.getIdx();
        for (int i = 0; i < allMembersPokemon.size(); i++) {
            if(idx == i) {
                allMembersPokemon.set(i, changedMem.get(Mypokemons));
            }
        }
    }
}
