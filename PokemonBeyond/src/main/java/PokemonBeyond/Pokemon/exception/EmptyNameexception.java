package PokemonBeyond.Pokemon.exception;

/* 이름을 공백으로 입력하는 예외 */
public class EmptyNameexception extends Exception {
    public EmptyNameexception(String message) {
        super(message);
    }
}
