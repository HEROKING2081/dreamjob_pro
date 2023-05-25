package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.dto.FileDTO;
import ru.job4j.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

@Service
public interface VacancyService {

    Vacancy save(Vacancy vacancy, FileDTO image);

    boolean deleteByID(int id);

    boolean update(Vacancy vacancy, FileDTO image);

    Optional<Vacancy> findByID(int id);

    Collection<Vacancy> findAll();
}
