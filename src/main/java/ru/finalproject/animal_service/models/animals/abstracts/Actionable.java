package ru.finalproject.animal_service.models.animals.abstracts;

import java.util.Set;

public interface Actionable {
    Long getId();
    String getName();
    void setName(String name);
    Set<Long> getMoves();
    Set<Long> getFood();
}
