package br.dev.hygino.dto;

import java.time.LocalDate;

public record RequestGameDto(
        String name,
        String genre,
        String platform,
        LocalDate releaseDate,
        String developer,
        String personalCode) {

}
