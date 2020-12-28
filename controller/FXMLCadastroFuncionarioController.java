package controller;

import dao.FuncionarioDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import model.Funcionario;

public class FXMLCadastroFuncionarioController implements Initializable{

    @FXML
    private Button btnRemoverFuncionario;

    @FXML
    private Label lblNomeFuncionario;

    @FXML
    private TableView<Funcionario> tableViewFuncionario;

    @FXML
    private Label lblCargoFuncionario;

    @FXML
    private Label lblEnderecoFuncionario;
    
    @FXML
    private Label lblTelefoneFuncionario;

    @FXML
    private Button btnAlterarFuncionario;

    @FXML
    private Label lblCodigoFuncionario;

    @FXML
    private Label lblCpfFuncionario;

    @FXML
    private TableColumn<Funcionario, String> tableColumnCargoFuncionario;

    @FXML
    private Button btnInserirFuncionario;

    @FXML
    private TableColumn<Funcionario, String> tableColumnNomeFuncionario;

    
    private List<Funcionario> listFuncionarios;
    private ObservableList<Funcionario> observableListClientes;
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    @FXML
    void btnInserirFuncionarioOnAction(ActionEvent event) throws SQLException, IOException {
        Funcionario funcionario = new Funcionario();
        boolean btnConfirmarClicked = showFuncionarioDialog(funcionario);
        if(btnConfirmarClicked){
            funcionarioDAO.inserir(funcionario);
            carregarTableViewFuncionario();
        }
    }

    @FXML
    void btnAlterarFuncionarioOnAction(ActionEvent event) throws SQLException, IOException {
        Funcionario funcionario = tableViewFuncionario.getSelectionModel().getSelectedItem();
        if (funcionario != null){
            boolean btnConfirmarClicked = showFuncionarioDialog(funcionario);
            funcionarioDAO.alterar(funcionario);
            carregarTableViewFuncionario();
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor selecione um funcionario na tabela");
            alert.show();
        }
    }

    @FXML
    void btnRemoverFuncionarioOnAction(ActionEvent event) throws SQLException, IOException {
        Funcionario funcionario = tableViewFuncionario.getSelectionModel().getSelectedItem();
        if (funcionario != null){
            funcionarioDAO.remover(funcionario);
            carregarTableViewFuncionario();
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor selecione um cliente na tabela");
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarTableViewFuncionario();
            tableViewFuncionario.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewFuncionario(newValue));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCadastroClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregarTableViewFuncionario() throws SQLException {
        tableColumnNomeFuncionario.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));
        tableColumnCargoFuncionario.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        listFuncionarios = funcionarioDAO.listar();

        observableListClientes = FXCollections.observableArrayList(listFuncionarios);
        tableViewFuncionario.setItems(observableListClientes);
    }
    
    public void selecionarItemTableViewFuncionario(Funcionario funcionario){
        if (funcionario != null) {
            lblCodigoFuncionario.setText(String.valueOf(funcionario.getCodFuncionario()));
            lblNomeFuncionario.setText(funcionario.getNomeFuncionario());
            lblCpfFuncionario.setText(funcionario.getCpfFuncionario());
            lblTelefoneFuncionario.setText(funcionario.getTelefoneFuncionario());
            lblEnderecoFuncionario.setText(funcionario.getEnderecoFuncionario());
            lblCargoFuncionario.setText(funcionario.getCargo());
            
        } else {
            lblCodigoFuncionario.setText("");
            lblNomeFuncionario.setText("");
            lblCpfFuncionario.setText("");
            lblTelefoneFuncionario.setText("");
            lblEnderecoFuncionario.setText("");
            lblCargoFuncionario.setText("");
        }
    }

    private boolean showFuncionarioDialog(Funcionario funcionario) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLFuncionarioDialogController.class.getResource("/view/FXMLFuncionarioDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Funcionário");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLFuncionarioDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFuncionario(funcionario);

        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();
    }

}
