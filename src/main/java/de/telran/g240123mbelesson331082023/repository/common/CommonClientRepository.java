package de.telran.g240123mbelesson331082023.repository.common;

import de.telran.g240123mbelesson331082023.domain.database.DataBase;
import de.telran.g240123mbelesson331082023.domain.entity.Client;
import de.telran.g240123mbelesson331082023.domain.entity.Product;
import de.telran.g240123mbelesson331082023.repository.ClientRepository;
import de.telran.g240123mbelesson331082023.repository.mysql.MySqlProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class CommonClientRepository implements ClientRepository {
    @Autowired
    private DataBase dataBase;

    @Autowired
    private MySqlProductRepository productRepository;

    @Override
    public List<Client> getAll() {
        try {
            return dataBase.select("Select all clients").stream().map(x -> (Client) x).toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client getById(int id) {
        try {
            return (Client) dataBase.select("Select client where id = " + id).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String name) {
        try {
            dataBase.execute("Add new client name = " + name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            dataBase.execute("Delete client where id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToCartById(int clientId, int productId) {
        Client client = getById(clientId);
        Product product = productRepository.getById(productId);
        client.getBasket().addProduct(product);
    }

    @Override
    public void deleteFromCart(int clientId, int productId) {
        Client client = getById(clientId);
        client.getBasket().getProducts().removeIf(x -> x.getId() == productId);
    }

    @Override
    public void clearCart(int clientId) {
        Client client = getById(clientId);
        client.getBasket().getProducts().clear();
    }
}
