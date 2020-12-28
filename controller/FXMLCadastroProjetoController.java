package controller;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import dao.ProjetoDAO;
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
import model.Projeto;

public class FXMLCadastroProjetoController implements Initializable{
    //Declarações FXML dos componentes visuais
    @FXML
    private Label lblFuncProjeto;

    @FXML
    private Button btnAlterarProjeto;

    @FXML
    private TableColumn<Projeto, Integer> tableColumnCodigoProjeto;

    @FXML
    private Label lblClienteProjeto;

    @FXML
    private Button btnInserirProjeto;

    @FXML
    private TableView<Projeto> tableViewProjeto;

    @FXML
    private Label lblCodigoProjeto;

    @FXML
    private Label lblDataProjeto;

    @FXML
    private Button btnRemoverProjeto;

    @FXML
    private Label lblDescricaoProjeto;

    @FXML
    private TableColumn<Projeto, String> tableColumnDescricaoProjeto;
    
    private final ProjetoDAO projetoDAO = new ProjetoDAO();
    private List<Projeto> listProjeto;
    private ObservableList<Projeto> observableListProjeto;
    
    //Tratamento de eventos
    @FXML
    void btnAlterarProjetoOnAction(ActionEvent event) throws IOException, SQLException {
        Projeto projeto = tableViewProjeto.getSelectionModel().getSelectedItem();
        if(projeto != null){
            boolean btnConfirmarClicked = showProjetoDialog(projeto);
            if(btnConfirmarClicked){
                projetoDAO.alterar(projeto);
                carregarTableViewProjeto();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor selecione um projeto na tabela");
            alert.show();
        }
    }

    @FXML
    void btnInserirProjetoOnAction(ActionEvent event) throws SQLException, IOException {
        Projeto projeto = new Projeto();
        boolean btnConfirmarClicked = showProjetoDialog(projeto);
        if(btnConfirmarClicked){
            projetoDAO.inserir(projeto);
            carregarTableViewProjeto();
        }
    }

    @FXML
    void btnRemoverProjetoOnAction(ActionEvent event) throws SQLException {
        Projeto projeto = tableViewProjeto.getSelectionModel().getSelectedItem();
        if(projeto != null){
            projetoDAO.remover(projeto);
            carregarTableViewProjeto();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor selecione um projeto na tabela");
            alert.show();
        }
    }


    
    private void carregarTableViewProjeto() throws SQLException {
        tableColumnCodigoProjeto.setCellValueFactory(new PropertyValueFactory<>("codProjeto"));
        tableColumnDescricaoProjeto.setCellValueFactory(new PropertyValueFactory<>("descricaoProjeto"));
        
        listProjeto = projetoDAO.listar();
        
        observableListProjeto = FXCollections.observableArrayList(listProjeto);
        tableViewProjeto.setItems(observableListProjeto);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarTableViewProjeto();
            tableViewProjeto.getSelectionModel().selectedItemProperty().addListener((obsevable,oldValue, newValue)->selecionarItemTableViewCompromisso(newValue));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCadastroProjetoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void selecionarItemTableViewCompromisso(Projeto projeto) {
        if(projeto != null){
            lblFuncProjeto.setText(projeto.getCompromisso().getFuncionario().getNomeFuncionario());
            lblClienteProjeto.setText(projeto.getCompromisso().getCliente().getNomeCliente());
            lblCodigoProjeto.setText(String.valueOf(projeto.getCodProjeto()));
            lblDataProjeto.setText(String.valueOf(projeto.getDataEntrega()));
            lblDescricaoProjeto.setText(projeto.getDescricaoProjeto());
        }else{
            lblFuncProjeto.setText("");
            lblClienteProjeto.setText("");
            lblCodigoProjeto.setText("");
            lblDataProjeto.setText("");
            lblDescricaoProjeto.setText("");
        }
    }

    private boolean showProjetoDialog(Projeto projeto) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLClienteDialogController.class.getResource("/view/FXMLProjetoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Projeto");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLProjetoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProjeto(projeto);

        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();
    }
}
