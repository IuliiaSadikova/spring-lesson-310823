package de.telran.g240123mbelesson331082023.logging_layer;

import de.telran.g240123mbelesson331082023.domain.entity.jpa.Task;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaTaskRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;

/*
После запроса конкретного продукта через 15 секунд отправить персональное предложение
на этот продукт с ценой на 5-10% (рандомно) ниже, чем базовая цена.
Имитировать отправку в виде вывода в консоль и логирования.
Данная задача должна выполняться при помощи АОП и сохраняться в БД.
 */

@Component
@Aspect
public class SpecialDealForProductLogging {

    private Logger logger = LoggerFactory.getLogger(SpecialDealForProductLogging.class);

    @Autowired
    private JpaTaskRepository taskRepository;

    @Pointcut("execution(* de.telran.g240123mbelesson331082023.service.jpa.JpaProductService.getById(..))")
    public void getById() {}

    @After("getById()")
    public void afterGetById(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Task task = new Task("Special deal for product");
        taskRepository.save(task);
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
        Instant instant = Instant.now().plusSeconds(15);
        scheduler.schedule(() -> logger.info("Успей купить товар дня (id = {}) со скидкой 20% !!!", args[0]), instant);
    }


}
