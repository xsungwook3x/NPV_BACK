package NPV.demo.repository;

import NPV.demo.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {

    @Override//create,update
    Member save(Member member);

    @Override//Read
    Optional<Member> findById(String id);

    @Override //delete
    void deleteById(String id);
}
