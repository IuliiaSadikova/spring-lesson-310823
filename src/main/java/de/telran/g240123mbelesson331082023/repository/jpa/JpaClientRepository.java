package de.telran.g240123mbelesson331082023.repository.jpa;

import de.telran.g240123mbelesson331082023.domain.entity.jpa.JpaClient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaClientRepository extends JpaRepository<JpaClient, Integer> {

    @Transactional
    void deleteByName(String name);

    @Query(value = "SELECT sum(p.price) as total_price FROM customer as c " +
            "left join cart_product as cp on c.id = cp.cart_id " +
            "left join product as p on cp.product_id = p.id where c.id = :id", nativeQuery = true)
    double getTotalPriceById(@Param("id") int id);

    @Query(value = "SELECT avg(p.price) as avg_price FROM customer as c " +
            "left join cart_product as cp on c.id = cp.cart_id " +
            "left join product as p on cp.product_id = p.id where c.id = :id", nativeQuery = true)
    double getAveragePriceById(@Param("id") int id);




}
