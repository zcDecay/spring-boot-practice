package com.basic.jdbc.dao;

import com.basic.jdbc.entity.Account;

import java.util.List;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/03
 */
public interface IAccountDAO {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
