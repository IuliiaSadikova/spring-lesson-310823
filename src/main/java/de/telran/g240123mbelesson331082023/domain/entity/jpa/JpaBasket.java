package de.telran.g240123mbelesson331082023.domain.entity.jpa;

import de.telran.g240123mbelesson331082023.domain.entity.Basket;
import de.telran.g240123mbelesson331082023.domain.entity.Product;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class JpaBasket implements Basket {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaBasket.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private JpaClient client;

    public void setProducts(List<JpaProduct> products) {
        this.products = products;
    }

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<JpaProduct> products;

    public JpaBasket(JpaClient client) {
//        LOGGER.info("Вызов конструктора JpaBasket класса JpaBasket с параметрами - {}", client);
        this.client = client;
    }

    public JpaBasket() {
//        LOGGER.info("Вызов конструктора JpaBasket класса JpaBasket без параметров");
    }

    @Override
    public double getTotalCost() {
//        LOGGER.info("Вызван метод getTotalCost класса JpaBasket");
        return products.stream()
                .mapToDouble(x -> x.getPrice())
                .sum();
    }

    @Override
    public void addProduct(Product product) {
        LOGGER.info("Вызван метод addProduct класса JpaBasket с параметрами - {}", product);
        products.add(new JpaProduct(product.getId(), product.getName(), product.getPrice()));
    }

    @Override
    public List<Product> getProducts() {
//        LOGGER.info("Вызван метод getProducts класса JpaBasket");
        return new ArrayList<>(products);
    }
}
