package PokemonBeyond.Encyclopedia.repository;

import PokemonBeyond.Encyclopedia.aggregate.Encyclopedia;
import PokemonBeyond.Encyclopedia.stream.MyOutput;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.repository.PokemonRepository;

import java.io.*;
import java.util.ArrayList;

public class EncyclopediaRepository {
    private ArrayList<Encyclopedia> encyclopediaList = new ArrayList<Encyclopedia>();
    private PokemonRepository pokemonRepository = new PokemonRepository();

    public EncyclopediaRepository(){
        File encyclopediaFile = new File(
                "src/main/java/PokemonBeyond/Encyclopedia/db/encyclopedia.dat");
        if(!encyclopediaFile.exists()){
            savePokemonMasterEncyclopedia(encyclopediaFile);
        }
        loadEncyclopediaList();
    }

    private void savePokemonMasterEncyclopedia(File file) {
        ObjectOutputStream ois = null;
        try {
            ois = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)
                    )
            );
            ArrayList<Pokemon> pokemonList = pokemonRepository.getPokemonList();
            ArrayList<Integer> pokemonNoList = new ArrayList<>();
            for(int i=0; i<pokemonList.size(); i++ ){
                pokemonNoList.add(pokemonList.get(i).getPokemonNo());
            }
            ois.writeObject(new Encyclopedia(0,pokemonNoList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(ois!=null)ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public ArrayList<Encyclopedia> selectAllEncyclopedia(){
        return encyclopediaList;
    }
    public Encyclopedia selectEncyclopedia(int memberNo){
        for (Encyclopedia encyclopedia : encyclopediaList){
            if(encyclopedia.getMemberNo() == memberNo){
                return encyclopedia;
            }
        }
        System.out.println("도감목록에 일치하는 회원번호 없음");
        return null;
    }


    private void loadEncyclopediaList() {
        File encyclopediaFile = new File(
                "src/main/java/PokemonBeyond/Encyclopedia/db/encyclopedia.dat");

        ObjectInputStream getEncyclopediaListStream = null;
        try {
            getEncyclopediaListStream = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(encyclopediaFile)
                    )
            );
            while(true){
                Encyclopedia encyclopedia = (Encyclopedia) getEncyclopediaListStream.readObject();
                encyclopediaList.add(encyclopedia);
            }
        } catch (IOException e) {
            System.out.println("도감정보 로딩 완료");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (getEncyclopediaListStream != null)getEncyclopediaListStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private int updateEncyclopediaList(Encyclopedia encyclopedia){
        int result = 0;
        File encyclopediaFile = new File(
                "src/main/java/PokemonBeyond/Encyclopedia/db/encyclopedia.dat");
        MyOutput updateEncyclopediaListStream = null;
        try {
            updateEncyclopediaListStream = new MyOutput(
                    new BufferedOutputStream(
                            new FileOutputStream(encyclopediaFile)
                    )
            );
            updateEncyclopediaListStream.writeObject(encyclopedia);
            result = 1;
            encyclopediaList.add(encyclopedia);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (updateEncyclopediaListStream != null) updateEncyclopediaListStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
