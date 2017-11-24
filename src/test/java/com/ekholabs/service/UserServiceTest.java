package com.ekholabs.service;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ekholabs.model.Role;
import com.ekholabs.model.User;
import com.ekholabs.repository.UserRepo;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepo userRepo;
	
	@Before
	public void setUp() throws Exception {
		final Role role = new Role("Admin", "Y");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateIn = formatter.parse("2017-01-01");
		final User user = new User("Erika", "erikanbs@", "Admin", new java.sql.Date(dateIn.getTime()), role);

		Mockito.when(userRepo.findAllByFullNameIgnoreCase(user.getFullName())).thenReturn(Arrays.asList(user));
		Mockito.when(userRepo.findAll()).thenReturn(Arrays.asList(user));
	}
	
	@Test
	public void testFindAll() {
		final List<User> users = userService.findAll();
		assertEquals(1, users.size());
		assertEquals("Erika", users.get(0).getFullName());
	}
	
	@Test
	public void testFindByName() {
		final List<User> users = userService.findByFullName("Erika");
		assertEquals(1, users.size());
		assertEquals("Erika", users.get(0).getFullName());
	}	

}
