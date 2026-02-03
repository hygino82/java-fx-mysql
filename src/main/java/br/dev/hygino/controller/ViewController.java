package br.dev.hygino.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import br.dev.hygino.db.MySQLConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ViewController {

    @FXML
    private Button btnTest;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtGenre;

    @FXML
    private ChoiceBox<String> choicePlatform;

    @FXML
    private DatePicker datePickerReleaseDate;

    @FXML
    private TextField txtDeveloper;

    @FXML
    private TextField txtPersonalCode;

    @FXML
    public void btnTestAction(ActionEvent event) {
        System.out.println("Button clicked!");
        insertGame();
    }

    private void insertGame() {

        String sql = """
                INSERT INTO game
                (name, genre, platform, release_date, developer, personal_code)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = MySQLConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "The Last of Us");
            ps.setString(2, "Action-adventure");
            ps.setString(3, "PlayStation 4");
            ps.setDate(4, Date.valueOf(LocalDate.of(2013, 6, 14)));
            ps.setString(5, "Naughty Dog");
            ps.setString(6, "TLU123");

            ps.executeUpdate();

            System.out.println("Game inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
