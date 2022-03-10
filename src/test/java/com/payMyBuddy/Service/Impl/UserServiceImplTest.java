package com.payMyBuddy.Service.Impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.payMyBuddy.DAO.UserAppRespository;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Models.dto.UserDto;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
	@Mock
	private UserAppRespository userRepository;
	@InjectMocks
	private UserServiceImpl userService = new UserServiceImpl();

	UserApp USER_RECORD_1;
	UserApp USER_RECORD_2;

	@BeforeEach
	void setUp() throws Exception {
		USER_RECORD_1 = UserApp.builder().userName("user1").email("user1@email.com").password("123456").build();

	}

	@Test
	void testFindAll() {
		List<UserApp> userList = new ArrayList<>();
		UserApp userOne = new UserApp("user1", "user1@email.com", "123456");
		UserApp userTwo = new UserApp("user2", "user2@email.com", "123456");
		UserApp userThree = new UserApp("user3", "user3@email.com", "123456");
		userList.add(userOne);
		userList.add(userTwo);
		userList.add(userThree);

		when(userRepository.findAll()).thenReturn(userList);

		List<UserApp> userListService = userService.findAll();
		assertEquals(3, userListService.size());
		verify(userRepository, times(1)).findAll();
	}

	@Test
	void testFindById() {

		UserApp user = new UserApp("user3", "user3@email.com", "123456");
		user.setId(1);

		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		Optional<UserApp> userOptService = userService.findById(1);
		verify(userRepository, times(1)).findById(Mockito.anyInt());

		assertNotNull(userOptService);
		UserApp userOptget = userOptService.get();
		assertEquals(user.getUserName(), userOptget.getUserName());
		assertEquals(user.getEmail(), userOptget.getEmail());
		assertEquals(user.getPassword(), userOptget.getPassword());

	}

	@Test
	void testFindByEmail() {
		String emailTest = "user3@email.com";
		UserApp userFake = new UserApp();
		userFake.setEmail(emailTest);

		when(userRepository.findByEmail(emailTest)).thenReturn(userFake);

		UserApp user = userService.findByEmail(emailTest);
		
		assertThat(user).isEqualTo(userFake);
		verify(userRepository).findByEmail(emailTest);
			

	}

	@Test
	void testDeleteById() {
		userService.deleteById(USER_RECORD_1.getId());
	
		verify(userRepository,times(1)).deleteById(Mockito.any());
	}

	@Test
	void testDeleteByEmail() {

	}

	@Test
	void testSave() {
		UserDto userDto = UserDto.builder().userName("user1").email("user1@email.com").password("123456").build();
		Mockito.when(userRepository.save(Mockito.any(UserApp.class))).thenReturn(USER_RECORD_1);
		UserApp userSaved = userService.save(userDto);
		assertNotNull(userSaved);
		assertEquals(userSaved.getUserName(), "user1");
		assertEquals(userSaved.getEmail(), "user1@email.com");
	}

}
