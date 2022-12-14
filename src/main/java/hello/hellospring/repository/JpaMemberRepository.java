package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;


    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);   // pk는 이런방식으로 조회
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {  //pk가 아니므로 jpql이라는 쿼리언어를 써줌
        List<Member> result =em.createQuery("select m from Member m",Member.class)
                .getResultList();
        return result;
    }
}
