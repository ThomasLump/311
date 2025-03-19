package web.mapper;

import org.springframework.security.core.GrantedAuthority;
import web.dto.NewUserDto;
import web.dto.UserDto;
import web.model.Role;
import web.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDtoMapper {
    public static User fromDtoToUser(UserDto dto) {
        User user = new User();
        user.setId(dto.id());
        user.setUsername(dto.username());
        user.setPhone_number(dto.phone_number());
        if (dto.roles() != null) {
            Set<Role> roles = dto.roles().stream().map(Role::new).collect(Collectors.toSet());
            user.setRole(roles);
        }
        return user;
    }

    public static UserDto fromUserToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPhone_number(),
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }
    public static User fromDtoToUser(NewUserDto dto) {
        User user = new User();
        user.setId(dto.id());
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setPhone_number(dto.phone_number());
        if (dto.roles() != null) {
            Set<Role> roles = dto.roles().stream().map(Role::new).collect(Collectors.toSet());
            user.setRole(roles);
        }
        return user;
    }
}
