package com.green.Team3.admin.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.board.vo.BoardTypeVO;
import com.green.Team3.board.vo.SearchVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;

import java.util.List;

public interface AdminService {

    // 선생님 전체 목록 조회
    List<TeacherVO> selectTeachers(SearchVO searchVO);

    // 선생님 상세 목록 조회
    List<ClsVO> detailTeacher(int teacherNum);

    // 회원 권한 수정
    void updateRoll(MemberVO memberVO);

    // 강사 재직 상태 수정
    void changeAttendance(TeacherVO teacherVO);

    // MEMBER_ROLL 리스트 조회
    List<MemberVO> rollList();

    // 인적 사항 정보 변경
    void changePersonalInfo(MemberVO memberVO);

    // 반 생성
    void makeCls(ClsVO clsVO);

    // 반정보 수정
    void updateClassInfo(ClsVO clsVO);

    // 선생님 이름 조회
    List<TeacherVO> selectTeacherName();

    // 반에서 정보 수정
    int updateClass(ClsVO clsVO);

    // 결제 실패 시
    void deleteOperator(OperatorVO operatorVO);

    // 수강 중복 확인
    int chkDuple(OperatorVO operatorVO);

    // 결제 요청 시
    ClsVO requestPayInfo(OperatorVO operatorVO);

    // 수강 신청 모달 열 때
    List<ClsVO> regClasses(OperatorVO operatorVO);

    // 결제 신청 시
//    void insertOperator(OperatorVO operatorVO);

    // 결제 성공 시 update
    void successPayment(OperatorVO operatorVO);

    // 결제 성공한 결제자 정보 조회
    ClsVO findNames(OperatorVO operatorVO);

    // OPER_NUM 조회
    int selectOperNum();

    // updateClassEnter
    void updateClassEnter();

    // 매출 발생 연도 검색 쿼리
    List<OperatorVO> findPayYear();

    // 월별 매출
    List<OperatorVO> monthlySales(OperatorVO operatorVO);

    // 매출 검색
    List<OperatorVO> searchSales(SearchVO searchVO);

    // class_Num 데이터 수
    int classInfoCnt();

    // 멤버 데이터 수
    int memberCnt(SearchVO searchVO);

    int teacherCnt();
    List<ClsVO> findTeacher(MemberVO memberVO);
    MemberVO selectMemberInfo(MemberVO memberVO);
    int findMaxTypeNum();
    List<BoardTypeVO> findBoardTypes();
    void setBoardType(BoardTypeVO boardTypeVO);
    void regBoardType(BoardTypeVO boardTypeVO);
    void delBoardType(BoardTypeVO boardTypeVO);
}
