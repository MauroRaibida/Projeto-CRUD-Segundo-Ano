package controller;
/**
 * @author Mauro Raibida
 * @version 1.0
 */

import dao.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Compromisso;

public class FXMLCadastroCompromissoController implements Initializable{

    @FXML
    private Label lblDataCompromisso;

    @FXML
    private TableColumn<Compromisso, Compromisso> tableColumnClienteCompromisso;

    @FXML
    private TableColumn<Compromisso, Date> tableColumnDataCompromisso;

    @FXML
    private TableView<Compromisso> tableViewCompromisso;

    @FXML
    private Label lblClienteCompromisso;
    
    @FXML
    private Label lblCompromissoAtendido;

    @FXML
    private TableColumn<Compromisso, Time> tableColumnHorarioCompromisso;

    @FXML
    private Button btnAlterarCompromisso;

    @FXML
    private Label lblHorarioCompromisso;

    @FXML
    private Label lblCodigoCompromisso;

    @FXML
    private Label lblFuncCompromisso;

    @FXML
    private Button btnInserirCompromisso;

    @FXML
    private Button btnRemoverCompromisso;
    
    @FXML
    private Label lblDescricaoCompromisso;
    
    private List<Compromisso> listCompromisso;
    private ObservableList<Compromisso> observableListCompromisso;
    private final CompromissoDAO compromissoDAO = new CompromissoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    @FXML
    void btnInserirCompromissoOnAction(ActionEvent event) throws SQLException, IOException {
        Compromisso compromisso = new Compromisso();
        boolean btnConfirmarClicked = showCompromissoDialog(compromisso);
        if(btnConfirmarClicked){
            compromissoDAO.inserir(compromisso);
            carregarTableViewCompromisso();
        }
    }
    
    @FXML
    void btnAlterarCompromissoOnAction(ActionEvent event) throws SQLException, IOException {
        Compromisso compromisso = tableViewCompromisso.getSelectionModel().getSelectedItem();
        if(compromisso != null){
            boolean btnConfirmarClicked = showCompromissoDialog(compromisso);
            compromissoDAO.alterar(compromisso);
            carregarTableViewCompromisso();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor selecione um compromisso");
            alert.show();
        }
    }

    @FXML
    void btnRemoverCompromissoOnAction(ActionEvent event) throws SQLException {
        Compromisso compromisso = tableViewCompromisso.getSelectionModel().getSelectedItem();
        if(compromisso != null){
            compromissoDAO.remover(compromisso);
            carregarTableViewCompromisso();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Por favor selecione um compromisso");
        alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarTableViewCompromisso();
            tableViewCompromisso.getSelectionModel().selectedItemProperty().addListener((obsevable,oldValue, newValue)->selecionarItemTableViewCompromisso(newValue));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCadastroCompromissoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregarTableViewCompromisso() throws SQLException {
        tableColumnDataCompromisso.setCellValueFactory(new PropertyValueFactory<>("dataCompromisso"));
        tableColumnClienteCompromisso.setCellValueFactory(new PropertyValueFactory<>("Cliente"));
        tableColumnHorarioCompromisso.setCellValueFactory(new PropertyValueFactory<>("horario"));
        
        listCompromisso = compromissoDAO.listar();
        
        observableListCompromisso = FXCollections.observableArrayList(listCompromisso);
        tableViewCompromisso.setItems(observableListCompromisso);
    }

    private void selecionarItemTableViewCompromisso(Compromisso compromisso) {
        if(compromisso != null){
            lblDataCompromisso.setText(String.valueOf(compromisso.getDataCompromisso()));
            lblClienteCompromisso.setText(compromisso.getCliente().getNomeCliente());
            lblHorarioCompromisso.setText(String.valueOf(compromisso.getHorario()));
            lblCodigoCompromisso.setText(String.valueOf(compromisso.getCodCompromisso()));
            lblFuncCompromisso.setText(compromisso.getFuncionario().getNomeFuncionario());
            lblCompromissoAtendido.setText(String.valueOf(compromisso.isAtendido()));
            lblDescricaoCompromisso.setText(compromisso.getDescricaoCompromisso());
        }else{
            lblDataCompromisso.setText("");
            lblClienteCompromisso.setText("");
            lblHorarioCompromisso.setText("");
            lblCodigoCompromisso.setText("");
            lblFuncCompromisso.setText("");
            lblCompromissoAtendido.setText("");
            lblDescricaoCompromisso.setText("");
        }
    }

    private boolean showCompromissoDialog(Compromisso compromisso) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLClienteDialogController.class.getResource("/view/FXMLCompromissoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Compromisso");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLCompromissoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCompromisso(compromisso);

        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();
    }
}
