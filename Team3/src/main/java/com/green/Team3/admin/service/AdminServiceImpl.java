package com.green.Team3.admin.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.board.vo.BoardTypeVO;
import com.green.Team3.board.vo.SearchVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 강사 전체 목록 조회 (완료)
    @Override
    public List<TeacherVO> selectTeachers(SearchVO searchVO) {
        return sqlSession.selectList("teacher.selectTeachers", searchVO);
    }

    // 강사 상세 정보 조회 (완료)
    @Override
    public List<ClsVO> detailTeacher(int teacherNum) {
        return sqlSession.selectList("teacher.detailTeacher", teacherNum);
    }

    // 회원 권한 수정 (완료)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoll(MemberVO memberVO) {
        sqlSession.update("admin.updateRoll", memberVO);
        // memberRoll : 2 (강사)일 경우, 강사 테이블 생성
        if(memberVO.getMemberRoll() == 2) {
            sqlSession.insert("admin.insertTeacher", memberVO);
        }
    }

    // 강사 재직 상태 수정 (완료)
    @Override
    public void changeAttendance(TeacherVO teacherVO) {
        sqlSession.update("admin.changeAttendance", teacherVO);
    }

    // memberRoll 전체 목록 조회 (완료)
    @Override
    public List<MemberVO> rollList() {
        return sqlSession.selectList("member.rollList");
    }

    // 인적 사항 정보 변경 (완료)
    @Override
    public void changePersonalInfo(MemberVO memberVO) {
        sqlSession.update("admin.changeMemberData", memberVO);
    }

    // 반 생성
    @Override
    public void makeCls(ClsVO clsVO) {
        sqlSession.insert("admin.makeCls", clsVO);
    }

    // 반정보 수정
    @Override
    public void updateClassInfo(ClsVO clsVO) {
        sqlSession.update("clsMapper.updateClass", clsVO);
    }

    // 강사 목록 조회
    @Override
    public List<TeacherVO> selectTeacherName() {
        return sqlSession.selectList("admin.selectTeacherName");
    }

    @Override
    public int updateClass(ClsVO clsVO) {
        return sqlSession.update("admin.updateClass", clsVO);
    }

    // 결제 실패 시
    @Override
    public void deleteOperator(OperatorVO operatorVO) {
        sqlSession.delete("admin.deleteOperator", operatorVO);
    }

    // 중복 수강신청 여부 체크
    @Override
    public int chkDuple(OperatorVO operatorVO) {
        return sqlSession.selectOne("admin.chkDuple", operatorVO);
    }

    // 결제 요청 시
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClsVO requestPayInfo(OperatorVO operatorVO) {
        sqlSession.insert("admin.insertOperator", operatorVO);
        return sqlSession.selectOne("member.requestPayInfo", operatorVO);
    }


    // 수강 신청 페이지 이동
    @Override
    public List<ClsVO> regClasses(OperatorVO operatorVO) {
        List<ClsVO> list = sqlSession.selectList("admin.regClasses");
        if(list != null){
            return list;
        } else {
            return null;
        }
    }

    // 결제 승인 시
    @Override
    public void successPayment(OperatorVO operatorVO) {
        sqlSession.update("admin.successPayment", operatorVO.getOperNum());
    }

    // 결제 정보 조회
    @Override
    public ClsVO findNames(OperatorVO operatorVO) {
        return sqlSession.selectOne("admin.findNames", operatorVO);
    }


    // OPER_NUM 조회
    @Override
    public int selectOperNum() {
        return  sqlSession.selectOne("admin.selectOperNum");
    }


    @Override
    public void updateClassEnter() {
        sqlSession.update("clsMapper.updateClassEnter");
    }

    @Override
    public List<OperatorVO> findPayYear() {
        return sqlSession.selectList("admin.findPayYear");
    }

    @Override
    public List<OperatorVO> monthlySales(OperatorVO operatorVO) {
        return sqlSession.selectList("admin.monthlySales", operatorVO);
    }

    @Override
    public List<OperatorVO> searchSales(SearchVO searchVO) {
        return sqlSession.selectOne("admin.searchSales", searchVO);
    }

    @Override
    public int classInfoCnt() {
        return sqlSession.selectOne("admin.classInfoCnt");
    }

    @Override
    public int memberCnt(SearchVO searchVO) {
        return sqlSession.selectOne("admin.memberCnt", searchVO);
    }

    // 강사 수 count
    @Override
    public int teacherCnt() {
        return sqlSession.selectOne("admin.teacherCnt");
    }

    @Override
    public List<ClsVO> findTeacher(MemberVO memberVO) {
        return sqlSession.selectList("teacher.findTeacher", memberVO);
    }

    @Override
    public MemberVO selectMemberInfo(MemberVO memberVO) {
        return sqlSession.selectOne("admin.selectMemberInfo", memberVO);
    }

    @Override
    public int findMaxTypeNum() {
        return sqlSession.selectOne("admin.findMaxTypeNum");
    }

    @Override
    public List<BoardTypeVO> findBoardTypes() {
        return sqlSession.selectList("admin.findBoardTypes");
    }

    @Override
    public void setBoardType(BoardTypeVO boardTypeVO) {
        sqlSession.insert("admin.setBoardType", boardTypeVO);
    }

    @Override
    public void regBoardType(BoardTypeVO boardTypeVO) {
        sqlSession.update("admin.regBoardType", boardTypeVO);
    }

    @Override
    public void delBoardType(BoardTypeVO boardTypeVO) {
        sqlSession.delete("admin.delBoardType", boardTypeVO);
    }

}
