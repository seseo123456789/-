package com.green.Team3.test.controller;


import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;
import com.green.Team3.test.service.StuTestServiceImpl;
import com.green.Team3.test.service.TestService;
import com.green.Team3.test.service.TestServiceImpl;
import com.green.Team3.test.vo.TestAskVO;
import com.green.Team3.test.vo.TestScoreVO;
import com.green.Team3.test.vo.TestSubjectVO;
import com.green.Team3.test.vo.TestVO;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/stuTest")
@Controller
public class StuTestController {

    @Resource(name = "stuTestService")
    private StuTestServiceImpl stuTestService;

    @Resource(name = "testService")
    private TestServiceImpl testService;



    // /////////////////////////학생이 로그인 했을때 성적확인하기 ///////////////////////////////////////

    // 성적 서치서비스 첫페이지
    @GetMapping("/stuTestFirst")
    public String stuTestFirst(Model model, Authentication authentication){

        User user=(User) authentication.getPrincipal();

        MemberVO stuInfoService = stuTestService.selectStuTest(user.getUsername());
        model.addAttribute("stuInfoService",stuInfoService);

        List<OperatorVO> clCnt = stuTestService.stuClCnt(user.getUsername());
        model.addAttribute("clCnt",clCnt);


        return "content/student/student_test_search";
    }

    // 학생이 수강별 조회
    @ResponseBody
    @PostMapping("/classListSearch")
    public List<TestVO> classListSearch(@RequestParam(name = "memberId") String memberId){

        List<TestVO> stuCLTest =stuTestService.selectStuCLTest(memberId);
        return stuCLTest;
    }

    // 학생이 기간별 조회
    @ResponseBody
    @PostMapping("/testListSearch")
    public List<TestVO> testListSearch(@RequestParam(name = "memberId") String memberId) {

        List<TestVO> stuTest = stuTestService.selectStuTestDetail(memberId);
        return stuTest;
    }

    // 학생이 과목별 조회
    @ResponseBody
    @PostMapping("/subListSearch")
    public List<TestSubjectVO> subListSearch(@RequestParam(name = "memberId") String memberId){

        List<TestSubjectVO> stuSubTest =stuTestService.selectStuSub(memberId);
        return stuSubTest;
    }

    // 학생이 전체이수표 조회
    @ResponseBody
    @PostMapping("/totalListSearch")
    public List<ClsVO> totalListSearch(@RequestParam(name = "memberId") String memberId){

        List<ClsVO> totalStuTest =stuTestService.totalSelectTest(memberId);
        return totalStuTest;
    }



// //////////////////////////시험조회버튼 클릭시 나의 상세성적 확인하기 ///////////////////////////

    // 학생이 본인 단일 시험 성적 상세성적 조회
    @GetMapping("/goMyScore")
    public String goMyScore(TestScoreVO testScoreVO, @RequestParam(name = "memberId")String memberId, Model model){


        MemberVO stuInfoService = stuTestService.selectStuTest(memberId);
        model.addAttribute("stuInfoService",stuInfoService);
        System.out.println(stuInfoService);


        TestScoreVO mainMyScore= stuTestService.mainTestMyScore(testScoreVO);
        model.addAttribute("mainMyScore",mainMyScore);

        return "content/student/student_test_check";

    }

    // 학생이 본인 과목시험 성적 상세성적 조회
    @GetMapping("/goMysubScore")
    public String goMysubScore(TestScoreVO testScoreVO, @RequestParam(name = "memberId")String memberId,
                                Model model){


        MemberVO stuInfoService = stuTestService.selectStuTest(memberId);
        model.addAttribute("stuInfoService",stuInfoService);


        List<TestScoreVO> subMyScores= stuTestService.subTestMyScore(testScoreVO);
        model.addAttribute("subMyScores",subMyScores);


        return "content/student/student_subject_check";
    }



    // 학생이 본인 성적 모든성적 조회 프린트 //

    @GetMapping("totalStuPrint")
    public String totalStuPrint(Model model, TestScoreVO testScoreVO){

            List<TestScoreVO> printMyGrade = stuTestService.printMyGrade(testScoreVO);
            model.addAttribute("printMyGrade", printMyGrade);

            MemberVO memberInfo = stuTestService.selectStuTest(testScoreVO.getMemberId());
            model.addAttribute("memberInfo", memberInfo);

            ClsVO clsInfo = testService.onlyClassNum(testScoreVO.getClassNum());
            model.addAttribute("clsInfo", clsInfo);

            return "content/student/student_total_test";
    }



// ############################# (학생) 이의신청 페이지  #############################


    // (학생)이의신청 목록페이지 (첫페이지)
    @GetMapping("/stuAskFirst")
    public String stuAskFirst(Model model, Authentication authentication){
        User user=(User) authentication.getPrincipal();
        List<TestAskVO> testAskList = stuTestService.selectStuAsk(user.getUsername());
        model.addAttribute("testAskList",testAskList);
        System.out.println(testAskList);

         return "content/student/student_test_ask";
    }

    // (학생) 이의신청 글 적기 페이지
    @GetMapping("/stuAskWrite")
    public String stuAskWrite(Model model, Authentication authentication ){
            User user=(User) authentication.getPrincipal();

            MemberVO stuInfoService = stuTestService.selectStuTest(user.getUsername());
            model.addAttribute("stuInfoService",stuInfoService);

            List<TestVO> stuTest = stuTestService.selectStuTestDetail(user.getUsername());
            model.addAttribute("stuTest",stuTest);

            List<ClsVO> totalStuTest =stuTestService.totalSelectTest(user.getUsername());
            model.addAttribute("totalStuTest", totalStuTest);

        return "content/student/student_ask_write";
    }


    // (학생) 이의신청 글 저장
    @PostMapping("/insertStuAsk")
    public String insertStuAsk(TestAskVO testAskVO, Authentication authentication){
        User user=(User) authentication.getPrincipal();

        stuTestService.insertStuAsk(testAskVO);

        return "redirect:/stuTest/stuAskFirst?memberId=" + testAskVO.getMemberId();
    }

    // 학생 이의신청 상세글보기
    @GetMapping("/stuAskDetail")
    public String stuAskDetail(TestAskVO testAskVO, Model model,Authentication authentication){

        User user=(User) authentication.getPrincipal();

        TestAskVO testAskOne = stuTestService.stuAskDetail(testAskVO);
        model.addAttribute("testAskOne",testAskOne);
        System.out.println(testAskOne);

       TeacherVO teacherID = stuTestService.askMemberId(user.getUsername());
        System.out.println("999999"+teacherID);

        if(teacherID!=null){
            List<TestAskVO> thTestAskList = testService.selTeacherAsk(user.getUsername());
            model.addAttribute("thTestAskList", thTestAskList);
        }
        return "content/student/student_ask_detail";
    }


    //상세내용을 가지고 글 수정 페이지로 가기
    @GetMapping("/goUpdatePG")
    public String goUpdatePG(TestAskVO testAskVO ,Model model, Authentication authentication){


        TestAskVO testAskOne = stuTestService.stuAskDetail(testAskVO);
        model.addAttribute("testAskOne",testAskOne);

        return "content/student/student_ask_wUpdate";
    }

    //학생 신청글 1개 수정하기
    @PostMapping("/askUpdate")
    public String askUpdate(TestAskVO testAskVO){
        stuTestService.updateMyAsk(testAskVO);
        return "redirect:/stuTest/stuAskFirst";
    }

    //학생 신청글 1개 삭제
    @GetMapping("/goMyDelete")
    public String goMyDelete(TestAskVO testAskVO){
        stuTestService.deleteMyAsk(testAskVO.getProtestNum());
        return "redirect:/stuTest/stuAskFirst";
    }


}
