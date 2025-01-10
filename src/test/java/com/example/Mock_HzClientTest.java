package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Mock_HzClientTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void testAddAndGetUser() {
        // Given
        String userName = "Dmitry";
        User mockUser = new User(userName);
        when(userRepository.findByName(userName)).thenReturn(mockUser);
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // When
        User savedUser = userService.addUser(userName);
        User foundUser = userService.getUser(userName);

        // Then
        assertNotNull(savedUser);
        assertNotNull(foundUser);
        assertEquals(userName, foundUser.getName());
        verify(userRepository).save(any(User.class));
        verify(userRepository).findByName(userName);
    }
}