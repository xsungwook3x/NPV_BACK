package NPV.demo.config;

import NPV.demo.repository.CertificateRepository;
import NPV.demo.repository.JpaUserRepository;
import NPV.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class SpringConfig {

    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public SpringConfig(JpaUserRepository jpaUserRepository){
        this.jpaUserRepository=jpaUserRepository;
    }

    @Bean
    public UserService userService(){
        return new UserService(jpaUserRepository);
    }

}
