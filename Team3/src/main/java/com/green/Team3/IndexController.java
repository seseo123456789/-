package com.green.Team3;

import com.green.Team3.admin.service.AdminService;
import com.green.Team3.board.service.BoardService;
import com.green.Team3.board.service.BoardServiceImpl;
import com.green.Team3.board.vo.BoardTypeVO;
import com.green.Team3.board.vo.BoardVO;
import com.green.Team3.board.vo.SearchVO;
import com.green.Team3.learn.service.CalenderServiceImpl;
import com.green.Team3.learn.service.ConsultServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Resource(name = "consultService")
    private ConsultServiceImpl consultService;
    @Resource(name = "calenderService")
    private CalenderServiceImpl calenderService;
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    @Resource(name = "adminService")
    private AdminService adminService;
    @GetMapping("/")
    public String firstPage(@RequestParam(value = "errorMsg",required = false,defaultValue = "success")String errorMsg, Model model
                            , HttpSession session, SearchVO searchVO
                            , @RequestParam(name = "searchValue" ,required = false) String searchValue
                            , @RequestParam(name = "searchType" ,required = false) String searchType
                            , @RequestParam(name = "isSearch" ,required = false, defaultValue = "0") int isSearch) {
        model.addAttribute("errorMsg",errorMsg);
        consultService.autoDeleteConsult();
        calenderService.autoDeleteCalender();
        adminService.updateClassEnter();
        List<BoardTypeVO> boardType = boardService.selectType();
        session.setAttribute("boardTypes", boardType);

        //메인화면 학사공지 조회
        int totalDataCnt = boardService.selectNoticeCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);
        // 페이지 정보 세팅
        searchVO.setPageInfo();
        System.out.println(searchVO);
        // 공지사항 검색 시 페이징코드 정리
        List<BoardVO> noticeList = boardService.selectNoticeListFirst(searchVO);
        model.addAttribute("isSearch", isSearch);
        // 공지사항 목록 조회
        model.addAttribute("noticeList", noticeList);
        // 공지사항 내 전체 데이터 목록
        model.addAttribute("totalDataCnt", totalDataCnt);
        // 공지사항 목록에서 검색한 데이터
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchType", searchType);

        return "content/member/firstPage";
    }


}