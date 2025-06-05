package br.com.OrderTrack.Payment.domain.repository;

import br.com.OrderTrack.Payment.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
