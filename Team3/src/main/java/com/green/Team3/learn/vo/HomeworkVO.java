package com.green.Team3.learn.vo;

import com.green.Team3.cls.vo.ClsVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HomeworkVO {
    private int hwNum;
    private String hwName;
    private String hwSdate;
    private String hwEdate;
    private int classNum;
    private ClsVO clsVO;
}
