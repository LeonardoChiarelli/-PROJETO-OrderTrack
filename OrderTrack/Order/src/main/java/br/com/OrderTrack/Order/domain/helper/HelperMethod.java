package br.com.OrderTrack.Order.domain.helper;

import br.com.OrderTrack.Order.domain.model.OrderItem;
import br.com.OrderTrack.Order.domain.model.Product;
import br.com.OrderTrack.Order.domain.repository.IOrderRepository;
import br.com.OrderTrack.Order.domain.repository.IProductRepository;
import br.com.OrderTrack.Order.general.infra.exception.ValidationException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HelperMethod {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductRepository productRepository;

    public static IOrderRepository orderRepo;
    public static IProductRepository productRepo;

    public static Product loadProductsByName(String productName){
        return productRepo.findByName(productName).orElseThrow(() -> new ValidationException("Product not found"));
    }

    @PostConstruct
    public void init(){
        HelperMethod.orderRepo = orderRepository;
        HelperMethod.productRepo = productRepository;
    }
}
