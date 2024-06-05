package com.green.Team3.cls.vo;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.member.vo.MemberVO;
import com.green.Team3.member.vo.TeacherVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.PipedReader;
import java.util.List;

@ToString
@Setter
@Getter
public class ClsVO {
    private int classNum;
    private String className;
    private int classPayment;
    private int classPersonnel;
    private String classSdate;
    private String classEdate;
    private String classEnter;
    private int classStatus;
    private int teacherNum;
    private int stuCnt;
    private String strEnter;
    private TeacherVO teacherVO;
    private List<OperatorVO> operatorVOList;
}
