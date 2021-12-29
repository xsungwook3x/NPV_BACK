package NPV.demo.controller;

import NPV.demo.DTO.MemberResponseDto;
import NPV.demo.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login/{id}")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String id){
        return ResponseEntity.ok(memberService.getMemberInfo(id));
    }
}
