package com.green.Team3.test.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;
import com.green.Team3.test.vo.TestAskVO;
import com.green.Team3.test.vo.TestScoreVO;
import com.green.Team3.test.vo.TestSubjectVO;
import com.green.Team3.test.vo.TestVO;

import java.util.List;

public interface StuTestService {

    // ////////////////////// 진행중 /////////////////////////


 // ########################  학생이 로그인했을때 성적 확인  ########################

    // ####### 성적 조회 서비스 기능들 #######

    // 학생정보조회
    MemberVO selectStuTest(String memberId);
    // 학생 강좌수 조회
    List<OperatorVO> stuClCnt(String memberId);
    // 학생 수강별 시험목록조회
    List<TestVO> selectStuCLTest(String memberId);

    // 학생 기간별 목록조회
    List<TestVO> selectStuTestDetail(String memberId);

    // 학생 과목별 목록조회
    List<TestSubjectVO> selectStuSub(String memberId);

    //학생 전체 성적이수표 조회
    List<ClsVO> totalSelectTest(String memberId);


    //############ 조회버튼 클릭시 성적 상세페이지 이동하여 조회 ########
    // 학생 과목 없을시   my 성적페이지 이동 & 조회
    TestScoreVO mainTestMyScore(TestScoreVO testScoreVO);
    // 학생 과목 있을시   my 성적페이지 이동 & 조회
    List<TestScoreVO> subTestMyScore(TestScoreVO testScoreVO);
    // 학생 성적증명서 총성적조회
    List<TestScoreVO> printMyGrade(TestScoreVO testScoreVO);


//############ 학생 성적 이의신청 페이지 #######################

    // [학생] 이의신청 글 적기
    void insertStuAsk(TestAskVO testAskVO);
    // [학생] 이의신청 글 목록
    List<TestAskVO>selectStuAsk(String memberId);
    // [학생] 이의신청 글 상세보기
    TestAskVO stuAskDetail(TestAskVO testAskVO);

    //  답글 표시 구분하려고
    TeacherVO askMemberId(String memberId);

    // 학생 원글 저장
    void updateOrigin(int protestNum);
    // 학생 원글 수정
    void updateMyAsk(TestAskVO testAskVO);
    // 학생 원글 삭제
    void deleteMyAsk(int protestNum);

}
