package com.basic.springcache.Runner;

import com.basic.springcache.dao.BookDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/04
 */
@Slf4j
@Component
public class BookRunner implements CommandLineRunner {

    @Resource
    BookDao bookDao;

//    private final BookDao bookDao;

//    public BookRunner(BookDao bookDao) {
//        this.bookDao = bookDao;
//    }

    @Override
    public void run(String... args) throws Exception {
        log.info(".... Fetching books");
        log.info("1 -->" + bookDao.getById("1"));
        log.info("2 -->" + bookDao.getById("2"));
        log.info("1 -->" + bookDao.getById("1"));
        log.info("1 -->" + bookDao.getById("1"));
        log.info("2 -->" + bookDao.getById("2"));
        log.info("1 -->" + bookDao.getById("1"));

    }
}
