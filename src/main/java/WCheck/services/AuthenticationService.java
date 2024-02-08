package WCheck.services;

import WCheck.dtos.JwtAuthenticationResponse;
import WCheck.dtos.SignInRequest;
import WCheck.dtos.SignUpRequest;
import WCheck.entities.UserName;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public String signUp(SignUpRequest request) {

        UserName newUser = new UserName(request.getUsername(), passwordEncoder.encode(request.getPassword()));

        Optional<UserName> existingUser = userService.getUserByUsername(request.getUsername());

        if (existingUser.isPresent()) {
            throw new UsernameNotFoundException("User exist");
        }

        userService.createUser(newUser);

        var jwt = jwtService.generateToken(newUser);

        return jwt;
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public String signIn(SignInRequest request) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return jwt;
    }
}
