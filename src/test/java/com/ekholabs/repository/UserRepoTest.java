package com.ekholabs.repository;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ekholabs.model.Role;
import com.ekholabs.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepoTest {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Before
	public void setUp() throws Exception {
		final Role role = new Role("Admin", "Y");
		roleRepo.save(role);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateIn = formatter.parse("2017-01-01");
		final User user = new User("Erika", "erikanbs@", "Admin", new java.sql.Date(dateIn.getTime()), role);
		userRepo.save(user);
		int id = user.getId();
		System.out.println("id"+id);
	}
	
	@Test
	public void findAll() {
		assertEquals("Erika", userRepo.findAll().get(0).getFullName());
		assertEquals(1, userRepo.findAll().size());
	}
	
	@Test
	public void findByName() {
		assertEquals("Erika", userRepo.findAllByFullNameIgnoreCase("ERIKA").get(0).getFullName());
		assertEquals(1, userRepo.findAllByFullNameIgnoreCase("ERIKA").size());
	}
	
	@Test
	public void updateName() {
		final User user = userRepo.findAllByFullNameIgnoreCase("Erika").get(0);
		user.setFullName("Maria");
		userRepo.save(user);
		
		assertEquals("Maria", user.getFullName());
	}

}
