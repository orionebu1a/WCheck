package WCheck.services;

import WCheck.entities.Role;
import WCheck.entities.UserName;
import WCheck.repos.RoleRepository;
import WCheck.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService{
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    public UserName createUser(UserName userName) {
        return userRepository.save(userName);
    }

    public UserName getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public UserName loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserName> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.get();
    }

    public Optional<UserName> getUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserDetailsService userDetailsService() {
        return this;
    }

    @Transactional
    public UserName saveUserNAme(UserName userName) {
        return userRepository.save(userName);
    }
}

