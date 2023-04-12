package ru.finalproject.animal_service.repositories.abstracts;

import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface AbstractRepo<E> extends CommonRepository<E> {
}

