package com.green.Team3.board.vo;

// 페이징 정보

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVO {
    //현재 선택된 페이지 번호
    private int nowPage;

    //전체 데이터 수(전체 게시글 수)
    private int totalDataCnt;

    //한 페이지에 보여지는 데이터 수
    private int displayDataCnt;

    //한 페이지에 보여지는 페이지 수
    private int displayPageCnt;

    //화면에 보이는 첫 번째 페이지 번호
    private int beginPage;

    //화면에 보이는 마지막 페이지 번호
    private int endPage;

    //이전 버튼의 유무
    private boolean prev;

    //다음 버튼의 유무
    private boolean next;

    //생성자
    public PageVO(){
        nowPage = 1;
        displayDataCnt = 5;
        displayPageCnt = 5;
    }

    public void setPageInfo(){
        //화면에 보이는 마지막 페이지 번호 세팅
        //올림(현재 페이지/한 페이지에 보이는 페이지수)*한 페이지에 보이는 페이지수
        endPage = (int)Math.ceil((double)nowPage / displayPageCnt) * displayPageCnt;

        //화면에 보이는 첫번째 페이지 번호 세팅
        beginPage = endPage - displayPageCnt + 1;

        //전체 페이지 수
        int totalPageCnt = (int)Math.ceil(totalDataCnt / (double)displayDataCnt);

        //next 버튼의 유무
        if(endPage < totalPageCnt){
            next = true;
        }
        else {
            next = false;
            endPage = totalPageCnt;
        }

        //prev 버튼의 유무
        prev = beginPage != 1;
        //prev = beginPage == 1 ? false : true;
    }

    //setter
    public void setTotalDataCnt(int totalDataCnt){
        this.totalDataCnt = totalDataCnt;
    }

    public int getBeginPage(){
        return beginPage;
    }

    public int getEndPage(){
        return endPage;
    }

    public boolean getPrev(){
        return prev;
    }

    public boolean getNext(){
        return next;
    }

    public void setNowPage(int nowPage){
        this.nowPage = nowPage;
    }

    public int getDisplayDataCnt(){
        return displayDataCnt;
    }

    public int getNowPage(){
        return nowPage;
    }

}
