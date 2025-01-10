package example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.User;
import com.example.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // Используем только эту аннотацию для тестирования JPA
public class InMemory_HzClientTest {

    @Autowired
    private UserService userService; // Убедитесь, что UserService правильно настроен для работы с JPA

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