package hello.hellospring.repository;
import hello.hellospring.domain.Member;


import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);  // 회원정보를 저장
    Optional<Member> findById(Long id);   // id나 name으로 회원정보를 찾아옴
    Optional<Member> findByName(String name);
    List<Member> findAll();  // 저장된 모든 회원을 리스트로 반환
}
