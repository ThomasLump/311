package web.dto;

import org.springframework.http.ResponseEntity;

public record ApiResponse<T>(String message, T body) {

}