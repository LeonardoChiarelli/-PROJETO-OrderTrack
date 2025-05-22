package br.com.OrderTrack.Order.domain.repository;

import br.com.OrderTrack.Order.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByName(String roleConsumer);
}
