package br.dev.hygino.service;

import br.dev.hygino.db.MySQLConfig;
import br.dev.hygino.dto.RequestGameDto;
import br.dev.hygino.model.Game;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<Game> findGames(String name, String platform, String personalCode) {
        List<Game> gameList = new ArrayList<>();

        String sql = """
                SELECT * FROM Game
                WHERE LOWER(name) LIKE LOWER(CONCAT('%', IFNULL(?, name), '%'))
                AND LOWER(platform) = LOWER(IFNULL(?, platform))
                AND LOWER(personal_code) = LOWER(IFNULL(?, personal_code))
                """;

        try (Connection conn = MySQLConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, platform);
            ps.setString(3, personalCode);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Game game = new Game(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("genre"),
                            rs.getString("platform"),
                            rs.getObject("release_date", LocalDate.class),
                            rs.getString("developer"),
                            rs.getString("personal_code"),
                            rs.getObject("created_at", LocalDateTime.class),
                            rs.getObject("updated_at", LocalDateTime.class));

                    gameList.add(game);
                }
            }
            return gameList;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar os jogos!", e);
        }
    }
}
