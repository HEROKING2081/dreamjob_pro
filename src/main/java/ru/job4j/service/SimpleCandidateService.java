package ru.job4j.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dto.FileDTO;
import ru.job4j.model.Candidate;
import ru.job4j.repository.CandidateRepository;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
public class SimpleCandidateService implements CandidateService {

    private final CandidateRepository candidateRepository;

    private final FileService fileService;

    public SimpleCandidateService(CandidateRepository candidateRepository, FileService fileService) {
        this.candidateRepository = candidateRepository;
        this.fileService = fileService;
    }

    @Override
    public Candidate save(Candidate candidate, FileDTO image) {
        saveNewFile(candidate, image);
        return candidateRepository.save(candidate);
    }

    public void saveNewFile(Candidate candidate, FileDTO image) {
        var file = fileService.save(image);
        candidate.setFileID(file.getId());
    }

    @Override
    public boolean deleteByID(int id) {
        var fileOptional = findByID(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        var isDelete = candidateRepository.deleteByID(id);
        fileService.deleteByID(fileOptional.get().getFileID());
        return isDelete;
    }

    @Override
    public boolean update(Candidate candidate, FileDTO image) {
        var isNewFileEmpty = image.getContent().length == 0;
        if (isNewFileEmpty) {
            return candidateRepository.update(candidate);
        }
        var oldFile = candidate.getFileID();
        saveNewFile(candidate, image);
        var isUpdated = candidateRepository.update(candidate);
        fileService.deleteByID(oldFile);
        return isUpdated;
    }

    @Override
    public Optional<Candidate> findByID(int id) {
        return candidateRepository.findByID(id);
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidateRepository.findAll();
    }
}
