package br.dev.hygino.model;

import br.dev.hygino.dto.ResponseGameDto;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Game {

    Long id;
    String name;
    String genre;
    String platform;
    LocalDate releaseDate;
    String developer;
    String personalCode;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public Game() {
    }

    public Game(Long id, String name, String genre, String platform, LocalDate releaseDate, String developer, String personalCode, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.platform = platform;
        this.releaseDate = releaseDate;
        this.developer = developer;
        this.personalCode = personalCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", name=" + name + ", genre=" + genre + ", platform=" + platform + ", releaseDate=" + releaseDate + ", developer=" + developer + ", personalCode=" + personalCode + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

    public ResponseGameDto toGameResponse() {
        return new ResponseGameDto(id, name, genre, platform, releaseDate, developer, personalCode, createdAt, updatedAt);
    }
}
