package com.green.Team3.admin.controller;

import com.green.Team3.admin.service.AdminService;
import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.admin.vo.SalesVO;
import com.green.Team3.board.vo.BoardTypeVO;
import com.green.Team3.board.vo.SearchVO;
import com.green.Team3.cls.service.ClsService;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.learn.service.ConsultService;
import com.green.Team3.member.service.MemberService;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminService")
    private AdminService adminService;

    @Resource(name = "memberService")
    private MemberService memberService;

    @Resource(name = "clsService")
    private ClsService clsService;

    @Resource(name = "consultService")
    private ConsultService consultService;

    // 관리자 클릭 시 페이지 이동
    @GetMapping("/notice")
    public String notice(){
        return "content/admin/admin_list";
    }

    // 강사 관리 페이지 이동
    @GetMapping("/goAdminTeacher")
    public String goAdminTeacher(Model model, @RequestParam(name = "teacherNum", required = false, defaultValue = "0")int teacherNum, SearchVO searchVO
                                , @RequestParam(name = "accorNum", required = false, defaultValue = "5") int accorNum){
        int totalTeachers = adminService.teacherCnt();
        searchVO.setTotalDataCnt(totalTeachers);
//        searchVO.setDisplayDataCnt(10);
        searchVO.setPageInfo();
        List<TeacherVO> list = adminService.selectTeachers(searchVO);
        model.addAttribute("teacherList", list); // 강사 목록 조회
        model.addAttribute("updateTeacherNum", teacherNum);
        model.addAttribute("teacherCnt", totalTeachers);
        model.addAttribute("accorNum", accorNum);
        return "content/admin/admin_teacher";
    }

    // 관리 페이지에서 강사 검색
    @ResponseBody
    @PostMapping("/findTeacher")
    public List<ClsVO> findTeacher(@RequestBody MemberVO memberVO){
        List<ClsVO> list = adminService.findTeacher(memberVO);
        return list;
    }
    // 강사 정보 상세 조회
    @ResponseBody
    @PostMapping("/selectTeacher")
    public List<ClsVO> detailTeacher(@RequestBody ClsVO clsVO){
        List<ClsVO> list = adminService.detailTeacher(clsVO.getTeacherNum());
        return list;
    }

    // 회원 관리 페이지 이동
    @RequestMapping("/goMemberList")
    public String memberList(Model model, SearchVO searchVO,
                             @RequestParam(name = "memberId", required = false, defaultValue = "")String updateMemberId){
        System.out.println(searchVO.getSearchType());
        int totalDataCnt = adminService.memberCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);
        System.out.println("@@@@@@@@@@@@@@@@@@@");
        System.out.println(totalDataCnt);
        searchVO.setDisplayDataCnt(10);
        searchVO.setPageInfo();
        // 전체 회원 목록 조회
        List<MemberVO> list = memberService.selectMembers(searchVO);
        model.addAttribute("memberList", list);
        // MEMBER_ROLL 목록 조회 (관리, 강사, 회원)
        model.addAttribute("rollList", adminService.rollList());
        model.addAttribute("updateMemberId", updateMemberId);
        return "content/admin/member_list";
    }

    // 인적 사항 보기 (모달)
    @ResponseBody
    @RequestMapping("/memberDetail")
    public MemberVO memberDetail(@RequestBody MemberVO memberVO){
        MemberVO vo = memberService.memberDetail(memberVO);
        return vo;
    }

    // 인적 사항 변경 쿼리 실행(변경 버튼 클릭 시) -> 회원 관리에서
    @PostMapping("/changePersonalInfo")
    public String changePersonalInfo(MemberVO memberVO){
        adminService.changePersonalInfo(memberVO);
        return "redirect:/admin/goMemberList?memberId=" + memberVO.getMemberId();
    }

    // 회원 정보 수정
    @PostMapping("/editPersonInfo")
    public String editPersonInfo(MemberVO memberVO, Model model, @RequestParam(name = "classNum")int classNum){
        adminService.changePersonalInfo(memberVO);
        model.addAttribute("updateMemberId", memberVO.getMemberId());
        return "redirect:/admin/goClassInfo?classNum=" + classNum;
    }

    // 회원 권한 수정 (memberRoll)
    @ResponseBody
    @PostMapping("/updateRoll")
    public MemberVO updateRoll(@RequestBody MemberVO memberVO){
        adminService.updateRoll(memberVO);
        MemberVO vo = adminService.selectMemberInfo(memberVO);
        return vo;
    }
    // 해당 회원의 수강 목록 페이지 이동 (모달)
    @ResponseBody
    @PostMapping("/showClass")
    public List<ClsVO> showClass(@RequestBody MemberVO memberVO){
        List<ClsVO> voList = clsService.selectClass(memberVO);
        return voList;
    }

    // 학급 생성 페이지 이동
    @GetMapping("/makeClassForm")
    public String makeClassForm(Model model, SearchVO searchVO, @RequestParam(name = "accorNum") int accorNum){
        int totalDataCnt = adminService.classInfoCnt();
        searchVO.setTotalDataCnt(totalDataCnt);
        searchVO.setPageInfo();
        model.addAttribute("clsList", clsService.selectAllClass(searchVO));
        model.addAttribute("teachers", adminService.selectTeacherName());
        model.addAttribute("accorNum", accorNum);
        return "content/admin/make_class_form";
    }

    // 학급 생성 버튼 클릭 시 실행 메소드
    @PostMapping("/makeClass")
    public String makeClass(ClsVO clsVO, @RequestParam(name = "accorNum",required = false,defaultValue = "3") int accorNum, Model model){
        adminService.makeCls(clsVO);
        return "redirect:/admin/makeClassForm?accorNum="+accorNum;
    }

    // 근무 상태 변경
    @PostMapping("/changeAttendance")
    public String changeAttendance(TeacherVO teacherVO){
        adminService.changeAttendance(teacherVO);
        return "redirect:/admin/goAdminTeacher";
    }

    // 선택한 반의 상세 정보 조회 페이지 이동
    @GetMapping("/goClassInfo")
    public String changeClass(@RequestParam(name = "classNum")int classNum, Model model){
        model.addAttribute("clsInfo", clsService.selectClassDetail(classNum));
        model.addAttribute("teachers", adminService.selectTeacherName());
        model.addAttribute("students", consultService.selectClassNumAndStuNum(classNum));
        return "content/admin/change_class";
    }

    // 반정보 수정 쿼리 실행
    @PostMapping("/updateClass")
    public String updateClass(ClsVO clsVO){
       adminService.updateClass(clsVO);
       return "redirect:/admin/goClassInfo?classNum=" + clsVO.getClassNum();
    }

    // 인적 사항 페이지 이동
    @ResponseBody
    @PostMapping("goStuInfo")
    public MemberVO goStuInfo(@RequestBody MemberVO memberVO){
        return memberService.memberDetail(memberVO);
    }


    // 반에 학생을 추가하기 위한 학생 목록 조회 페이지 이동
    @ResponseBody
    @PostMapping("/goInsertStu")
    public List<MemberVO> goInsertStu(){
        return memberService.selectStudents();
    }

    // 수강 신청 페이지 이동
    @ResponseBody
    @PostMapping("/goRegClass")
    public List<ClsVO> goRegClass(OperatorVO operatorVO){
        return adminService.regClasses(operatorVO);
    }

    // 결제 중복 체크 (단수)
    @ResponseBody
    @PostMapping("/chkDuple")
    public int chkDuple(@RequestBody OperatorVO operatorVO){
        return adminService.chkDuple(operatorVO);
    }

    // 결제 중복 체크 (복수)
    @ResponseBody
    @PostMapping("/chkDuples")
    public int chkDuples(@RequestBody List<OperatorVO> list){
        List<Integer> numList = new ArrayList<>();
        int sum = 0;
        for(OperatorVO e : list){
            int num = adminService.chkDuple(e);
            if(num > 0) {
                numList.add(num);
            }
        }
        for(int e : numList){
            sum += e;
        }
        return sum;
    }

    // 결제 실패 시 (단수)
    @ResponseBody
    @PostMapping("/deleteOperator")
    public void deleteOperator(@RequestBody OperatorVO operatorVO){
        adminService.deleteOperator(operatorVO);
    }

    // 결제 실패 시 (복수)
    @ResponseBody
    @PostMapping("/deleteOperators")
    public void deleteOperator(@RequestBody List<OperatorVO> list){
        for(OperatorVO e : list) {
            adminService.deleteOperator(e);
        }
    }

    // 결제 시스템 페이지 이동 (카카오페이 실행)
    @ResponseBody
    @RequestMapping("/goPayment")
    public ClsVO goPayment(@RequestBody OperatorVO operatorVO){
        operatorVO.setOperNum(adminService.selectOperNum());
        ClsVO list = adminService.requestPayInfo(operatorVO);
        if(list != null){
            return list;
        } else {
            System.out.println("정보가 없음");
            return null;
        }
    }
    // 여러개 결제
    @ResponseBody
    @RequestMapping("/goPayments")
    public List<ClsVO> goPayments(@RequestBody List<OperatorVO> operatorVOList){
        List<ClsVO> list = new ArrayList<>();
        for(int i = 0; i < operatorVOList.size(); i++){
            OperatorVO operatorVO = new OperatorVO();
            operatorVO.setOperNum(adminService.selectOperNum());
            operatorVO.setClassNum(operatorVOList.get(i).getClassNum());
            operatorVO.setMemberId(operatorVOList.get(i).getMemberId());
            list.add(adminService.requestPayInfo(operatorVO));
        }
        return list;
    }

    // 단일 결제 성공 시 이동 페이지
    @GetMapping("/successPayment")
    public String successPayment(OperatorVO operatorVO, Model model){
        adminService.successPayment(operatorVO);
        model.addAttribute("info", adminService.findNames(operatorVO));
        return "content/admin/payment_system";
    }

    // 복수 결제 성공 시 이동할 페이지(비동기)
    @ResponseBody
    @PostMapping("/checkReceipt")
    public void checkReceipt(@RequestBody List<OperatorVO> operatorVOList){
        List<ClsVO> list = new ArrayList<>();
        for(OperatorVO e : operatorVOList){
            adminService.successPayment(e);
        }
    }

    // 복수 결제 성공 시 이동하는 최종 페이지
    @GetMapping("/successPayments")
    public String successPayments(@RequestParam(name = "operNumList")List<Integer> operNumList, Model model){
        List<ClsVO> list = new ArrayList<>();
        for(Integer i : operNumList){
            OperatorVO operatorVO = new OperatorVO();
            operatorVO.setOperNum(i);
            if(operatorVO.getOperNum() != 0) {
                list.add(adminService.findNames(operatorVO));
            } else {
                System.out.println("operNum이 없음");
            }
        }
        model.addAttribute("infoList", list);
        return "content/admin/payment_systems";
    }

    // 매출 관리 페이지 이동을 위해 비동기 컨트롤러 이동용
    @GetMapping("/goSales")
    public String goSales(Model model){
        model.addAttribute("years", adminService.findPayYear());
        return "content/admin/sales_manage";
    }

    // 매출 관리 페이지 이동
    @ResponseBody
    @RequestMapping("/showGraphic")
    public List<SalesVO> showGraphic(@RequestBody OperatorVO operatorVO){
        List<OperatorVO> monthlySales = adminService.monthlySales(operatorVO);
        List<SalesVO> list = new ArrayList<>();
        double[] arr = new double[12]; // 월별 매출용 배열
        double[] arr2 = new double[12]; // 월별 결제건 배열
        for(int i = 0; i < arr.length; i++){
            boolean found = false;
            for (int j = 0; j < monthlySales.size(); j++) {
                if(monthlySales.get(j).getPayMonth() == i+1) {
                    arr[i] = monthlySales.get(j).getTotalSales();
                    arr2[i] = monthlySales.get(j).getOperNumCnt();
                    found = true;
                }
            }
            if(!found){
                arr[i] = 0;
                arr2[i] = 0;
            }
        }

        // arr의 값들을 만 단위로 절사
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] / 10000);
        }

        SalesVO vo = new SalesVO();
        vo.setData(arr);
        list.add(vo);
        SalesVO vo2 = new SalesVO();
        vo2.setData(arr2);
        list.add(vo2);
        return list;
    }

    // 게시판 관리 페이지 이동
    @GetMapping("/boardControll")
    public String boardControll(@RequestParam(name = "accorNum", defaultValue = "1", required = false) int accorNum, Model model){
        model.addAttribute("accorNum", accorNum);
        model.addAttribute("types", adminService.findBoardTypes());
        return "content/admin/board_controll";
    }

    // 보드 Type 데이터 입력
    @ResponseBody
    @PostMapping("/setBoardType")
    public int setBoardType(@RequestBody BoardTypeVO boardTypeVO){
        boardTypeVO.setTypeNum(adminService.findMaxTypeNum());
        System.out.println(boardTypeVO);
        adminService.setBoardType(boardTypeVO);
        return boardTypeVO.getTypeNum();
    }

    // 보드타입 데이터 수정
    @ResponseBody
    @PostMapping("/regBoardType")
    public BoardTypeVO regBoardType(@RequestBody BoardTypeVO boardTypeVO){
        adminService.regBoardType(boardTypeVO);
        return boardTypeVO;
    }

    // 보드타입 데이터 삭제
    @ResponseBody
    @PostMapping("/delBoardType")
    public void delBoardType(@RequestBody BoardTypeVO boardTypeVO){
        adminService.delBoardType(boardTypeVO);
    }

}
