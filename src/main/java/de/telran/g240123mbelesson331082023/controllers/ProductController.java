package de.telran.g240123mbelesson331082023.controllers;

import de.telran.g240123mbelesson331082023.domain.entity.Client;
import de.telran.g240123mbelesson331082023.domain.entity.CommonClient;
import de.telran.g240123mbelesson331082023.domain.entity.CommonProduct;
import de.telran.g240123mbelesson331082023.domain.entity.Product;
import de.telran.g240123mbelesson331082023.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
class ProductController {

    @Autowired
    private ProductService service;

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
        return service.getTotalPrice() / service.getCount();
    }

}
