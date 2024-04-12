package org.example.gestion_cinema.payload.request;

import lombok.Data;

@Data

public class LoginRequest {
    private String username;

    private String password;
}
