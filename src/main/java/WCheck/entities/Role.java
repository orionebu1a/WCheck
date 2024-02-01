package WCheck.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role implements GrantedAuthority {
    @Id
    private Long id;

    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}
