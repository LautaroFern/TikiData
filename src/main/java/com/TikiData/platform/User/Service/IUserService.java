package com.TikiData.platform.User.Service;

import com.TikiData.platform.User.DTO.AdminCreateUserDTO;
import com.TikiData.platform.User.DTO.UserRequestDTO;
import com.TikiData.platform.User.DTO.UserResponseDTO;

public interface IUserService {
    UserResponseDTO createUserByAdmin(AdminCreateUserDTO adminDTO);

    public UserResponseDTO registerUser(UserRequestDTO requestDTO);
}
