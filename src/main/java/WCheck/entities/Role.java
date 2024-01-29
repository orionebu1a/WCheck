package WCheck.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;


import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<UserName> users;
    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserName> getUsers() {
        return users;
    }

    public void setUsers(Set<UserName> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
