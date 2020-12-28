package controller;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import trabalhofinal.TrabalhoFinal;

public class FXMLMenuBarPrincipalController {
    //Declarações FXML dos componentes visuais
    @FXML
    private MenuItem menuItemProjeto;

    @FXML
    private MenuItem menuItemCliente;

    @FXML
    private MenuItem menuItemCompromisso;

    @FXML
    private MenuItem menuItemFuncionario;
    
    //Tratamento de eventos
    @FXML
    void menuItemClienteOnAction(ActionEvent event) throws IOException {
        //Carrega BorderPane cliente no principal
        BorderPane borderPaneCliente = FXMLLoader.load(getClass().getResource("/view/FXMLCadastroCliente.fxml"));
        BorderPane bpCliente = TrabalhoFinal.getRoot();
        bpCliente.setCenter(borderPaneCliente);
    }

    @FXML
    void menuItemFuncionarioOnAction(ActionEvent event) throws IOException {
        //Carrega BorderPane funcionario no principal
        BorderPane borderPaneFuncionario = FXMLLoader.load(getClass().getResource("/view/FXMLCadastroFuncionario.fxml"));
        BorderPane bpFuncionario = TrabalhoFinal.getRoot();
        bpFuncionario.setCenter(borderPaneFuncionario);
        
    }

    @FXML
    void menuItemCompromissoOnAction(ActionEvent event) throws IOException {
        //Carrega BorderPane compromisso no principal
        BorderPane borderPaneCompromisso = FXMLLoader.load(getClass().getResource("/view/FXMLCadastroCompromisso.fxml"));
        BorderPane bpCompromisso = TrabalhoFinal.getRoot();
        bpCompromisso.setCenter(borderPaneCompromisso);
    }

    @FXML
    void menuItemProjetoOnAction(ActionEvent event) throws IOException {
        //Carrega BorderPane projeto no principal
        BorderPane borderPaneProjeto = FXMLLoader.load(getClass().getResource("/view/FXMLCadastroProjeto.fxml"));
        BorderPane bpProjeto = TrabalhoFinal.getRoot();
        bpProjeto.setCenter(borderPaneProjeto);
    }
}

