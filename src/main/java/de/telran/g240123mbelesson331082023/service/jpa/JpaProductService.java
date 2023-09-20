package de.telran.g240123mbelesson331082023.service.jpa;

import de.telran.g240123mbelesson331082023.domain.entity.Product;
import de.telran.g240123mbelesson331082023.domain.entity.jpa.JpaProduct;
import de.telran.g240123mbelesson331082023.domain.entity.jpa.Task;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaProductRepository;
import de.telran.g240123mbelesson331082023.repository.jpa.JpaTaskRepository;
import de.telran.g240123mbelesson331082023.schedule_layer.ScheduleExecutor;
import de.telran.g240123mbelesson331082023.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JpaProductService implements ProductService {

    private static final Logger LOGGER = LogManager.getLogger(JpaProductService.class);

    @Autowired
    private JpaProductRepository repository;

    @Autowired
    private JpaTaskRepository taskRepository;

    @Override
    public List<Product> getAll() {
//        Task task = new Task("Task scheduled after getting all products");
        Task task = new Task("Task scheduled for single execution after getting all products");
        taskRepository.save(task);
//        ScheduleExecutor.taskSchedulerTaskWithTrigger(task);
        ScheduleExecutor.taskSchedulerTaskWithInstant(task);
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Product getById(int id) {
//        LOGGER.log(Level.INFO, String.format("INFO запрошен продукт с id %d.", id));
//        LOGGER.log(Level.WARN, String.format("WARN запрошен продукт с id %d.", id));
//        LOGGER.log(Level.ERROR, String.format("ERROR запрошен продукт с id %d.", id));

//        LOGGER.info(String.format("INFO запрошен продукт с id %d.", id));
//        LOGGER.warn(String.format("WARN запрошен продукт с id %d.", id));
//        LOGGER.error(String.format("ERROR запрошен продукт с id %d.", id));


        return repository.findById(id).get();  //orElse(null);
    }

    @Override
    public Product add(Product product) {
        return repository.save(new JpaProduct(0, product.getName(), product.getPrice()));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    @Override
    public int getCount() {
        return (int) repository.count();
    }

    @Override
    public double getTotalPrice() {
        return repository.getTotalPrice();
    }

    @Override
    public double getAveragePrice() {
        return repository.getAveragePrice();
    }

//    public void test(JpaProduct product) {
//        product.setName("New name");
//    }

}
