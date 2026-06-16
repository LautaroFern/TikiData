package com.TikiData.platform.Account.Service;

import com.TikiData.platform.Account.DTO.AuthRequestDTO;
import com.TikiData.platform.Account.Model.AccountModel;
import com.TikiData.platform.Account.Repository.AccountRepository;
import com.TikiData.platform.Common.Config.JwtUtil;
import com.TikiData.platform.Account.DTO.AuthResponseDTO;
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

        AccountModel account = accountRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(dto.getPassword(), account.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(
                account.getEmail(),
                account.getRole().name()
        );

        return new AuthResponseDTO(
                token,
                account.getEmail(),
                account.getRole().name()
        );
    }
}
