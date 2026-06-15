package com.TikiData.platform.Account.Service;

import com.TikiData.platform.User.DTO.AuthRequestDTO;
import com.TikiData.platform.User.DTO.AuthResponseDTO;

public interface IAuthService {
    AuthResponseDTO login(AuthRequestDTO dto);
}
