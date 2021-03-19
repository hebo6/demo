package com.example.demo.properties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("properties")
public class PropertiesController {
    private final DemoProperties demoProperties;

    public PropertiesController(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    @GetMapping("name")
    public String selectName() {
        return demoProperties.getName();
    }
}
