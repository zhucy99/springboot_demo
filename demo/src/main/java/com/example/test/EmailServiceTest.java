package com.example.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.email.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    //@Test
    public void sendSimpleMail() throws Exception {
        emailService.sendSimpleEmail("zhucy99@gmail.com","this is simple mail"," hello LingDu");
    }
    
    //@Test
    public void sendHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        emailService.sendHtmlEmail("zhucy99@gmail.com","this is html mail",content);
    }
    
    //@Test
    public void sendAttachmentsMail() {
        String filePath="c:\\image\\WeChat Image_20171129115946.jpg";
        emailService.sendAttachmentsEmail("zhucy99@gmail.com", "主题：带附件的邮件", "收到附件，请查收！", filePath);

    }
    
    @Test
    public void sendInlineResourceMail() {
        String rscId = "001";
        String content="<html><body>Je suis robot Bonnie：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "c:\\image\\WeChat Image_20171129115946.jpg";

        emailService.sendInlineResourceEmail("zhucy99@gmail.com", "Je suis Robot Bonnie", content, imgPath, rscId);
    }
    
}
