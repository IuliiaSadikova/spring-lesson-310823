package de.telran.g240123mbelesson331082023.controllers;

import de.telran.g240123mbelesson331082023.domain.entity.Client;
import de.telran.g240123mbelesson331082023.domain.entity.common.CommonClient;
import de.telran.g240123mbelesson331082023.service.ClientService;
import de.telran.g240123mbelesson331082023.service.jpa.JpaClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private JpaClientService service;

    @GetMapping
    List<Client> getAll() {
        return service.getAll();
    }

    @GetMapping("/{clientId}")
    Client getById(@PathVariable int clientId){
        return service.getById(clientId);
    }

    @PostMapping
    public void addClient(@RequestBody CommonClient client) {
        service.add(client);
    }

    @DeleteMapping("/{clientId}")
    public void deleteById(@PathVariable int clientId) {
        service.deleteById(clientId);
    }

    @DeleteMapping(params = "name")
    public void deleteByName(@RequestParam String name) {
        service.deleteByName(name);
    }

    @GetMapping("/count")
    public int getCount() {
        return service.getCount();
    }

    @GetMapping("/{clientId}/cart/total-price")
    public double getTotalPriceById(@PathVariable int clientId) {
        return service.getTotalPriceById(clientId);
    }

    @GetMapping("/{clientId}/cart/avg-price")
    public double getAveragePriceById(@PathVariable int clientId) {
        return service.getAveragePriceById(clientId);
    }

    @PostMapping("/{clientId}/cart/product/{productId}")
    public void addToCartById(@PathVariable int clientId, @PathVariable int productId) {
        service.addToCartById(clientId, productId);
    }

    @DeleteMapping("/{clientId}/cart/product/{productId}")
    public void deleteFromCart(@PathVariable int clientId, @PathVariable int productId) {
        service.deleteFromCart(clientId, productId);
    }

    @DeleteMapping("/{clientId}/cart")
    public void clearCart(@PathVariable int clientId) {
        service.clearCart(clientId);
    }
}
