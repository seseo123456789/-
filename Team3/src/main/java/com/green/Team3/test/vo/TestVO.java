package com.green.Team3.test.vo;

import com.green.Team3.admin.vo.OperatorVO;
import com.green.Team3.cls.vo.ClsVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class TestVO {

    private int  testNum;
    private String testName;
    private String testDate;
    private int testMaxScore;
    private int classNum;

    private int subCnt;
    private int totalSubMax;

    private List<OperatorVO> studentListVO;
    private ClsVO classOneVo;

}
