package com.practice;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @Description shiro测试类
 * @Author zcc
 * @Date 18/12/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroTest {
    /**
     * 登录操作
     * 通过ini配置文件，来模拟数据库操作
     */
    @Test
    public void shiroLogin() throws Exception {
        //1.创建SecurityManager工厂对象：加载配置文件，创建工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.通过工厂对象，创建SecurityManager对象
        SecurityManager securityManager = factory.getInstance();
        //3.将SecurityManager绑定到当前运行环境中，让系统随时可以访问securityManager对象
        SecurityUtils.setSecurityManager(securityManager);

        //4.创建当前登录的主体，此时主体没有经过认证
        Subject subject = SecurityUtils.getSubject();

        //5.绑定主体登录的身份、凭证，账号密码
        //参数1：将要登录的用户名，参数2：登录用户的密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "6666");
        //6.用户登录
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("账号找不到异常！");
            //e.printStackTrace();
        }catch (IncorrectCredentialsException e) {
            System.out.println("错误凭证（账号正常，密码错误）异常！");
        }

        //7.判断登录是否成功
        System.out.println("验证登录是否成功：" + subject.isAuthenticated());

        //8.退出
        subject.logout();
        System.out.println("验证登录是否成功：" + subject.isAuthenticated());
    }

    /**
     * 登录操作
     * 通过ini配置文件，配置自定义的realm
     * 自定义realm
     */
    @Test
    public void shiroLoginByMyRealm() throws Exception {
        //1.创建SecurityManager工厂对象：加载配置文件，创建工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2.通过工厂对象，创建SecurityManager对象
        SecurityManager securityManager = factory.getInstance();
        //3.将SecurityManager绑定到当前运行环境中，让系统随时可以访问securityManager对象
        SecurityUtils.setSecurityManager(securityManager);

        //4.创建当前登录的主体，此时主体没有经过认证
        Subject subject = SecurityUtils.getSubject();

        //5.绑定主体登录的身份、凭证，账号密码
        //参数1：将要登录的用户名，参数2：登录用户的密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "6666");
        //6.用户登录
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("账号找不到异常！");
            //e.printStackTrace();
        }catch (IncorrectCredentialsException e) {
            System.out.println("错误凭证（账号正常，密码错误）异常！");
        }

        //7.判断登录是否成功
        System.out.println("验证登录是否成功：" + subject.isAuthenticated());

        //8.退出
        subject.logout();
        System.out.println("验证登录是否成功：" + subject.isAuthenticated());
    }

    /**
     * 登录操作
     * 通过ini配置文件，配置自定义的realm
     * 自定义realm + md5散列
     */
    @Test
    public void shiroLoginByMyRealmAndMd5() throws Exception {
        //1.创建SecurityManager工厂对象：加载配置文件，创建工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");
        //2.通过工厂对象，创建SecurityManager对象
        SecurityManager securityManager = factory.getInstance();
        //3.将SecurityManager绑定到当前运行环境中，让系统随时可以访问securityManager对象
        SecurityUtils.setSecurityManager(securityManager);

        //4.创建当前登录的主体，此时主体没有经过认证
        Subject subject = SecurityUtils.getSubject();

        //5.绑定主体登录的身份、凭证，账号密码
        //参数1：将要登录的用户名，参数2：登录用户的密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        //6.用户登录
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("账号找不到异常！");
            //e.printStackTrace();
        }catch (IncorrectCredentialsException e) {
            System.out.println("错误凭证（账号正常，密码错误）异常！");
        }

        //7.判断登录是否成功
        System.out.println("验证登录是否成功：" + subject.isAuthenticated());

        //8.退出
        subject.logout();
        System.out.println("验证登录是否成功：" + subject.isAuthenticated());
    }

    /**
     * 角色判断
     */
    @Test
    public void shiroLoginByRole() throws Exception {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        subject.login(token);

        //进行授权前提，用户必须通过认证

        System.out.println("验证登录是否成功：" + subject.isAuthenticated());

        //判断当前用户是否拥有某个角色,return: boolean
        System.out.println(subject.hasRole("role1"));
        //判断当前用户是否拥有全部角色，return: boolean
        System.out.println(subject.hasAllRoles(Arrays.asList("role1","role2","role3")));
        //判断当前用户是否拥有全部角色，return: list<boolean>
        System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("role1","role2","role3"))));


        //判断当前用户是否拥有某个角色,没有该角色直接抛异常UnauthorizedException
        subject.checkRole("role3");
        subject.checkRoles("role1","role2","role3");

    }

    /**
     * 权限判断
     */
    @Test
    public void shiroLoginByPermission() throws Exception {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        subject.login(token);

        //进行授权前提，用户必须通过认证

        System.out.println("验证登录是否成功：" + subject.isAuthenticated());

        //判断当前用户是否拥有某个权限,return: boolean
        System.out.println(subject.isPermitted("user:delete"));
        //判断当前用户是否拥有全部权限，return: boolean
        System.out.println(subject.isPermittedAll("user:delete","user:create"));
        //判断当前用户是否拥有全部权限，return: list<boolean>
        System.out.println(Arrays.toString(subject.isPermitted("user:delete","user:create","user:list")));


        //判断当前用户是否拥有某个权限,没有该权限直接抛异常UnauthorizedException
        subject.checkRole("user:delete");
        subject.checkRoles("user:delete","user:create","user:list");

    }

    /**
     * 权限Realm
     */
    @Test
    public void shiroByPermissionRealm() throws Exception {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        subject.login(token);

        //进行授权前提，用户必须通过认证

        System.out.println("验证登录是否成功：" + subject.isAuthenticated());

        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user:list"));

    }
}
