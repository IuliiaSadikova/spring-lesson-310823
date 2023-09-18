package de.telran.g240123mbelesson331082023.logging_layer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ProductLogging {

    private Logger logger = LoggerFactory.getLogger(ProductLogging.class);

    @Pointcut("execution(* de.telran.g240123mbelesson331082023.service.jpa.JpaProductService.*(..))")
    public void productService() {}


    @Before("productService()")
    public void beforeProductService(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length != 0) {
            logger.info("Вызван метод {} класса {} c параметром {}",
                    joinPoint.getSignature().getName(), joinPoint.getSourceLocation().getWithinType().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        } else {
            logger.info("Вызван метод {} класса {}",
                    joinPoint.getSignature().getName(), joinPoint.getSourceLocation().getWithinType().getName());
        }
    }

    @After("productService()")
    public void afterProductService(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length != 0) {
            logger.info("Завершил работу метод {} класса {} c параметром {}",
                    joinPoint.getSignature().getName(), joinPoint.getSourceLocation().getWithinType().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        } else {
            logger.info("Завершил работу метод {} класса {}",
                    joinPoint.getSignature().getName(), joinPoint.getSourceLocation().getWithinType().getName());
        }
    }

    @AfterReturning("productService()")
    public void afterProductServiceReturning(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length != 0) {
            logger.info("Метод {} класса {} успешно вернул результат с параметром: {}",
                    joinPoint.getSignature().getName(), joinPoint.getSourceLocation().getWithinType().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        } else {
            logger.info("Метод {} класса {} успешно вернул результат",
                    joinPoint.getSignature().getName(), joinPoint.getSourceLocation().getWithinType().getName());
        }
    }

    @AfterThrowing("productService()")
    public void afterThrowingWhileProductServiceReturning(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length != 0) {
            logger.info("Метод {} класса {} вернул ошибку! Несуществующий параметр: {}",
                    joinPoint.getSignature().getName(), joinPoint.getSourceLocation().getWithinType().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        } else {
            logger.info("Метод {} класса {} вернул ошибку!",
                    joinPoint.getSignature().getName(), joinPoint.getSourceLocation().getWithinType().getName());
        }
    }
}
