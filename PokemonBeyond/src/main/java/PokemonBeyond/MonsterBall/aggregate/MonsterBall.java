package PokemonBeyond.MonsterBall.aggregate;

import java.io.Serializable;

public class MonsterBall implements Serializable {
    // 우선 볼 종류가 하나뿐이므로 몬스터볼, 잡는 확률은 100으로 고정
   private String Ballname = "몬스터볼";
   private int Probability = 100;

    public MonsterBall() {
    }
}
