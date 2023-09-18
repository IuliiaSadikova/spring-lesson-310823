package de.telran.g240123mbelesson331082023.domain.database;

import de.telran.g240123mbelesson331082023.domain.entity.*;
import de.telran.g240123mbelesson331082023.domain.entity.common.CommonBasket;
import de.telran.g240123mbelesson331082023.domain.entity.common.CommonClient;
import de.telran.g240123mbelesson331082023.domain.entity.common.CommonProduct;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CommonDataBase implements DataBase{

    private List<Product> products = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private int idProductCounter = 5;
    private int idClientCounter = 3;

    public CommonDataBase() {
        products.add(new CommonProduct(1, "Bread", 1.2));
        products.add(new CommonProduct(2, "Milk", 1.0));
        products.add(new CommonProduct(3, "Butter", 2.3));
        products.add(new CommonProduct(4, "Cheese", 3.5));
        products.add(new CommonProduct(5, "Eggs", 2.9));
//        clients.add(new CommonClient(1, "John", new CommonBasket()));
//        clients.add(new CommonClient(2, "Paul", new CommonBasket()));
//        clients.add(new CommonClient(3, "Anna", new CommonBasket()));
    }

    @Override
    public void execute(String query) throws SQLException {
       // "Add new customer name = Vasya" - такие запросы выполняет метод execute
        // "Delete customer where id = 1" - такие запросы выполняет метод execute
        // "Add new product name = Milk price = 2.3"
        if (!query.startsWith("Add")&& !query.startsWith("Delete")) {
            throw new SQLException();
        }
        String[] parts = query.split(" ");
        if (parts[0].equals("Add")) {
            if (parts[2].equals("client")) {
                clients.add(new CommonClient(++idClientCounter, parts[5], new CommonBasket(), 1980));
            }
            if (parts[2].equals("product")) {
                products.add(new CommonProduct(++idProductCounter, parts[5], Double.parseDouble(parts[8])));
            }
        }
        if (parts[0].equals("Delete")) {
            if (parts[1].equals("client")) {
                clients.removeIf(x -> x.getId() == Integer.parseInt(parts[5]));
                Optional<Client> optional = clients.stream().filter(x -> x.getId() == Integer.parseInt(parts[5])).findFirst();
            }
            if (parts[1].equals("product")) {
                products.removeIf(x -> x.getId() == Integer.parseInt(parts[5]));
            }
        }
    }

    @Override
    public List<Object> select(String query) throws SQLException {
        // "Select all customers" - такие запросы выполняет метод select
        // "Select customer where id = 1" - такие запросы выполняет метод select
        if (!query.startsWith("Select")) {
            throw new SQLException();
        }

        String[] parts = query.split(" ");
        List<Object> result = new ArrayList<>();

        if (parts.length == 3) {
            if (parts[2].equals("clients")) {
                result.addAll(clients);
            }

            if (parts[2].equals("products")) {
                result.addAll(products);
            }
        }

        if (parts.length == 6) {
            if (parts[1].equals("client")) {
                Client client = clients.stream()
                        .filter(x -> x.getId() == Integer.parseInt(parts[5]))
                        .toList()
                        .get(0);
                result.add(client);
            }

            if (parts[1].equals("product")) {
                Product product = products.stream()
                        .filter(x -> x.getId() == Integer.parseInt(parts[5]))
                        .toList()
                        .get(0);
                result.add(product);
            }
        }
        return result;
    }
}
