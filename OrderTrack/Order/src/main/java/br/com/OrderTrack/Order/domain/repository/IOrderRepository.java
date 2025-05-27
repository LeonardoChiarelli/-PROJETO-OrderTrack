package br.com.OrderTrack.Order.domain.repository;

import br.com.OrderTrack.Order.domain.mapper.SalesStatisticsMapperRecord;
import br.com.OrderTrack.Order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {

    @Query("""
            SELECT SUM(i.unitPrice * i.quantity)
            FROM Order o
            JOIN o.items i
            WHERE o.orderDate = :yestedayDate
            """)
    BigDecimal getTotalYesterdayBilling(LocalDate yesterdayDate);

    @Query("""
            SELECT NEW  br.com.OrderTrack.Order.domain.mapper.SalesStatisticsMapperRecord(
            product.category,
            SUM(i.quantity),
            SUM(i.unitPrice * i.quantity)
            )
            FROM Order o
            JOIN o.items i
            JOIN i.product product
            WHERE o.orderDate = :yesterdayDate
            GROUP BY product.category
""")
    List<SalesStatisticsMapperRecord> getTotalYesterdayBillingByCategory(LocalDate yesterdayDate);
}
