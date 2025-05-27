package br.com.OrderTrack.Order.domain.service;

import br.com.OrderTrack.Order.domain.mapper.InventoryBillingMapperRecord;
import br.com.OrderTrack.Order.domain.mapper.InventoryReportMapperRecord;
import br.com.OrderTrack.Order.domain.mapper.ProductMapperRecord;
import br.com.OrderTrack.Order.domain.repository.IInventoryRepository;
import br.com.OrderTrack.Order.domain.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportService {

    @Autowired
    private IInventoryRepository inventoryRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Async
    public CompletableFuture<InventoryReportMapperRecord> inventoryData(Pageable pageable) {

        var products = inventoryRepository.findAllProducts(pageable).map(ProductMapperRecord::new);
        var inventoryReport = new InventoryReportMapperRecord(products);

        return CompletableFuture.completedFuture(inventoryReport);
    }

    @Async
    public CompletableFuture<InventoryBillingMapperRecord> getBilling() {
        var yesterdayDate = LocalDate.now().minusDays(1);
        var totalBilling = orderRepository.getTotalYesterdayBilling(yesterdayDate);

        var statistics = orderRepository.getTotalYesterdayBillingByCategory(yesterdayDate);

        return CompletableFuture.completedFuture(new InventoryBillingMapperRecord(totalBilling, statistics));
    }
}
