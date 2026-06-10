package com.TikiData.platform.User.Service;

import com.TikiData.platform.Account.Model.AccountModel;
import com.TikiData.platform.Account.Repository.AccountRepository;
import com.TikiData.platform.User.Model.UserModel;
import com.TikiData.platform.User.Repository.UserRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountModel account = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

        String roleName = "ROLE_" + account.getRole().name();

        return new org.springframework.security.core.userdetails.User(
                account.getEmail(),
                account.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(roleName))
        );
    }
}
