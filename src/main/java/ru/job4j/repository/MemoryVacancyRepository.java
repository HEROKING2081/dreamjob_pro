package ru.job4j.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.model.Vacancy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class MemoryVacancyRepository implements  VacancyRepository {

    private final AtomicInteger nextId = new AtomicInteger(0);

    private final Map<Integer, Vacancy> vacancies = new HashMap<>();

    private MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "0", null, true, 1, 0));
        save(new Vacancy(0, "Junior Java Developer", "0", null, true, 2, 0));
        save(new Vacancy(0, "Junior+ Java Developer", "0", null, true, 3, 0));
        save(new Vacancy(0, "Middle Java Developer", "0", null, true, 4, 0));
        save(new Vacancy(0, "Middle+ Java Developer", "0", null, true, 2, 0));
        save(new Vacancy(0, "Senior Java Developer", "0", null, true, 5, 0));
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(nextId.incrementAndGet());
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public boolean deleteByID(int id) {
        return vacancies.remove(id) != null;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(), (id, oldVacancy) ->
                new Vacancy(oldVacancy.getId(), vacancy.getTitle(), vacancy.getDescription(),
                        vacancy.getCreationDate(), vacancy.getVisible(), vacancy.getCityID(),
                        vacancy.getFileId())) != null;
    }

    @Override
    public Optional<Vacancy> findByID(int id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }
}
