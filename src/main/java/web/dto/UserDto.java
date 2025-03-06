package web.dto;

import java.util.List;

public record UserDto(
        long id,
        String username,
        String phone_number,
        List<String> roles) {
    public UserDto() {
        this(0,"","",List.of(""));
    }
}
