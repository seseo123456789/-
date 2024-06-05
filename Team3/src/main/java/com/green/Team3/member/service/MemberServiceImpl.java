package com.green.Team3.member.service;

import com.green.Team3.board.vo.SearchVO;
import com.green.Team3.learn.vo.ConsultVO;
import com.green.Team3.learn.vo.HomeworkVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.test.vo.TestVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //회원 가입
    @Override
    public void join(MemberVO memberVO) {
        sqlSession.insert("member.join", memberVO);
    }

    //아이디 중복 확인
    @Override
    public int idCheck(String memberId) {
        return sqlSession.selectOne("member.idCheck", memberId);
    }

    //로그인
    @Override
    public MemberVO login(String memberId) {
        return sqlSession.selectOne("member.login", memberId);
    }

    // 전체 회원 목록 조회
    public List<MemberVO> selectMembers(SearchVO searchVO){
        return sqlSession.selectList("member.selectMembers", searchVO);
    }

    // 회원 상세 정보 목록 조회
    @Override
    public MemberVO memberDetail(MemberVO memberVO) {
        return sqlSession.selectOne("member.memberDetail", memberVO);
    }

    @Override
    public List<MemberVO> selectStudents() {
        return sqlSession.selectList("member.selectStudents");
    }

    @Override
    public String getMemberEmail(MemberVO memberVO) {
        return sqlSession.selectOne("member.getMemberEmail",memberVO);
    }

    @Override
    public void updateMemberPw(MemberVO memberVO) {
        sqlSession.update("member.updateMemberPw",memberVO);
    }

    @Override
    public MemberVO selectMyInformation(String memberId) {
        return sqlSession.selectOne("member.selectMyInformation",memberId);
    }

    @Override
    public String matchPassWord(String memberId) {
        return sqlSession.selectOne("member.matchPassWord",memberId);
    }

    @Override
    public String findMemberId(MemberVO memberVO) {
        return sqlSession.selectOne("member.findMemberId",memberVO);
    }

    @Override
    public List<ConsultVO> selectMyConsult(String memberId) {
        return sqlSession.selectList("member.selectMyConsult",memberId);
    }

    @Override
    public List<HomeworkVO> selectMyHomework(String memberId) {
        return sqlSession.selectList("member.selectMyHomework",memberId);
    }

    @Override
    public List<TestVO> selectMyTest(String memberId) {
        return sqlSession.selectList("member.selectMyTest",memberId);
    }

    @Override
    public String selectMemberName(String memberId) {
        return sqlSession.selectOne("member.selectMemberName",memberId);
    }

    @Override
    public List<MemberVO> selectMyAttendance(String memberId) {
        return sqlSession.selectList("member.selectMyAttendance",memberId);
    }


}
