package ru.finalproject.animal_service.services.entityServices;

import org.springframework.stereotype.Service;
import ru.finalproject.animal_service.models.Counter;
import ru.finalproject.animal_service.repositories.CounterRepository;
import ru.finalproject.animal_service.services.abstracts.AbstractService;

@Service
public class CounterService extends AbstractService<Counter, CounterRepository> {
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
