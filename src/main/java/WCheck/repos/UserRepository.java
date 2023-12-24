package WCheck.repos;

import WCheck.entities.UserName;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserName, Long> {
}
