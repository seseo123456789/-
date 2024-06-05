package com.green.Team3.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service("mailService")
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    //단순 문자 메일 보내기
    public void sendSimpleEmail(MailVO mailVO){
        //단순 문자 메일을 보낼 수 있는 객체 생성
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mailVO.getTitle());  //메일 제목
        message.setTo(mailVO.getRecipient());   //받는사람
        message.setText(mailVO.getContent());
//        String text = "임시 비밀번호 = " + createRandomPw();
//        message.setText(text);
        javaMailSender.send(message);
    }
    //html 메일 보내기
    public void sendHTMLEmail(){
        MimeMessage message = javaMailSender.createMimeMessage();
        String password = "1111";
        try {
            message.setSubject("임시 제목");
            message.setText(setContext(password),"UTF-8","html");
            message.addRecipients(MimeMessage.RecipientType.TO,"alswo990602@naver.com");
            javaMailSender.send(message);
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }
    //12자리의 랜덤 비밀번호 생성
    public String createRandomPw(){
        String[] charSet = new String[]{
                "0","1","2","3","4","5","6","7","8","9",
                "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
        };
        String imsiPw = "";
        for (int i = 0; i < 12; i++){
            int randomIndex = (int)(Math.random() * charSet.length);
            imsiPw += charSet[randomIndex];
        }
        return imsiPw;
    }

    public String setContext(String password){
        Context context = new Context();
        context.setVariable("password",password);
        return templateEngine.process("/content/mail/mail",context);
    }





}
