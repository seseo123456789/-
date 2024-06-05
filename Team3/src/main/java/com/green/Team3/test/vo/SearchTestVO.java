package com.green.Team3.test.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SearchTestVO {
    private String searchTestType;
    private String searchValue;
    private String searchFromDate;
    private String searchToDate;
    private int classNum;
}
