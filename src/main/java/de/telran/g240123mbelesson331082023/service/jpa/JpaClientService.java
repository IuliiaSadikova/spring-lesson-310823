package de.telran.g240123mbelesson331082023.service.jpa;

import de.telran.g240123mbelesson331082023.domain.entity.Client;
import de.telran.g240123mbelesson331082023.domain.entity.jpa.JpaBasket;
import de.telran.g240123mbelesson331082023.domain.entity.jpa.JpaClient;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaBasketRepository;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaClientRepository;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaProductRepository;
import de.telran.g240123mbelesson331082023.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaClientService implements ClientService {
    @Autowired
    private JpaClientRepository clientRepository;
    @Autowired
    private JpaProductRepository productRepository;
    @Autowired
    private JpaBasketRepository basketRepository;

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(clientRepository.findAll());
    }

    @Override
    public Client getById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void add(Client client) {
        JpaBasket basket = basketRepository.save(new JpaBasket());
        clientRepository.save(new JpaClient(0, client.getName(), basket));
    }

    @Override
    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        clientRepository.deleteByName(name);
    }

    @Override
    public int getCount() {
        return clientRepository.findAll().size();
    }

    @Override
    public double getTotalPriceById(int id) {
        return clientRepository.getTotalPriceById(id);
    }

    @Override
    public double getAveragePriceById(int id) {
        return clientRepository.getAveragePriceById(id);
    }

    @Override
    public void addToCartById(int customerId, int productId) {
        productRepository.findById(productId)
                .ifPresent(product -> clientRepository.findById(customerId)
                        .ifPresent(client -> {
                            client.getBasket().addProduct(product);
                            clientRepository.save(client);
                        }));
    }

    @Override
    public void deleteFromCart(int customerId, int productId) {
        productRepository.findById(productId)
                .ifPresent(product -> clientRepository.findById(customerId)
                        .ifPresent(client -> {
                            client.getBasket().getProducts().remove(product);
                            clientRepository.save(client);
                        }));
    }

    @Override
    public void clearCart(int customerId) {
        clientRepository.findById(customerId)
                .ifPresent(client -> {
                    client.getBasket().getProducts().clear();
                    clientRepository.save(client);
                });
    }
}