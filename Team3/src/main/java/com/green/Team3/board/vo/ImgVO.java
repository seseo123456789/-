package com.green.Team3.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImgVO {
    private int imgNum;
    private String originFileName;
    private String attachedFileName;
    private String isMain;
    private int boardNum;
}
