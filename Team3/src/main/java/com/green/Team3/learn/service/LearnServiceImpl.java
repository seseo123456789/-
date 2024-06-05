package com.green.Team3.learn.service;

import com.green.Team3.learn.vo.AttendanceTypeVO;
import com.green.Team3.learn.vo.AttendanceVO;
import com.green.Team3.learn.vo.InsertAtdListVO;
import com.green.Team3.member.vo.MemberVO;
import org.apache.ibatis.jdbc.SQL;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("learnService")
public class LearnServiceImpl implements LearnService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<AttendanceTypeVO> selectAtd() {
        return sqlSession.selectList("learnMapper.selectAtd");
    }

    @Override
    public void insertAttendance(InsertAtdListVO vo) {
        sqlSession.insert("learnMapper.insertAttendance",vo);
    }
    @Override
    public List<MemberVO> fullAttendance(int classNum){
        return sqlSession.selectList("learnMapper.fullAttendance",classNum);
    }

    @Override
    public boolean nowCheckAttendance(int classNum) {
        return sqlSession.selectOne("learnMapper.nowCheckAttendance",classNum).equals("true") ? true : false;
    }

    @Override
    public int selectLoopCnt(int teacherNum) {
        return sqlSession.selectOne("learnMapper.selectLoopCnt",teacherNum);
    }

    @Override
    public List<Integer> selectTotalDayForClass(int teacherNum) {
        return sqlSession.selectList("learnMapper.selectTotalDayForClass",teacherNum);
    }

    @Override
    public List<Integer> selectIngDayForClass(int teacherNum) {
        return sqlSession.selectList("learnMapper.selectIngDayForClass",teacherNum);
    }
}
