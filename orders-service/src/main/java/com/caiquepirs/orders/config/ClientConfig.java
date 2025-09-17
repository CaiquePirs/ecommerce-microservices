package com.caiquepirs.orders.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.caiquepirs.orders.client")
public class ClientConfig {

}
