package bw5.energyservices.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
