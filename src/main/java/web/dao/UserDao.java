package web.dao;

import web.model.User;

import java.util.stream.StreamSupport;

public interface UserDao {
    Iterable<User> getAllUsers();
    User getUserByName(String name);
    void updateUser(User user);
    void saveNewUser(User user);
    void deleteUser(long id);
}
