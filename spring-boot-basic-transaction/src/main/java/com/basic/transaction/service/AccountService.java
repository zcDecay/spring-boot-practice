package com.basic.transaction.service;

import com.basic.transaction.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/03
 */
@Service
public class AccountService {


    @Autowired
    AccountMapper accountMapper;

    @Transactional
    public void transfer() throws RuntimeException{
        //用户1减10块 用户2加10块
        accountMapper.updateAccount(90,1);
        int i=1/0;
        accountMapper.updateAccount(110,2);
    }
}
