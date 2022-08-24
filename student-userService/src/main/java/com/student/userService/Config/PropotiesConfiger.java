package com.student.userService.Config;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "white.list")
@Data
@EnableAutoConfiguration
public class PropotiesConfiger {
    private Map<String,String> map;
}
