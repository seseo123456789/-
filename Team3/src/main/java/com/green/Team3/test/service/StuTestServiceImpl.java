package com.green.Team3.test.service;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;
import com.green.Team3.test.vo.TestAskVO;
import com.green.Team3.test.vo.TestScoreVO;
import com.green.Team3.test.vo.TestSubjectVO;
import com.green.Team3.test.vo.TestVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuTestService")
public class StuTestServiceImpl implements StuTestService {


    @Autowired
    private SqlSessionTemplate sqlSession;

            // /////////////////// 진행중

    // ##################################### 학생이 로그인했을때 성적 확인 #####################################
            // 서치 학생정보조회
            @Override
            public MemberVO selectStuTest(String memberId) {
                return sqlSession.selectOne("testMapper.selectStuTest", memberId);
            }
            // 학생 강좌수 조회
            @Override
            public List<OperatorVO> stuClCnt(String memberId) {
                return sqlSession.selectList("testMapper.stuClCnt", memberId);
            }

    // 서치 학생 수강별 시험목록조회
            @Override
            public List<TestVO> selectStuCLTest(String memberId) {
                return sqlSession.selectList("testMapper.selectStuCLTest", memberId);
            }
            // 서치 학생 시험지별 목록조회
            @Override
            public List<TestVO> selectStuTestDetail(String memberId) {
                return sqlSession.selectList("testMapper.selectStuTestDetail", memberId);
            }
            // 서치 학생 과목별 목록조회
            @Override
            public List<TestSubjectVO> selectStuSub(String memberId) {
                return sqlSession.selectList("testMapper.selectStuSub", memberId);
            }
            // 서치 학생 전체 성적이수표 조회
            @Override
            public List<ClsVO> totalSelectTest(String memberId) {
                return sqlSession.selectList("testMapper.totalSelectTest", memberId);
            }

//############ 조회버튼 클릭시 성적 상세페이지 이동하여 조회 ########
            // 학생 과목 없을시  my 성적페이지 이동 & 조회
            @Override
            public TestScoreVO mainTestMyScore(TestScoreVO testScoreVO) {
                return sqlSession.selectOne("testMapper.mainTestMyScore", testScoreVO);
            }
            // 학생 과목 있을시   my 성적페이지 이동 & 조회
            @Override
            public List<TestScoreVO> subTestMyScore(TestScoreVO testScoreVO) {
                return sqlSession.selectList("testMapper.subTestMyScore", testScoreVO);
            }
            // 학생 성적증명서 나의 총 성적 조회
            @Override
            public List<TestScoreVO> printMyGrade(TestScoreVO testScoreVO) {
                return sqlSession.selectList("testMapper.printMyGrade", testScoreVO);
            }


    //################# 학생 성적 이의신청 페이지 #######################


            // [학생] 이의신청 글 적기
            @Override
            public void insertStuAsk(TestAskVO testAskVO) {
                sqlSession.insert("testMapper.insertStuAsk", testAskVO);
            }
            // [학생] 이의신청 글 목록
            @Override
            public List<TestAskVO> selectStuAsk(String memberId) {
                return sqlSession.selectList("testMapper.selectStuAsk", memberId);
            }
            // [학생] 이의신청 글 상세보기
            @Override
            public TestAskVO stuAskDetail(TestAskVO testAskVO) {
                return sqlSession.selectOne("testMapper.stuAskDetail", testAskVO);
            }

            //  답글 표시 구분하려고
            @Override
            public TeacherVO askMemberId(String memberId) {
                return sqlSession.selectOne("testMapper.askMemberId",memberId);
            }

            // 학생 원글 오리지널 번호 부여
            @Override
            public void updateOrigin(int protestNum) {
               sqlSession.update("testMapper.updateOrigin",protestNum);
            }

            // 학생 원글 수정
            @Override
            public void updateMyAsk(TestAskVO testAskVO) {
               sqlSession.update("testMapper.updateMyAsk", testAskVO);
            }
            // 학생 원글 삭제
            @Override
            public void deleteMyAsk(int protestNum) {
                sqlSession.delete("testMapper.deleteMyAsk", protestNum);
            }


}
