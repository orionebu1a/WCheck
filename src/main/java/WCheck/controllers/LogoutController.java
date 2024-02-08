package WCheck.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/logout")
public class LogoutController {

    @PostMapping
    public String logout() {
        SecurityContextHolder.clearContext();
        return "You have successfully logged out.";
    }
}
