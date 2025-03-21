package web.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.dto.UserDto;
import web.mapper.UserDtoMapper;
import web.model.User;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService implements UserServiceCrud, UserDetailsService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Iterable<UserDto> getAllUsers(){
        return userDao.getAllUsers().stream().map(UserDtoMapper::fromUserToDto).collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public void addUserByDto(UserDto userDto){
        User user = UserDtoMapper.fromDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveNewUser(user);
    }
    @Transactional
    @Override
    public void updateUserByDto(UserDto userdto){
        userDao.updateUser(UserDtoMapper.fromDtoToUser(userdto));
    }

    @Transactional
    @Override
    public void deleteUserById(long id){
        userDao.deleteUser(id);
    }

    @Override
    public UserDto getUserDtoByName(String name) {
        Optional<User> userOptional = userDao.findUserByName(name);
        if(userOptional.isPresent()) {
            return UserDtoMapper.fromUserToDto(userOptional.get());
        } else {
            throw new EntityNotFoundException("🙈user with name: \\'\" + username + \"\\' is not found 🥲");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.findUserByName(username);
        if(userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UsernameNotFoundException("🙈user with name: \'" + username + "\' is not found 🥲");
        }
    }
}
