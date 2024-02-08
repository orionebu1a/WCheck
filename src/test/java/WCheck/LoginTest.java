package WCheck;

import WCheck.controllers.AuthController;
import WCheck.dtos.JwtAuthenticationResponse;
import WCheck.dtos.SignInRequest;
import WCheck.dtos.SignUpRequest;
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
    private AuthController authController;

    @Test
    public void goodLoginTest() throws Exception{
        String name = "alex1327", password = "vpm2568";
        authController.signUp(new SignUpRequest(name, password));
        ResponseEntity<String> responseEntity = authController.signIn(new SignInRequest(name, password));
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void badLoginTest() throws Exception{
        String name = "alex1327", password = "vpm2568", wrongPassword = "something";
        authController.signUp(new SignUpRequest(name, password));
        ResponseEntity<String> responseEntity = authController.signIn(new SignInRequest(name, wrongPassword));
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
