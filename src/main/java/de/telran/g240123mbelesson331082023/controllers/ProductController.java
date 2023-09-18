package de.telran.g240123mbelesson331082023.controllers;

import de.telran.g240123mbelesson331082023.domain.entity.common.CommonProduct;
import de.telran.g240123mbelesson331082023.domain.entity.Product;
import de.telran.g240123mbelesson331082023.domain.entity.jpa.JpaClient;
import de.telran.g240123mbelesson331082023.domain.entity.jpa.JpaProduct;
import de.telran.g240123mbelesson331082023.exception_layer.Response;
import de.telran.g240123mbelesson331082023.exception_layer.exceptions.EntityValidationException;
import de.telran.g240123mbelesson331082023.exception_layer.exceptions.FirstTestException;
import de.telran.g240123mbelesson331082023.exception_layer.exceptions.ThirdTestException;
import de.telran.g240123mbelesson331082023.service.ProductService;
import de.telran.g240123mbelesson331082023.service.jpa.JpaProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements Controller{

    @Autowired
    private JpaProductService service;

    @GetMapping
    List<Product> getAll() {
        //service.test(new JpaProduct(0, "Test Name", 100));
        List<Product> products = service.getAll();
        if (products.size() == 0) {
            throw new ThirdTestException("Список продуктов пуст!");
        }
        return products;
    }

    @GetMapping("/{productId}")
    Product getById(@PathVariable int productId){
        Product product = service.getById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Продукт не найден");
        }
        return product;
    }

    @PostMapping
    public Product addProduct(@Valid @RequestBody CommonProduct product) {
        try {
            return service.add(product);
        } catch (Exception e) {
            throw new EntityValidationException(e.getMessage());
        }
    }

    @DeleteMapping("/{productId}")
    void deleteById(@PathVariable int productId) {
        service.deleteById(productId);
    }

    @DeleteMapping(params = "name")
    void deleteByName(@RequestParam String name) {
        service.deleteByName(name);
    }

    @GetMapping("/count")
    int getCount() {
        return service.getCount();
    }

    @GetMapping("total-price")
    double getTotalPrice() {
        return service.getTotalPrice();
    }

    @GetMapping("avg-price")
    double getAveragePrice() {
        return service.getAveragePrice();
    }


}
