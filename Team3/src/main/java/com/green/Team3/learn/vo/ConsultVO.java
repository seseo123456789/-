package com.green.Team3.learn.vo;

import com.green.Team3.cls.vo.ClsVO;
import com.green.Team3.member.vo.MemberVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConsultVO {
    private int consultNum;
    private String consultContent;
    private String consultDate;
    private String memberId;
    private int classNum;
    private ClsVO clsVO;
    private MemberVO memberVO;
}
