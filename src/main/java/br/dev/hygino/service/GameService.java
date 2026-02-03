package br.dev.hygino.service;

import br.dev.hygino.db.MySQLConfig;
import br.dev.hygino.dto.RequestGameDto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameService {

    private static GameService instance;

    private GameService() {
    }

    public static GameService getInstance() {
        if (instance == null) {
            instance = new GameService();
        }

        return instance;
    }

    public void insertGame(RequestGameDto dto) {

        String sql = """
                INSERT INTO Game
                (name, genre, platform, release_date, developer, personal_code)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = MySQLConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dto.name());
            ps.setString(2, dto.genre());
            ps.setString(3, dto.platform());
            ps.setDate(4, Date.valueOf(dto.releaseDate()));
            ps.setString(5, dto.developer());
            ps.setString(6, dto.personalCode());

            ps.executeUpdate();

            System.out.println("Game inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir o Jogo!");
        }
    }
}
