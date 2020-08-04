package com.xdomain.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xdomain.user.entities.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, UUID>{

	UserEntity findByEmail(String mail);
}
