package de.telran.g240123mbelesson331082023.controllers;

import de.telran.g240123mbelesson331082023.domain.entity.common.CommonProduct;
import de.telran.g240123mbelesson331082023.domain.entity.Product;
import de.telran.g240123mbelesson331082023.service.ProductService;
import de.telran.g240123mbelesson331082023.service.jpa.JpaProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
class ProductController {

    @Autowired
    private JpaProductService service;

    @GetMapping
    List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{productId}")
    Product getById(@PathVariable int productId){
        return service.getById(productId);
    }

    @PostMapping
    void addProduct(@RequestBody CommonProduct product) {
        service.add(product);
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
