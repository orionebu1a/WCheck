package WCheck.repos;

import WCheck.entities.UserName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserName, Long> {
    UserName findByUsername(String username);
}
