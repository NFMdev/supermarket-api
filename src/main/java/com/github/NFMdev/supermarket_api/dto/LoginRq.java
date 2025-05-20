package com.github.NFMdev.supermarket_api.dto;

import lombok.Data;

@Data
public class LoginRq {
    String email;
    String password;
}
