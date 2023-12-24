package WCheck.repos;

import WCheck.entities.Location;
import WCheck.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
