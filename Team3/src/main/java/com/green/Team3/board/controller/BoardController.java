package com.green.Team3.board.controller;

import com.green.Team3.board.service.BoardService;
import com.green.Team3.board.service.BoardServiceImpl;
import com.green.Team3.board.service.ReplyServiceImpl;
import com.green.Team3.board.utill.UploadUtil;
import com.green.Team3.board.vo.*;
import com.green.Team3.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

    ///////////////////////////////// 공지 사항 /////////////////////////////////////

    // 공지사항 - 학사공지 목록 페이지
    @RequestMapping("/noticeListStu")
    public String List1(SearchVO searchVO, Model model
            , @RequestParam(name = "searchValue" ,required = false) String searchValue
            , @RequestParam(name = "searchType" ,required = false) String searchType
            , @RequestParam(name = "isSearch" ,required = false, defaultValue = "0") int isSearch
            , @RequestParam(name = "accorNum", required = false, defaultValue = "1") int accorNum){
        // 공지사항 전체 데이터 수
        System.out.println(searchVO);
        int totalDataCnt = boardService.selectNoticeCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);
        // 페이지 정보 세팅
        searchVO.setPageInfo();
        System.out.println(searchVO);
        // 공지사항 검색 시 페이징코드 정리
        List<BoardVO> noticeList = boardService.selectNoticeListStu(searchVO);
        if(isSearch == 1){
            searchVO.setTotalDataCnt(noticeList.size());
            searchVO.setPageInfo();
            if(searchVO.getTotalDataCnt() == 0){
                isSearch = 2;
            }
        }
        model.addAttribute("isSearch", isSearch);
        // 공지사항 목록 조회
        model.addAttribute("noticeList", noticeList);
        // 공지사항 내 전체 데이터 목록
        model.addAttribute("totalDataCnt", totalDataCnt);
        // 공지사항 목록에서 검색한 데이터
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchType", searchType);
        model.addAttribute("accorNum", accorNum);
        return "content/common/notice_list_stu";
    }

    // 공지사항 - 강사공지 목록 페이지
    @RequestMapping("/noticeListTea")
    public String List2(SearchVO searchVO, Model model
            , @RequestParam(name = "searchValue" ,required = false) String searchValue
            , @RequestParam(name = "searchType" ,required = false) String searchType
            , @RequestParam(name = "isSearch" ,required = false, defaultValue = "0") int isSearch
            , @RequestParam(name = "accorNum", required = false, defaultValue = "1") int accorNum){
        // 공지사항 전체 데이터 수
        int totalDataCnt = boardService.selectNoticeCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);
        // 페이지 정보 세팅
        searchVO.setPageInfo();
        System.out.println(searchVO);
        // 공지사항 검색 시 페이징코드 정리
        List<BoardVO> noticeList = boardService.selectNoticeListTea(searchVO);
        if(isSearch == 1){
            searchVO.setTotalDataCnt(noticeList.size());
            searchVO.setPageInfo();
            if(searchVO.getTotalDataCnt() == 0){
                isSearch = 2;
            }
        }
        model.addAttribute("isSearch", isSearch);
        // 공지사항 목록 조회
        model.addAttribute("noticeList", noticeList);
        // 공지사항 내 전체 데이터 목록
        model.addAttribute("totalDataCnt", totalDataCnt);
        // 공지사항 목록에서 검색한 데이터
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchType", searchType);
        model.addAttribute("accorNum", accorNum);
        return "content/common/notice_list_tea";
    }

    // 공지사항 작성 페이지로 이동
    @GetMapping("/noticeWriteForm")
    public String noticeWriteForm(){
        return "content/common/notice_write_form";
    }

    // 공지사항 게시글 작성 + 이미지 첨부 기능
    @PostMapping("/noticeWrite")
    public String noticeWrite(BoardVO boardVO
                            , Authentication authentication
                            , @RequestParam(name = "subImgs") MultipartFile[] subImgs){

        //------------------- 사용자 로그인 정보 받아오기 --------------------
        User user = (User) authentication.getPrincipal();
        boardVO.setMemberId(user.getUsername());

        //----------------------- 파일 첨부 기능 -----------------------
        //첨부 이미지들 업로드
        List<ImgVO> imgList = UploadUtil.multiUploadFile(subImgs);

        //다음에 들어갈 boardNum 조회
        int boardNum = boardService.selectNextNoticeCode();

        //------------------------ 공지사항 등록 ------------------------
        boardVO.setBoardNum(boardNum);

        //------------------------ 파일 첨부 등록 -----------------------
        boardVO.setImgList(imgList);

        System.out.println(boardVO);
        //쿼리 실행
        boardService.insertNotice(boardVO);

        if(boardVO.getTypeNum() == 1){
            return "redirect:/board/noticeListStu";}
        else if(boardVO.getTypeNum() == 2){
            return "redirect:/board/noticeListTea";}
        else{return null;}
    }


    // 공지사항 상세 조회
    @GetMapping("/noticeDetail")
    public String noticeDetail(BoardVO boardVO
                                , Model model){
        //조회수 증가
        boardService.updateBoardCnt(boardVO.getBoardNum());

        //상세 조회
        BoardVO vo = boardService.selectNoticeDetail(boardVO.getBoardNum());
        System.out.println(vo);
        model.addAttribute("notice", vo);

        //이전글 조회
        BoardVO prevPage = boardService.prevPage(boardVO);
        if (prevPage != null){
            model.addAttribute("currentBoardNum", boardVO.getBoardNum());
            model.addAttribute("prevPage", prevPage);
        }
        //이전 글이 없을 때
        else {
            model.addAttribute("prevPageNotFound", true);
        }

        // 다음글 조회
        BoardVO nextPage = boardService.nextPage(boardVO.getBoardNum());
        if(nextPage != null){
            model.addAttribute("currentBoardNum", boardVO.getBoardNum());
            model.addAttribute("nextPage", nextPage);
        }
        //다음 글이 없을 때
        else {
            model.addAttribute("nextPageNotFound", true);
        }
        return "content/common/notice_detail";
    }

    // 공지사항 게시글 삭제(첨부 파일 있을 때 / 없을 때 모두 가능) //리턴페이지 어디로 감...
    @GetMapping("/deleteNotice")
    public String deleteNotice(BoardVO boardVO){
        boardService.deleteNotice(boardVO);
        //return "redirect:/board/noticeList";
        if(boardVO.getTypeNum() == 1){
            return "redirect:/board/noticeListStu";}
        else if(boardVO.getTypeNum() == 2){
            return "redirect:/board/noticeListTea";}
        else{return null;}
    }

    // 공지사항 게시글 수정 양식 페이지 이동
    @GetMapping("/updateNotice")
    public String update(@RequestParam(name = "boardNum", required=false) int boardNum, Model model){
        model.addAttribute("notice", boardService.selectNoticeDetail(boardNum));
        return "content/common/notice_update";
    }

    //공지사항 게시글 수정 시 첨부파일 이미지 삭제(비동기)
//    @ResponseBody
//    @PostMapping("/deleteImgFile")
//    public BoardVO deleteImgFile(@RequestParam(name="imgNum") int imgNum, BoardVO boardVO){
//        boardService.deleteImgFile(imgNum);
//        return boardService.selectNoticeDetail(boardVO.getBoardNum());
//    }

    //공지사항 게시글 수정 시 첨부파일 이미지 삭제(비동기)
    @ResponseBody
    @PostMapping("/deleteImgFile")
    public BoardVO deleteImgFile(@RequestParam(name="imgNum") int imgNum, @RequestParam(name="boardNum") int boardNum){
        boardService.deleteImgFile(imgNum);
        return boardService.selectNoticeDetail(boardNum);
    }



    //공지사항 게시글 수정
//    @PostMapping("/updateNotice")
//    public String update(BoardVO boardVO, @RequestParam("boardNum") int boardNum){
//        boardVO.setBoardNum(boardNum);
//        boardService.updateNotice(boardVO);
//        return "redirect:/board/noticeDetail?boardNum=" + boardNum;
//    }


    // 공지사항 게시글 수정 + 이미지 첨부 기능 *******************************************************구현중
    @PostMapping("/updateNotice")
    public String updateNotice(BoardVO boardVO
                            , @RequestParam(name="boardNum") int boardNum
                            , @RequestParam(name = "subImgs") MultipartFile[] subImgs
                            , Authentication authentication){
        //----------------------- 파일 첨부 기능 -----------------------
        //첨부 이미지들 업로드
        List<ImgVO> imgList = UploadUtil.multiUploadFile(subImgs);

        //글번호 세팅
        boardVO.setBoardNum(boardNum);

        //------------------------ 파일 첨부 등록 -----------------------
        // 새로운 첨부파일이 있는 경우
        if (subImgs != null && subImgs.length > 0) {
            // 새로운 첨부 파일 업로드
            List<ImgVO> newImgList = UploadUtil.multiUploadFile(subImgs);
            // 기존 첨부파일 삭제 + 새로운 첨부 파일 추가
            boardVO.setImgList(newImgList);
        }

        boardVO.setImgList(imgList);
        System.out.println(boardVO);

        //-------------------------공지사항 수정 쿼리 실행
        boardService.updateNotice(boardVO);
        return "redirect:/board/noticeDetail?boardNum=" + boardNum;
    }

    ///////////////////////////////// 문의 사항 /////////////////////////////////////

    // 문의사항 페이지 - 공지사항이랑 분리
    @RequestMapping("/qnaList")
    public String qnaList(SearchVO searchVO, Model model
            , @RequestParam(name = "searchValue" ,required = false) String searchValue
            , @RequestParam(name = "searchType" ,required = false) String searchType
            , @RequestParam(name = "isSearch" ,required = false, defaultValue = "0") int isSearch
            , @RequestParam(name = "accorNum", required = false, defaultValue = "1") int accorNum){
        // 문의사항 전체 데이터 수
        int totalDataCnt = boardService.selectNoticeCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);

        // 페이지 정보 세팅
        searchVO.setPageInfo();
        System.out.println(searchVO);

        // 공지사항 검색 시 페이징코드 정리
        List<BoardVO> qnaList = boardService.selectQnaList(searchVO);

        if(isSearch == 1){
            searchVO.setTotalDataCnt(qnaList.size());
            searchVO.setPageInfo();
            if(searchVO.getTotalDataCnt() == 0){
                isSearch = 2;
            }
        }
        model.addAttribute("isSearch", isSearch);
        // 문의사항 목록 조회
        model.addAttribute("qnaList", qnaList);
        // 문의사항 내 전체 데이터 목록
        model.addAttribute("totalDataCnt", totalDataCnt);
        // 문의사항 목록에서 검색한 데이터
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchType", searchType);
        model.addAttribute("accorNum", accorNum);
        return "content/common/qna_list";
    }

    // 문의사항 작성 페이지로 이동
    @GetMapping("/qnaWriteForm")
    public String qnaWriteForm(){
        return "content/common/qna_write_form";
    }

    // 문의사항 게시글 작성
    @PostMapping("/qnaWrite")
    public String qnaWrite(BoardVO boardVO, Authentication authentication){
        //로그인 정보
        User user = (User) authentication.getPrincipal();
        boardVO.setMemberId(user.getUsername());
        //문의사항 게시글 등록
        boardService.insertQna(boardVO);
        return "redirect:/board/qnaList";
    }

    // 문의사항 상세 조회
    @GetMapping("/qnaDetail")
    public String qnaDetail(@RequestParam(name = "boardNum") int boardNum
            , Model model){
        //조회수 증가
        boardService.updateBoardCnt(boardNum);
        System.out.println(boardNum);

        //상세 조회
        BoardVO vo = boardService.selectQnaDetail(boardNum);
        model.addAttribute("qna", vo);

        //댓글 조회
        List<ReplyVO> replyList = replyService.selectReplyList(boardNum);
        model.addAttribute("replyList", replyList);

        //이전글 조회
        BoardVO prevPage = boardService.prevPage(vo);
        if (prevPage != null){
            model.addAttribute("currentBoardNum", boardNum);
            model.addAttribute("prevPage", prevPage);
        }
        //이전 글이 없을 때
        else {
            model.addAttribute("prevPageNotFound", true);
        }

        // 다음글 조회
        BoardVO nextPage = boardService.nextPage(boardNum);
        if(nextPage != null){
            model.addAttribute("currentBoardNum", boardNum);
            model.addAttribute("nextPage", nextPage);
        }
        //다음 글이 없을 때
        else {
            model.addAttribute("nextPageNotFound", true);
        }

        return "content/common/qna_detail";
    }

    // 문의사항 게시글 삭제
    @GetMapping("/deleteQna")
    public String deleteQna(@RequestParam(name = "boardNum") int boardNum){
        boardService.deleteQna(boardNum);
        return "redirect:/board/qnaList";
    }

    // 문의사항 게시글 수정 양식 페이지 이동
    @GetMapping("/updateQna")
    public String updateQna(@RequestParam(name = "boardNum", required=false) int boardNum, Model model){
        model.addAttribute("qna", boardService.selectNoticeDetail(boardNum));
        return "content/common/qna_update";
    }

    //문의사항 게시글 수정
    @PostMapping("/updateQna")
    public String updateQna(BoardVO boardVO, @RequestParam("boardNum") int boardNum){
        boardVO.setBoardNum(boardNum);
        boardService.updateBoard(boardVO);
        return "redirect:/board/qnaDetail?boardNum=" + boardNum;
    }

}
