package com.practice.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Description 自定义Realm
 * @Author zcc
 * @Date 18/11/02
 */
public class MyRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "myRealm";
    }

    /**
     * 授权操作
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证操作
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //参数token，表示登录时包装的UsernamePasswordToken

        //通过用户名到数据库中查询用户信息，封装成一个AuthenticationInfo对象返回，方便认证器进行对比
        String username = (String)authenticationToken.getPrincipal();

        //通过用户名查询数据库，将改用户对应数据查询返回：账号与密码
        if(!"zhangsan".equals(username)) {
            return  null;
        }
        String password = "6666";

        //当前info对象表示realm登录对比信息， 参数1：用户信息（真实登录中是登录对象user对象）参数2：密码，参数3：当前realm的名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());


        return info;
    }
}
