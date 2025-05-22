package br.com.OrderTrack.Order.domain.model;

import br.com.OrderTrack.Order.domain.dto.SignUpDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String  email;
    private String password;

    @ManyToMany
    @JoinTable(name = "users_profiles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private List<Profile> profiles = new ArrayList<>();

    public User(@Valid SignUpDTO dto, String password, Profile profile) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = password;
        this.profiles = List.of(profile);
    }

    public Boolean isAdmin() { return this.profiles.stream().anyMatch(Profile::isAdmin); }
    public Boolean isConsumer() { return this.profiles.stream().anyMatch(Profile::isConsumer); }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
