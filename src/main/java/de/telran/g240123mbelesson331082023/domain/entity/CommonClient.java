package de.telran.g240123mbelesson331082023.domain.entity;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonClient implements Client{

    private int id;
    private String name;
    private Basket basket;

}
