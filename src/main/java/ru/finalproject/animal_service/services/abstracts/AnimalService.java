package ru.finalproject.animal_service.services.abstracts;

import ru.finalproject.animal_service.models.animals.abstracts.Animals;
import ru.finalproject.animal_service.repositories.abstracts.CommonRepository;

import java.util.Set;

public class AnimalService<E extends Animals, R extends CommonRepository<E>> extends AbstractService<E,R> {
    protected AnimalService(R repository) {
        super(repository);
    }

    public void addToMovesList(E animal, Long id){
        Set<Long> moves = animal.getMoves();
        moves.add(id);
        animal.setMoves(moves);
        this.repository.save(animal);
    }

    public void delFromMovesList(E animal, Long id){
        Set<Long> moves = animal.getMoves();
        moves.remove(id);
        animal.setMoves(moves);
        this.repository.save(animal);
    }

    public void addToFoodList(E animal, Long id){
        Set<Long> food = animal.getFood();
        food.add(id);
        animal.setMoves(food);
        this.repository.save(animal);
    }

    public void delFromFoodList(E animal, Long id){
        Set<Long> food = animal.getFood();
        food.remove(id);
        animal.setMoves(food);
        this.repository.save(animal);
    }
}
