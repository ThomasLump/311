package web.dao;

import web.data.User;

public interface UserDao {
    Iterable<User> getAllUsers();
    void updateUser(User user);
    void saveNewUser(User user);
    void deleteUser(long id);
}
