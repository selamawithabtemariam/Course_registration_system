package edu.mum.se.mumsched.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.catalina.connector.CoyoteOutputStream;

import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationHandler.class})
@ExtendWith(SpringExtension.class)
class AuthenticationHandlerTest {
    @Autowired
    private AuthenticationHandler authenticationHandler;


    @Test
    void testOnAuthenticationSuccess() throws ServletException, IOException {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        Response response = new Response();
        authenticationHandler.onAuthenticationSuccess(mockHttpServletRequest, response,
                new TestingAuthenticationToken("Principal", "Credentials"));
        assertFalse(mockHttpServletRequest.isAsyncStarted());
        assertTrue(mockHttpServletRequest.isActive());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
        assertEquals("", mockHttpServletRequest.getServletPath());
        assertEquals(80, mockHttpServletRequest.getServerPort());
        assertEquals("localhost", mockHttpServletRequest.getServerName());
        assertEquals("http", mockHttpServletRequest.getScheme());
        assertEquals("", mockHttpServletRequest.getRequestURI());
        assertEquals(80, mockHttpServletRequest.getRemotePort());
        assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
        assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
        assertEquals("", mockHttpServletRequest.getMethod());
        assertEquals(80, mockHttpServletRequest.getLocalPort());
        assertEquals("localhost", mockHttpServletRequest.getLocalName());
        assertTrue(mockHttpServletRequest.getInputStream() instanceof DelegatingServletInputStream);
        assertTrue(mockHttpServletRequest.getHttpServletMapping() instanceof MockHttpServletMapping);
        assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
        assertEquals("", mockHttpServletRequest.getContextPath());
        assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
    }


    @Test
    void testOnAuthenticationSuccess2() throws ServletException, IOException {
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        Response response = new Response();
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken("Principal",
                "Credentials");

        authenticationHandler.onAuthenticationSuccess(request, response, testingAuthenticationToken);
        HttpServletResponse response1 = response.getResponse();
        assertTrue(response1 instanceof ResponseFacade);
        assertSame(response.getOutputStream(), response1.getOutputStream());
        assertFalse(testingAuthenticationToken.isAuthenticated());
    }


    @Test
    @Disabled
    void testOnAuthenticationSuccess3() throws ServletException, IOException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        authenticationHandler.onAuthenticationSuccess(request, new Response(), null);
    }


    @Test
    void testOnAuthenticationSuccess4() throws ServletException, IOException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        authenticationHandler.onAuthenticationSuccess(request, response,
                new TestingAuthenticationToken("Principal", "Credentials", "JaneDoe"));
    }
}

