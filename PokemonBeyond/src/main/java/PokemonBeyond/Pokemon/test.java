package PokemonBeyond.Pokemon;

import PokemonBeyond.Encyclopedia.service.EncyclopediaService;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.service.PokemonService;

import java.util.ArrayList;
import java.util.Scanner;

public class test {
    static final PokemonService ps = new PokemonService();
    static final EncyclopediaService es = new EncyclopediaService();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("메뉴");
            System.out.println("1. 도감 전체 조회");
            System.out.println("2. 한명 도감 조회");
            System.out.println("3. 도감 하나 추가하기");
            System.out.println("4. 도감에 포켓몬 하나 추가하기"); // 없는번호 포켓몬 넣는것 유효성체크 필요!
            System.out.println("5. 도감 삭제하기");
            System.out.println("6. 풀숲에서 포켓몬 만나기");
            System.out.println("7. 모든 포켓몬 기본값 보기");
            System.out.println("8. 하나의 포켓몬 기본값 보기");
            System.out.println("9. 종료");
            System.out.print("입력: ");
            int input = sc.nextInt();

            switch(input){
                case 1:
                    es.findAllEncyclopedia();
                    break;
                case 2:
                    System.out.print("조회할 회원ID 입력: ");
                    String memId = sc.next();
                    es.findEncyclopedia(memId);
                    break;
                case 3:
                    System.out.print("추가할 회원ID 입력: ");
                    String addEncycMemId = sc.next();
                    es.addEncyclopedia(addEncycMemId,ps.meetRandomPokemon());
                    break;
                case 4:
                    System.out.print("추가할 포켓몬번호, 추가할 회원ID 입력: ");
                    int addPokemon = sc.nextInt();
                    String addMemId = sc.next();
                    es.addPokemonToEncylopedia(addMemId,addPokemon);
                    break;
                case 5 :
                    System.out.print("삭제할 회원ID 입력: ");
                    String delMemId = sc.next();
                    es.removeEncyclopedia(delMemId);
                    break;
                case 6:
                    System.out.println("앗! 야생 " + ps.meetRandomPokemon().getPokemonName()+" 이(가) 튀어나왔다!");
                    break;
                case 7 :
                    ArrayList<Pokemon> pokemonList = ps.findAllPokemon();
                    for(Pokemon pokemon:pokemonList){
                        System.out.println(pokemon);
                    }
                    break;
                case 8 :
                    System.out.print("찾을 포켓몬 번호 입력: ");
                    int findPokemon = sc.nextInt();
                    Pokemon findedPokemon = ps.findPokemon(findPokemon);
                    if(findedPokemon!=null) System.out.println(ps.findPokemon(findPokemon));
                    break;
                case 9 :
                    return;
            }
        }
    }
}
