package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class) // Укажите основной класс приложения
public class InMemory_HzClientTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService.deleteAll(); // Очищаем базу перед каждым тестом
    }

    @Test
    void testAddAndGetUser() {
        // Given
        String userName = "Alex";

        // When
        User savedUser = userService.addUser(userName);
        User foundUser = userService.getUser(userName);

        // Then
        assertNotNull(savedUser);
        assertNotNull(foundUser);
        assertEquals(userName, foundUser.getName());
    }
}