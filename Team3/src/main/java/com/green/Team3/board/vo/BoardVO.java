package com.green.Team3.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BoardVO {
    private int boardNum;
    private String boardTitle;
    private String boardContent;
    private String memberId;
    private int typeNum;
    private int boardCnt;
    private SearchVO searchVO;
    private List<ReplyVO> replyVO;
    private List<ImgVO> imgList;
    private int imgNum;
    private int currentBoardNum;
    private String memberName;
    private String createDate;

}
