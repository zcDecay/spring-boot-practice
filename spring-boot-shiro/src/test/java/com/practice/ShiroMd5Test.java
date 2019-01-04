package com.practice;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description shiro加密测试
 * @Author zcc
 * @Date 18/12/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroMd5Test {
    @Test
    public void md5Test() throws Exception {
        //明文密码
        String password = "666";

        //加密:md5
        //fae0b27c451c728867a567e8c1bb4e53
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println(md5Hash);

        //加密：md5 + salt
        //1a71736cc4ecd2225831a5bf7db2bfa6
        md5Hash = new Md5Hash(password, "zhangchaochao");
        System.out.println(md5Hash);

        //加密：md5 + salt + 散列次数
        //3de878922892a28cdd98a7e0f0558e55
        md5Hash = new Md5Hash(password, "zhangchaochao", 3);
        System.out.println(md5Hash);


    }
}
