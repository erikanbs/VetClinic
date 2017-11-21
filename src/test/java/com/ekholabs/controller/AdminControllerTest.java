package com.ekholabs.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ekholabs.model.Role;
import com.ekholabs.model.User;
import com.ekholabs.repository.RoleRepo;
import com.ekholabs.repository.UserRepo;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
public class AdminControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	@Before
	public void setUp() throws Exception {
		final Role role = new Role("Admin", "Y");
		roleRepo.save(role);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateIn = formatter.parse("2017-01-01");
		final User user = new User("Erika", "erikanbs@", "Admin", new java.sql.Date(dateIn.getTime()), role);
		userRepo.save(user);
		
		//MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup(new AdminController()).build();
		//mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testAllUsers() throws Exception {
		//final UserDto userDto = new UserDto();
		//userDto.setFullName("Erika");
		//userDto.setEmail("erikanbs@");
		
		mockMvc.perform(get("/vet/admin/users/getAll"))
		.andExpect(status().isOk())
		//.andExpect(content().contentType(contentType))
		//.andExpect(jsonPath("$.id", is(1)))
		//.andExpect(jsonPath("$.fullName", is("Erika")))
		         ;
		
	}

}
