package ru.job4j.service;

import ru.job4j.dto.FileDTO;
import ru.job4j.model.Candidate;

import java.util.Collection;
import java.util.Optional;

public interface CandidateService {

    Candidate save(Candidate candidate, FileDTO image);

    boolean deleteByID(int id);

    boolean update(Candidate candidate, FileDTO imade);

    Optional<Candidate> findByID(int id);

    Collection<Candidate> findAll();
}
