package com.TikiData.platform.User.Service;

import com.TikiData.platform.Account.Model.AccountModel;
import com.TikiData.platform.Account.Repository.AccountRepository;
import com.TikiData.platform.Common.Exception.TeamNotFoundException;
import com.TikiData.platform.Team.DTO.TeamResponseDTO;
import com.TikiData.platform.Team.Mapper.TeamMapper;
import com.TikiData.platform.Team.Model.TeamModel;
import com.TikiData.platform.Team.Repository.TeamRepository;
import com.TikiData.platform.User.DTO.*;
import com.TikiData.platform.User.Mapper.UserMapper;
import com.TikiData.platform.User.Model.Role;
import com.TikiData.platform.User.Model.UserModel;
import com.TikiData.platform.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserMapper mapper;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    @Transactional
    public UserResponseDTO registerUser(UserRequestDTO requestDTO) {
        if (accountRepository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        AccountModel newAccount = mapper.toAccountEntity(requestDTO);
        UserModel newProfile = mapper.toProfileEntity(requestDTO);

        newAccount.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        newProfile.setAccount(newAccount);
        newAccount.setUserProfile(newProfile);

        AccountModel savedAccount = accountRepository.save(newAccount);

        return mapper.toResponseDTO(savedAccount);
    }

    @Override
    @Transactional
    public UserResponseDTO createUserByAdmin(AdminCreateUserDTO adminDTO) {
        if (accountRepository.existsByEmail(adminDTO.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        AccountModel newAccount = mapper.toAccountEntity(adminDTO);
        UserModel newProfile = mapper.toProfileEntity(adminDTO);

        newAccount.setPassword(passwordEncoder.encode(adminDTO.getPassword()));

        newProfile.setAccount(newAccount);
        newAccount.setUserProfile(newProfile);

        AccountModel savedAccount = accountRepository.save(newAccount);
        return mapper.toResponseDTO(savedAccount);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return accountRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        AccountModel account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return mapper.toResponseDTO(account);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, AdminUpdateUserDTO updateDTO) {
        AccountModel account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        if (!account.getEmail().equals(updateDTO.getEmail()) && accountRepository.existsByEmail(updateDTO.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        account.setEmail(updateDTO.getEmail());
        account.setRole(Role.valueOf(updateDTO.getRole()));



        return mapper.toResponseDTO(accountRepository.save(account));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Cuenta no encontrada");
        }
        accountRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO getOwnProfile(String currentEmail) {
        AccountModel account = accountRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return mapper.toResponseDTO(account);
    }

    @Override
    @Transactional
    public UserResponseDTO updateOwnAccount(String currentEmail, UserUpdateOwnDTO dto) {
        AccountModel account = accountRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        if (!account.getEmail().equals(dto.getEmail()) && accountRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        account.setEmail(dto.getEmail());
        account.setPassword(passwordEncoder.encode(dto.getPassword()));

        return mapper.toResponseDTO(accountRepository.save(account));
    }

    @Override
    @Transactional
    public void deleteOwnAccount(String currentEmail) {
        AccountModel account = accountRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        accountRepository.delete(account);
    }

    @Override
    public List<UserResponseDTO> filterUsers(String email, String role) {
        Role roleEnum = null;
        if (role != null && !role.isEmpty()) {
            try {
                roleEnum = Role.valueOf(role.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Rol no válido para filtrar");
            }
        }

        return accountRepository.searchAccountsByFilters(email, roleEnum)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDTO addFavoriteTeam(String email, Long teamId) {
        AccountModel account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        TeamModel team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Equipo no encontrado con el ID: " + teamId));

        if (!account.getUserProfile().getFavoriteTeams().contains(team)) {
            account.getUserProfile().getFavoriteTeams().add(team);
        }

        return mapper.toResponseDTO(accountRepository.save(account));
    }

    @Override
    @Transactional
    public UserResponseDTO removeFavoriteTeam(String email, Long teamId) {
        AccountModel account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        TeamModel team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Equipo no encontrado"));

        account.getUserProfile().getFavoriteTeams().remove(team);
        return mapper.toResponseDTO(accountRepository.save(account));
    }

    public List<TeamResponseDTO> getFavoriteTeams(String email) {
        AccountModel account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        return account.getUserProfile().getFavoriteTeams()
                .stream()
                .map(teamMapper::toDTO)
                .toList();
    }

    @Override
    public List<TeamResponseDTO> filterFavoriteTeams(String email, String name, String country) {
        AccountModel account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return account.getUserProfile().getFavoriteTeams().stream()
                .filter(team -> name == null || team.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(team -> country == null || team.getCountry().toLowerCase().contains(country.toLowerCase()))
                .map(teamMapper::toDTO)
                .toList();

    }
}
