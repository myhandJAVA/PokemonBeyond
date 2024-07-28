package PokemonBeyond.Member.service;

import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.Member.repository.MemberRepository;

public class MemberService {

    private final MemberRepository mr = new MemberRepository();

    public MemberService() {
    }

    public void registMember(Member newMember) {

        int result = mr.insertMember(newMember);

        if (result == 1) {
            System.out.println(newMember.getNickName() + "님 포켓몬 세계에 오신걸 환영합니다!!!");
        }
    }
}
