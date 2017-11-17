package com.ekholabs.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ekholabs.dto.UserDto;
import com.ekholabs.model.Role;
import com.ekholabs.model.User;
import com.ekholabs.repository.RoleRepo;
import com.ekholabs.repository.UserRepo;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AdminControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@InjectMocks
	private AdminController adminController;
	
	//@Before
	public void setUp() throws Exception {
		final Role role = roleRepo.findById(1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateIn = formatter.parse("2017-01-01");
		final User user = new User("Erika", "erikanbs@", "Admin", new java.sql.Date(dateIn.getTime()), role);
		userRepo.save(user);
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	}
	
	@Test
	public void testAllUsers() throws Exception {
		//final UserDto userDto = new UserDto();
		//userDto.setFullName("Erika");
		//userDto.setEmail("erikanbs@");
		
		mockMvc.perform(get("localhost:8080/vet/admin/users/getAll")
				).andExpect(status().isOk())
		         ;
		
	}

}
