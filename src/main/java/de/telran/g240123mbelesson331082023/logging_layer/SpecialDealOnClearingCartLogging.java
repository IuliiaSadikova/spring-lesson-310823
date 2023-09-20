package de.telran.g240123mbelesson331082023.logging_layer;

import de.telran.g240123mbelesson331082023.domain.entity.Basket;
import de.telran.g240123mbelesson331082023.domain.entity.Product;
import de.telran.g240123mbelesson331082023.domain.entity.jpa.Task;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaClientRepository;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaTaskRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

/*
После того, как покупатель очистил корзину, через 20 секунд выбрать из корзины случайный товар,
который там был, сделать на него скидку 15% и предложить покупателю всё-таки
приобрести все эти товары, вывести все товары (один с новой ценой), а также старую и новую стоимость корзины.
Данная задача должна выполняться при помощи АОП и сохраняться в БД.
 */

@Component
@Aspect
public class SpecialDealOnClearingCartLogging {

    private Logger logger = LoggerFactory.getLogger(SpecialDealForProductLogging.class);

    @Autowired
    private JpaTaskRepository taskRepository;

    @Autowired
    private JpaClientRepository clientRepository;

    @Pointcut("execution(* de.telran.g240123mbelesson331082023.service.jpa.JpaClientService.clearCart(..))")
    public void clearCart() {
    }

    @Before("clearCart()")
    public void beforeClearCart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer clientId = (Integer) args[0];
        clientRepository.findById(clientId).ifPresent(client -> {
            Basket basket = client.getBasket();
            List<Product> productsInBasket = basket.getProducts();
            int randomProductIndex = (int) (Math.random() * productsInBasket.size());
            Product discountProduct = productsInBasket.get(randomProductIndex);
            double newPrice = discountProduct.getPrice() * 0.85;
            double oldTotalCost = basket.getTotalCost();

            Task task = new Task("Special deal for cart after clearing");
            taskRepository.save(task);
            TaskScheduler scheduler = new DefaultManagedTaskScheduler();
            Instant instant = Instant.now().plusSeconds(20);
            scheduler.schedule(() -> {
                double newTotalCost = 0;
                logger.info("Успей купить следующие товары !!!");
                for (int i = 0; i < productsInBasket.size(); i++) {
                    Product product = productsInBasket.get(i);
                    String name = product.getName();
                    double price = i == randomProductIndex ? newPrice : product.getPrice();
                    newTotalCost += price;
                    logger.info("{} : {}", name, price);
                }
                logger.info("Старая стоимость корзины: {}, новая стоимость корзины: {}", oldTotalCost, newTotalCost);
            }, instant);
        });
    }
}
