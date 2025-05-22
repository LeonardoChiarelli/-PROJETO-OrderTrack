package br.com.OrderTrack.Order.domain.repository;

import br.com.OrderTrack.Order.domain.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(@NotBlank @Email String email);

    Optional<User> findByEmail(String email);
}
