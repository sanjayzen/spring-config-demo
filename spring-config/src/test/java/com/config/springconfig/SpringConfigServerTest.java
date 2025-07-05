package com.config.springconfig;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.cloud.config.server.git.uri=https://github.com/sanjayzen/spring-config-demo",
    "spring.cloud.config.enabled=false"
})
public class SpringConfigServerTest {

    @Test
    public void contextLoads() {
        // Test that the Spring context loads successfully
    }
    
    @Test
    public void configServerStarts() {
        // Test that the config server starts without errors
    }
}
