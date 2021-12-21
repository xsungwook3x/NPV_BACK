package NPV.demo.controller;

import NPV.demo.domain.entity.User;
import NPV.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/user")//회원가입
    public ResponseEntity<User> userSignUp(@RequestBody User user){

        return ResponseEntity.ok().body(userService.create(user));
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
        return ResponseEntity.ok().body(userService.update(id,password));
    }

    @DeleteMapping("/user")//탈퇴
    public void delete(@RequestParam String id){
        userService.delete(id);
    }


}
