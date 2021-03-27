package com.example.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("demo")
@Data
public class DemoProperties {
    /**
     * 设置name属性
     */
    private String name = "default";
}
