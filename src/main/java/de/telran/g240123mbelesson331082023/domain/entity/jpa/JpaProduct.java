package de.telran.g240123mbelesson331082023.domain.entity.jpa;

import de.telran.g240123mbelesson331082023.domain.entity.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class JpaProduct implements Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // бд сама будет назначать id для новый сущностей
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;

    public JpaProduct() {  // обязательно создать пустой конструктор
    }

    public JpaProduct(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
