package com.TikiData.platform.Account.Model;

import com.TikiData.platform.User.Model.Role;
import com.TikiData.platform.User.Model.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private UserModel userProfile;
}
