package com.green.Team3.learn.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chartService")
public class ChartService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    public String createRandomColor(){
        String[] charSet = new String[]{
                "0","1","2","3","4","5","6","7",
                "8","9","a","b","c","d","e","f"
        };
        String randomColor = "#";
        for(int i = 0; i < 6; i++){
            int randomIndex = (int)(Math.random() * charSet.length);
            randomColor += charSet[randomIndex];
        }
        return randomColor;
    }

}
