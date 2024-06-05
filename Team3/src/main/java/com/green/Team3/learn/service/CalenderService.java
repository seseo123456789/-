package com.green.Team3.learn.service;

import com.green.Team3.learn.vo.EventCalenderVO;
import com.green.Team3.learn.vo.EventTypeVO;

import java.util.List;

public interface CalenderService {
    EventTypeVO selectEventTypeForTeacherByConsult();
    void insertEventCalender(EventCalenderVO calenderVO);
    String selectClassNameByClassNum(int classNum);
    List<EventCalenderVO> addEventBar();
    void deleteCalender(String title);
    void autoDeleteCalender();
}
