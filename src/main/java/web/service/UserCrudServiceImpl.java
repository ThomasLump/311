package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.data.User;

@Service
public class UserCrudServiceImpl implements UserCrudService {
    private final UserDao userDao;

    public UserCrudServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Transactional
    @Override
    public Iterable<User> getAllUsers(){
        return userDao.getAllUsers();
    }
    @Transactional
    @Override
    public void addUser(User user){
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
}
