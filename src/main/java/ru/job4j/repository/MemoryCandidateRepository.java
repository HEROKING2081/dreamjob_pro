package ru.job4j.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Candidate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class MemoryCandidateRepository implements CandidateRepository {

    private final AtomicInteger nextId = new AtomicInteger(0);

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    public MemoryCandidateRepository() {
        save(new Candidate(0, "Igor G", "0", null, true, 2, 0));
        save(new Candidate(0, "Vasya M", "0", null, true, 3, 0));
        save(new Candidate(0, "Jura G", "0", null, true, 1, 0));
        save(new Candidate(0, "Bogdan P", "0", null, true, 2, 0));
        save(new Candidate(0, "Ksenka P", "0", null, true, 4, 0));
        save(new Candidate(0, "Danil V", "0", null, true, 5, 0));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteByID(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(), (id, oldCandidate) ->
                new Candidate(oldCandidate.getId(), candidate.getName(), candidate.getDescription(),
                        candidate.getCreationDate(), candidate.getVisible(), candidate.getCityID(),
                        candidate.getFileID())) != null;
    }

    @Override
    public Optional<Candidate> findByID(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
