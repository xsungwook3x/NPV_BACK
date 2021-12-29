package NPV.demo.DTO;

import NPV.demo.domain.entity.Authority;
import NPV.demo.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class MemberRequestDto {

    private String id;
    private String password;
    private String name;
    private String phone;
    private String fileName;

    public Member toMember(PasswordEncoder passwordEncoder){
        return Member.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .phone(phone)
                .name(name)
                .fileName(fileName)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(id,password);
    }
}
