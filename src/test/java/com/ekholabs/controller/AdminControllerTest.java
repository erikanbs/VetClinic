package com.ekholabs.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ekholabs.dto.UserDto;
import com.ekholabs.model.Role;
import com.ekholabs.model.User;
import com.ekholabs.service.RoleService;
import com.ekholabs.service.UserService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class AdminControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private AdminController adminController;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void testAllUsers() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateIn = formatter.parse("2017-01-01");
        final Role role = new Role("Admin", "Y");
        final User user = new User("Erika", "erikanbs@", "Admin", new java.sql.Date(dateIn.getTime()), role);
        final List<User> users = Arrays.asList(user);

        final UserDto userDto = new UserDto();
        userDto.setFullName("Erika");
        userDto.setEmail("erikanbs@");

        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/vet/admin/users/getAll")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$[0].fullName", is("Erika")))
                .andExpect(jsonPath("$[0].email", is("erikanbs@"))).andDo(print());

        verify(userService, times(1)).findAll();
    }

}