package web.dao;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAllUsers();
    Optional<User> findUserByName(String name);
    void updateUser(User user);
    void saveNewUser(User user);
    void deleteUser(long id);
}
