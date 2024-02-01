package WCheck.controllers;

import WCheck.configs.CustomAuthenticationManager;
import WCheck.configs.JwtTokenUtil;
import WCheck.dtos.LoginDTO;
import WCheck.dtos.TokenDTO;
import WCheck.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            authenticate(loginDTO.getUserName(), loginDTO.getPassword());
            final UserDetails userDetails = userService.loadUserByUsername(loginDTO.getUserName());
            final String token = jwtTokenUtil.generateToken(userDetails);
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            return new ResponseEntity<>(tokenDTO.getToken(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }


    private void authenticate(String userName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (Exception e) {
            throw new Exception("Wrong UserName and Password");
        }
    }
}
