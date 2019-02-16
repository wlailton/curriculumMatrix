package com.wlailton.curriculumMatrixapi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wlailton.curriculumMatrixapi.enums.UserTypeEnum;
import com.wlailton.curriculumMatrixapi.exception.UserNotFoundException;
import com.wlailton.curriculumMatrixapi.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void whenFindByIdThenReturnUser() {
		User user = new User();
		user.setName("Test");
		user.setPassword("test");
		user.setEmail("teste@xpto.com");
		user.setUserType(UserTypeEnum.PROFESSOR);
	
		entityManager.persist(user);
		entityManager.flush();

		User userFound = userRepository.findById(user.getId())
				.orElseThrow(() -> new UserNotFoundException(user.getId().toString()));

		assertThat(userFound.getName()).isEqualTo(user.getName());
		assertThat(userFound.getPassword()).isEqualTo(userFound.getPassword());
		assertThat(userFound.getEmail()).isEqualTo(userFound.getEmail());
		assertThat(userFound.getUserType()).isEqualTo(userFound.getUserType());
		
	}

}
