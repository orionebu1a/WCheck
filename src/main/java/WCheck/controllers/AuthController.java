package WCheck.controllers;

import WCheck.dtos.JwtAuthenticationResponse;
import WCheck.dtos.SignInRequest;
import WCheck.dtos.SignUpRequest;
import WCheck.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest request) {
        try{
            String token = authenticationService.signUp(request);
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }
        catch (UsernameNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody @Valid SignInRequest request) {
        try{
            String token = authenticationService.signIn(request);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        catch (BadCredentialsException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
