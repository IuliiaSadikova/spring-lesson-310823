package de.telran.g240123mbelesson331082023.repository.mysql;

import de.telran.g240123mbelesson331082023.domain.entity.common.CommonProduct;
import de.telran.g240123mbelesson331082023.domain.entity.Product;
import de.telran.g240123mbelesson331082023.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static de.telran.g240123mbelesson331082023.domain.database.MySqlConnector.getConnection;
@Repository
public class MySqlProductRepository implements ProductRepository {
    @Override
    public List<Product> getAll() {
        try (Connection connection = getConnection()) {
            String query = "select * from product;";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            List<Product> result = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1); // либо передать "id"
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                result.add(new CommonProduct(id, name, price));
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getById(int id) {
        try (Connection connection = getConnection()) {
            String query = String.format("select * from product where id = %d;", id);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            resultSet.next();
            String name = resultSet.getString(2);
            double price = resultSet.getDouble(3);
            return new CommonProduct(id, name, price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String name, double price) {
        try (Connection connection = getConnection()) {
            String query = String.format(Locale.US, "INSERT INTO `product` (`name`, `price`) " +
                    "VALUES ('%s', '%.2f');", name, price);
            connection.createStatement().execute(query);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `product` WHERE (`id` = '7');", id);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
