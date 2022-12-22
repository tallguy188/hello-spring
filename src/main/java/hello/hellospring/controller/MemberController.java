package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//화면에 붙이려면 컨트롤러와 뷰템플릿이 필요
//멤버 컨트롤러가 멤버서비스를 통해 회원가입과 데이터 조회가 가능하게해야됨(의존관계)
@Controller
public class MemberController {

    //private final MemberService memberService = new MemberService();
    //이런식으로 new로 생성해주게 되면 앞으로 다른 서비스컨트롤러에서도 다 new로 생성해줘야된다.
    //이런방법보다 그냥 하나만 생성하고 같이 공용으로 쓰는 것이더 낫다. -> 스프링컨테이너에 등록

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
