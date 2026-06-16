package com.TikiData.platform.Account.Service;

import com.TikiData.platform.Account.DTO.AuthRequestDTO;
import com.TikiData.platform.Account.DTO.AuthResponseDTO;

import java.util.Optional;

public interface IAuthService {
    AuthResponseDTO login(AuthRequestDTO dto);
}
