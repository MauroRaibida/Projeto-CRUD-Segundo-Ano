package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FXMLInserirClienteController {

    @FXML
    private TextField txtFieldEnderecoCliente;

    @FXML
    private TextField txtFieldCpfCliente;

    @FXML
    private TextField txtFieldTelefoneCliente;

    @FXML
    private TextField txtFieldNomeCliente;

    @FXML
    private Button btnCancelarInserirCliente;

    @FXML
    private Button btnConfirmarInserirCliente;

    @FXML
    void btnConfirmarInserirClienteOnAction(ActionEvent event) {
        
    }

    @FXML
    void btnCancelarInserirClienteOnAction(ActionEvent event) {
        
    }
    
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (txtFieldNomeCliente.getText() == null || txtFieldNomeCliente.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        
        if (txtFieldTelefoneCliente.getText() == null || txtFieldTelefoneCliente.getText().length() == 0) {
            errorMessage += "Telefone inválido!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
