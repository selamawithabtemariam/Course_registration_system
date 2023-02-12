package edu.mum.se.mumsched.component;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SessionManager.class})
@ExtendWith(SpringExtension.class)
class SessionManagerTest {
    @Autowired
    private SessionManager sessionManager;


    @Test
    @Disabled
    void testGetUser() {

        sessionManager.getUser();
    }
}

