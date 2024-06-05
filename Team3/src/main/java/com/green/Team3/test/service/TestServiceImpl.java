package com.green.Team3.test.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.test.vo.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testService")
public class TestServiceImpl implements TestService{

    @Autowired
    private SqlSessionTemplate sqlSession;



 // ########################################### 강사가 로그인해서 성적 처리 관리 ####################################
        // 강사 강의 목록조회
        @Override
        public List<ClsVO> selectTeacherClassList(String memberId) {
            return sqlSession.selectList("testMapper.selectTeacherClassList",memberId);
        }



 //###########################################  선생님 반별 시험목록조회 페이지  ###########################################

        // (선생님 시험 목록조회 페이지) 검색란
        @Override
        public List<TestVO> searchTestList(SearchTestVO searchTestVO) {
            return sqlSession.selectList("testMapper.searchTestList", searchTestVO);
        }

        // (선생님 시험 목록조회 페이지) class 명만 조회
        @Override
        public ClsVO onlyClassNum(int classNum) {
            return sqlSession.selectOne("testMapper.onlyClassNum", classNum);
        }


        // (선생님 시험 목록조회 페이지) 메인테스트명 목록 조회
        @Override
        public List<TestVO> selectTest(int classNum) {
            return sqlSession.selectList("testMapper.selectTest", classNum);
        }



// ##################################### 시험 설정 모달  #########################################



    // (선생님 모달) 메인 테스트 저장
    @Override
    public void insertTest(TestVO testVO) {
        sqlSession.selectOne("testMapper.insertTest", testVO);
    }

    // (선생님 모달) 과목있을시 메인테스트 저장 (만점제외)
    @Override
    public void subMainTestInsert(TestVO testVO) {
        sqlSession.insert("testMapper.subMainTestInsert", testVO);
    }

    //  (선생님 모달) 과목 조회 하기
    @Override
    public List<TestSubjectVO> selectSubList(int testNum) {
        return sqlSession.selectList("testMapper.selectSubList", testNum);
    }

    // (선생님 모달) 모달 과목저장 할때 테스트넘버 조회  (과목저장 할때 테스트만점 확인용)
    @Override
    public List<TestVO> testNumInfo2(int testNum) {
        return sqlSession.selectList("testMapper.testNumInfo2", testNum);
    }

    // (선생님 모달) 과목저장
    @Override
    public void insertSub(TestSubjectVO testSubjectVO) {
        sqlSession.insert("testMapper.insertSub", testSubjectVO);
    }


// ######################################### 시험 상세정보 조회시  ##################################

    // (시험 상세정보) 메인테스트 full 상세정보 수정1
    @Override
    public void updateTestDetail(TestVO testVO) {
        sqlSession.update("testMapper.updateTestDetail",testVO);
    }

    // (시험 상세정보) 메인테스트 만점제외 수정2
    @Override
    public void updateTestDeTwo(TestVO testVO) {
        sqlSession.update("testMapper.updateTestDeTwo",testVO);
    }

    // (시험 상세정보) 과목 1개 상세정보  조회
    @Override
    public TestSubjectVO selectSubOne(int subTestNum) {
        return sqlSession.selectOne("testMapper.selectSubOne", subTestNum);
    }

    //  (시험 상세정보) 과목 상세정보 수정
    @Override
    public void updateSubDetail(TestSubjectVO testSubjectVO) {
        sqlSession.update("testMapper.updateSubDetail",testSubjectVO);
    }
    //  (시험 상세정보) 메인 시험 '삭제'
    @Override
    public void deleteTest(int testNum) {
        sqlSession.delete("testMapper.deleteTest", testNum);
    }

    //  (시험 상세정보) 과목 '삭제'
    @Override
    public void deleteSub(int subTestNum) {
        sqlSession.delete("testMapper.deleteSub", subTestNum);
    }



// ######################################### 학생 성적 등록 버튼 클릭시 (1.단일시험)  ##################################


    // (단일시험 성적입력페이지) 시험테스트 번호로 반학생 정보조회
    @Override
    public List<MemberVO> memNumInfo(int testNum) {
        return  sqlSession.selectList("testMapper.memNumInfo", testNum);
    }

    //  (단일시험 성적입력페이지) 시험테스트 번호로 테스트명 조회
    @Override
    public TestVO testNumInfo(int testNum) {
        return sqlSession.selectOne("testMapper.testNumInfo", testNum);
    }


    // (단일시험 성적입력페이지) 테스트목록 등록버튼 클릭시 학생별 성적 조회
    @Override
    public List<TestScoreVO> selectTestScore(int testNum) {
        return sqlSession.selectList("testMapper.selectTestScore", testNum);
    }
    // (단일시험 성적입력페이지) 평균구하기
    @Override
    public TestScoreVO selectAvg(int testNum) {
        return sqlSession.selectOne("testMapper.selectAvg", testNum);
    }

    // (단일시험 성적입력페이지) 점수등록버튼 클릭시 성적저장
    @Override
    public void insertStuScore(TestScoreVO testScoreVO) {
        sqlSession.insert("testMapper.insertStuScore", testScoreVO);
    }


    // (단일시험 성적입력페이지) 수정버튼 클릭 시 성적수정
    @Override
    public void updateScore(TestScoreVO testScoreVO) {
        sqlSession.update("testMapper.updateScore", testScoreVO);
    }




    // /////////////////// 진행중
   // ##################################### 학생 성적 등록 버튼 클릭시 (2.과목별 성적 입력)  ##################################

    // (과목시험 성적입력페이지) 과목조회
    @Override
    public List<TestSubjectVO> subSelect(int testNum) {
        return sqlSession.selectList("testMapper.subSelect",testNum);
    }
    // (과목시험 성적입력페이지) 학생 수 조회
    @Override
    public List<MemberVO> stuCnt(int classNum) {
        return sqlSession.selectList("testMapper.stuCnt",classNum);
    }

    // (과목시험 성적입력페이지) 과목 성적 저장
    @Override
    public void insertSubScore(TestScoreVO testScoreVO) {
        sqlSession.insert("testMapper.insertSubScore", testScoreVO);
    }

    // [선생님] 과목별 점수 조회
    @Override
    public List<TestScoreVO> selectSubScore(int testNum) {
        return sqlSession.selectList("testMapper.selectSubScore", testNum);
    }

    // ######################################### 선생님 이의신청 페이지  ##################################
    //  선생님 이의신청 목록
    @Override
    public List<TestAskVO> selTeacherAsk(String memberId) {
        return sqlSession.selectList("testMapper.selTeacherAsk",memberId);
    }
    //  선생님 답글 저장(1)
    @Override
    public void insertCom(TestAskVO testAskVO) {
        sqlSession.insert("testMapper.insertCom",testAskVO);
    }
    //  선생님 답글 저장(2) (그룹번호 업데이트)
    @Override
    public void updateComm(int protestOrigino) {
        sqlSession.update("testMapper.updateComm",protestOrigino);
    }

    // 선생님이 학생글 삭제(2)
    @Override
    public void deleteThAsk(int protestOrigino) {
        sqlSession.delete("testMapper.deleteThAsk", protestOrigino);
    }


}
