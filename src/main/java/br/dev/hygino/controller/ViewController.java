package br.dev.hygino.controller;

import java.time.LocalDate;

import br.dev.hygino.dto.RequestGameDto;
import br.dev.hygino.model.Game;
import br.dev.hygino.service.GameService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<Game> tableViewGames;

    @FXML
    private Label lbLog;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnInsertGame;

    @FXML
    private Button btnSearch;

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

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colPlataforma.setCellValueFactory(new PropertyValueFactory<>("platform"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("personalCode"));
        colDesenvolvedora.setCellValueFactory(new PropertyValueFactory<>("developer"));
        colLancamento.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        colDataCadastro.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colDataAtualizacao.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        loadTableData();
        lbLog.setText("Dados Carregados!");
    }

    @FXML
    public void btnTestAction(ActionEvent event) {
        System.out.println("Button clicked!");
        insertGame();
    }

    @FXML
    public void btnClearAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void btnFindAction(ActionEvent event) {
        lbLog.setText("Busca realizada!");
        final String name = verifyString(txtName.getText());
        final String personalCode = verifyString(txtPersonalCode.getText());
        final String plataform = verifyString(choicePlatform.getValue());

        findGames(name, plataform, personalCode);
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

        loadTableData();
        lbLog.setText("Jogo inserido com sucesso!");
    }

    private void clearFields() {
        txtName.setText("");
        txtGenre.setText("");
        txtPersonalCode.setText("");
        txtDeveloper.setText("");
        datePickerReleaseDate.setValue(LocalDate.of(2014, 2, 23));
        txtName.requestFocus();
        lbLog.setText("Campos Limpos!");
    }

    @FXML
    private TableColumn<Game, Long> colId;

    @FXML
    private TableColumn<Game, String> colNome;

    @FXML
    private TableColumn<Game, LocalDate> colLancamento;

    @FXML
    private TableColumn<Game, String> colPlataforma;

    @FXML
    private TableColumn<Game, String> colCode;

    @FXML
    private TableColumn<Game, String> colGenero;

    @FXML
    private TableColumn<Game, String> colDesenvolvedora;

    @FXML
    private TableColumn<Game, LocalDate> colDataCadastro;

    @FXML
    private TableColumn<Game, LocalDate> colDataAtualizacao;

    private void loadTableData() {
        tableViewGames.setItems(
                FXCollections.observableArrayList(
                        service.findGames(null, null, null)));
    }

    private void findGames(String name, String plataform, String personalCode) {
        tableViewGames.setItems(
                FXCollections.observableArrayList(
                        service.findGames(name, plataform, personalCode)));
    }

    private String verifyString(String text) {
        return (text == null || text == "") ? null : text;
    }
}
