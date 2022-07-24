package com.rully.spring.webflux.service;

import com.rully.spring.webflux.dao.CustomerDao;
import com.rully.spring.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        long end = System.currentTimeMillis();

        System.out.println("Total execution time : " + (end - start));

        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomersStream();
        long end = System.currentTimeMillis();

        System.out.println("Total execution time : " + (end - start));

        return customers;
    }
}
