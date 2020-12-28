package controller;

/**
 * @author Mauro Raibida
 * @version 1.0
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;

public class FXMLClienteDialogController implements Initializable {

    //Declarações FXML dos componentes visuais
    @FXML
    private TextField txtFieldEnderecoCliente;

    @FXML
    private Button btnCancelarCliente;

    @FXML
    private TextField txtFieldCpfCliente;

    @FXML
    private TextField txtFieldTelefoneCliente;

    @FXML
    private TextField txtFieldNomeCliente;

    @FXML
    private Button btnConfirmarInserirCliente;

    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Cliente cliente;

    //Tratamento de eventos
    @FXML
    void btnConfirmarClienteOnAction(ActionEvent event) {
        if (verificaDados()) {
            cliente.setNomeCliente(txtFieldNomeCliente.getText());
            cliente.setCpfCliente(txtFieldCpfCliente.getText());
            cliente.setEnderecoCliente(txtFieldEnderecoCliente.getText());
            cliente.setTelefoneCliente(txtFieldTelefoneCliente.getText());
            btnConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    void btnCancelarClienteOnAction(ActionEvent event) {
        dialogStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnConfirmarCLicked() {
        return btnConfirmarClicked;
    }

    public void setBtnConfirmarCLicked(boolean btnConfirmarCLicked) {
        this.btnConfirmarClicked = btnConfirmarCLicked;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.txtFieldNomeCliente.setText(cliente.getNomeCliente());
        this.txtFieldCpfCliente.setText(cliente.getCpfCliente());
        this.txtFieldTelefoneCliente.setText(cliente.getTelefoneCliente());
        this.txtFieldEnderecoCliente.setText(cliente.getEnderecoCliente());
    }

    public boolean verificaDados() {
        String errorMsg = "";
        if (txtFieldNomeCliente.getText() == null || txtFieldNomeCliente.getText().length() == 0) {
            errorMsg += "Nome inválido\n";
        }
        if (txtFieldTelefoneCliente.getText() == null || txtFieldTelefoneCliente.getText().length() == 0) {
            errorMsg += "Telefone inválido\n";
        }
        if (errorMsg.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no Cadastro");
            alert.setHeaderText("Campos inválidos, por favor corrija:");
            alert.setContentText(errorMsg);
            alert.show();
            return false;
        }
    }
}
