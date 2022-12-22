package com.student.userService;

import com.student.userService.Config.FilterConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@MapperScan("com.student.userService.Mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
//		ConfigurableApplicationContext run=SpringApplication.run(UserApplication.class, args);
//		System.out.println(run.getBeanNamesForType(FilterConfig.class).);
    }

}
