package com.rully.spring.webflux.router;

import com.rully.spring.webflux.handler.CustomerHandler;
import com.rully.spring.webflux.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler customerHandler;

    @Autowired
    private CustomerStreamHandler customerStreamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
            .GET("/api/customers", customerHandler::loadCustomers)
            .GET("/api/customers/stream", customerStreamHandler::getCustomers)
            .GET("/api/customers/{input}", customerHandler::findCustomer)
            .POST("/api/customers", customerHandler::saveCustomer)
            .build();
    }

}
