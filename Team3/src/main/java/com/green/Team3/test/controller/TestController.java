package com.green.Team3.test.controller;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.test.service.StuTestService;
import com.green.Team3.test.service.StuTestServiceImpl;
import com.green.Team3.test.service.TestServiceImpl;
import com.green.Team3.test.vo.*;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/test")
@Controller
public class TestController {


    @Resource(name = "testService")
    private TestServiceImpl testService;

    @Resource(name = "stuTestService")
    private StuTestServiceImpl stuTestService;



// ############################# 강사가 로그인해서 성적 처리 관리 #############################
    @GetMapping("/testList")
    public String testList(Model model, Authentication authentication){
        User user=(User) authentication.getPrincipal();
                // ------------- 강사가 수업하는 수강명 목록 조회 ---------------
                // 로그인한 유저 아이디 user.getUsername()
                List<ClsVO> classList = testService.selectTeacherClassList(user.getUsername());
                model.addAttribute("classList", classList);

        return "content/test/teacher_first_sc";

    }


//  #############################  선생님 반별 시험목록조회 페이지  #############################



    // --------- teacher_test_List html에서 시험명 목록조회 ---------
    @RequestMapping("/scoreTeacher")
    public String scoreTeacher(Model model,
                               @RequestParam(name ="classNum") int classNum,
                               SearchTestVO searchTestVO, TestVO testVO) {

                //반 번호 넘겨서 평가명 추가 버튼 클릭 가능
                ClsVO onlyClassNum =testService.onlyClassNum(classNum);
                model.addAttribute("onlyClassNum", onlyClassNum);

                //반 번호 넘겨서 시험지명 목록 조회
                List<TestVO> selectTestNames = testService.selectTest(classNum);
                model.addAttribute("selectTestNames", selectTestNames);

                // 검색란
                List<TestVO> searchTests = testService.searchTestList(searchTestVO);
                model.addAttribute("searchTests",searchTests);


        return "content/test/teacher_test_List";

    }


// ######################################## 시험 설정 모달  ###############################################

    // ---------------(선생님 모달) 비동기 메인테스트 목록 조회--------------
    @ResponseBody
    @PostMapping("/selectTest")
    public List<TestVO> selectTest(TestVO testVO) {

                List<TestVO> testNames = testService.selectTest(testVO.getClassNum());
                return testNames;

    }

    // ---------------(선생님 모달) 비동기 메인테스트 저장  -----------
    @ResponseBody
    @PostMapping("/insertTest")
    public void insertTest(TestVO testVO) {
                testService.insertTest(testVO);
    }

    //------------- (선생님 모달) 과목있을시 메인테스트명, 일자 저장-----------
    @ResponseBody
    @PostMapping("/insertSubSc")
    public void insertSubSc(TestVO testVO) {
                testService.subMainTestInsert(testVO);
    }



    //--------------- (선생님 모달) 과목저장 ------------------
    @ResponseBody
    @PostMapping("/insertSub")
    public void insertSub(TestSubjectVO testSubjectVO){

                testService.insertSub(testSubjectVO);

    }

    //------------- (선생님 모달)비동기 과목 목록 + 총점 조회-----------
    @ResponseBody
    @PostMapping("/selectSubTest")
    public Map<String, Object> selectSubTest(@RequestParam(name = "testNum")int testNum) {

                // 과목조회
                List<TestSubjectVO> subNames = testService.selectSubList(testNum);
                // 메인테스트 조회
                List<TestVO> testNum2 = testService.testNumInfo2(testNum);

                Map<String, Object> map2 = new HashMap<>();
                map2.put("subNames",subNames);
                map2.put("testNum2", testNum2);

        return map2;

    }




// ######################################### 시험 상세정보 조회시  ##################################

    // ---------------(시험 상세정보)비동기  시험목록에서 조회 버틑 클릭시 상세정보 조회------------
    @ResponseBody
    @PostMapping("/selectScoreList")
    public Map<String, Object> selectScoreList(TestSubjectVO testSubjectVO, TestVO testVO) {

                // 메인테스트 조회
                List<TestVO> testSelectList = testService.testNumInfo2(testVO.getTestNum());

                // 과목목록 조회
                List<TestSubjectVO> subDetailList = testService.selectSubList(testSubjectVO.getTestNum());

                Map<String, Object> map3 = new HashMap<>();
                map3.put("testSelectList",testSelectList);
                map3.put("subDetailList", subDetailList);

        return map3;
    }


    //---------------(시험 상세정보) 과목업데이트 후 메인테스트 만점 조회------------------
    @ResponseBody
    @PostMapping("/afterSub")
     public List<TestVO> aftersub(Model model, TestVO testVO){

                List<TestVO> testSelectList = testService.testNumInfo2(testVO.getTestNum());
                model.addAttribute("testSelectList", testSelectList);

       return testSelectList;
    }

// ######################################### 시험 상세정보 업데이트   ##################################

    // -----------------  (시험 상세정보)메인테스트 1개 상세정보 조회------------------
    @ResponseBody
    @PostMapping("/selUpdateMainTest")
    public TestVO selUpdateMainTest(@RequestParam(name = "testNum") int testNum){

                TestVO mainDetailOne= testService.testNumInfo(testNum);
        return mainDetailOne;
    }


    // ------------------(시험 상세정보)메인테스트 full 상세정보 수정---------------
    @ResponseBody
    @PostMapping("/updateMainTest")
    public void updateMainTest(TestVO testVO){
                testService.updateTestDetail(testVO);
    }


    // ------------------(시험 상세정보) 메인테스트 만점제외하고 상세정보 수정 ----------------
    @ResponseBody
    @PostMapping("/updateMainTwo")
    public void updateMainTwo(TestVO testVO){
                 testService.updateTestDeTwo(testVO);

    }


    // ------------------(시험 상세정보) 과목상세정보 1개 조회 --------------------
    @ResponseBody
    @PostMapping("/selUpdateSubTest")
    public TestSubjectVO selUpdateSubTest(@RequestParam(name = "subTestNum")int subTestNum){

                    TestSubjectVO subDetailOne= testService.selectSubOne(subTestNum);
        return subDetailOne;
    }

    // ------------------(시험 상세정보) 과목 상세정보 수정 ---------------
    @ResponseBody
    @PostMapping("/updateSubTest")
    public void updateSubTest(TestSubjectVO testSubjectVO){
                    testService.updateSubDetail(testSubjectVO);

    }



    // ######################################### 시험 상세정보 삭제   ##################################
    //  (시험 상세정보)  시험정보 삭제
    @GetMapping("/deleteTest")
    public String deleteTest(@RequestParam(name = "testNum")int testNum,@RequestParam(name ="classNum")int classNum){

                    testService.deleteTest(testNum);
        return "redirect:/test/scoreTeacher?classNum="+ classNum;
    }


    //  (시험 상세정보)  과목정보 삭제
    @ResponseBody
    @PostMapping("/deleteSub")
    public void deleteSub(@RequestParam(name = "subTestNum")int subTestNum,@RequestParam(name ="classNum")int classNum){
                    testService.deleteSub(subTestNum);

    }




// ############################## 시험목록에서 등록 버튼 클릭시  (과목없음 과목있음 페이지 다르게 이동)##################################

    // ----------- 과목별성적입력 페이지 이동  -------------------------
    @GetMapping("/goTestN")
    public String goTestN(@RequestParam(name = "testNum") int testNum, @RequestParam(name = "classNum")int classNum
            , Model model, MemberVO memberVO) {

                    // 메인테스트 정보조회
                    List<TestVO> testList = testService.testNumInfo2(testNum);
                    model.addAttribute("testList", testList);

                    // 과목별시험 정보조회
                    List<TestSubjectVO> subsList = testService.subSelect(testNum);
                    model.addAttribute("subsList", subsList);


                    // 수강생 정보조회
                    List<MemberVO> stuCnt = testService.stuCnt(classNum);
                    model.addAttribute("stuCnt", stuCnt);


                    // 과목별 성적조회
                    List<TestScoreVO> subScoreList =testService.selectSubScore(testNum);
                    if(subScoreList!=null){
                        model.addAttribute("subScoreList", subScoreList);

                    }


                    // 만약 과목이 있는데 아직 설정을 안했다면 경고창 띄우기
            if(subsList.size()==0){

                List<MemberVO> stu = testService.stuCnt(classNum);
                model.addAttribute("stuList", stu);

                return "content/test/teacher_test_alert";
            }


        return "content/test/teacher_sub_sc";

    }



// ####################################  단일시험 페이지 기능 ~~~~##############################

    /// ------------- (1.단일시험) 성적 페이지 이동하면서 각종 정보 조회 -------------------
    @GetMapping("/goInputScore")
    public String goInputScore(@RequestParam(name = "testNum") int testNum, Model model) {

                // 넘버로 받는 반학생명 , 테스트명 조회
                List<MemberVO> memNumInfo = testService.memNumInfo(testNum);
                model.addAttribute("memNumInfo",memNumInfo);

                // test 넘버를 비동기에 넘기기 위해서
                TestVO testNumInfo = testService.testNumInfo(testNum);
                model.addAttribute("testNumInfo",testNumInfo);

                // 넘버로 받는 랭킹이 포함된 점수 조회
                List<TestScoreVO> classScoresList = testService.selectTestScore(testNum);
                    model.addAttribute("classScoresList", classScoresList);
                // 평균
                TestScoreVO avgTest = testService.selectAvg(testNum);
                if(avgTest!=null){
                    model.addAttribute("avgTest", avgTest.getTestAvg());
                }


        return "content/test/teacher_score";
    }


    // ---------------(1.단일시험) 성적입력하고 등록 버튼 클릭시 저장 --------------
    @PostMapping("/insertStuScore")
    public String insertStuScore(TestScoreVO testScoreVO) {

                testService.insertStuScore(testScoreVO);
        return "redirect:/test/goInputScore?testNum="+testScoreVO.getTestNum();

    }


    // --------------(1.단일시험) 입력된 성적 수정 버튼 클릭시 성적조회 하여 input에 뿌려주기------------
    @ResponseBody
    @PostMapping("/selectMainList")
    public List<TestScoreVO> selectMainList(TestScoreVO testScoreVO) {

                //시험명 클릭시 학생별 성적 조회
                List<TestScoreVO> scoreSelectList = testService.selectTestScore(testScoreVO.getTestNum());
         return scoreSelectList;
    }



    // ------------- (1.단일시험) 점수 수정 버튼 클릭시 업데이트 -----------

    @PostMapping("/updateScore")
    public String updateScore(@RequestParam(name = "score") List<Integer> scoreList
            , @RequestParam(name = "scoreNum") List<Integer> scoreNumList ,TestScoreVO testScoreVO){

                 for(int i = 0 ; i < scoreNumList.size() ; i++){
                        TestScoreVO vo = new TestScoreVO();
                        vo.setScore(scoreList.get(i));
                        vo.setScoreNum(scoreNumList.get(i));
                        testService.updateScore(vo);
                    }
        return "redirect:/test/goInputScore?testNum="+ testScoreVO.getTestNum();
    }






// ####################################  과목별 시험 페이지 기능 ~~~~##############################

    // 과목별 시험입력페이지 가서 입력생성버튼 클릭시 학생명, 과목명 자동조회
    @ResponseBody
    @RequestMapping ("/subSelectStu")
    public Map<String, Object> subSelectStu(@RequestParam(name ="testNum") int testNum,
                                            @RequestParam(name = "classNum")int classNum ){

                    // 과목조회
                    List<TestSubjectVO> subsList= testService.subSelect(testNum);
                    // 학생정보 조회
                    List<MemberVO> stuCnt = testService.stuCnt(classNum);

                    Map<String, Object> map = new HashMap<>();
                    map.put("subsList",subsList);
                    map.put("stuCnt", stuCnt);

        return map;

    }


    // 입력한 과목 점수 저장
    @ResponseBody
    @PostMapping("/insertSubNtest")
    public void insertSubNtest(@RequestBody TestScoreVO testScoreVO){

        testService.insertSubScore(testScoreVO);
    }

    // --------------과목별  입력된 성적 수정 버튼 클릭시 성적조회 하여 input에 뿌려주기------------
    @ResponseBody
    @PostMapping("/selectSubList")
    public Map<String, Object> selectSubList(TestScoreVO testScoreVO, Model model) {

        //시험명 클릭시 학생별 성적 조회
        List<TestScoreVO> scoreSelectList = testService.selectTestScore(testScoreVO.getTestNum());
        model.addAttribute("scoreSelectList", scoreSelectList);

        // 과목조회
        List<TestSubjectVO> subsList= testService.subSelect(testScoreVO.getTestNum());
        model.addAttribute("subsList", subsList);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("scoreSelectList",scoreSelectList);
        map1.put("subsList", subsList);

        return map1;
    }


    // 입력한 과목 점수 수정
    @PostMapping("/updateSubList")
    public String updateSubList(@RequestParam(name = "score") List<Integer> scoreList
            , @RequestParam(name = "scoreNum") List<Integer> scoreNumList ,TestScoreVO testScoreVO){
        System.out.println("33333333333333333333333"+testScoreVO);
        for(int i = 0 ; i < scoreNumList.size() ; i++){
            TestScoreVO vo = new TestScoreVO();
            vo.setScore(scoreList.get(i));
            vo.setScoreNum(scoreNumList.get(i));
            testService.updateScore(vo);
        }
        return "redirect:/test/goTestN?testNum="+ testScoreVO.getTestNum()+"&classNum="+testScoreVO.getClassNum();
    }

// ####################################  선생님 이의신청 페이지 기능 ~~~~##############################


        // 선생님 이의신청 글 목록
        @GetMapping("/teacherAskFirst")
        public String teacherAskFirst(Model model, Authentication authentication){
            User user=(User) authentication.getPrincipal();
            List<TestAskVO> thTestAskList = testService.selTeacherAsk(user.getUsername());
            model.addAttribute("thTestAskList", thTestAskList);

            return "/content/test/teacher_test_ask";
        }

        // 선생님 이의신청 답글페이지 이동
        @GetMapping("/teacherComment")
        public String teacherComment(TestAskVO testAskVO, Model model, Authentication authentication){
            User user=(User) authentication.getPrincipal();

            MemberVO stuInfoService = stuTestService.selectStuTest(user.getUsername());
            model.addAttribute("stuInfoService",stuInfoService);


            List<TestAskVO> thTestAskList = testService.selTeacherAsk(user.getUsername());
            model.addAttribute("thTestAskList", thTestAskList);


            TestAskVO testAskOne = stuTestService.stuAskDetail(testAskVO);
            model.addAttribute("testAskOne",testAskOne);


            return "/content/test/teacher_ask_comment";
        }

        // 선생님 이의신청 답글 저장
        @PostMapping("/insertThComment")
        public String insertThComment(TestAskVO testAskVO, @RequestParam(name = "protestN")int protestN){
            testService.updateComm(testAskVO.getProtestOrigino());
            testService.insertCom(testAskVO);
            stuTestService.updateOrigin(protestN);

            return "redirect:/test/teacherAskFirst?memberId="+testAskVO.getMemberId();
        }

        // 선생님이 학생글 1개 삭제(1)
        @GetMapping("/goThDelete1")
        public String goThDelete1(TestAskVO testAskVO){

            stuTestService.deleteMyAsk(testAskVO.getProtestNum());
            return "redirect:/test/teacherAskFirst";
        }

        // 선생님이 학생글과 답글 함께 삭제(2)
        @GetMapping("/goThDelete2")
        public String goThDelete2(TestAskVO testAskVO){
            testService.deleteThAsk(testAskVO.getProtestOrigino());
            return "redirect:/test/teacherAskFirst";
        }



    // ####################################  운영자 학생 성적 조회 기능 ~~~~##############################

            // 운영자 성적 서치서비스 첫페이지 이동
            @GetMapping("/stuTestAdmin")
            public String stuTestAdmin(Model model, @RequestParam(name = "memberId") String memberId){

                System.out.println("$$$$$$$$$$$$$$$$$$$"+ memberId);
                MemberVO stuInfoService = stuTestService.selectStuTest(memberId);
                model.addAttribute("stuInfoService",stuInfoService);

                List<OperatorVO> clCnt = stuTestService.stuClCnt(memberId);
                model.addAttribute("clCnt",clCnt);


                return "content/student/student_test_search";
            }







}
