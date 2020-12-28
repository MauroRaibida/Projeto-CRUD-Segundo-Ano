package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Funcionario;

public class FXMLFuncionarioDialogController implements Initializable{

    @FXML
    private TextField txtFieldEnderecoFuncionario;

    @FXML
    private TextField txtFieldNomeFuncionario;

    @FXML
    private TextField txtFieldTelefoneFuncionario;

    @FXML
    private ComboBox<String> comboBoxCargoFuncionario;

    @FXML
    private Button btnCancelarFuncionario;

    @FXML
    private Button btnConfirmarFuncionario;

    @FXML
    private TextField txtFieldCpfFuncionario;
    
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Funcionario funcionario;
    
    @FXML
    void btnConfirmarFuncionarioOnAction(ActionEvent event) {
        if (verificaDados()) {
            funcionario.setNomeFuncionario(txtFieldNomeFuncionario.getText());
            funcionario.setCpfFuncionario(txtFieldCpfFuncionario.getText());
            funcionario.setEnderecoFuncionario(txtFieldEnderecoFuncionario.getText());
            funcionario.setTelefoneFuncionario(txtFieldTelefoneFuncionario.getText());
            funcionario.setCargo(comboBoxCargoFuncionario.getSelectionModel().getSelectedItem());
            btnConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    void btnCancelarFuncionarioOnAction(ActionEvent event) {
        dialogStage.close();
    }

    private boolean verificaDados() {
        String errorMsg = "";
        if (txtFieldNomeFuncionario.getText() == null || txtFieldNomeFuncionario.getText().length() == 0) {
            errorMsg += "Nome inválido\n";
        }
        if (comboBoxCargoFuncionario.getSelectionModel().getSelectedItem() == null ) {
            errorMsg += "Cargo inválido\n";
        }
        if (txtFieldCpfFuncionario.getText() == null || txtFieldCpfFuncionario.getText().length() == 0) {
            errorMsg += "Cpf inválido\n";
        }
        if (txtFieldEnderecoFuncionario.getText() == null || txtFieldEnderecoFuncionario.getText().length() == 0) {
            errorMsg += "Endereço inválido\n";
        }
        if (txtFieldTelefoneFuncionario.getText() == null || txtFieldTelefoneFuncionario.getText().length() == 0) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarComboBoxCargoFuncionario();
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
        this.btnConfirmarClicked = btnConfirmarClicked;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.txtFieldNomeFuncionario.setText(funcionario.getNomeFuncionario());
        this.comboBoxCargoFuncionario.setValue(funcionario.getCargo());
        this.txtFieldCpfFuncionario.setText(funcionario.getCpfFuncionario());
        this.txtFieldEnderecoFuncionario.setText(funcionario.getEnderecoFuncionario());
        this.txtFieldTelefoneFuncionario.setText(funcionario.getTelefoneFuncionario());
    }

    private void carregarComboBoxCargoFuncionario() {
        ArrayList<String> listcargos = new ArrayList<>();
        
        listcargos.add("Recepcionista");
        listcargos.add("Desenvolvedor(a)");
        listcargos.add("Gerente");
        ObservableList<String> observableListCargos = FXCollections.observableArrayList(listcargos);
        comboBoxCargoFuncionario.setItems(observableListCargos);
    }

}