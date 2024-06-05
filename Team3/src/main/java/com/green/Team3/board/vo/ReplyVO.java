package com.green.Team3.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReplyVO {
    private int replyNum;
    private String replyContent;
    private String replyCreateDate;
    private String memberId;
    private int boardNum;
}
