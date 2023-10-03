package com.company.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.company.enums.Role;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class JwtUtilTest {
    /**
     * Method under test: {@link JwtUtil#encode(UUID, Role)}
     */
    @Test
    void testEncode() {
        // Arrange, Act and Assert
        assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiRElTUEFUQ0hFUiIsImlkIjpudWxsfQ.hgGm3d3qgv-PyKZ-uzbv3SPc7jwN0bcVB"
                + "L5oV91qxxU", JwtUtil.encode(null, Role.DISPATCHER));
    }
}

