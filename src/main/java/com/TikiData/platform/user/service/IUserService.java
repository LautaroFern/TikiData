package com.TikiData.platform.user.service;

import com.TikiData.platform.user.dto.AdminCreateUserDTO;
import com.TikiData.platform.user.dto.AdminUpdateUserDTO;
import com.TikiData.platform.user.dto.UserRequestDTO;
import com.TikiData.platform.user.dto.UserResponseDTO;

import java.util.List;

public interface IUserService {
    UserResponseDTO createUserByAdmin(AdminCreateUserDTO adminDTO);

    public UserResponseDTO registerUser(UserRequestDTO requestDTO);

    public List<UserResponseDTO> getAllUsers();
    public UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, AdminUpdateUserDTO updateDTO);
    public void deleteUser(Long id);
}
