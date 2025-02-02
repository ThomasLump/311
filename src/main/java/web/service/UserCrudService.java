package web.service;

import org.springframework.transaction.annotation.Transactional;
import web.data.User;

public interface UserCrudService {
    @Transactional
    Iterable<User> getAllUsers();

    @Transactional
    void addUser(User user);

    @Transactional
    void updateUser(User user);

    @Transactional
    void deleteUserById(long id);
}
