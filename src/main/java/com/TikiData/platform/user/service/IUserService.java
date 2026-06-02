package com.TikiData.platform.user.service;

import com.TikiData.platform.user.dto.AdminCreateUserDTO;
import com.TikiData.platform.user.dto.UserRequestDTO;
import com.TikiData.platform.user.dto.UserResponseDTO;

public interface IUserService {
    UserResponseDTO createUserByAdmin(AdminCreateUserDTO adminDTO);

    public UserResponseDTO registerUser(UserRequestDTO requestDTO);
}
