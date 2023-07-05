package org.agendafx.vista;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.agendafx.modelo.Person;
import org.agendafx.utilidad.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class PersonEditDialogController {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellido;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfCodPost;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfCump;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        tfNombre.setText(person.getNombre());
        tfApellido.setText(person.getApellido());
        tfCalle.setText(person.getCalle());
        tfCodPost.setText(Integer.toString(person.getCodPost()));
        tfCiudad.setText(person.getCiudad());
        tfCump.setText(DateUtil.format(person.getCump()));
        tfCump.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setNombre(tfNombre.getText());
            person.setApellido(tfApellido.getText());
            person.setCalle(tfCalle.getText());
            person.setCodPost(Integer.parseInt(tfCodPost.getText()));
            person.setCiudad(tfCiudad.getText());
            person.setCump(DateUtil.parse(tfCump.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (tfNombre.getText() == null || tfNombre.getText().length() == 0) {
            errorMessage += "No valido el Nombre!\n"; 
        }
        if (tfApellido.getText() == null || tfApellido.getText().length() == 0) {
            errorMessage += "No valido el Apellido!\n"; 
        }
        if (tfCalle.getText() == null || tfCalle.getText().length() == 0) {
            errorMessage += "No valido la Calle!\n"; 
        }

        if (tfCodPost.getText() == null || tfCodPost.getText().length() == 0) {
            errorMessage += "No valido el Codigo Postal!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(tfCodPost.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valido el Codigo Postal (Debe ser un numero entero)!\n"; 
            }
        }

        if (tfCiudad.getText() == null || tfCiudad.getText().length() == 0) {
            errorMessage += "No valido la Ciudad!\n"; 
        }

        if (tfCump.getText() == null || tfCump.getText().length() == 0) {
            errorMessage += "No valido el Cumpleaños!\n";
        } else {
            if (!DateUtil.validDate(tfCump.getText())) {
                errorMessage += "No valido el Cumpleaños. Debe usar el formato dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campos no validos");
            alert.setHeaderText("Por favor corrija los campo no validos");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}