package com.xdomain.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xdomain.user.entities.UserEntity;
import com.xdomain.user.test.util.Constants;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void whenfindByEmail_thenReturnUser() {
		
		//when
		UserEntity found = userRepository.findByEmail(Constants.USER_TEST_EMAIL);
		
		//then
		assertThat(found.getEmail()).isEqualTo(Constants.USER_TEST_EMAIL);
	}
}
