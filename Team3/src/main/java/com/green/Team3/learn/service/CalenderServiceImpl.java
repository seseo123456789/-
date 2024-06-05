package com.green.Team3.learn.service;

import com.green.Team3.learn.vo.EventCalenderVO;
import com.green.Team3.learn.vo.EventTypeVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("calenderService")
public class CalenderServiceImpl implements CalenderService{
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Override
    public EventTypeVO selectEventTypeForTeacherByConsult() {
        return sqlSession.selectOne("calender.selectEventTypeForTeacherByConsult");
    }

    @Override
    public void insertEventCalender(EventCalenderVO calenderVO) {
        sqlSession.insert("calender.insertEventCalender",calenderVO);
    }

    @Override
    public String selectClassNameByClassNum(int classNum) {
        return sqlSession.selectOne("calender.selectClassNameByClassNum",classNum);
    }

    @Override
    public List<EventCalenderVO> addEventBar() {
        return sqlSession.selectList("calender.addEventBar");
    }

    @Override
    public void deleteCalender(String title) {
        sqlSession.delete("calender.deleteCalender",title);
    }

    @Override
    public void autoDeleteCalender() {
        sqlSession.delete("calender.autoDeleteCalender");
    }
}
