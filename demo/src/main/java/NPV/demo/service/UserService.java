package NPV.demo.service;

import NPV.demo.domain.entity.User;
import NPV.demo.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private JpaUserRepository jpaUserRepository;

    @Autowired
    public UserService(JpaUserRepository jpaUserRepository){//의존성 주입
        this.jpaUserRepository = jpaUserRepository;
    }

    public User create(User user){
        return jpaUserRepository.save(user);
    }

    public Optional<User> read(String id){
        return jpaUserRepository.findById(id);
    }

    public User update(String id, Long password){
        User user =read(id).get();
        user.setPassword(password);
        return jpaUserRepository.save(user);
    }

    public void delete(String id){
        jpaUserRepository.deleteById(id);
    }
}
