package com.green.Team3.test.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.test.vo.*;

import java.util.List;

public interface TestService {



   // ############################# 강사가 로그인해서 성적 처리 관리 #############################

    // 강사 강의 목록조회
    List<ClsVO> selectTeacherClassList(String memberId);


    //  #############################  선생님 반별 시험목록조회 페이지  #############################

    //(선생님 시험목록조회 페이지) 테스트 검색란 에서 조회///
    List<TestVO> searchTestList(SearchTestVO searchTestVO);

    // (선생님 시험목록조회 페이지)  클래스 번호 쓰기
    ClsVO onlyClassNum(int classNum);

    //(선생님 시험목록조회 페이지)  평가명 목록 조회
    List<TestVO> selectTest(int classNum);




    // ############################## 시험 설정 모달  ###########################

    // (선생님 모달) 평가명 추가
    void insertTest(TestVO testVO);

    // (선생님 모달) 과목있음 선택시 테스트명, 날짜만 저장
    void subMainTestInsert(TestVO testVO);

    // (선생님 모달)  모달 과목저장할때 테스트넘버 쓸려고..
    List<TestVO> testNumInfo2(int testNum);

    // (선생님 모달) 과목저장
    void insertSub(TestSubjectVO testSubjectVO);
    // (선생님 모달)  과목목록 조회  %%%%%%%%%%%%%%%%%확인중%%%%%%%%%%%%%%%%%%%%
    List<TestSubjectVO> selectSubList(int testNum);



    // ######################################### 시험 상세정보 조회시  ##################################


    // (시험 상세정보) 메인 테스트 상세정보 수정1   (메인테스트full)
    void updateTestDetail(TestVO testVO);

    //  (시험 상세정보) 메인 테스트 상세정보 수정2  (과목있을시: 메인테스트 만점제외)
    void  updateTestDeTwo(TestVO testVO);

    //  (시험 상세정보) 과목 1개 조회
    TestSubjectVO selectSubOne(int subTestNum);

    //  (시험 상세정보) 과목 상세정보 수정
    void updateSubDetail(TestSubjectVO testSubjectVO);

    //  (시험 상세정보) 메인시험 '삭제'
    void deleteTest(int testNum);

    //  (시험 상세정보) 과목 '삭제'
    void deleteSub(int subTestNum);


// ######################################### 학생 성적 등록 버튼 클릭시 (1.단일시험)  ##################################

    //  (단일시험 성적입력페이지) 시험테스트 번호로 학생명 조회///
    List<MemberVO> memNumInfo(int testNum);

    //  (단일시험 성적입력페이지) 시험테스트 번호로 테스트명 조회
    TestVO testNumInfo(int testNum);

    //  (단일시험 성적입력페이지) 테스트 목록 등록버튼 클릭시 학생별 성적 조회
    List<TestScoreVO> selectTestScore(int testNum);

    // (단일시험 성적입력페이지) 평균구하기
    TestScoreVO selectAvg(int testNum);

    //  (단일시험 성적입력페이지) 성적입력시 학생이름 조회
    //List<MemberVO> selectStuName(int classNum);

    // (단일시험 성적입력페이지) 성적 저장
    void insertStuScore(TestScoreVO testScoreVO);


    // (단일시험 성적입력페이지) 성적 수정
    void updateScore(TestScoreVO testScoreVO);




    // ######################################### 학생 성적 등록 버튼 클릭시 (2.과목별시험 입력)  ##################################

    // (과목시험 성적입력페이지) 과목조회  (sub 입력페이지 이동할때) %%%%%%%%%%%%%%%%%%확인중%%%%%%%%%%%%%%%%%%%
    List<TestSubjectVO> subSelect(int testNum);

    // (과목시험 성적입력페이지) 학생 수 조회
    List<MemberVO> stuCnt(int classNum);

    // (과목시험 성적입력페이지) 과목 저장   %%%%%%%%%%%%%%%%%%%%%%%%% 진행중
    void insertSubScore(TestScoreVO testScoreVO);

    // [선생님] 과목별 점수 조회
    List<TestScoreVO> selectSubScore(int testNum);


// ######################################### 선생님 이의신청 페이지  ##################################
    //  선생님 이의신청 목록
    List<TestAskVO> selTeacherAsk(String memberId);
    //  선생님 답글 저장(1)
    void insertCom(TestAskVO testAskVO);
    //  선생님 답글 저장(2) (그룹번호 업데이트)
    void updateComm(int protestOrigino);
    // 선생님이 학생글 삭제(2)
    void deleteThAsk(int protestOrigino);


}
