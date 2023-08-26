package com.ezeu.testing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezeu.testing.Entity.UserInfo;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer>{

    Optional<UserInfo> findByName(String username);

    Optional<UserInfo> findByEmail(String email);
}
