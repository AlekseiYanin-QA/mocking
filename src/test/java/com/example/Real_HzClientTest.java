import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.User;
import com.example.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Transactional
public class Real_HzClientTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService.deleteAll();
    }

    @Test
    void testAddAndGetUser() {
        // Given
        String userName = "Sergey";

        // When
        User savedUser = userService.addUser(userName);
        User foundUser = userService.getUser(userName);

        // Then
        assertNotNull(savedUser);
        assertNotNull(foundUser);
        assertEquals(userName, foundUser.getName());
    }
}