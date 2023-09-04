package de.telran.g240123mbelesson331082023.service;

import de.telran.g240123mbelesson331082023.domain.entity.Basket;
import de.telran.g240123mbelesson331082023.domain.entity.Client;
import de.telran.g240123mbelesson331082023.domain.entity.Product;
import de.telran.g240123mbelesson331082023.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonClientService implements ClientService{

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ProductService productService;


    @Override
    public List<Client> getAll() {
        return repository.getAll();
    }

    @Override
    public Client getById(int id) {
        return repository.getById(id);
    }

    @Override
    public void add(Client client) {
        repository.add(client.getName());
    }

    @Override
    public void deleteById(int id) {
        repository.delete(id);
    }

    @Override
    public void deleteByName(String name) {
        int idToDelete = repository.getAll().stream().filter(x -> x.getName().equals(name)).findFirst().get().getId();
        repository.delete(idToDelete);
    }

    @Override
    public int getCount() {
        return repository.getAll().size();
    }

    @Override
    public double getTotalPriceById(int id) {
        return repository.getById(id).getBasket().getTotalCost();
    }

    @Override
    public double getAveragePriceById(int id) {
        Basket cart = repository.getById(id).getBasket();
        return cart.getTotalCost() / cart.getProducts().size();
    }

    @Override
    public void addToCartById(int clientId, int productId) {
        repository.addToCartById(clientId, productId);
    }

    @Override
    public void deleteFromCart(int clientId, int productId) {
        repository.deleteFromCart(clientId, productId);
    }

    @Override
    public void clearCart(int clientId) {
        repository.clearCart(clientId);
    }

}
