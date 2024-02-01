package WCheck;

import WCheck.controllers.LoginController;
import WCheck.controllers.RegistrationController;
import WCheck.dtos.LoginDTO;
import WCheck.services.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {
    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private LoginController loginController;

    @Test
    public void goodLoginTest() throws Exception{
        String name = "alex1327", password = "vpm2568";
        registrationController.registerUser(new LoginDTO(name, password));
        ResponseEntity<String> responseEntity = loginController.login(new LoginDTO(name, password));
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void badLoginTest() throws Exception{
        String name = "alex1327", password = "vpm2568", wrongPassword = "something";
        registrationController.registerUser(new LoginDTO(name, password));
        ResponseEntity<String> responseEntity = loginController.login(new LoginDTO(name, wrongPassword));
        //Assertions.assertEquals(responseEntity.getStatusCode(), .UNAUTHORIZED);
    }
}
