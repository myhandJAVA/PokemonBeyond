package PokemonBeyond.Member.repository;

import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.Member.stream.MyObjectOutput;

import java.io.*;
import java.util.ArrayList;

public class MemberRepository {

    String filePath = "src/main/java/PokemonBeyond/Member/db/memberDB.dat";
    File file = new File(filePath);

    private ArrayList<Member> memberList = new ArrayList<>();

    public MemberRepository() {

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }



    }


    public int insertMember(Member newMember) {

        MyObjectOutput moo = null;
        int result = 0;
        try {
            moo = new MyObjectOutput(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath, true)
                    )
            );

            /* 궁금. 왜 그냥 newMember를 넣으면 안되고 하나씩 넣어줘야하지??? */
            moo.writeObject(newMember.getId() + newMember.getPwd() + newMember.getName()
            + newMember.getAge() + newMember.getNickName());

            memberList.add(newMember);

            result = 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (moo != null) moo.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }




}
