package com.TikiData.platform.Account.Service;

import com.TikiData.platform.Account.Model.AccountModel;
import com.TikiData.platform.Account.Repository.AccountRepository;
import com.TikiData.platform.Common.Config.JwtUtil;
import com.TikiData.platform.User.DTO.AuthRequestDTO;
import com.TikiData.platform.User.DTO.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService{

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO login(AuthRequestDTO dto) {

        // 1. Busca la cuenta por email
        AccountModel account = accountRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        // 2. Verifica la contraseña
        if (!passwordEncoder.matches(dto.getPassword(), account.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // 3. Genera el token
        String token = jwtUtil.generateToken(
                account.getEmail(),
                account.getRole().name()
        );

        // 4. Devuelve el token con info básica
        return new AuthResponseDTO(
                token,
                account.getEmail(),
                account.getRole().name()
        );
    }
}
