package br.com.OrderTrack.Order.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@IdClass(UserProfileID.class)
public class UserProfile {

    @Id
    private Long userId;

    @Id
    private Long profileId;
}
