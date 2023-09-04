package de.telran.g240123mbelesson331082023.repository;

import de.telran.g240123mbelesson331082023.domain.entity.Client;

import java.util.List;

public interface ClientRepository {

    List<Client> getAll();

    Client getById(int id);

    void add(String name);

    void delete(int id);

    void addToCartById(int customerId, int productId);

    void deleteFromCart(int customerId, int productId);

    void clearCart(int customerId);



//    Client findById(int id);
//    List<Client> getClients();
//    void deleteById(int id);
//    void addClient(String name);


}
