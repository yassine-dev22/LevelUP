package com.ehtp.kanban_backend.dto;

import com.ehtp.kanban_backend.model.User;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private User.Role role;
}
