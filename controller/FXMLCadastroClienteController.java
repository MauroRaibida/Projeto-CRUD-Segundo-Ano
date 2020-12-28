package controller;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import dao.ClienteDAO;
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
import model.Cliente;

public class FXMLCadastroClienteController implements Initializable{
    //Declarações FXML dos componentes visuais
    @FXML
    private TableView<Cliente> tableViewCliente;
    
    @FXML
    private Label lblCodigoCliente;

    @FXML
    private Label lblNomeCliente;

    @FXML
    private Label lblEnderecoCliente;

    @FXML
    private Label lblTelefoneCliente;

    @FXML
    private Button btnInserirCliente;

    @FXML
    private TableColumn<Cliente, String> tableColumnNomeCliente;

    @FXML
    private TableColumn<Cliente, String> tableColumnTelefoneCliente;

    @FXML
    private Button btnAlterarCliente;

    @FXML
    private Button btnRemoverCliente;

    @FXML
    private Label lblCpfCliente;
    
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    //Tratamento de eventos
    @FXML
    void btnInserirClienteOnAction(ActionEvent event) throws IOException, SQLException  {
        Cliente cliente = new Cliente();
        boolean btnConfirmarClicked = showClienteDialog(cliente);
        if(btnConfirmarClicked){
            clienteDAO.inserir(cliente);
            carregarTableViewCliente();
        }
    }
    
    @FXML
    void btnAlterarClienteOnAction(ActionEvent event) throws IOException, SQLException {
        Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();
        if (cliente != null){
            boolean btnConfirmarClicked = showClienteDialog(cliente);
            clienteDAO.alterar(cliente);
            carregarTableViewCliente();
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor selecione um cliente na tabela");
            alert.show();
        }
    }
    
    @FXML
    void btnRemoverClienteOnAction(ActionEvent event) throws SQLException, IOException {
        Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();
        if (cliente != null){
            clienteDAO.remover(cliente);
            carregarTableViewCliente();
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor selecione um cliente na tabela");
            alert.show();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarTableViewCliente();
            tableViewCliente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCadastroClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void carregarTableViewCliente() throws SQLException{
        tableColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        tableColumnTelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefoneCliente"));
        listClientes = clienteDAO.listar();

        observableListClientes = FXCollections.observableArrayList(listClientes);
        tableViewCliente.setItems(observableListClientes);
    }
    
    public void selecionarItemTableViewClientes(Cliente cliente){
        if (cliente != null) {
            lblCodigoCliente.setText(String.valueOf(cliente.getCodCliente()));
            lblNomeCliente.setText(cliente.getNomeCliente());
            lblCpfCliente.setText(cliente.getCpfCliente());
            lblTelefoneCliente.setText(cliente.getTelefoneCliente());
            lblEnderecoCliente.setText(cliente.getEnderecoCliente());
        } else {
            lblCodigoCliente.setText("");
            lblNomeCliente.setText("");
            lblCpfCliente.setText("");
            lblTelefoneCliente.setText("");
            lblEnderecoCliente.setText("");
        }

    }
    
    public boolean showClienteDialog(Cliente cliente) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLClienteDialogController.class.getResource("/view/FXMLClienteDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cliente");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLClienteDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);

        dialogStage.showAndWait();

        return controller.isBtnConfirmarCLicked();
    }
}

