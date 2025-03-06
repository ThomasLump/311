package web.service;

import web.dto.UserDto;
import web.model.User;

public interface UserCrudService {

    Iterable<UserDto> getAllUsers();


    void addUserByDto(UserDto user);


    void updateUserByDto(UserDto user);


    void deleteUserById(long id);


    UserDto getUserDtoByName(String name);
}
