package ru.job4j.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dto.FileDTO;
import ru.job4j.model.Vacancy;
import ru.job4j.repository.VacancyRepository;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
public class SimpleVacancyService implements VacancyService {

    private final VacancyRepository vacancyRepository;

    private final FileService fileService;

    public SimpleVacancyService(VacancyRepository vacancyRepository, FileService fileService) {
        this.vacancyRepository = vacancyRepository;
        this.fileService = fileService;
    }

    @Override
    public Vacancy save(Vacancy vacancy, FileDTO image) {
        saveNewFile(vacancy, image);
        return vacancyRepository.save(vacancy);
    }

    private void saveNewFile(Vacancy vacancy, FileDTO image) {
        var file = fileService.save(image);
        vacancy.setFileId(file.getId());
    }

    @Override
    public boolean deleteByID(int id) {
        var fileOptional = findByID(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        var isDeleted = vacancyRepository.deleteByID(id);
        fileService.deleteByID(fileOptional.get().getFileId());
        return isDeleted;
    }

    @Override
    public boolean update(Vacancy vacancy, FileDTO image) {
        var isNewFileEmpty = image.getContent().length == 0;
        if (isNewFileEmpty) {
            return vacancyRepository.update(vacancy);
        }
        var oldFileId = vacancy.getFileId();
        saveNewFile(vacancy, image);
        var isUpdated = vacancyRepository.update(vacancy);
        fileService.deleteByID(oldFileId);
        return isUpdated;
    }

    @Override
    public Optional<Vacancy> findByID(int id) {
        return vacancyRepository.findByID(id);
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }
}
