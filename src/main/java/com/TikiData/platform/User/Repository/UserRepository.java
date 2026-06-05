package com.TikiData.platform.User.Repository;

import com.TikiData.platform.User.Model.Role;
import com.TikiData.platform.User.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByEmail(String email);
    Optional<UserModel> findByEmail(String email);

    @Query("SELECT u FROM UserModel u WHERE " +
            "(:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
            "(:role IS NULL OR u.role = :role)")
    List<UserModel> searchUsersByFilters(@Param("email") String email, @Param("role") Role role);
}
