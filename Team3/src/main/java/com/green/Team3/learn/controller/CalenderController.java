package com.green.Team3.learn.controller;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.learn.service.CalenderServiceImpl;
import com.green.Team3.learn.service.ConsultServiceImpl;
import com.green.Team3.learn.service.HomeworkServiceImpl;
import com.green.Team3.learn.vo.ConsultVO;
import com.green.Team3.learn.vo.EventCalenderVO;
import com.green.Team3.learn.vo.EventTypeVO;
import com.green.Team3.learn.vo.HomeworkVO;
import jakarta.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/calender")
public class CalenderController {
    @Resource(name = "calenderService")
    private CalenderServiceImpl calenderService;
    @Resource(name = "consultService")
    private ConsultServiceImpl consultService;

    //반 선택시 해당 반 학생 조회
    @ResponseBody
    @PostMapping("/changeStuOption")
    public List<OperatorVO> changeStuOption(@RequestParam(name = "classNum")int classNum){
        List<OperatorVO> list = consultService.selectClassNumAndStuNum(classNum);
        return list;
    }

    //달력 추가
    @PostMapping("/addCalender")
    public String addCalender(@RequestParam(name = "memberId")String memberId,
                              @RequestParam(name = "classNum")int classNum,
                              @RequestParam(name = "start")String start){
        EventTypeVO eventTypeVO = calenderService.selectEventTypeForTeacherByConsult();
        EventCalenderVO calenderVO = new EventCalenderVO();
        String title = memberId;
        title = eventTypeVO.getEventTypeName() + "-" + calenderService.selectClassNameByClassNum(classNum) + "-" + title + "-" + start;
        calenderVO.setMemberId(memberId);
        calenderVO.setEventTypeNum(eventTypeVO.getEventTypeNum());
        calenderVO.setStart(start);
        calenderVO.setTitle(title);
        System.out.println(calenderVO);
        calenderService.insertEventCalender(calenderVO);
        ConsultVO vo = new ConsultVO();
        vo.setClassNum(classNum);
        vo.setMemberId(memberId);
        vo.setConsultDate(start);
        consultService.insertConsult(vo);
        return "redirect:/consult/consultAddCalender";
    }

    //달력에 스케줄 표시
    @ResponseBody
    @PostMapping("/addEventBar")
    public List<EventCalenderVO> addEventBar(){
        return calenderService.addEventBar();
    }
}
