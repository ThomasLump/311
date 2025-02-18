package web.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

@Service
public class UserCrudServiceImpl implements UserCrudService {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public UserCrudServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    @Override
    public Iterable<User> getAllUsers(){
        return userDao.getAllUsers();
    }
    @Transactional
    @Override
    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveNewUser(user);
    }
    @Transactional
    @Override
    public void updateUser(User user){
        userDao.updateUser(user);
    }
    @Transactional
    @Override
    public void deleteUserById(long id){
        userDao.deleteUser(id);
    }

    @Transactional
    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
