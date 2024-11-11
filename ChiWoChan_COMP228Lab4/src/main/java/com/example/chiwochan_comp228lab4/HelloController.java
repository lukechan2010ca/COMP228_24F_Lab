    package com.example.chiwochan_comp228lab4;

    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.fxml.FXML;
    import javafx.scene.control.*;
    import javafx.scene.control.TextArea;
    import javafx.scene.control.TextField;

    public class HelloController {
        @FXML
        private TextField nameField;
        @FXML
        private TextField addressField;
        @FXML
        private TextField provinceField;
        @FXML
        private TextField cityField;
        @FXML
        private TextField postalCodeField;
        @FXML
        private TextField phoneField;
        @FXML
        private TextField emailField;
        @FXML
        private RadioButton csMajorRadio;
        @FXML
        private RadioButton businessMajorRadio;
        @FXML
        private ComboBox<String> courseComboBox;
        @FXML
        private ListView<String> courseListView;
        @FXML
        private CheckBox studentCouncilCheck;
        @FXML
        private CheckBox volunteerWorkCheck;
        @FXML
        public Button displayButton;
        @FXML
        private TextArea outputArea;

        // Define regex patterns
        @FXML
        private static final String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        @FXML
        private static final String phoneRegex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
        @FXML
        private static final String canadaPostalCodeRegex = "^[A-Za-z]\\d[A-Za-z][ ]?\\d[A-Za-z]\\d$";
        // Canadian postal code validation method
        @FXML
        private boolean ValidCanadianPostalCode(String postalCode) {
            return postalCode.matches(canadaPostalCodeRegex);
        }

        // Phone number validation method
        @FXML
        private boolean ValidPhone(String phone) {
            return phone.matches(phoneRegex);
        }

        // Email validation method
        @FXML
        private boolean ValidEmail(String email) {
            return email.matches(emailRegex);
        }

        // Utility method to show alert dialog
        @FXML
        private void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
        }

        // Define course lists
        @FXML
        private final ObservableList<String> csCourses = FXCollections.observableArrayList("Java", "JavaScript", "C#", "Python");
        @FXML
        private final ObservableList<String> businessCourses = FXCollections.observableArrayList("Marketing", "Accounting", "Management", "Economics");

        @FXML
        private void initialize() {
            // Toggle group for major selection
            ToggleGroup majorGroup = new ToggleGroup();
            csMajorRadio.setToggleGroup(majorGroup);
            businessMajorRadio.setToggleGroup(majorGroup);

            // Update courses based on selected major
            majorGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                if (csMajorRadio.isSelected()) {
                    courseComboBox.setItems(csCourses);
                } else if (businessMajorRadio.isSelected()) {
                    courseComboBox.setItems(businessCourses);
                }
            });
        }

        // Add courses to ListView, only one major is allowed at a time
        @FXML
        private void addCourse() {
            String selectedCourse = courseComboBox.getSelectionModel().getSelectedItem();
            if (courseListView.getItems().contains(selectedCourse)) {
                showAlert("Error", "This course already exists");
            }
            else {
                courseListView.getItems().add(selectedCourse);
            }
        }

        // Clear ListView when selected major radio button
        @FXML
        private void changeMajor() {
            courseListView.getItems().clear();
            courseComboBox.promptTextProperty().setValue("Select Course");
            }

        // Show info in Display Area
        @FXML
        private void onDisplayButtonClick() {

            // Check if any personal info field is empty
            if (nameField.getText().isEmpty() ||
                    addressField.getText().isEmpty() ||
                    provinceField.getText().isEmpty() ||
                    cityField.getText().isEmpty() ||
                    postalCodeField.getText().isEmpty() ||
                    phoneField.getText().isEmpty() ||
                    emailField.getText().isEmpty()) {

                // Show an alert if any Info field is empty
                showAlert("Missing Info", "All personal information fields must be filled out.");
                return;
            }

            // Validate Canadian postal code format
            if (!ValidCanadianPostalCode(postalCodeField.getText())) {
                showAlert("Format Error", "Please enter a valid Canadian postal code (e.g., A1A 1A1).");
                return;
            }

            // Check if phone number is valid
            if (!ValidPhone(phoneField.getText())) {
                showAlert("Format Error", "Please enter a valid phone number (e.g., (123) 456-7890 or 123-456-7890).");
                return;
            }

            // Validate email and phone number formats
            if (!ValidEmail(emailField.getText())) {
                showAlert("Format Error", "Please enter a valid email address.(e.g., abc@abc.xyz).");
                return;
            }

            // Clear previous content in the output area and course list view
            outputArea.clear();

            // Build personal info string
            StringBuilder personalInfo = new StringBuilder();
            personalInfo.append(nameField.getText()).append(", ").append(addressField.getText()).append(", ").append(provinceField.getText()).append(", ").append(cityField.getText()).append(", ").append(postalCodeField.getText()).append(", ").append(phoneField.getText()).append(", ").append(emailField.getText()).append("\n").append("Courses: ");

            // Display the list of courses
            if (!courseListView.getItems().isEmpty()) {
                for (String course : courseListView.getItems()) {
                    personalInfo.append(course).append("\n");
                }
            } else {
                showAlert("Missing Info", "Please select at least one course");
                return;
            }
            //if checkbox is checked, add "Student Council" or/and "Volunteer Work to personal info
            if (studentCouncilCheck.isSelected()) {
                if (volunteerWorkCheck.isSelected()) {
                    personalInfo.append("Additional Info:").append("\n").append("Student Council").append(",").append("Volunteer Work\n");
                } else {
                    personalInfo.append("Additional Info:").append("\n").append("Student Council\n");
                }
            } else if (volunteerWorkCheck.isSelected()) {
                personalInfo.append("Additional Info:").append("\n").append("Volunteer Work\n");
            }
            //if checkbox is not checked,nothing need to be added to personal info.
            outputArea.appendText(personalInfo.toString());
        }

    }