package com.green.Team3.cls.service;

import com.green.Team3.board.vo.SearchVO;
import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;

import java.util.List;

public interface ClsService {

    // 선택한 회원의 수강(강의) 목록 조회
    List<ClsVO> selectClass(MemberVO memberVO);

    // 선택한 반의 상세 정보 조회
    ClsVO selectClassDetail(int classNum);

    // 전체 반 목록 조회
    List<ClsVO> selectAllClass(SearchVO searchVO);


}
