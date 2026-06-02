package com.TikiData.platform.user.service;

import com.TikiData.platform.user.dto.AdminCreateUserDTO;
import com.TikiData.platform.user.dto.AdminUpdateUserDTO;
import com.TikiData.platform.user.dto.UserRequestDTO;
import com.TikiData.platform.user.dto.UserResponseDTO;
import com.TikiData.platform.user.mapper.UserMapper;
import com.TikiData.platform.user.model.Role;
import com.TikiData.platform.user.model.UserModel;
import com.TikiData.platform.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        UserModel user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, AdminUpdateUserDTO updateDTO) {
        UserModel user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getEmail().equals(updateDTO.getEmail()) && repository.existsByEmail(updateDTO.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        user.setEmail(updateDTO.getEmail());
        user.setRole(Role.valueOf(updateDTO.getRole()));

        UserModel updatedUser = repository.save(user);
        return mapper.toResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        repository.deleteById(id);
    }
}
