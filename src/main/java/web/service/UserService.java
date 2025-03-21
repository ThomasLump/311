package web.service;

import web.dto.NewUserDto;
import web.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();
    void addUserByDto(NewUserDto user);
    void updateUserByDto(UserDto user);
    void deleteUserById(long id);
    UserDto getUserDtoByName(String name);
}
