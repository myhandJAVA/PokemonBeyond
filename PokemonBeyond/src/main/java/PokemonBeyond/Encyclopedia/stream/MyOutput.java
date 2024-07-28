package PokemonBeyond.Encyclopedia.stream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyOutput extends ObjectOutputStream {
    public MyOutput(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
    }
}
