package com.practice.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @Description 密码Realm
 * @Author zcc
 * @Date 18/11/02
 */
public class PasswordRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "passwordRealm";
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    /**
     * 认证
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
        //模拟数据库中的数据加密 666 + md5 + salt + 散列3次
        String password = "3de878922892a28cdd98a7e0f0558e55";//明文密码


        //当前info对象表示realm登录对比信息， 参数1：用户信息（真实登录中是登录对象user对象）参数2：密码，参数3：当前realm的名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes("zhangchaochao"), getName());


        return info;
    }
}
