package NPV.demo.repository;

import NPV.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, String> {

    @Override//create,update
    User save(User user);

    @Override//Read
    Optional<User> findById(String id);

    @Override //delete
    void deleteById(String id);


}
