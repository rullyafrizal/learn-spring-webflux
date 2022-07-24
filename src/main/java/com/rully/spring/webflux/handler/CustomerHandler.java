package com.rully.spring.webflux.handler;

import com.rully.spring.webflux.dao.CustomerDao;
import com.rully.spring.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> customerList = customerDao.getCustomerList();

        return ServerResponse.ok()
            .body(customerList, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request) {
        Integer input = Integer.valueOf(request.pathVariable("input"));

        Mono<Customer> customer = customerDao.getCustomerList()
            .filter(val -> input.equals(val.getId()))
            .next();

        return ServerResponse.ok()
            .body(customer, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);

        Mono<String> saveResp = customerMono.map(cust -> cust.getId() + ":" + cust.getName());

        return ServerResponse.ok()
            .body(saveResp, String.class);
    }
}
