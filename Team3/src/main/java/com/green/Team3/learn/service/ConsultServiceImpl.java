package com.green.Team3.learn.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.learn.vo.ConsultVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("consultService")
public class ConsultServiceImpl implements ConsultService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<OperatorVO> selectClassNumAndStuNum(int classNum) {
        return sqlSession.selectList("learnMapper.selectClassNumAndStuNum",classNum);
    }

    @Override
    public void insertConsult(ConsultVO vo) {
        sqlSession.insert("learnMapper.insertConsult",vo);
    }

    @Override
    public List<ConsultVO> selectEndConsultList(int teacherNum) {
        return sqlSession.selectList("learnMapper.selectEndConsultList",teacherNum);
    }

    @Override
    public List<ConsultVO> selectWillConsultList(int teacherNum) {
        return sqlSession.selectList("learnMapper.selectWillConsultList",teacherNum);
    }

    @Override
    public List<ConsultVO> selectTodayConsultList(int teacherNum) {
        return sqlSession.selectList("learnMapper.selectTodayConsultList",teacherNum);
    }

    @Override
    public int selectTeacherNumOfMemberId(String memberId) {
        return sqlSession.selectOne("learnMapper.selectTeacherNumOfMemberId",memberId);
    }

    @Override
    public ConsultVO selectOneConsult(int consultNum) {
        return sqlSession.selectOne("learnMapper.selectOneConsult",consultNum);
    }

    @Override
    public List<ConsultVO> selectClassNumByTeacherNumConsult(int teacherNum) {
        return sqlSession.selectList("learnMapper.selectClassNumByTeacherNumConsult",teacherNum);
    }

    @Override
    public void updateConsult(ConsultVO consultVO) {
        sqlSession.update("learnMapper.updateConsult",consultVO);
    }

    @Override
    public void deleteConsult(int consultNum) {
        sqlSession.delete("learnMapper.deleteConsult",consultNum);
    }

    @Override
    public void addConsultContent(ConsultVO consultVO) {
        sqlSession.update("learnMapper.addConsultContent",consultVO);
    }

    @Override
    public List<ConsultVO> contentComplete(int teacherNum) {
        return sqlSession.selectList("learnMapper.contentComplete",teacherNum);
    }

    @Override
    public void autoDeleteConsult() {
        sqlSession.delete("learnMapper.autoDeleteConsult");
    }


}
