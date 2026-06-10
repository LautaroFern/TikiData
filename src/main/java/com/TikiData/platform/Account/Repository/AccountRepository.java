package com.TikiData.platform.Account.Repository;

import com.TikiData.platform.Account.Model.AccountModel;
import com.TikiData.platform.User.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {
    boolean existsByEmail(String email);
    Optional<AccountModel> findByEmail(String email);

    @Query("SELECT a FROM AccountModel a WHERE " +
            "(:email IS NULL OR LOWER(a.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
            "(:role IS NULL OR a.role = :role)")
    List<AccountModel> searchAccountsByFilters(@Param("email") String email, @Param("role") Role role);
}