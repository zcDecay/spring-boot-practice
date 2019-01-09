package com.basic.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件发送测试类
 * <p>
 * 163邮箱常见问题 ： http://help.163.com/09/1224/17/5RAJ4LMH00753VB8.html
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJmsApplicationTests {

    private final static String ROOT_MAIL = "15611700291@163.com";

    @Test
    public void contextLoads() {
    }


    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 发送包含简单文本的邮件
     */
    @Test
    public void sendTxtMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        simpleMailMessage.setTo(new String[]{ROOT_MAIL});
        simpleMailMessage.setFrom(ROOT_MAIL);
        simpleMailMessage.setSubject("Spring Boot Mail");
        simpleMailMessage.setText("1111111111111");
        // 发送邮件
        mailSender.send(simpleMailMessage);

        System.out.println("邮件已发送");
    }

    /**
     * 发送包含HTML文本的邮件
     *
     * @throws Exception
     */
    @Test
    public void sendHtmlMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(ROOT_MAIL);
        mimeMessageHelper.setFrom(ROOT_MAIL);
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【HTML】");

        //  StringBuilder sb = new StringBuilder();
        //  sb.append("<html><head></head>");
        //  sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
        //  sb.append("</html>");
        String sb = "<html><head></head>" +
                "<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>" +
                "</html>";

        // 启用html
        mimeMessageHelper.setText(sb, true);
        // 发送邮件
        mailSender.send(mimeMessage);

        System.out.println("邮件已发送");

    }

    /**
     * 发送包含内嵌图片的邮件
     *
     * @throws Exception
     */
    @Test
    public void sendAttachedImageMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(ROOT_MAIL);
        mimeMessageHelper.setFrom(ROOT_MAIL);
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【图片】");

        // StringBuilder sb = new StringBuilder();
        // sb.append("<html><head></head>");
        // sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p>");
        // cid为固定写法，imageId指定一个标识
        // sb.append("<img src=\"cid:imageId\"/></body>");
        // sb.append("</html>");

        // cid为固定写法，imageId指定一个标识
        String sb = "<html><head></head>" +
                "<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p>" +
                "<img src=\"cid:imageId\"/></body>" +
                "</html>";

        // 启用html
        mimeMessageHelper.setText(sb, true);

        // 设置imageId
        FileSystemResource img = new FileSystemResource(new File("C:/Users/admin_l/Pictures/Saved Pictures/newt.jpg"));
        mimeMessageHelper.addInline("imageId", img);

        // 发送邮件
        mailSender.send(mimeMessage);

        System.out.println("邮件已发送");
    }

    /**
     * 发送包含附件的邮件
     *
     * @throws Exception
     */
    @Test
    public void sendAttendedFileMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setTo("zk145690@163.com");
        mimeMessageHelper.setFrom(ROOT_MAIL);
        mimeMessageHelper.setSubject("Spring Boot Mail zhangchaochao【附件】");


        // StringBuilder sb = new StringBuilder();
        // sb.append("<html><head></head>");
        // sb.append("<body><h1>spring zhangchaochao邮件测试发给zk145690@163.com</h1><p>hello!this is spring mail test。</p></body>");
        // sb.append("</html>");

        String sb = "<html><head></head>" +
                "<body><h1>spring zhangchaochao邮件测试发给zk145690@163.com</h1><p>hello!this is spring mail test。</p></body>" +
                "</html>";

        // 启用html
        mimeMessageHelper.setText(sb, true);
        // 设置附件
        FileSystemResource img = new FileSystemResource(new File("C:/Users/admin_l/Pictures/Saved Pictures/zhangqiaoqiao.jpg"));
        mimeMessageHelper.addAttachment("zhangqiaoqiao.jpg", img);

        // 发送邮件
        mailSender.send(mimeMessage);

        System.out.println("邮件已发送");
    }

}

