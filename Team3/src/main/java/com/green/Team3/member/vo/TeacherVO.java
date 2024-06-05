package com.green.Team3.member.vo;

import com.green.Team3.cls.vo.ClsVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TeacherVO {
    private int teacherNum;
    private int teacherWork;
    private String memberId;
    private MemberVO memberVO;
    private String teacherName;
    private String strWork;
}
