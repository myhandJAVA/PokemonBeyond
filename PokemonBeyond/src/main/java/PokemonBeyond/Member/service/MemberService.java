package PokemonBeyond.Member.service;

import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.Member.repository.MemberRepository;

public class MemberService {

    private final MemberRepository mr = new MemberRepository();

    public MemberService() {
    }

    public void registMember(Member newMember) {

        boolean result = mr.insertMember(newMember);

        if (result == true) {
            System.out.println(newMember.getNickName() + "님 포켓몬 세계에 오신걸 환영합니다!!!");
        }

    }

//    public void registHeadMember(Member newMember) {
//
//        int result = mr.insertHeadMember(newMember);
//
//        if (result == 1) {
//            System.out.println(newMember.getNickName() + "님 포켓몬 세계에 오신걸 환영합니다!!!");
//        }
//    }

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

    public void findMemberBy(Member memberNickName) {

        Member selectedMember = mr.selectMemberBy(memberNickName);

        if (selectedMember != null) {
            System.out.println("조회된 회원은: "+selectedMember.getNickName()+" 입니다.");
        } else {
            System.out.println("그런 회원은 없네요 ㅠㅠ");
        }
    }

    public void removeMember(Member resultMember) {
        int result = mr.deleteMember(resultMember);
        if (result == 1) {
            System.out.println("언제든 포켓몬 세계로 돌아오십쇼!");
        }

    }

    public void modifyMember(Member reform) {
        int result = mr.updateMember(reform);
        if (result == 1) {
            System.out.println("수정 성공!");
            return;
        }

        System.out.println("수정 내역 없음");
    }

//    public void viewMember(Member resultMember) {           // repo까지 갈 필요 있나?
//        System.out.println("===== 내 정보 보기 =====");
//        System.out.println("닉네임: " + resultMember.getNickName());
//        System.out.println("이름: " + resultMember.getName());
//        System.out.println("나이: " + resultMember.getAge());
//    }
}
