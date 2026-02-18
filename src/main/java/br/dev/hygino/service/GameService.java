package br.dev.hygino.service;

import br.dev.hygino.app.exceptions.DatabaseException;
import br.dev.hygino.app.exceptions.GameNotFoundException;
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
            throw new DatabaseException("Erro ao inserir o Jogo!", e);
        }
    }

    public void updateGame(long id, RequestGameDto dto) {

        String sql = """
            UPDATE Game
            SET name = ?,
                genre = ?,
                platform = ?,
                release_date = ?,
                developer = ?,
                personal_code = ?
            WHERE id = ?
            """;

        try (Connection conn = MySQLConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dto.name());
            ps.setString(2, dto.genre());
            ps.setString(3, dto.platform());
            ps.setDate(4, Date.valueOf(dto.releaseDate()));
            ps.setString(5, dto.developer());
            ps.setString(6, dto.personalCode());
            ps.setLong(7, id);

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Game atualizado com sucesso!");
            } else {
                System.out.println("Nenhum game encontrado com esse ID.");
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar o jogo!", e);
        }
    }

    public List<Game> findGames(String name, String platform, String personalCode) {

        List<Game> gameList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Game WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (name != null && !name.isBlank()) {
            sql.append(" AND LOWER(name) LIKE LOWER(?) ");
            params.add("%" + name + "%");
        }

        if (platform != null && !platform.isBlank()) {
            sql.append(" AND LOWER(platform) = LOWER(?) ");
            params.add(platform);
        }

        if (personalCode != null && !personalCode.isBlank()) {
            sql.append(" AND LOWER(personal_code) = LOWER(?) ");
            params.add(personalCode);
        }

        try (Connection conn = MySQLConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            // Define parâmetros dinamicamente
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

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
                            rs.getObject("updated_at", LocalDateTime.class)
                    );

                    gameList.add(game);
                }
            }

            return gameList;

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar os jogos!", e);
        }
    }

    public void removeGame(long id) {

        String sql = "DELETE FROM Game WHERE id = ?";

        try (Connection conn = MySQLConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new GameNotFoundException("Nenhum jogo encontrado com id: " + id);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao remover o jogo!", e);
        }
    }
}
