package com.example.demo.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = "demo.name=test", classes = DemoProperties.class)
@EnableConfigurationProperties(DemoProperties.class)
class DemoPropertiesTest {
    @Autowired
    DemoProperties demoProperties;

    @Test
    void getName() {
        assertNotNull(demoProperties);
        assertEquals("test", demoProperties.getName());
    }
}