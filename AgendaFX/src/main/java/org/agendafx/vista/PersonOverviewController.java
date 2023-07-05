package org.agendafx.vista;

import org.agendafx.modelo.Person;
import org.agendafx.MainApp;
import org.agendafx.utilidad.DateUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> NombreColum;
    @FXML
    private TableColumn<Person, String> ApellidoColum;

    @FXML
    private Label txtnombre;
    @FXML
    private Label txtapellido;
    @FXML
    private Label txtcalle;
    @FXML
    private Label txtcodpost;
    @FXML
    private Label txtciudad;
    @FXML
    private Label txtcump;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
        NombreColum.setCellValueFactory(cellData -> cellData.getValue().NombreProperty());
        ApellidoColum.setCellValueFactory(cellData -> cellData.getValue().ApellidoProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Rellena todos los campos de texto para mostrar Datos de la Persona.
     * Si la personas especificada es nulo, se borran todos los campos de texto.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            txtnombre.setText(person.getNombre());
            txtapellido.setText(person.getApellido());
            txtcalle.setText(person.getCalle());
            txtcodpost.setText(Integer.toString(person.getCodPost()));
            txtciudad.setText(person.getCiudad());
    
            // Nosotros necesitamos un forma de convertir el cumpleaños en una cadena! 
            txtcump.setText(DateUtil.format(person.getCump()));
            // txtcump.setText(...);
        } else {
            // Person is null, remove all the text.
            txtnombre.setText("");
            txtapellido.setText("");
            txtcalle.setText("");
            txtcodpost.setText("");
            txtciudad.setText("");
            txtcump.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}