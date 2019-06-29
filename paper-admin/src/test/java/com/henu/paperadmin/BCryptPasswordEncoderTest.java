package com.henu.paperadmin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


public class BCryptPasswordEncoderTest {

    public static void main(String[] args) {

        String password="123456";
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String code=bCryptPasswordEncoder.encode(password);
        if(bCryptPasswordEncoder.matches(password,"$2a$10$Okwlv56j1kPA/tHjnIxhY.VY4afOKSbu0oQsGWPVTTOxQGkDOCEyG")){
            System.out.println("匹配成功");
        }else{
            System.out.println("匹配失败");
        }
    }
}
