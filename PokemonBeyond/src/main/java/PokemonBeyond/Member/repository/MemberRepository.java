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
//        loadMember(file);
    }

//    private void loadMember(File file) {
//        if (file.length() == 0) {
//            return;
//        }
//
//        ObjectInputStream ois = null;
//        try {
//            ois = new ObjectInputStream(
//                    new BufferedInputStream(
//                            new FileInputStream(file)
//                    )
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                if(ois != null) ois.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

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
//            moo.writeObject(newMember.getId() + newMember.getPwd() + newMember.getName()
//            + newMember.getAge() + newMember.getNickName());

            moo.writeObject(newMember);

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


    public Member logInCheckMember(Member logInMember) {
        for (Member member : memberList) {
            if (member.getId().equals(logInMember.getId())) {
                if (member.getPwd().equals(logInMember.getPwd())) {
                    return member;
                }
            }
        }
        return null;
    }

    public Member selectMemberBy(Member memberNickName) {
        for (Member member: memberList) {
            if (memberNickName.equals(member.getNickName())) {
                return member;
            }
        } return null;
    }


    public int deleteMember(Member resultMember) {
        int result = 0;
        for (Member member : memberList) {
            if (member.getId().equals(resultMember.getId())) {
                member.setId(null);
            }
        }
        result = 1;
        return result;
    }

    public int updateMember(Member reform) {
        for (int i = 0; i < memberList.size(); i++) {
            Member iMember = memberList.get(i);
            if(iMember.getId() == reform.getId()){
                System.out.println("===== 수정 전 기존 회원 정보와의 비교 =====");
                System.out.println("reform = " + reform);
                System.out.println("iMember = " + iMember);

                memberList.set(i, reform);

                File file = new File("src/main/java/com/ohgiraffers/section04/testapp/db/memberDB.dat");
                saveMembers(file, memberList);

                if(!iMember.equals(reform)) return 1;
            }
        }
        return 0;
    }

    private void saveMembers(File file, ArrayList<Member> memberList) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)
                    )
            );

            /* 설명. 초기 회원 세명을 각각 객체 출력 내보내기 */
            for(Member member: memberList) {
                oos.writeObject(member);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
