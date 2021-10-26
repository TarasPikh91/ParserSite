package customertimes.springParserDb.dao;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "users_roles",
//            joinColumns = @JoinColumn(name="users_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles = new HashSet<Role>();

//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
    @Column(name="roles")
//    @Enumerated(EnumType.STRING)
    private Role roles;
//    private Set<Role> roles = new HashSet<Role>();

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    //    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(final Set<Role> roles) {
//        this.roles = roles;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
