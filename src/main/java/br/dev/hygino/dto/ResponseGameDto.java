package br.dev.hygino.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ResponseGameDto(
                Long id,
                String name,
                String genre,
                String platform,
                LocalDate releaseDate,
                String developer,
                String personalCode,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

}
