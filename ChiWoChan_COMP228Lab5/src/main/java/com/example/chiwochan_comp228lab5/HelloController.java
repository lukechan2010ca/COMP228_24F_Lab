package com.example.chiwochan_comp228lab5;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {

    @FXML
    private TextField playerIDField, firstNameField, lastNameField, addressField, postalCodeField, provinceField, gameTitleField, gameScoreField, datePlayedField, phoneNumberField, updatePlayerByIDField,  displayPlayerByIDField;
    @FXML
    private TableView<PlayerAndGameInfo> recordTable;
    @FXML
    private TableColumn<PlayerAndGameInfo, String> firstNameColumn, lastNameColumn, addressColumn, postalCodeColumn, provinceColumn, gameTitleColumn, datePlayedColumn, phoneNumberColumn;
    @FXML
    private TableColumn<PlayerAndGameInfo, Integer> playerIDColumn, scoreColumn;
    @FXML
    private Button addPlayerButton, updatePlayerButton, displayAllPlayerButton, displayPlayerByIDButton;

    private final OracleSqlManager dbManager;
    private final ObservableList<PlayerAndGameInfo> records;
    private static final Dotenv dotenv = Dotenv.configure().directory("src/main/resources").load();
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASS = dotenv.get("DB_PASS");

    // Constructor
    public HelloController() {
        dbManager = new OracleSqlManager(URL, USER, PASS); // Create a new instance of the OracleSqlManager
        records = FXCollections.observableArrayList(); // Create an empty observable list to hold the records
    }

    @FXML
    public void initialize() {
        // Set up the table columns
        playerIDColumn.setCellValueFactory(new PropertyValueFactory<>("playerID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        provinceColumn.setCellValueFactory(new PropertyValueFactory<>("province"));
        gameTitleColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));
        datePlayedColumn.setCellValueFactory(new PropertyValueFactory<>("datePlayed"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        // Link the data to the table
        recordTable.setItems(records);

        // Load table with existing records
        onDisplayAllPlayerButtonClick();

        // add event listeners to buttons
        addPlayerButton.setOnAction(event -> onAddPlayerButtonClick());
        updatePlayerButton.setOnAction(event -> onUpdatePlayerButtonClick());
        displayAllPlayerButton.setOnAction(event -> onDisplayAllPlayerButtonClick());
        displayPlayerByIDButton.setOnAction(event -> onDisplayPlayerByIDButtonClick());
    }

    // method to add new player
    @FXML
    public void onAddPlayerButtonClick() {
        try {
            int playerID = Integer.parseInt(playerIDField.getText());
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String address = addressField.getText();
            String postalCode = postalCodeField.getText();
            String province = provinceField.getText();
            String phoneNumber = phoneNumberField.getText();
            String gameTitle = gameTitleField.getText();
            String datePlayed = datePlayedField.getText();
            int gameScore = Integer.parseInt(gameScoreField.getText());

            dbManager.addRecord(playerID, firstName, lastName, address, postalCode, province, phoneNumber, gameTitle, gameScore, datePlayed);
            clearFields();
            showAlert("Success","Player added successfully!", Alert.AlertType.CONFIRMATION);
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Fill All Fields Correctly.", Alert.AlertType.ERROR);
        }
    }

    // method to update player information
    @FXML
    public void onUpdatePlayerButtonClick() {
        try {
            if (updatePlayerByIDField.getText().isEmpty()) {
                showAlert("Invalid Input", "Player ID is required.", Alert.AlertType.ERROR);
                return;
            }
            else if (!playerIDField.getText().isEmpty()) {
                showAlert("Invalid Input", "Player ID cannot be changed", Alert.AlertType.ERROR);
                return;
            }
            int playerID = Integer.parseInt(updatePlayerByIDField.getText());
            String firstName = firstNameField.getText().isEmpty() ? null : firstNameField.getText();
            String lastName = lastNameField.getText().isEmpty() ? null : lastNameField.getText();
            String address = addressField.getText().isEmpty() ? null : addressField.getText();
            String postalCode = postalCodeField.getText().isEmpty() ? null : postalCodeField.getText();
            String province = provinceField.getText().isEmpty() ? null : provinceField.getText();
            String phoneNumber = phoneNumberField.getText().isEmpty() ? null : phoneNumberField.getText();

            // Validate the phone number format (10 digits only for example)
            if (phoneNumber != null && !phoneNumber.matches("\\d{10}")) {  // Validates 10-digit phone number
                showAlert("Invalid Input", "Phone number must be exactly 10 digits.", Alert.AlertType.ERROR);
                return;
            }
            dbManager.updatePlayer(playerID, firstName, lastName, address, postalCode, province, phoneNumber);
            clearFields();
            showAlert("Update Success", "Player information updated successfully.", Alert.AlertType.CONFIRMATION);
        }
        // Catch NumberFormatException if the player ID or phone number is not a valid integer.
        catch (NumberFormatException e) {
                showAlert("Invalid Input", "Invalid Player ID or Phone Number." , Alert.AlertType.ERROR);
        }
        catch (IllegalArgumentException e) {
        // This exception can be thrown from `updatePlayer` if no valid updates are passed.
        showAlert("Update Error", e.getMessage(), Alert.AlertType.ERROR);
        }
        // Catch any other exceptions that may occur.
        catch (Exception e) {
        showAlert("Error", "An unexpected error occurred: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // method to display all players
    @FXML
    public void onDisplayAllPlayerButtonClick() {
        records.clear();
        records.addAll(dbManager.getAllRecords());
    }

    // method to display player by ID
    @FXML
    public void onDisplayPlayerByIDButtonClick() {
        int playerID = Integer.parseInt(displayPlayerByIDField.getText());
        records.clear();
        records.addAll(dbManager.getSelectedRecords(playerID));
    }

    // method to clear all fields
    private void clearFields(){
        playerIDField.clear();
        firstNameField.clear();
        lastNameField.clear();
        addressField.clear();
        postalCodeField.clear();
        provinceField.clear();
        phoneNumberField.clear();
        gameTitleField.clear();
        gameScoreField.clear();
        datePlayedField.clear();
        updatePlayerByIDField.clear();
    }

    // method to show alert dialog
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

