package de.telran.g240123mbelesson331082023.domain.entity.common;

import de.telran.g240123mbelesson331082023.domain.entity.Basket;
import de.telran.g240123mbelesson331082023.domain.entity.Client;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonClient implements Client {

    private int id;
    private String name;
    private Basket basket;

}
