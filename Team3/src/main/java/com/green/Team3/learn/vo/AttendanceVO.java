package com.green.Team3.learn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttendanceVO {
    private int atdNum;
    private String memberId;
    private String atdDate;
    private int atdtNum;
    private int classNum;
}
