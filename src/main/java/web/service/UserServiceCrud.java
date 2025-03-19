package web.service;

import web.dto.NewUserDto;
import web.dto.UserDto;

public interface UserServiceCrud {

    Iterable<UserDto> getAllUsers();
    void addUserByDto(NewUserDto user);
    void updateUserByDto(UserDto user);
    void deleteUserById(long id);
    UserDto getUserDtoByName(String name);
}
