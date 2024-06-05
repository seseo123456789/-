package com.green.Team3.test.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestSubjectVO {

    private int subTestNum;
    private String subName;
    private int  subMaxScore;
    private int testNum;

    private TestVO testOneVO;

}
