package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class); // Создание логгера

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(String name) {
        logger.info("Добавление пользователя с именем: {}", name); // Логирование информации о добавлении пользователя
        User user = new User(name);
        return userRepository.save(user);
    }

    public User getUser(String name) {
        logger.info("Запрос на получение пользователя с именем: {}", name); // Логирование информации о запросе пользователя
        User foundUser = userRepository.findByName(name);
        if (foundUser != null) {
            logger.info("Пользователь найден: {}", foundUser); // Логирование успешного поиска
        } else {
            logger.warn("Пользователь с именем '{}' не найден", name); // Логирование предупреждения, если пользователь не найден
        }
        return foundUser;
    }

    public void deleteAll() {
        logger.info("Удаление всех пользователей"); // Логирование информации о удалении всех пользователей
        userRepository.deleteAll();
    }
}