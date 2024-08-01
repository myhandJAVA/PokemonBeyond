package PokemonBeyond.Member.service;

import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.Member.repository.MemberRepository;

public class MemberService {

    private MemberRepository mr = new MemberRepository();

    public MemberService() {
    }

    public void registMember(Member newMember) {

        boolean result = mr.insertMember(newMember);

        if (result == true) {
            System.out.println(newMember.getNickName() + "님 포켓몬 세계에 오신걸 환영합니다!!!");
        }

    }
  
    public Member logInMember(Member logInMember) {

        // 로그인 전 확인
        Member checkMember = mr.logInCheckMember(logInMember);

        if (checkMember != null) {
            return checkMember;
        } else if (checkMember == null) {
            System.out.println("아이디 및 패스워드가 틀렸거나 없는 아이디입니다.");
        }
        return null;
    }

    public Member searchMember(String nickName) {
        Member member = mr.findMemberNickname(nickName);
        if (member == null) {
            System.out.println("일치하는 회원이 없습니다.");
        }
        return member;
    }

    public void modifyMember(Member member) {
        mr.updateMember(member);
    }

    public void exitMember(Member member) {
        mr.removeMember(member);

    }

//    public void viewMember(Member resultMember) {           // repo까지 갈 필요 있나?
//        System.out.println("===== 내 정보 보기 =====");
//        System.out.println("닉네임: " + resultMember.getNickName());
//        System.out.println("이름: " + resultMember.getName());
//        System.out.println("나이: " + resultMember.getAge());
//    }
}
