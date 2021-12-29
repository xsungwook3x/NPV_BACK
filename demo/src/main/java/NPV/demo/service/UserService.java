package NPV.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    /*
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

    public User updatePassword(String id, Long password){
        User user =read(id).get();
        user.setPassword(password);
        return jpaUserRepository.save(user);
    }

    public User updateDate(String id, LocalDate date){
        User user=read(id).get();
        user.setDate(date);
        return jpaUserRepository.save(user);
    }
    public void delete(String id){
        jpaUserRepository.deleteById(id);
    }
    */



}
