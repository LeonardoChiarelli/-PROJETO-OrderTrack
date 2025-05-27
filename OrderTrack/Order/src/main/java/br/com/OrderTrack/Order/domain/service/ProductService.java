package br.com.OrderTrack.Order.domain.service;

import br.com.OrderTrack.Order.domain.dto.CreateProductDTO;
import br.com.OrderTrack.Order.domain.dto.ListOfProductsDTO;
import br.com.OrderTrack.Order.domain.model.Inventory;
import br.com.OrderTrack.Order.domain.model.Product;
import br.com.OrderTrack.Order.domain.repository.IInventoryRepository;
import br.com.OrderTrack.Order.domain.repository.IProductRepository;
import br.com.OrderTrack.Order.general.infra.exception.ValidationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private IProductRepository repository;

    @Autowired
    private IInventoryRepository inventoryRepository;

    public Product createProduct(@Valid CreateProductDTO dto) {
        var existingProduct = repository.existsByName(dto.name());

        // Strategy
        if (existingProduct) { throw new ValidationException("Product already existing."); }

        var product = new Product(dto);
        repository.save(product);

        var inventory = new Inventory(dto.initialInventory(), product);
        inventoryRepository.save(inventory);

        return product;
    }

    public Product changeProductStauts(Long id, Boolean status) {
        var product = repository.findById(id).orElseThrow(() -> new ValidationException("Product with id: '%d' wasn't find.".formatted(id)));

        // Strategy
        if (product.isActive() == status){
            if (!status) { throw new ValidationException("Product is already deactivated"); }
            throw new ValidationException("Product is already activated");
        }

        product.changeStatus(status);
        return product;
    }

    public Page<ListOfProductsDTO> listActivatedProducts(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(ListOfProductsDTO::new);
    }

    public Page<ListOfProductsDTO> listAllProducts(Pageable pageable) {
        return repository.findAll(pageable).map(ListOfProductsDTO::new);
    }

    public void deleteProduct(Long id) {
        repository.findById(id).orElseThrow(() -> new ValidationException("Product with id: '%d' wasn't find.".formatted(id)));
        repository.deleteById(id);
    }
}
