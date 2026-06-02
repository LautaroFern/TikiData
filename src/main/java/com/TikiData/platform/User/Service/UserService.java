package com.TikiData.platform.User.Service;

import com.TikiData.platform.User.DTO.AdminCreateUserDTO;
import com.TikiData.platform.User.DTO.UserRequestDTO;
import com.TikiData.platform.User.DTO.UserResponseDTO;
import com.TikiData.platform.User.Mapper.UserMapper;
import com.TikiData.platform.User.Model.Role;
import com.TikiData.platform.User.Model.UserModel;
import com.TikiData.platform.User.Repository.UserRepository;
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
