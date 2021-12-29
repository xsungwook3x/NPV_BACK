package NPV.demo.DTO;

import NPV.demo.domain.entity.Member;

public class MemberResponseDto {
    private String id;

    public MemberResponseDto(String id) {
        this.id=id;
    }


    public static MemberResponseDto of(Member member){
        return new MemberResponseDto(member.getId());
    }
}
