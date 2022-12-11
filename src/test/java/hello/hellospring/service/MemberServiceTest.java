package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
   // 테스트는 과감하게 한글로 바꿔도 된다.

    /*
    MemberService memberService = new MemberService(memberRepository);
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    */
    // 이런식의 코드면 memberservice에서 만든 리포지토리랑 다른 리포지토리가 또 생성되는 것임.
    // 이걸 방지하려면 memberservice에서 리포지토리를 new로 생성하지말고 constructor로 외부에서 넣어주도록 바꿔줘야됨.

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach // clear function
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member); // 리포지토리에 저장하고 id를 반환

        //then

        Member findMember = memberService.findOne(saveId).get();

        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
// join test의 핵심은 중복회원로직이 잘 돌아가는지 확인하는 것도 중요함.

    @Test
    public void 중복_회원_검증(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //오른쪽 람다가 진행되면 왼쪽 exception이 터져야됨.

        /* try {
            memberService.join(member2);
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}