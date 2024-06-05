package com.green.Team3.learn.service;

import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.learn.vo.HomeworkVO;

import java.util.List;

public interface HomeworkService {
    List<ClsVO> selectClassByThisTeacher(String memberId);
    void homeworkAdd(HomeworkVO homeworkVO);
    List<HomeworkVO> selectIngHomework(String memberId);
    List<HomeworkVO> selectEndHomework(String memberId);
    List<HomeworkVO> selectWillHomework(String memberId);
    void deleteHomework(HomeworkVO vo);
    void updateHomework(HomeworkVO vo);
    HomeworkVO selectOneHomework(int hwNum);
    int selectTeacherNumByClassNum(int classNum);
    List<HomeworkVO> selectClassNumByTeacherNum(int teacherNum);
}
