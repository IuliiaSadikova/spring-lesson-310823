package de.telran.g240123mbelesson331082023.domain.entity.jpa;

import de.telran.g240123mbelesson331082023.domain.entity.Basket;
import de.telran.g240123mbelesson331082023.domain.entity.Client;
import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class JpaClient implements Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "client")
    private JpaBasket basket;

    public JpaClient() {
    }

    public JpaClient(int id, String name, JpaBasket basket) {
        this.id = id;
        this.name = name;
        this.basket = basket;
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
    public Basket getBasket() {
        return basket;
    }
}
