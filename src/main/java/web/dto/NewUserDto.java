package web.dto;

import java.util.List;

public record NewUserDto(
        long id,
        String username,
        String password,
        String phone_number,
        List<String> roles) {
    public NewUserDto() {
        this(0, "", "","", List.of(""));
    }
}



