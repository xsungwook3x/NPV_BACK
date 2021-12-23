package NPV.demo.controller;

import NPV.demo.domain.entity.User;
import NPV.demo.service.CertificateService;
import NPV.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final CertificateService certificateService;

    @Autowired
    public UserController(UserService userService, CertificateService certificateService){
        this.userService=userService;
        this.certificateService=certificateService;
    }

    @PostMapping("/user")//회원가입
    public ResponseEntity<User> userSignUp(@RequestBody User user){

        return ResponseEntity.ok().body(userService.create(user));
    }

    @PostMapping("/user/certificate")//회원가입시 인증서 제출
    public ResponseEntity<String> uploadCertificate(@RequestBody MultipartFile file)throws IllegalStateException, IOException {
        certificateService.store(file);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/user/certificate")//인증서 다운로드
    public ResponseEntity<Resource> downloadCertificate(@RequestParam String filename){
        Resource file = certificateService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +file.getFilename()+"\"").body(file);

    }

    @DeleteMapping("/user/certificate")//인증서 삭제
    public void deleteCertificate(@RequestParam String filename){
        certificateService.delete(filename);
    }

    @PutMapping("/user/certificate")//인증서 업데이트
    public ResponseEntity<User> updateCertificate(@RequestParam String filename, @RequestParam String id,@RequestBody MultipartFile file) throws IllegalStateException, IOException{
        certificateService.update(file,filename);
        return ResponseEntity.ok().body(userService.updateDate(id, LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
    }

    @GetMapping("/user")//로그인
    public ResponseEntity<User> userLogin(@RequestParam String id){
        if(userService.read(id).get()==null){
            return ResponseEntity.notFound().build();
        }

        return  ResponseEntity.ok().body(userService.read(id).get());
    }

    @GetMapping("/user/password")//비번 변경전 확인
    public ResponseEntity<User> userCheck(@RequestParam String id,@RequestParam Long password){
        if(userService.read(id).get()==null){
            return ResponseEntity.notFound().build();
        }
        else if(userService.read(id).get().getPassword().equals(password)) {
            return ResponseEntity.ok().body(userService.read(id).get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/user/password")//비번 번경
    public ResponseEntity<User> userChange(@RequestParam String id,@RequestParam long password){
        if(userService.read(id).get()==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userService.updatePassword(id,password));
    }


    @DeleteMapping("/user")//탈퇴
    public void delete(@RequestParam String id){
        userService.delete(id);
    }


}
