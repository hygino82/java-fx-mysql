package br.dev.hygino.controller;

import br.dev.hygino.dto.RequestGameDto;
import br.dev.hygino.service.GameService;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ViewController {

    private GameService service = GameService.getInstance();

    final String[] consoles = {
        "Mega Drive",
        "Super Nintendo",
        "Nintendo",
        "Nintendo 64",
        "Nintendo WII",
        "XBOX",
        "XBOX 360",
        "XBOX ONE",
        "PlayStation",
        "PlayStation 2",
        "PlayStation 3",
        "PlayStation 4",
        "PlayStation 5"
    };

    @FXML
    private Button btnInsertGame;

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
    public void initialize() {
        choicePlatform.getItems().addAll(consoles);
    }

    @FXML
    public void btnTestAction(ActionEvent event) {
        System.out.println("Button clicked!");
        insertGame();
    }

    private void insertGame() {
        final String name = txtName.getText();
        final String genre = txtGenre.getText();
        final String personalCode = txtPersonalCode.getText();
        final String developer = txtDeveloper.getText();
        final LocalDate releaseDate = datePickerReleaseDate.getValue();
        final String plataform = choicePlatform.getValue();
        final RequestGameDto dto = new RequestGameDto(name, genre, plataform, releaseDate, developer, personalCode);

        System.out.println(dto);

        service.insertGame(dto);
        clearFields();
    }

    private void clearFields() {
        txtName.setText("");
        txtGenre.setText("");
        txtPersonalCode.setText("");
        txtDeveloper.setText("");
        datePickerReleaseDate.setValue(LocalDate.of(2014, 2, 23));
        txtName.requestFocus();
    }
}
