package br.dev.hygino.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;



@NoArgsConstructor
@Data
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
}
