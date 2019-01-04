package com.practice;

import com.google.code.kaptcha.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAuthCodeApplicationTests {


    @Autowired
    private Producer producer;

    @Test
    public void authCodeTest() {
        //生成文字验证码
        //String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage("12.7000000000");


        File outputfile = new File("C:/images/yanzhengma.png");
        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 生成透明的图片
     * @param: * @param
     * @return: void
     */
    @Test
    public void lucencyTest() throws Exception {
        int width = 100;
        int height = 100;
        int fontHeight = 20;
        String drawStr = "0.12";
        File outputfile = new File("C:/images/BOLD.png");
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D gd = buffImg.createGraphics();
        //设置透明  start
        buffImg = gd.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        gd = buffImg.createGraphics();
        //设置透明  end
        gd.setFont(new Font("微软雅黑", Font.PLAIN, fontHeight)); //设置字体
        gd.setColor(Color.ORANGE); //设置颜色
        gd.drawString(drawStr, width / 2 - fontHeight * drawStr.length() / 2, fontHeight); //输出文字（中文横向居中）

        ImageIO.write(buffImg, "png", outputfile);

    }

}

