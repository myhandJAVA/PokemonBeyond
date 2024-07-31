package PokemonBeyond.Member.repository;

import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.Member.stream.MyObjectOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private final String filePath = "src/main/java/PokemonBeyond/Member/db/memberDB.dat";
    private final File file;
    private final ArrayList<Member> memberList = new ArrayList<>();

    public MemberRepository() {
        file = new File(filePath);
        // 파일이 없는 경우
        if (!file.exists()) {
            // 파일 생성 및 헤더 추가
            saveMembers(file, memberList);
        }
        loadMember(file);

    }

    private void saveMembers(File file, ArrayList<Member> memberList) {
        // 파일을 덮어 씌워 저장
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

    private void loadMember(File file) {
        // 파일을 읽어 ArrayList에 저장
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(file)
                    )
            );

            while(true) {
                memberList.add((Member)ois.readObject());
            }
        } catch (EOFException e) {
            System.out.println("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private void saveMembers(File file, ArrayList<Member> memberList) {
        // 파일을 덮어 씌워 저장
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

    public boolean insertMember(Member newMember) {
        // 파일에 이어붙여 저장
        MyObjectOutput moo = null;
        boolean result = false;
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

            result = true;
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

    public Member findMemberId(String id) {
        for (Member member : memberList) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }

    public Member findMemberNickname(String nickName) {
        for (Member member : memberList) {
            if (member.getNickName().equals(nickName)) {
                return member;
            }
        }
        return null;
    }

    public void updateMember(Member member) {
        Member Member = findMemberId(member.getId());

        Member.setPwd(member.getPwd());
        Member.setName(member.getName());
        Member.setAge(member.getAge());
        Member.setNickName(member.getNickName());
        saveMembers(file, memberList);
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

    public void removeMember(Member member) {
        memberList.remove(member);
        System.out.println("포켓몬 세계는 언제나 당신을 기다립니다.");

        saveMembers(file, memberList);

    }
}
