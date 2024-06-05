package com.green.Team3.admin.vo;

import com.green.Team3.board.vo.SearchVO;
import com.green.Team3.member.vo.TeacherVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OperatorVO {
    private int operNum;
    private int operPay;
    private String isPay;
    private String payDay;
    private String memberId;
    private int classNum;
    private int operNumCnt;
    private int totalSales;
    private int payYear;
    private int payMonth;
    private SearchVO searchVO;
    private List<Integer> operNumList;
}
