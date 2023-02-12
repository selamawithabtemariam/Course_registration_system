package edu.mum.se.mumsched.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@ContextConfiguration(classes = {MvcConfig.class})
@ExtendWith(SpringExtension.class)
class MvcConfigTest {
    @Autowired
    private MvcConfig mvcConfig;


    @Test
    void testAddResourceHandlers() {

        MvcConfig mvcConfig = new MvcConfig();
        AnnotationConfigReactiveWebApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext();
        ResourceHandlerRegistry resourceHandlerRegistry = new ResourceHandlerRegistry(applicationContext,
                new MockServletContext());

        mvcConfig.addResourceHandlers(resourceHandlerRegistry);
        assertFalse(resourceHandlerRegistry.hasMappingForPattern("Path Pattern"));
    }


    @Test
    void testAddResourceHandlers2() {

        MvcConfig mvcConfig = new MvcConfig();
        AnnotationConfigApplicationContext applicationContext = mock(AnnotationConfigApplicationContext.class);
        ResourceHandlerRegistry resourceHandlerRegistry = new ResourceHandlerRegistry(applicationContext,
                new MockServletContext());

        mvcConfig.addResourceHandlers(resourceHandlerRegistry);
        assertFalse(resourceHandlerRegistry.hasMappingForPattern("Path Pattern"));
    }


    @Test
    @Disabled
    void testAddResourceHandlers3() {

        (new MvcConfig()).addResourceHandlers(null);
    }


    @Test
    @Disabled
    void testAddResourceHandlers4() {

        MvcConfig mvcConfig = new MvcConfig();
        ResourceHandlerRegistry resourceHandlerRegistry = mock(ResourceHandlerRegistry.class);
        when(resourceHandlerRegistry.addResourceHandler((String[]) any())).thenReturn(null);
        mvcConfig.addResourceHandlers(resourceHandlerRegistry);
    }


    @Test
    void testAddViewControllers() {

        mvcConfig.addViewControllers(new ViewControllerRegistry(new AnnotationConfigReactiveWebApplicationContext()));
    }


    @Test
    @Disabled
    void testAddViewControllers2() {

        mvcConfig.addViewControllers(new ViewControllerRegistry(null));
    }


    @Test
    void testAddViewControllers3() {

        mvcConfig.addViewControllers(new ViewControllerRegistry(mock(AnnotationConfigApplicationContext.class)));
    }


    @Test
    @Disabled
    void testAddViewControllers4() {

        ViewControllerRegistry viewControllerRegistry = mock(ViewControllerRegistry.class);
        when(viewControllerRegistry.addViewController((String) any())).thenReturn(null);
        mvcConfig.addViewControllers(viewControllerRegistry);
    }
}

