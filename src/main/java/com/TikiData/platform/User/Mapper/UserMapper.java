package com.TikiData.platform.User.Mapper;

import com.TikiData.platform.Account.Model.AccountModel;
import com.TikiData.platform.User.DTO.AdminCreateUserDTO;
import com.TikiData.platform.User.DTO.UserRequestDTO;
import com.TikiData.platform.User.DTO.UserResponseDTO;
import com.TikiData.platform.User.Model.Role;
import com.TikiData.platform.User.Model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public AccountModel toAccountEntity(UserRequestDTO dto) {
        AccountModel account = new AccountModel();
        account.setEmail(dto.getEmail());
        account.setRole(Role.USER); // Rol forzado
        return account;
    }

    public UserModel toProfileEntity(UserRequestDTO dto) {
        UserModel profile = new UserModel();
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        return profile;
    }

    public AccountModel toAccountEntity(AdminCreateUserDTO dto) {
        AccountModel account = new AccountModel();
        account.setEmail(dto.getEmail());
        account.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        return account;
    }

    public UserModel toProfileEntity(AdminCreateUserDTO dto) {
        UserModel profile = new UserModel();
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        return profile;
    }

    public UserResponseDTO toResponseDTO(AccountModel account) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(account.getId());
        dto.setEmail(account.getEmail());
        dto.setRole(account.getRole().name());

        if (account.getUserProfile() != null) {
            dto.setFirstName(account.getUserProfile().getFirstName());
            dto.setLastName(account.getUserProfile().getLastName());
        }
        return dto;
    }
}
