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
            saveEncyclopedia(makePokemonMasterEncyc(),encyclopediaFile);
        }
        loadEncyclopediaList();
    }

    private void saveEncyclopedia(ArrayList<Encyclopedia> encycList ,File file) {
        ObjectOutputStream ois = null;
        try {
            ois = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)
                    )
            );
            for(int i=0; i<encycList.size();i++){
                ois.writeObject(encycList.get(i));
            }
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

    private ArrayList<Encyclopedia> makePokemonMasterEncyc() { // 삭제하기
        ArrayList<Pokemon> pokemonList = pokemonRepository.getPokemonList();
        ArrayList<Integer> pokemonNoList = new ArrayList<>();
        for(int i=0; i<pokemonList.size(); i++ ){
            pokemonNoList.add(pokemonList.get(i).getPokemonNo());
        }
        ArrayList<Encyclopedia> masterList = new ArrayList<>();
        Encyclopedia pokemonMasterEncyc = new Encyclopedia("PokemonMaster", pokemonNoList);
        masterList.add(pokemonMasterEncyc);
        return masterList;
    }

    public ArrayList<Encyclopedia> selectAllEncyclopedia(){
        return encyclopediaList;
    }
    public Encyclopedia selectEncyclopedia(String memberId){
        for (Encyclopedia encyclopedia : encyclopediaList){
            if(encyclopedia.getMemberId() == memberId){
                return encyclopedia;
            }
        }
        System.out.println("도감목록에 일치하는 회원아이디 없음");
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

    public int insertEncyclopedia(Encyclopedia encyclopedia){
        int result = 0;
        String memberId = encyclopedia.getMemberId();
        for(int i=0; i <encyclopediaList.size(); i++){
            if(encyclopediaList.get(i).getMemberId() == memberId){
                result = -1;
                return result;
            }
        }
        File encyclopediaFile = new File(
                "src/main/java/PokemonBeyond/Encyclopedia/db/encyclopedia.dat");
        MyOutput updateEncyclopediaListStream = null;
        try {
            updateEncyclopediaListStream = new MyOutput(
                    new BufferedOutputStream(
                            new FileOutputStream(encyclopediaFile,true)
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

    public int deleteEncyclopedia(String memberId){
        int result = 0;
        boolean isInList = false;
        int deletedMemNo = -1;
        ArrayList<Encyclopedia> copiedList = (ArrayList<Encyclopedia>) encyclopediaList.clone();
        for(int i=0; i<copiedList.size();i++){
            if(copiedList.get(i).getMemberId() == memberId){
                copiedList.remove(i);
                isInList = true;
                deletedMemNo = i;
            }
        }
        if(!isInList){
            System.out.println("해당 멤버 아이디가 없습니다. 다시 확인해주세요.");
            result = -1;
            return result;
        }
        saveEncyclopedia(copiedList,new File("src/main/java/PokemonBeyond/Encyclopedia/db/encyclopedia.dat"));
        encyclopediaList.remove(deletedMemNo);
        result = 1;
        return result;
    }

    public ArrayList<Encyclopedia> getEncyclopediaList() {
        return encyclopediaList;
    }
}
