package de.telran.g240123mbelesson331082023.schedule_layer;

import de.telran.g240123mbelesson331082023.domain.entity.jpa.JpaProduct;
import de.telran.g240123mbelesson331082023.domain.entity.jpa.Task;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaProductRepository;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@EnableScheduling // можно отключить запуск запланированных задач, закомментировав эту аннотацию
@EnableAsync // для параллельного выполнения задач
public class ScheduleExecutor {

    @Autowired
    private JpaTaskRepository repository;
    @Autowired
    private JpaProductRepository productRepository;

    private static Logger logger = LoggerFactory.getLogger(ScheduleExecutor.class);

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        Task task = new Task("Fixed delay task");
//        logger.info(task.getDescription());
//        repository.save(task);
//    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        Task task = new Task("Fixed delay task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        Task task = new Task("Fixed delay task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask() {
//        Task task = new Task("Fixed rate task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask() {
//        Task task = new Task("Fixed rate task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedRate = 5000)
//    @Async // задачи выполняются параллельно, в разных потоках
//    public void fixedRateTask() {
//        Task task = new Task("Fixed rate task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedRate = 5000, initialDelay = 15000)
//    @Async
//    public void initialDelayTask() {
//        Task task = new Task("Initial delay task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // fixedDelay = 7200000 (2 часа)
    // fixedDelayString = "PT02H" (2 часа)
//    @Scheduled(fixedDelayString = "PT07S")
//    public void anotherDelayFormatTask() {
//        Task task = new Task("Another delay format task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedDelayString = "${interval}")
//    public void delayInPropertyTask() {
//        Task task = new Task("Delay in property task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // 55 * * * * * задача будет выполняться в 55 сек. каждой минуты
    /* 0 15 9-17 * * MON-FRI задача будет выполняться
        в 15 минут каждого часа с 9 до 17 только по рабочим дням
     */
    // 0 0 * * * * -> @hourly
//    @Scheduled(cron = "${cron-interval}")
//    public void cronExpressionTask() {
//        Task task = new Task("Cron expression task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void taskSchedulerTaskWithTrigger(Task task) {
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
        scheduler.schedule(() -> logger.info(task.getDescription()),
                new CronTrigger("0,10,20,30,40,50 * * * * *"));
    }

    public static void taskSchedulerTaskWithInstant(Task task) {
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
        Instant instant = Instant.now().plusSeconds(15);
        scheduler.schedule(() -> logger.info(task.getDescription()),
                instant);
    }

//    @Scheduled(fixedRate = 30000)
//    public void showLastFiveTasks() {
//        List<Task> list = repository.findAll();
//        list = list.subList(list.size() - 5, list.size());
//        list.forEach(task -> System.out.println(task));
//    }
//
//    @Scheduled(cron = "15,45 * * * * *")
//    public void printLastProductFromProductRepo() {
//        List<JpaProduct> list = productRepository.findAll();
//        JpaProduct product = (list.get(list.size()-1));
//
//        Task task = new Task(product.getName() + " был выведен в консоль");
//        logger.info(task.getDescription());
//        repository.save(task);
//        System.out.println(product);
//    }

}
