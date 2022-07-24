package com.rully.spring.webflux.dao;

import com.rully.spring.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(Long i) {
        try {
            Thread.sleep(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
            .peek(i -> sleepExecution(500L))
            .peek(i -> System.out.println("processing: " + i))
            .mapToObj(i -> new Customer(i, "Customer " + i))
            .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream() {
//        List<Customer> customers =  IntStream.rangeClosed(1, 50)
//            .peek(i -> sleepExecution(1000L))
//            .peek(i -> System.out.println("processing: " + i))
//            .mapToObj(i -> new Customer(i, "Customer " + i))
//            .collect(Collectors.toList());
       return Flux.range(1, 10)
            .delayElements(Duration.ofMillis(500L))
            .doOnNext(i -> System.out.println("processing stream: " + i))
            .map(i -> new Customer(i, "Customer " + i));
    }

    public Flux<Customer> getCustomerList() {
        return Flux.range(1, 50)
            .doOnNext(i -> System.out.println("processing stream: " + i))
            .map(i -> new Customer(i, "Customer " + i));
    }

}
