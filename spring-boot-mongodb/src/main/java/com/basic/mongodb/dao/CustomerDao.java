package com.basic.mongodb.dao;

import com.basic.mongodb.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * MongoRepository : 里面有一些普通增删改查接口
 */
public interface CustomerDao extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}
