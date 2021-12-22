package NPV.demo.repository;

import NPV.demo.domain.entity.User;

import java.util.Optional;

public interface JpaCertificateRepository {

    @Override//create,update
    User save(User user);

    @Override//Read
    Optional<User> findById(String id);

    @Override //delete
    void deleteById(String id);
}
