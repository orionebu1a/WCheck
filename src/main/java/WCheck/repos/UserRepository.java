package WCheck.repos;

import WCheck.entities.UserName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserName, Long> {
    Optional<UserName> findByUsername(String username);
}
