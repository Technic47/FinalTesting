package ru.finalproject.animal_service.services.entityServices;

import org.springframework.stereotype.Service;
import ru.finalproject.animal_service.models.Counter;
import ru.finalproject.animal_service.repositories.CounterRepository;
import ru.finalproject.animal_service.services.abstracts.AbstractService;

import java.io.FileWriter;

@Service
public class CounterService extends AbstractService<Counter, CounterRepository> {
    private final static String PATH = "src/main/resources/counter.txt";

    protected CounterService(CounterRepository repository) {
        super(repository);
    }

    public void countPlus(String type) {
        Counter counter = repository.findByAnimalType(type);
        if (counter == null) {
            repository.save(new Counter(type));
        } else {
            counter.countPlus();
            repository.save(counter);
            try (FileWriter writer = new FileWriter(PATH)) {
                StringBuilder builder = new StringBuilder();
                builder.append(counter.getAnimalType())
                        .append(" - ")
                        .append(counter.getCount());
                writer.write(builder.toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new RuntimeException();
            }
        }
    }

    public void countMinus(String type) {
        Counter counter = repository.findByAnimalType(type);
        if (counter != null) {
            counter.countMinus();
            repository.save(counter);
        }
    }
}
