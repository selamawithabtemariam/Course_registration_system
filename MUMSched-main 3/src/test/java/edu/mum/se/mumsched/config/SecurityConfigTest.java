package edu.mum.se.mumsched.config;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecurityConfig.class})
@ExtendWith(SpringExtension.class)
class SecurityConfigTest {
    @Autowired
    private SecurityConfig securityConfig;


    @Test
    @Disabled
    void testSecurityFilterChain() throws Exception {

        HttpSecurity http = null;


        SecurityFilterChain actualSecurityFilterChainResult = this.securityConfig.securityFilterChain(http);
    }

    @Test
    @Disabled
    void testWebSecurityCustomizer() {

        WebSecurityCustomizer actualWebSecurityCustomizerResult = this.securityConfig.webSecurityCustomizer();

    }


    @Test
    @Disabled
    void testAuthenticationProvider() {

        AuthenticationProvider actualAuthenticationProviderResult = this.securityConfig.authenticationProvider();


    }
}

