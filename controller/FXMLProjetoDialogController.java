package controller;

import dao.CompromissoDAO;
import dao.ProjetoDAO;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Cliente;
import model.Compromisso;
import model.Funcionario;
import model.Projeto;

public class FXMLProjetoDialogController implements Initializable{

    @FXML
    private TableColumn<Cliente, String> tableColumnClienteCompromissoProjeto;

    @FXML
    private TableColumn<Compromisso, String> tableColumnDescricaoCompromissoProjeto;

    @FXML
    private TableView<Compromisso> tableViewCompromissoProjeto;

    @FXML
    private DatePicker dataPickerDataEntregaProjeto;

    @FXML
    private TextArea txtAreaDescricaoProjeto;

    @FXML
    private Button btnConfirmarProjeto;

    @FXML
    private Button btnCancelarProjeto;

    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncionarioCompromissoProjeto;
    
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Projeto projeto;
    
    @FXML
    void btnCancelarProjetoOnAction(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void btnConfirmarProjetonAction(ActionEvent event) {
        if(verificaDados()){
            projeto.setCompromisso(tableViewCompromissoProjeto.getSelectionModel().getSelectedItem());
            projeto.setDataEntrega(Date.valueOf(dataPickerDataEntregaProjeto.getValue()));
            projeto.setDescricaoProjeto(txtAreaDescricaoProjeto.getText());
            btnConfirmarClicked= true;
            dialogStage.close();
        }
    }

    
    private List<Compromisso> listCompromisso;
    private ObservableList<Compromisso> observableListCompromisso;
    private final CompromissoDAO compromissoDAO = new CompromissoDAO();
    private final ProjetoDAO projetoDAO = new ProjetoDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarTableViewCompromisso();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLProjetoDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void carregarTableViewCompromisso() throws SQLException {
        tableColumnClienteCompromissoProjeto.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tableColumnDescricaoCompromissoProjeto.setCellValueFactory(new PropertyValueFactory<>("descricaoCompromisso"));
        tableColumnFuncionarioCompromissoProjeto.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        
        listCompromisso = compromissoDAO.listar();
        
        observableListCompromisso = FXCollections.observableArrayList(listCompromisso);
        tableViewCompromissoProjeto.setItems(observableListCompromisso);
    }

    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return the btnConfirmarClicked
     */
    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    /**
     * @param btnConfirmarClicked the btnConfirmarClicked to set
     */
    public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
        this.btnConfirmarClicked = btnConfirmarClicked;
    }

    /**
     * @return the projeto
     */
    public Projeto getProjeto() {
        return projeto;
    }

    /**
     * @param projeto the projeto to set
     */
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
        //this.tableViewCompromissoProjeto;
        this.txtAreaDescricaoProjeto.setText(projeto.getDescricaoProjeto());
        this.dataPickerDataEntregaProjeto.setValue(toLocalDate(projeto.getDataEntrega()));      
    }

    private boolean verificaDados() {
        String erroMsg = "";
        if(tableViewCompromissoProjeto.getSelectionModel().getSelectedItem() == null){
            erroMsg += "Compromisso inválido\n";
        }
        if(txtAreaDescricaoProjeto.getText() == null||txtAreaDescricaoProjeto.getText().length() == 0){
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
