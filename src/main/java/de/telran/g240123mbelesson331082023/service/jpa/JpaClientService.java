package de.telran.g240123mbelesson331082023.service.jpa;

import de.telran.g240123mbelesson331082023.domain.entity.Basket;
import de.telran.g240123mbelesson331082023.domain.entity.Client;
import de.telran.g240123mbelesson331082023.domain.entity.Product;
import de.telran.g240123mbelesson331082023.domain.entity.jpa.JpaBasket;
import de.telran.g240123mbelesson331082023.domain.entity.jpa.JpaClient;
import de.telran.g240123mbelesson331082023.domain.entity.jpa.JpaProduct;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaBasketRepository;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaClientRepository;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaProductRepository;
import de.telran.g240123mbelesson331082023.service.ClientService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JpaClientService implements ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaClientService.class);

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
//        LOGGER.info(String.format("INFO запрошен клиент с id %d.", id));
//        LOGGER.warn(String.format("WARN запрошен клиент с id %d.", id));
//        LOGGER.error(String.format("ERROR запрошен клиент с id %d.", id));
        return clientRepository.findById(id).orElse(null);
    }

    public int getAgeById(int id) {
        return clientRepository.findById(id).get().getAge();
    }

    @Override
    public void add(Client client) {
        JpaClient newClient = clientRepository.save(new JpaClient(0, client.getName(), client.getAge()));
        basketRepository.save(new JpaBasket(newClient));

    }

    @Override
    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        clientRepository.findJpaClientByName(name).ifPresent(client -> {
            JpaBasket basket = (JpaBasket) client.getBasket();
            basketRepository.delete(basket);
            clientRepository.delete(client);
        });

    }

    @Override
    public int getCount() {
        return (int) clientRepository.count();
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
        clientRepository.findById(customerId)
                .ifPresent(client -> {
                    JpaBasket basket = (JpaBasket) client.getBasket();
                    List<JpaProduct> products = basket.getProducts().stream()
                            .map(p -> (JpaProduct) p)
                            .filter(p -> p.getId() != productId)
                            .toList();
                    basket.setProducts(new ArrayList<>(products));
                    basketRepository.save(basket);
                });
    }

    @Override
    public void clearCart(int customerId) {
        clientRepository.findById(customerId)
                .ifPresent(client -> {
                    JpaBasket basket = (JpaBasket) client.getBasket();
                    basket.setProducts(new ArrayList<>());
                    basketRepository.save(basket);
                });
    }
}
