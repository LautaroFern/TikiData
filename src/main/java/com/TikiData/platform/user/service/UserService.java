package com.TikiData.platform.user.service;

import com.TikiData.platform.user.dto.AdminCreateUserDTO;
import com.TikiData.platform.user.dto.UserRequestDTO;
import com.TikiData.platform.user.dto.UserResponseDTO;
import com.TikiData.platform.user.mapper.UserMapper;
import com.TikiData.platform.user.model.Role;
import com.TikiData.platform.user.model.UserModel;
import com.TikiData.platform.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private UserMapper mapper;
    private UserRepository repository;

    @Override
    public UserResponseDTO createUserByAdmin(AdminCreateUserDTO adminDTO) {
        if (repository.existsByEmail(adminDTO.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        UserModel newUser = new UserModel();
        newUser.setEmail(adminDTO.getEmail());
        newUser.setPassword(adminDTO.getPassword());
        newUser.setRole(Role.valueOf(adminDTO.getRole()));

        UserModel savedUser = repository.save(newUser);

        return mapper.toResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO requestDTO) {
        if (repository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        UserModel newUser = mapper.toEntity(requestDTO);
        UserModel savedUser = repository.save(newUser);

        return mapper.toResponseDTO(savedUser);
    }
}
