package ru.job4j.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Vacancy {

    private int id;

    private String title;

    private String description;

    private LocalDateTime creationDate = LocalDateTime.now();

    private boolean visible;

    private int cityID;

    private int fileId;

    public Vacancy() { }

    public Vacancy(int id, String title, String description, LocalDateTime creationDate,
                   boolean visible, int cityID, int fileId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.visible = visible;
        this.cityID = cityID;
        this.fileId = fileId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setCityID(int cityId) {
        this.cityID = cityId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
