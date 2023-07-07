package com.student.userService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@MapperScan("com.student.userService.Mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
//		ConfigurableApplicationContext run=SpringApplication.run(UserApplication.class, args);
//		System.out.println(run.getBeanNamesForType(FilterConfig.class).);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
