package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get(); // optional의 값을 꺼낼때는 get을 써준다.
        //Assertions.assertEquals(member,result);
        Assertions.assertThat(member).isEqualTo(result);
    }
}
