package com.green.Team3.learn.service;

import com.green.Team3.learn.vo.AttendanceTypeVO;
import com.green.Team3.learn.vo.AttendanceVO;
import com.green.Team3.learn.vo.InsertAtdListVO;
import com.green.Team3.member.vo.MemberVO;

import java.util.ArrayList;
import java.util.List;

public interface LearnService {
    List<AttendanceTypeVO> selectAtd();
    void insertAttendance(InsertAtdListVO vo);
    List<MemberVO> fullAttendance(int classNum);
    boolean nowCheckAttendance(int classNum);
    int selectLoopCnt(int teacherNum);
    List<Integer> selectTotalDayForClass(int teacherNum);
    List<Integer> selectIngDayForClass(int teacherNum);
}
