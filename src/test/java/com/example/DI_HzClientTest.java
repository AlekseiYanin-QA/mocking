import com.example.Application;
import com.example.User;
import com.example.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (classes =  Application.class)
@Transactional
public class DI_HzClientTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService.deleteAll();
    }

    @Test
    void testAddAndGetUser() {
        // Given
        String userName = "Ivan";

        // When
        User savedUser = userService.addUser(userName);
        User foundUser = userService.getUser(userName);

        // Then
        assertNotNull(savedUser);
        assertNotNull(foundUser);
        assertEquals(userName, foundUser.getName());
    }
}
