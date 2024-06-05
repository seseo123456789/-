package com.green.Team3.learn.controller;

import com.green.Team3.learn.service.HomeworkService;
import com.green.Team3.learn.service.HomeworkServiceImpl;
import com.green.Team3.learn.vo.HomeworkVO;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
    @Resource(name = "homeworkService")
    private HomeworkServiceImpl homeworkService;

    //과제 추가
    @GetMapping("/addHomework")
    private String addHomework(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("classList",homeworkService.selectClassByThisTeacher(user.getUsername()));
        return "/content/teacher/add_homework";
    }
    //과제 목록 클릭시
    @GetMapping("/homeworkList")
    private String homeworkList(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("classList",homeworkService.selectClassByThisTeacher(user.getUsername()));
        model.addAttribute("IngHomeworkList",homeworkService.selectIngHomework(user.getUsername()));
        model.addAttribute("EndHomeworkList",homeworkService.selectEndHomework(user.getUsername()));
        model.addAttribute("WillHomeworkList",homeworkService.selectWillHomework(user.getUsername()));
        return "/content/teacher/homework_list";
    }
    //과제 추가시 과제 목록으로
    @PostMapping("/addHomeworkResult")
    private String addHomeworkResult(HomeworkVO homeworkVO,Model model,Authentication authentication){
        User user = (User) authentication.getPrincipal();
        homeworkService.homeworkAdd(homeworkVO);
        model.addAttribute("classList",homeworkService.selectClassByThisTeacher(user.getUsername()));
        model.addAttribute("IngHomeworkList",homeworkService.selectIngHomework(user.getUsername()));
        model.addAttribute("EndHomeworkList",homeworkService.selectEndHomework(user.getUsername()));
        model.addAttribute("WillHomeworkList",homeworkService.selectWillHomework(user.getUsername()));
        return "/content/teacher/homework_list";
    }
    //과제 수정
    @PostMapping("/updateHomework")
    private String updateHomework(HomeworkVO vo){
        homeworkService.updateHomework(vo);
        return "redirect:/homework/homeworkList";
    }
    //과제 삭제
    @PostMapping("/deleteHomework")
    private String deleteHomework(HomeworkVO vo){
        homeworkService.deleteHomework(vo);
        return "redirect:/homework/homeworkList";
    }
    //과제를 수정할 때 모달 안의 반 선택 체인지
    @ResponseBody
    @PostMapping("/modalChange")
    private Map<String,Object> modalChange(@RequestParam(name = "hwNum")int hwNum){
        HomeworkVO homeworkVO = new HomeworkVO();
        homeworkVO = homeworkService.selectOneHomework(hwNum);
        int classNum = homeworkVO.getClassNum();
        int teacherNum = homeworkService.selectTeacherNumByClassNum(classNum);
        List<HomeworkVO> classList = homeworkService.selectClassNumByTeacherNum(teacherNum);
        Map<String, Object> map = new HashMap<>();
        map.put("homeworkVO",homeworkVO);
        map.put("classList",classList);
        return map;
    }



}
