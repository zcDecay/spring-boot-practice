package com.basic.springcache.dao.impl;

import com.basic.springcache.dao.BookDao;
import com.basic.springcache.entity.Book;
import org.springframework.stereotype.Component;
import org.springframework.cache.annotation.Cacheable;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/04
 */
@Component
public class BookDaoImpl implements BookDao {


    @Override
    @Cacheable(value = "book")
    public Book getById(String id) {
        simulateSlowService();
        return new Book(id,"解忧杂货店", "0.01");
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
