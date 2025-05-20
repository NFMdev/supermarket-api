package com.github.NFMdev.supermarket_api.dto;

import lombok.Data;

@Data
public class RegisterRq {
    String email;
    String password;
    String username;
}
