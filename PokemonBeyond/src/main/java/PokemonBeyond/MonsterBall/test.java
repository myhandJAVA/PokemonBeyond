import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.MonsterBall.run.MonsterballApplication;
import PokemonBeyond.MonsterBall.service.MonsterballService;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.service.PokemonService;

class test {
    static PokemonService pokemonService = new PokemonService();
    static MonsterballService monsterballService = new MonsterballService();
    static MonsterballApplication monsterballApplication = new MonsterballApplication();
    public static void main(String[] args) {
        Pokemon pokemon = pokemonService.meetRandomPokemon();
        String memberId = "a";

        monsterballService.fightCatch(memberId, pokemon);
    }
}