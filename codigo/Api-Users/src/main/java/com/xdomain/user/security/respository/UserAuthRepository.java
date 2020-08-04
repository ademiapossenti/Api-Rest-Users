package com.xdomain.user.security.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xdomain.user.security.entities.UserAuthEntity;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Long>{

	
	UserAuthEntity findByUsername(String username);
	
}
