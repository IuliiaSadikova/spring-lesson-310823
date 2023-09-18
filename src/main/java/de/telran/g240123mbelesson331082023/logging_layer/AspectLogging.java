package de.telran.g240123mbelesson331082023.logging_layer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AspectLogging {

    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);

//    @Pointcut("execution(* de.telran.g240123mbelesson331082023.service.jpa.JpaProductService.add(..))")
//    public void addProduct() {}
//
//    @Before("addProduct()")
//    public void beforeAddingProduct(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        // вывести в консоль: Вызван метод add класса JpaProductService с параметром - {продукт}
//        logger.info(String.format("Вызван метод add класса JpaProductService с параметром - %s", args[0]));
//    }
//
//    @Pointcut("execution(* de.telran.g240123mbelesson331082023.service.jpa.JpaProductService.test(..))")
//    public void test() {}
//
//
//    @Before("test()")
//    public void beforeTest(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        logger.info(String.format("Вызван метод тест класса JpaProductService с входящим параметром - %s", args[0]));
//    }
//
//    @After("test()")
//    public void afterTest(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        // вывести в консоль: Вызван метод add класса JpaProductService с параметром - {продукт}
//        logger.info(String.format("Завершил работу метод test класса JpaProductService, новое значение параметра: %s", args[0]));
//    }
//
//    @Pointcut("execution(* de.telran.g240123mbelesson331082023.service.jpa.JpaProductService.getById(..))")
//    public void getProductById() {}
//
//    @AfterReturning("getProductById()")
//    public void afterProductReturning(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        logger.info(String.format("Метод getById класса JpaProductService успешно вернул продукт с id %d", args[0]));
//    }
//
//    @AfterThrowing("getProductById()")
//    public void afterThrowingWhileProductReturning(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        logger.info(String.format("Метод getById класса JpaProductService вызвал ошибку! Несуществующий id %d", args[0]));
//    }
//
//    @Pointcut("execution(* de.telran.g240123mbelesson331082023.service.jpa.JpaProductService.getCount(..))")
//    public void getProductsCount() {}


//    @Around("getProductsCount()")
//    public Object aroundGetProductCount(ProceedingJoinPoint joinPoint) {
//        logger.info("Around getProductsCount()");
//        try {
//            return joinPoint.proceed();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Around("getProductsCount()")
//    public Object aroundGetProductCount(ProceedingJoinPoint joinPoint) {
//        logger.info("Around getProductsCount()");
//        try {
//            logger.info("Действительный результат - " + joinPoint.proceed());
//            logger.info("Подменяем результат и возвращаем -1");
//            return new Integer(-1);
//        } catch (Throwable e) {
//            logger.error("Тут какая-то ошибка");
//            throw new RuntimeException(e);
//        }
//    }






}
