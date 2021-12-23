package NPV.demo.repository;

import NPV.demo.domain.entity.Certificate;
import NPV.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCertificateRepository extends JpaRepository<Certificate,String> {

    @Override//create,update
    Certificate save(Certificate certificate);

    @Override//Read
    Optional<Certificate> findById(String id);

    @Override //delete
    void deleteById(String id);
}
