package controller;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.Compromisso;
import model.Funcionario;

public class FXMLCompromissoDialogController implements Initializable{

    @FXML
    private ComboBox<Cliente> comboBoxClienteCompromisso;

    @FXML
    private TextField txtFieldHorarioCompromisso;

    @FXML
    private Button btnCancelarCompromisso;

    @FXML
    private ComboBox<Funcionario> comboBoxFuncionarioCompromisso;

    @FXML
    private Button btnConfirmarCompromisso;

    @FXML
    private DatePicker dataPickerDataCompromisso;

    @FXML
    private CheckBox checkBoxAtendido;
    
    @FXML
    private TextArea textAreaDescricaoCompromisso;
    
    private List<Cliente> listCliente;
    private List<Funcionario> listFuncionario;
    private ObservableList<Cliente> observableListCliente;
    private ObservableList<Funcionario> observableListFuncionario;
    
    private final ClienteDAO clienteDAO =  new ClienteDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Compromisso compromisso;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarComboBoxFuncionario();
            carregarComboBoxCliente();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCompromissoDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void carregarComboBoxCliente() throws SQLException{
        listCliente = clienteDAO.listar();
        observableListCliente = FXCollections.observableArrayList(listCliente);
        comboBoxClienteCompromisso.setItems(observableListCliente);
    }
    
    public void carregarComboBoxFuncionario() throws SQLException{
        listFuncionario = funcionarioDAO.listarDesenvolvedor();
        observableListFuncionario = FXCollections.observableArrayList(listFuncionario);
        comboBoxFuncionarioCompromisso.setItems(observableListFuncionario);
    }
    
    
 
    @FXML
    void btnCancelarCompromissoOnAction(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void btnConfirmarCompromissoOnAction(ActionEvent event) {
        if(validarDados()){
            compromisso.setCliente(comboBoxClienteCompromisso.getSelectionModel().getSelectedItem());
            compromisso.setFuncionario(comboBoxFuncionarioCompromisso.getSelectionModel().getSelectedItem());
            compromisso.setDataCompromisso(Date.valueOf(dataPickerDataCompromisso.getValue()));
            compromisso.setAtendido(checkBoxAtendido.isSelected());
            compromisso.setHorario(txtFieldHorarioCompromisso.getText());
            compromisso.setDescricaoCompromisso(textAreaDescricaoCompromisso.getText());
            btnConfirmarClicked= true;
            dialogStage.close();
        }
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

    public Compromisso getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(Compromisso compromisso) {
        this.compromisso = compromisso;
            
            this.dataPickerDataCompromisso.setValue(toLocalDate(compromisso.getDataCompromisso()));
            this.comboBoxClienteCompromisso.setValue(compromisso.getCliente());
            this.comboBoxFuncionarioCompromisso.setValue(compromisso.getFuncionario());
            this.checkBoxAtendido.setSelected(compromisso.isAtendido());
            this.textAreaDescricaoCompromisso.setText(compromisso.getDescricaoCompromisso());
            this.checkBoxAtendido.setCache(compromisso.isAtendido());
            this.txtFieldHorarioCompromisso.setText(compromisso.getHorario());
       
    }

    public boolean validarDados(){
        String erroMsg = "";
        if(comboBoxClienteCompromisso.getSelectionModel().getSelectedItem() == null){
            erroMsg += "Cliente inválido\n";
        }
        if(comboBoxFuncionarioCompromisso.getSelectionModel().getSelectedItem() == null){
            erroMsg += "Funcionario inválido\n";
        }
        if(dataPickerDataCompromisso.getValue() == null){
            erroMsg += "Data inválida\n";
        }
        if(txtFieldHorarioCompromisso.getText() == null||txtFieldHorarioCompromisso.getText().length() == 0){
            erroMsg += "Horário inválido\n";
        }
        if(erroMsg.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos");
            alert.setContentText(erroMsg);
            alert.show();
            return false;
        }
    }
    
    /**
     * Converte Date para LocalDate
     *
     * @param d
     * @return LocalDate
     */
    public static LocalDate toLocalDate(Date d) {
        if(d!=null){
        Instant instant = Instant.ofEpochMilli(d.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
        }else{
            return null;
        }
    }
}
