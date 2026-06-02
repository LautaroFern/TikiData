package com.TikiData.platform.user.mapper;

import com.TikiData.platform.user.dto.UserRequestDTO;
import com.TikiData.platform.user.dto.UserResponseDTO;
import com.TikiData.platform.user.model.Role;
import com.TikiData.platform.user.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel toEntity(UserRequestDTO dto) {
        UserModel user = new UserModel();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(Role.USER);
        return user;
    }

    public UserResponseDTO toResponseDTO(UserModel user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        return dto;
    }
}
