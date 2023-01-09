package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스가 인터페이스를 받을때는 implements가 아니라 extends
public interface SpringDataJpaMemberRopository extends JpaRepository<Member,Long>,MemberRepository{
    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
