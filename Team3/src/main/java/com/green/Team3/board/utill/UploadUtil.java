package com.green.Team3.board.utill;

import com.green.Team3.board.vo.ImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//공지사항 파일 첨부 기능 관련
public class UploadUtil {
    //파일의 확장자를 문자열로 return하는 메소드
    public static String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    //uuid를 통한 파일명 생성
    public static String getUUID(){
        //랜덤으로 만들어진 uuid를 문자열로 리턴
        return UUID.randomUUID().toString();
    }

    //단일 파일 업로드 메소드
    public static ImgVO uploadFile(MultipartFile uploadFile){
        ImgVO imgVO = null;

        //첨부 파일이 존재할 때만 첨부 기능 실행
        if(!uploadFile.isEmpty()){
            //객체 생성
            imgVO = new ImgVO();

            //확장자 추출
            String extension = getExtension(uploadFile.getOriginalFilename());

            //중복 되지 않는 파일명 생성(파일명: 랜덤한 uuid + 확장자)
            String fileName = getUUID() + extension;

            //예외 처리
            try {
                File file1 = new File(ConstantVariable.UPLOAD_PATH + fileName); //경로 및 파일명
                uploadFile.transferTo(file1);
                //첨부 끝난 후 - 첨부파일명, 원본파일명
                imgVO.setAttachedFileName(fileName);
                imgVO.setOriginFileName(uploadFile.getOriginalFilename());
                imgVO.setIsMain("Y");
            }catch (Exception e){
                System.out.println("파일 첨부 중 예외 발생!");
                e.printStackTrace(); //오류가 난 위치 및 간략한 원인을 콘솔창에 출력
            }
        }
        return imgVO;
    }

    //다중 첨부 메소드
    public static List<ImgVO> multiUploadFile(MultipartFile[] uploadFiles){
        List<ImgVO> imgList = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles){
            ImgVO vo = uploadFile(uploadFile); //이미지 하나에 대한 원본 파일명 등
            if(vo != null){
                vo.setIsMain("N");
                imgList.add(vo);
            }
        }
        return imgList;
    }

}
