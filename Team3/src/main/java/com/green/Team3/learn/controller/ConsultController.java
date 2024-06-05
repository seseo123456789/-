package com.green.Team3.learn.controller;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.learn.service.CalenderServiceImpl;
import com.green.Team3.learn.service.ConsultServiceImpl;
import com.green.Team3.learn.service.HomeworkServiceImpl;
import com.green.Team3.learn.vo.ConsultVO;
import com.green.Team3.learn.vo.EventCalenderVO;
import com.green.Team3.learn.vo.EventTypeVO;
import com.green.Team3.learn.vo.HomeworkVO;
import com.green.Team3.util.MailService;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/consult")
public class ConsultController {
    @Resource(name = "consultService")
    private ConsultServiceImpl consultService;
    @Resource(name = "homeworkService")
    private HomeworkServiceImpl homeworkService;
    @Resource(name = "calenderService")
    private CalenderServiceImpl calenderService;

    // 선생 아이디로 반 조회 후 상담추가 페이지로
    @GetMapping("/addConsultForm")
    public String addConsultForm(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("classInfo",homeworkService.selectClassByThisTeacher(user.getUsername()));
        return "/content/teacher/add_consult";
    }

    //셀렉트에 반을 바꾸면 학생명단이 바뀜
    @ResponseBody
    @PostMapping("/changeStuOption")
    public List<OperatorVO> changeStuOption(@RequestParam(name = "classNum")int classNum){
        List<OperatorVO> list = consultService.selectClassNumAndStuNum(classNum);
        return list;
    }

    //상담내용 추가하는 곳으로 이동
    @GetMapping("/addConsultContentForm")
    public String addConsult(@RequestParam(name = "consultNum")int consultNum,Model model){
        model.addAttribute("consultVO",consultService.selectOneConsult(consultNum));
        return "/content/teacher/add_consult_content";
    }

    //추가가 완료된 곳으로 이동할 때 수정이 완료된후 이동
    @PostMapping("/addConsultContent")
    public String addConsultContent(ConsultVO consultVO,Model model,Authentication authentication){
        User user = (User) authentication.getPrincipal();
        consultService.addConsultContent(consultVO);
        model.addAttribute("consultList",consultService.contentComplete(consultService.selectTeacherNumOfMemberId(user.getUsername())));
        return "/content/teacher/content_complete";
    }

    //추가가 완료된 곳으로 이동할 때 그냥 이동
    @GetMapping("/contentComplete")
    public String contentComplete(Model model,Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("consultList",consultService.contentComplete(consultService.selectTeacherNumOfMemberId(user.getUsername())));
        return "/content/teacher/content_complete";
    }

    //캘린더 추가화면으로 이동
    @GetMapping("/consultAddCalender")
    public String consultAddCalender(Authentication authentication,Model model){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("classInfo",homeworkService.selectClassByThisTeacher(user.getUsername()));
        return "/content/teacher/consult_add_calender";
    }

    //내용이 작성안된 상담 목록
    @GetMapping("/consultList")
    public String consultList(Authentication authentication, Model model){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("endConsultList",consultService.selectEndConsultList(consultService.selectTeacherNumOfMemberId(user.getUsername())));
        model.addAttribute("willConsultList",consultService.selectWillConsultList(consultService.selectTeacherNumOfMemberId(user.getUsername())));
        model.addAttribute("todayConsultList",consultService.selectTodayConsultList(consultService.selectTeacherNumOfMemberId(user.getUsername())));
        return "/content/teacher/consult_list";
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //모달안의 학생을 바꿈.
    @ResponseBody
    @PostMapping("/modalChange")
    private Map<String,Object> modalChange(@RequestParam(name = "consultNum")int consultNum){
        ConsultVO consultVO = new ConsultVO();
        consultVO = consultService.selectOneConsult(consultNum);
        int classNum = consultVO.getClassNum();
        int teacherNum = homeworkService.selectTeacherNumByClassNum(classNum);
        List<ConsultVO> classList = consultService.selectClassNumByTeacherNumConsult(teacherNum);
        Map<String, Object> map = new HashMap<>();
        map.put("consultVO",consultVO);
        map.put("classList",classList);
        return map;
    }

    //상담 수정
    @PostMapping("/updateConsult")
    private String updateConsult(@RequestParam(name = "beforeTitle")String beforeTitle,ConsultVO consultVO){
        consultService.updateConsult(consultVO);//상담 쿼리 업데이트
        calenderService.deleteCalender(beforeTitle);//달력 테이블에서 삭제
        EventCalenderVO eVO = new EventCalenderVO();
        EventTypeVO eventTypeVO = calenderService.selectEventTypeForTeacherByConsult();
        eVO.setStart(consultVO.getConsultDate());
        eVO.setMemberId(consultVO.getMemberId());
        String tt = eventTypeVO.getEventTypeName() + "-" + calenderService.selectClassNameByClassNum(consultVO.getClassNum()) + "-" + consultVO.getMemberId() + "-" + consultVO.getConsultDate();
        eVO.setTitle(tt);
        eVO.setEventTypeNum(eventTypeVO.getEventTypeNum());
        calenderService.insertEventCalender(eVO);//달력 추가
        return "redirect:/consult/consultList";
    }

    //상담 삭제
    @PostMapping("/deleteConsult")
    private String deleteConsult(@RequestParam(name = "consultNum")int consultNum,
                                 @RequestParam(name = "title")String title){
        consultService.deleteConsult(consultNum);
        calenderService.deleteCalender(title);
        return "redirect:/consult/consultList";
    }



}
