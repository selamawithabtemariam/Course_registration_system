package edu.mum.se.mumsched.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppConfig.class})
@ExtendWith(SpringExtension.class)
class AppConfigTest {
    @Autowired
    private AppConfig appConfig;


    @Test
    void testPasswordEncoder() {
        appConfig.passwordEncoder();
    }
}

