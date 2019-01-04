package com.basic.jdbc.service;

import com.basic.jdbc.entity.Account;

import java.util.List;

public interface IAccountService {
    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
