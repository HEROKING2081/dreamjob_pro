package ru.job4j.repository;

import ru.job4j.model.File;

import java.util.Optional;

public interface FileRepository {

    File save(File file);

    Optional<File> findByID(int id);

    boolean deleteByID(int id);
}