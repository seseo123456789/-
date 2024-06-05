package com.green.Team3.member.vo;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.board.vo.SearchVO;
import com.green.Team3.cls.vo.ClsVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MemberVO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberTel;
    private String memberEmail;
    private String memberBirth;
    private int memberAge; // 계산한 나이 활용
    private String memberGender;
    private int memberRoll;
    private String memberAddr;
    private String addrDetail;
    private String postCode;
    private String regDate;
    private String strRoll;
    private RollTypeVO rollTypeVO;
    private List<OperatorVO> operatorVOList;

    private SearchVO searchVO;
    private int check;//출석
    private int absent;//결석
    private int tardy;//지각
    private int early;//조퇴
    private int supple;//보강
    private String className;
}
