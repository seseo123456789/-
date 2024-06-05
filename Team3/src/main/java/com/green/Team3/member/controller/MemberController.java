package com.green.Team3.member.controller;

import com.green.Team3.member.service.MemberServiceImpl;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.util.MailService;
import com.green.Team3.util.MailVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberServiceImpl memberService;
    @Resource(name = "mailService")
    private MailService mailService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    //회원가입 페이지
    @GetMapping("/joinForm")
    public String joinForm(){
        return "content/member/join";
    }

    //회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        //연락처 세팅 (010,0000, 0000 -> 010-0000-0000) 변환 후 set(저장)
        memberVO.setMemberTel(memberVO.getMemberTel().replace(",", "-"));
        //이메일 세팅
        memberVO.setMemberEmail(memberVO.getMemberEmail().replace(",", ""));
        //회원가입 전 비밀번호 암호화
        memberVO.setMemberPw(encoder.encode(memberVO.getMemberPw()));
        //회원가입 쿼리
        memberService.join(memberVO);
        return "redirect:/";
    }

    //아이디 중복 확인
    @ResponseBody
    @PostMapping("/idCheckFetch")
    public int idCheck(@RequestParam(name = "memberId") String memberId){
        int result = memberService.idCheck(memberId);
        return result;
    }

    // 메인 로고 클릭 시 첫 화면
    @GetMapping("/logoClick")
    public String logoClick(){
        return "redirect:/";
    }

    //아이디 찾으러 가기
    @GetMapping("/findIdForm")
    public String findIdForm(@RequestParam(value = "errorMsg",required = false,defaultValue = "success")String errorMsg,Model model){
        model.addAttribute("errorMsg",errorMsg); // 에러코드 전송이유 : 로그인을 안하고 갈 수 있는 페이지로 갈 경우 모달이 뜸.
        return "/content/member/findId";
    }

    // 아이디 찾기
    @ResponseBody
    @PostMapping("/findIdFetch")
    public String findIdFetch(MemberVO memberVO){
        return memberService.findMemberId(memberVO);
    }

    // 비밀번호 찾으러 가기
    @GetMapping("/findPasswordForm")
    public String findPw(@RequestParam(value = "errorMsg",required = false,defaultValue = "success")String errorMsg,Model model){
        model.addAttribute("errorMsg",errorMsg); //에러코드 전송이유 : 로그인을 안하고 갈 수 있는 페이지로 갈 경우 모달이 뜸.
        return "/content/member/findPassword";
    }

    // 비밀번호 찾기
    @ResponseBody
    @PostMapping("/findPwFetch")
    public boolean findPwFetch(MemberVO memberVO){
        String memberEmail = memberService.getMemberEmail(memberVO);
        // 쿼리가 정상 작동하여 이메일이 들어왔을 경우
        if(memberEmail != null){
            //비밀번호 변경
            //임시 비밀번호 생성
            String imsiPw = mailService.createRandomPw();
            //암호화
            String encodedPw = encoder.encode(imsiPw);
            //vo에 담고
            memberVO.setMemberPw(encodedPw);
            //암호화된 임시비밀번호를 업데이트
            memberService.updateMemberPw(memberVO);
            //메일 보내기
            MailVO mailVO = new MailVO();
            mailVO.setTitle("임시 비밀번호 발송");
            mailVO.setRecipient(memberEmail);
            mailVO.setContent("임시 비밀번호 : "+imsiPw + "\n\n로그인 이후 비밀번호를 꼭 변경해 주세요.");
            mailService.sendSimpleEmail(mailVO);

        }
        return memberEmail != null ? true : false;
    }

    // 내정보 보러가기
    @GetMapping("/myInformationForm")
    public String myInformation(Authentication authentication,Model model){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("memberVO",memberService.selectMyInformation(user.getUsername()));
        return "/content/member/myInformation";
    }
    // 비밀번호 체크
    @ResponseBody
    @PostMapping("/pwCheck")
    public boolean pwCheck(@RequestParam(name = "memberPw")String memberPw, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return encoder.matches(memberPw,memberService.matchPassWord(user.getUsername()));
    }
    // 내가 설정한 비밀번호로 비밀번호 업데이트
    @ResponseBody
    @PostMapping("/updatePassword2")
    public void updatePassword2(@RequestParam(name = "memberPw")String memberPw,Authentication authentication){
        User user = (User) authentication.getPrincipal();
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberPw(encoder.encode(memberPw));
        memberVO.setMemberId(user.getUsername());
        memberService.updateMemberPw(memberVO);
    }

    @GetMapping("/stuInfoForm")
    public String stuInfo(Authentication authentication,Model model){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("member",memberService.selectMyInformation(user.getUsername()));
        model.addAttribute("homework",memberService.selectMyHomework(user.getUsername()));
        model.addAttribute("consult",memberService.selectMyConsult(user.getUsername()));
        model.addAttribute("test",memberService.selectMyTest(user.getUsername()));
        model.addAttribute("atd",memberService.selectMyAttendance(user.getUsername()));
        return "/content/student/stu_info";
    }

}
