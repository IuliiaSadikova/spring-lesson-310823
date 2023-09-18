package de.telran.g240123mbelesson331082023.domain.entity.jpa;

import de.telran.g240123mbelesson331082023.domain.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "product")
public class JpaProduct implements Product {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaProduct.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // бд сама будет назначать id для новый сущностей
    @Column(name = "id")
    private int id;
    @Column(name = "name")
//    @NotNull
//    @NotBlank // если есть паттерн, эта аннотации не нужны
    /*
    Potato - valid
    potato - not valid
    Potato5 - not valid
    Potato# - not valid
    Po - not valid
     */
    @Pattern(regexp = "[A-Z][a-z]*")
    private String name;
    @NotNull
    //@Min(value = 1/100)
    @Min(value = 1)
    @Max(value = 999999)
    @Column(name = "price")
    private double price;

    public JpaProduct() {  // обязательно создать пустой конструктор
//        LOGGER.info(String.format("Вызов конструктора JpaProduct класса JpaProduct без параметров"));
    }

    public JpaProduct(int id, String name, double price) {
//        LOGGER.info(String.format("Вызов конструктора JpaProduct класса JpaProduct с параметрами - %d, %s, %f", id, name, price));
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int getId() {
//       LOGGER.info(String.format("Вызван метод getId класса JpaProduct"));
        return id;
    }

    @Override
    public String getName() {
//        LOGGER.info(String.format("Вызван метод getName класса JpaProduct"));
        return name;
    }

    @Override
    public double getPrice() {
//        LOGGER.info(String.format("Вызван метод getPrice класса JpaProduct"));
        return price;
    }

    @Override
    public String toString() {
        return "JpaProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public void setName(String name) {
//        LOGGER.info(String.format("Вызван метод setName класса JpaProduct с параметром %s", name));
        this.name = name;
    }
}
