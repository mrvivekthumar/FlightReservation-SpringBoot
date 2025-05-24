package com.vivek.flightreservation.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.vivek.flightreservation.entities.User;
import com.vivek.flightreservation.repos.UserRepository;
import com.vivek.flightreservation.services.SecurityService;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private SecurityService securityService;

    @MockBean
    private BCryptPasswordEncoder encoder;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
    }

    @Test
    void registerForm_ShouldReturnRegisterUserView() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerUser"));
    }

    @Test
    void registerUser_WithValidData_ShouldReturnLoginView() throws Exception {
        when(encoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/loginUser")
                .param("firstname", "John")
                .param("lastname", "Doe")
                .param("email", "john.doe@example.com")
                .param("password", "password123"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void loginForm_ShouldReturnLoginView() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void login_WithValidCredentials_ShouldReturnFindFlightsView() throws Exception {
        when(securityService.login(any(), any(), any(), any())).thenReturn(true);

        mockMvc.perform(post("/findFlights")
                .param("email", "john.doe@example.com")
                .param("password", "password123"))
                .andExpect(status().isOk())
                .andExpect(view().name("findFlights"));
    }

    @Test
    void login_WithInvalidCredentials_ShouldReturnLoginViewWithError() throws Exception {
        when(securityService.login(any(), any(), any(), any())).thenReturn(false);

        mockMvc.perform(post("/findFlights")
                .param("email", "john.doe@example.com")
                .param("password", "wrongpassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("msg", "Invalid user name or password .Please try again."));
    }
} 