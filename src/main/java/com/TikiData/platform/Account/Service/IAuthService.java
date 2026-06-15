package com.TikiData.platform.Account.Service;

import com.TikiData.platform.Account.DTO.AuthRequestDTO;
import com.TikiData.platform.Account.DTO.AuthResponseDTO;

public interface IAuthService {
    AuthResponseDTO login(AuthRequestDTO dto);
}
