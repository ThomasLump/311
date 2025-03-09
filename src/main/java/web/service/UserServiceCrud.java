package web.service;

import web.dto.UserDto;

public interface UserServiceCrud {

    Iterable<UserDto> getAllUsers();
    void addUserByDto(UserDto user);
    void updateUserByDto(UserDto user);
    void deleteUserById(long id);
    UserDto getUserDtoByName(String name);
}
