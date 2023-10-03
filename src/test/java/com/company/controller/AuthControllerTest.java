package com.company.controller;

import com.company.dto.AuthDTO;
import com.company.dto.ProfileDTO;
import com.company.dto.UserDTO;
import com.company.entity.UserEntity;
import com.company.enums.ProfileStatus;
import com.company.enums.Role;
import com.company.repository.UserRepository;
import com.company.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AuthController.class, AuthService.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AuthController#login(AuthDTO)}
     */


    /**
     * Method under test: {@link AuthController#login(AuthDTO)}
     */
    @Test
    void testLogin() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID());
        userEntity.setName("Name");
        userEntity.setPassword("iloveyou");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setRole(Role.DISPATCHER);
        userEntity.setStatus(ProfileStatus.ACTIVE);
        userEntity.setSurname("Doe");
        userEntity.setUsername("janedoe");
        Optional<UserEntity> ofResult = Optional.of(userEntity);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByPhoneNumber(Mockito.<String>any())).thenReturn(ofResult);
        AuthController authController = new AuthController(new AuthService(userRepository, new BCryptPasswordEncoder()));

        AuthDTO dto = new AuthDTO();
        dto.setPassword("iloveyou");
        dto.setPhoneNumber("6625550144");

        // Act
        ResponseEntity<ProfileDTO> actualLoginResult = authController.login(dto);

        // Assert
        assertTrue(actualLoginResult.hasBody());
        assertTrue(actualLoginResult.getHeaders().isEmpty());
        assertEquals(200, actualLoginResult.getStatusCodeValue());
        ProfileDTO body = actualLoginResult.getBody();
        assertEquals("6625550144", body.getPhoneNumber());
        assertEquals("janedoe", body.getUsername());
        verify(userRepository).findByPhoneNumber(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthController#login(AuthDTO)}
     */
    @Test
    void testLogin2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // Arrange
        AuthService authService = mock(AuthService.class);
        when(authService.login(Mockito.<AuthDTO>any())).thenReturn(new ProfileDTO("janedoe", "6625550144", "Jwt"));
        AuthController authController = new AuthController(authService);

        AuthDTO dto = new AuthDTO();
        dto.setPassword("iloveyou");
        dto.setPhoneNumber("6625550144");

        // Act
        ResponseEntity<ProfileDTO> actualLoginResult = authController.login(dto);

        // Assert
        assertTrue(actualLoginResult.hasBody());
        assertTrue(actualLoginResult.getHeaders().isEmpty());
        assertEquals(200, actualLoginResult.getStatusCodeValue());
        verify(authService).login(Mockito.<AuthDTO>any());
    }

    /**
     * Method under test: {@link AuthController#registration(UserDTO)}
     */
    @Test
    void testRegistration() throws Exception {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Name");
        userDTO.setPassword("iloveyou");
        userDTO.setPhoneNumber("6625550144");
        userDTO.setSurname("Doe");
        userDTO.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link AuthController#registration(UserDTO)}
     */
    @Test
    void testRegistration2() throws Exception {
        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID());
        userEntity.setName("Name");
        userEntity.setPassword("iloveyou");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setRole(Role.DISPATCHER);
        userEntity.setStatus(ProfileStatus.ACTIVE);
        userEntity.setSurname("Doe");
        userEntity.setUsername("janedoe");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(UUID.randomUUID());
        userEntity2.setName("Name");
        userEntity2.setPassword("iloveyou");
        userEntity2.setPhoneNumber("6625550144");
        userEntity2.setRole(Role.DISPATCHER);
        userEntity2.setStatus(ProfileStatus.ACTIVE);
        userEntity2.setSurname("Doe");
        userEntity2.setUsername("janedoe");
        Optional<UserEntity> ofResult = Optional.of(userEntity2);

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setId(UUID.randomUUID());
        userEntity3.setName("Name");
        userEntity3.setPassword("iloveyou");
        userEntity3.setPhoneNumber("6625550144");
        userEntity3.setRole(Role.DISPATCHER);
        userEntity3.setStatus(ProfileStatus.ACTIVE);
        userEntity3.setSurname("Doe");
        userEntity3.setUsername("janedoe");
        Optional<UserEntity> ofResult2 = Optional.of(userEntity3);
        when(userRepository.save(Mockito.<UserEntity>any())).thenReturn(userEntity);
        when(userRepository.findByPhoneNumber(Mockito.<String>any())).thenReturn(ofResult);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult2);

        UserDTO userDTO = new UserDTO();
        userDTO.setName("");
        userDTO.setPassword("iloveyou");
        userDTO.setPhoneNumber("+998999999999");
        userDTO.setSurname("Doe");
        userDTO.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

