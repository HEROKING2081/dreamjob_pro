package ru.job4j.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Candidate {

    private int id;

    private String name;

    private String description;

    private LocalDateTime creationDate = LocalDateTime.now();

    private boolean visible;

    private int cityID;

    private int fileID;

    public Candidate() { }

    public Candidate(int id, String name, String description, LocalDateTime creationDate,
                     boolean visible, int cityID, int fileID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.visible = visible;
        this.cityID = cityID;
        this.fileID = fileID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
