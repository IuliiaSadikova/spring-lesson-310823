package de.telran.g240123mbelesson331082023.domain.entity.jpa;

import de.telran.g240123mbelesson331082023.domain.entity.Basket;
import de.telran.g240123mbelesson331082023.domain.entity.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class JpaClient implements Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaProduct.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @Pattern(regexp = "[A-Z][a-z]*")
    private String name;
    @Column(name = "age")
//    @Min(1900)
//    @Max(2005)
    private int age;
    @Column(name = "email")
    @Email
    private String email;

    @OneToOne(mappedBy = "client")
    private JpaBasket basket;

    public JpaClient() {
//        LOGGER.info(String.format("Вызов конструктора JpaClient класса JpaClient без параметров"));
    }

    public JpaClient(int id, String name, JpaBasket basket) {
        this.id = id;
        this.name = name;
        this.basket = basket;
    }

    public JpaClient(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public JpaClient(int id, String name, Integer age) {
//        LOGGER.info(String.format("Вызов конструктора JpaClient класса JpaClient с параметрами - %d, %s, %d", id, name, age));
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public int getId() {
//        LOGGER.info(String.format("Вызван метод getId класса JpaClient"));
        return id;
    }

    @Override
    public String getName() {
//        LOGGER.info(String.format("Вызван метод getName класса JpaClient"));
        return name;
    }

    public int getAge() {
//        LOGGER.info(String.format("Вызван метод getAge класса JpaClient"));
        return LocalDate.now().getYear() - age;
    }

    @Override
    public Basket getBasket() {
//        LOGGER.info(String.format("Вызван метод getBasket класса JpaClient"));
        return basket;
    }

    public String getEmail() {
//        LOGGER.info(String.format("Вызван метод getEmail класса JpaClient"));
        return email;
    }


}
