package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /** 회원 가입
     *
     * @param member
     * @return
     */
    public Long join(Member member) {
        //같은 이름의 중복 회원은 안된다.
        validateDuplicateMember(member); // 중복 회원 검증 메소드
        // findbyname의 반환이 어차피 optional이기 때문에 따로 optional로 감싸주지 않음.
        // ifPresent는 optional의 기능중 하나임.

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
// null이 반환될 가능성이 있으면 optional로 감싸준다.