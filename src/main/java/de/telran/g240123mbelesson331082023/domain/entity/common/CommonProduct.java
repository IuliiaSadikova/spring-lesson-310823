package de.telran.g240123mbelesson331082023.domain.entity.common;

import de.telran.g240123mbelesson331082023.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonProduct implements Product {

    private int id;
    private String name;
    private double price;

}
