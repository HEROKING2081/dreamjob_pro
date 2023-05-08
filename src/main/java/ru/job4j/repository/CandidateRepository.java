package ru.job4j.repository;

import ru.job4j.model.Candidate;

import java.util.Collection;
import java.util.Optional;

public interface CandidateRepository {

    Candidate save(Candidate candidate);

    boolean deleteByID(int id);

    boolean update(Candidate candidate);

    Optional<Candidate> findByID(int id);

    Collection<Candidate> findAll();
}
