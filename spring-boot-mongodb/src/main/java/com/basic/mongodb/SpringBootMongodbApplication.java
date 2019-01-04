package com.basic.mongodb;

import com.basic.mongodb.dao.CustomerDao;
import com.basic.mongodb.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CommandLineRunner : 启动时执行该run方法
 */
@Slf4j
@SpringBootApplication
public class SpringBootMongodbApplication implements CommandLineRunner {

    @Autowired
    CustomerDao customerDao;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customerDao.deleteAll();

        // save a couple of customers
        customerDao.save(new Customer("Alice", "Smith"));
        customerDao.save(new Customer("Bob", "Smith"));

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (Customer customer : customerDao.findAll()) {
            System.out.println(customer);
        }

        // fetch an individual customer
        log.info("Customer found with findByFirstName('Alice'):");
        log.info("--------------------------------");
        log.info("" + customerDao.findByFirstName("Alice"));

        log.info("Customers found with findByLastName('Smith'):");
        log.info("--------------------------------");
        for (Customer customer : customerDao.findByLastName("Smith")) {
            log.info("" + customer);
        }
    }
}

