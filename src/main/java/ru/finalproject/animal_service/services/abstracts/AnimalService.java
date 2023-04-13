package ru.finalproject.animal_service.services.abstracts;

import ru.finalproject.animal_service.models.AnimalMove;
import ru.finalproject.animal_service.models.animals.abstracts.Animals;
import ru.finalproject.animal_service.repositories.abstracts.CommonRepository;

import java.util.Set;

public class AnimalService<E extends Animals, R extends CommonRepository<E>> extends AbstractService<E,R> {
    protected AnimalService(R repository) {
        super(repository);
    }

    public void addToMovesList(E animal, AnimalMove move){
        Set<AnimalMove> moves = animal.getMoves();
        moves.add(move);
        animal.setMoves(moves);
        this.repository.save(animal);
    }

    public void delFromMovesList(E animal, AnimalMove move){
        Set<AnimalMove> moves = animal.getMoves();
        moves.remove(move);
        animal.setMoves(moves);
        this.repository.save(animal);
    }

    public void addToFoodList(E animal, Long id){
        Set<Long> food = animal.getFood();
        food.add(id);
        animal.setFood(food);
        this.repository.save(animal);
    }

    public void delFromFoodList(E animal, Long id){
        Set<Long> food = animal.getFood();
        food.remove(id);
        animal.setFood(food);
        this.repository.save(animal);
    }
}
