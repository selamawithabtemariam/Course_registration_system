package edu.mum.se.mumsched.service.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {


    @Test
    void testConstructor() {
        assertEquals("An error occurred", (new NotFoundException("An error occurred")).getMessage());
    }
}

