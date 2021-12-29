package NPV.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="member")
@Entity
public class Member {

    @Id
    @JsonIgnore
    @Column(name = "serialnumber")
    private String id;

    private String name;

    @JsonIgnore
    private String password;

    private String phone;

    @Column(name = "filename")
    private String fileName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="filedate")
    private LocalDate fileDate;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String id,String name, String password, String phone, String fileName,Authority authority){
        this.id=id;
        this.name=name;
        this.password=password;
        this.phone=phone;
        this.fileName=fileName;
        this.fileDate=LocalDate.now();
        this.authority=authority;
    }
}
