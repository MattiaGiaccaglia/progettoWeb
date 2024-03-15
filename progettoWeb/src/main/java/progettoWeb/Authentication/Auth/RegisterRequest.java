package progettoWeb.Authentication.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String email;
    private String telefono;
}
