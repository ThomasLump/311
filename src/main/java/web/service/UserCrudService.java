package web.service;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.stream.StreamSupport;

public interface UserCrudService {
    @Transactional
    Iterable<User> getAllUsers();

    @Transactional
    void addUser(User user);

    @Transactional
    void updateUser(User user);

    @Transactional
    void deleteUserById(long id);

    @Transactional
    User getUserByName(String name);
}
