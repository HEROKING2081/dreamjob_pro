package ru.job4j.service;

import ru.job4j.dto.FileDTO;
import ru.job4j.model.File;

import java.util.Optional;

public interface FileService {

    File save(FileDTO fileDTO);

    Optional<FileDTO> getFileByID(int id);

    boolean deleteByID(int id);
}
