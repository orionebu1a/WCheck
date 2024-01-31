package WCheck;

import WCheck.controllers.RegistrationController;
import WCheck.dtos.LoginDTO;
import WCheck.entities.UserName;
import WCheck.services.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@Sql(value = {"classpath:before-register.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RegisterTest {
    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void registerTest() throws Exception{
        String name = "alex1327", password = "vpm2568";
        registrationController.registerUser(new LoginDTO(name, password));
        UserName userName = userService.loadUserByUsername(name);
        Assertions.assertEquals(userName.getUsername(), name);
        Assertions.assertTrue(encoder.matches(password, userName.getPassword()));
    }

    @Test
    public void multiRegisterTest() throws Exception{
        String name1 = "alex1327", password1 = "vpm2568";
        String password2 = "mmmmmmm";
        ResponseEntity<String> responseEntity1 = registrationController.registerUser(new LoginDTO(name1, password1));
        ResponseEntity<String> responseEntity2 = registrationController.registerUser(new LoginDTO(name1, password2));
        UserName userName = userService.loadUserByUsername(name1);
        Assertions.assertEquals(userName.getUsername(), name1);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity1.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity2.getStatusCode());
    }

    @Test
    public void falseRegisterTest() throws Exception{
    }
}


