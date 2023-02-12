package edu.mum.se.mumsched.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.mum.se.mumsched.dao.UserDao;
import edu.mum.se.mumsched.domain.CustomUser;
import edu.mum.se.mumsched.domain.User;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserCredentialServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserCredentialServiceImplTest {
    @Autowired
    private UserCredentialServiceImpl userCredentialServiceImpl;

    @MockBean
    private UserDao userDao;


    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = new User();
        user.setEnabled(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(123);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findUserByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = userCredentialServiceImpl.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals(123, ((CustomUser) actualLoadUserByUsernameResult).getId());
        verify(userDao).findUserByUsername((String) any());
    }

    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        User user = mock(User.class);
        when(user.getUserId()).thenThrow(new UsernameNotFoundException("Msg"));
        when(user.getPassword()).thenThrow(new UsernameNotFoundException("Msg"));
        when(user.getUsername()).thenThrow(new UsernameNotFoundException("Msg"));
        when(user.getRole()).thenReturn("Role");
        doNothing().when(user).setEnabled(anyBoolean());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRole((String) any());
        doNothing().when(user).setUserId(anyInt());
        doNothing().when(user).setUsername((String) any());
        user.setEnabled(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(123);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findUserByUsername((String) any())).thenReturn(ofResult);
        assertThrows(UsernameNotFoundException.class, () -> userCredentialServiceImpl.loadUserByUsername("janedoe"));
        verify(userDao).findUserByUsername((String) any());
        verify(user).getRole();
        verify(user).getUsername();
        verify(user).setEnabled(anyBoolean());
        verify(user).setPassword((String) any());
        verify(user).setRole((String) any());
        verify(user).setUserId(anyInt());
        verify(user).setUsername((String) any());
    }


    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {

        User user = mock(User.class);
        when(user.getUserId()).thenReturn(123);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("");
        when(user.getRole()).thenReturn("Role");
        doNothing().when(user).setEnabled(anyBoolean());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRole((String) any());
        doNothing().when(user).setUserId(anyInt());
        doNothing().when(user).setUsername((String) any());
        user.setEnabled(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(123);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findUserByUsername((String) any())).thenReturn(ofResult);
        userCredentialServiceImpl.loadUserByUsername("janedoe");
    }


    @Test
    void testLoadUserByUsername4() throws UsernameNotFoundException {

        User user = mock(User.class);
        when(user.getUserId()).thenReturn(123);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getRole()).thenReturn("");
        doNothing().when(user).setEnabled(anyBoolean());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRole((String) any());
        doNothing().when(user).setUserId(anyInt());
        doNothing().when(user).setUsername((String) any());
        user.setEnabled(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(123);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findUserByUsername((String) any())).thenReturn(ofResult);
        userCredentialServiceImpl.loadUserByUsername("janedoe");
    }


    @Test
    void testLoadUserByUsername5() throws UsernameNotFoundException {
        when(userDao.findUserByUsername((String) any())).thenReturn(Optional.empty());
        User user = mock(User.class);
        when(user.getUserId()).thenReturn(123);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getRole()).thenReturn("Role");
        doNothing().when(user).setEnabled(anyBoolean());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRole((String) any());
        doNothing().when(user).setUserId(anyInt());
        doNothing().when(user).setUsername((String) any());
        user.setEnabled(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(123);
        user.setUsername("janedoe");
        assertThrows(UsernameNotFoundException.class, () -> userCredentialServiceImpl.loadUserByUsername("janedoe"));
        verify(userDao).findUserByUsername((String) any());
        verify(user).setEnabled(anyBoolean());
        verify(user).setPassword((String) any());
        verify(user).setRole((String) any());
        verify(user).setUserId(anyInt());
        verify(user).setUsername((String) any());
    }
}

