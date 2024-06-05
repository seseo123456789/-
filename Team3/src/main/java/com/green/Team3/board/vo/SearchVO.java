package com.green.Team3.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@ToString
public class SearchVO extends PageVO{
    private String searchType;
    private String searchValue;
    private int typeNum;

}
