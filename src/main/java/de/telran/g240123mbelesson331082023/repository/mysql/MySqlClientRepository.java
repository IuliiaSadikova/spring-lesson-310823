package de.telran.g240123mbelesson331082023.repository.mysql;

import de.telran.g240123mbelesson331082023.domain.entity.Basket;
import de.telran.g240123mbelesson331082023.domain.entity.Client;

import de.telran.g240123mbelesson331082023.domain.entity.common.CommonBasket;
import de.telran.g240123mbelesson331082023.domain.entity.common.CommonClient;
import de.telran.g240123mbelesson331082023.domain.entity.Product;
import de.telran.g240123mbelesson331082023.domain.entity.common.CommonProduct;
import de.telran.g240123mbelesson331082023.repository.ClientRepository;
import org.springframework.stereotype.Repository;
import static de.telran.g240123mbelesson331082023.domain.database.MySqlConnector.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MySqlClientRepository implements ClientRepository {

    @Override
    public List<Client> getAll() {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM customer as c left join customer_product as cp on c.id = cp.customer_id left join product as p on cp.product_id = p.id;";
            // Создать список клиентов и наполнить корзины каждого клиента их товарами
            // Учесть момент, что у клиента вообще может не быть никаких товаров,
            // в таком случае корзина просто должна быть пустая.
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            Map<Integer, CommonClient> clients = new HashMap<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                CommonClient client = clients.get(id);
                if (client == null) {
                    client = new CommonClient();
                    client.setId(id);
                    client.setName(name);
                    clients.put(id, client);
                }
                Basket basket = client.getBasket();
                if (basket == null) {
                    basket = new CommonBasket();
                    client.setBasket(basket);
                }
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("p.name");
                double price = resultSet.getDouble("price");
                basket.addProduct(new CommonProduct(productId, productName, price));
            }
            return new ArrayList<>(clients.values());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client getById(int id) {
        try (Connection connection = getConnection()) {
            String query = String.format("SELECT * FROM customer as c left join customer_product as cp on c.id = cp.customer_id left join product as p on cp.product_id = p.id where c.id = %d;", id);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            CommonClient client = null;
            while (resultSet.next()) {
                if (client == null) {
                    String name = resultSet.getString("c.name");
                    client = new CommonClient(id, name, new CommonBasket());
                }
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("p.name");
                double price = resultSet.getDouble("price");
                Basket basket = client.getBasket();
                basket.addProduct(new CommonProduct(productId, productName, price));
            }
            return client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String name) {
        try (Connection connection = getConnection()) {
            String query = String.format("INSERT INTO `customer` (`name`) VALUES ('%s');", name);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `customer` WHERE (`id` = '%d');", id);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToCartById(int customerId, int productId) {
        try (Connection connection = getConnection()) {
            String query = String.format("INSERT INTO `customer_product` (`customer_id`, `product_id`) VALUES ('%d', '%d');", customerId, productId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFromCart(int customerId, int productId) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `customer_product` WHERE (`customer_id` = '%d' and `product_id` = '%d');", customerId, productId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearCart(int customerId) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `customer_product` WHERE (`customer_id` = '%d');", customerId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
