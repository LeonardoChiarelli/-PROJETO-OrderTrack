package br.com.OrderTrack.Order.domain.repository;

import br.com.OrderTrack.Order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
