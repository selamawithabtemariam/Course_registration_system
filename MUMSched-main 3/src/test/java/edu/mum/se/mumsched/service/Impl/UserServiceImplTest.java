package edu.mum.se.mumsched.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.mum.se.mumsched.component.SessionManager;
import edu.mum.se.mumsched.dao.UserDao;
import edu.mum.se.mumsched.domain.User;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private SessionManager sessionManager;

    @MockBean
    private UserDao userDao;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Test
    void testCreateUser() {
        User user = new User();
        user.setEnabled(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(123);
        user.setUsername("janedoe");
        when(userDao.save((User) any())).thenReturn(user);
        assertEquals(123, userServiceImpl.createUser("janedoe", "Role").intValue());
        verify(userDao).save((User) any());
    }


    @Test
    void testUpdateUser() {
        User user = new User();
        user.setEnabled(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(123);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
        user1.setEnabled(true);
        user1.setPassword("iloveyou");
        user1.setRole("Role");
        user1.setUserId(123);
        user1.setUsername("janedoe");
        when(userDao.save((User) any())).thenReturn(user1);
        when(userDao.findById((Integer) any())).thenReturn(ofResult);
        userServiceImpl.updateUser(123, "jane.doe@example.org");
        verify(userDao).save((User) any());
        verify(userDao).findById((Integer) any());
    }


    @Test
    void testUpdateUser2() {

        User user = new User();
        user.setEnabled(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(123);
        user.setUsername("janedoe");
        when(userDao.save((User) any())).thenReturn(user);
        when(userDao.findById((Integer) any())).thenReturn(Optional.empty());
        userServiceImpl.updateUser(123, "jane.doe@example.org");
    }
}

