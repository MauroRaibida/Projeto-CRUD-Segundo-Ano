package dao;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import interfaceDAO.IConst;
import interfaceDAO.IProjeto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Compromisso;
import model.Projeto;

public class ProjetoDAO implements IProjeto, IConst {
    private String sql;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    
    private void open() throws SQLException {
	this.connection = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
    }

    private void close() throws SQLException {
           this.connection.close();
    }

    @Override
    public void inserir(Projeto projeto) throws SQLException {
        open();
        this.sql = "INSERT INTO projeto (codProjeto, descricaoProjeto, dataEntrega, codCompromisso) VALUES (default, ?, ?, ?);";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setString(1, projeto.getDescricaoProjeto());
        this.statement.setDate(2, projeto.getDataEntrega());
        this.statement.setInt(3,projeto.getCompromisso().getCodCompromisso());
        this.statement.execute();
        close();
    }

    @Override
    public void remover(Projeto projeto) throws SQLException {
        open();
        this.sql = "DELETE FROM projeto WHERE codProjeto = ?;";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setInt(1, projeto.getCodProjeto());
        this.statement.execute();
        close();
    }

    @Override
    public void alterar(Projeto projeto) throws SQLException {
        open();
        this.sql = "UPDATE projeto SET descricaoProjeto = ?, dataEntrega = ?, codCompromisso = ?  WHERE codProjeto = ?;";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setString(1, projeto.getDescricaoProjeto());
        this.statement.setDate(2, projeto.getDataEntrega());
        this.statement.setInt(3,projeto.getCompromisso().getCodCompromisso());
        this.statement.setInt(4,projeto.getCompromisso().getCodCompromisso());
        this.statement.execute();
        close();
    }

    @Override
    public List<Projeto> listar() throws SQLException {
        open();
         this.sql = "SELECT * FROM projeto";
         ArrayList<Projeto> projetos = new ArrayList<>();
         this.statement = this.connection.prepareStatement(sql);
         this.result = this.statement.executeQuery();
         while(this.result.next()) {
            Projeto projeto = new Projeto();
            Compromisso compromisso = new Compromisso();
            projeto.setCodProjeto(this.result.getInt("codProjeto"));
            projeto.setDescricaoProjeto(this.result.getString("descricaoProjeto"));
            projeto.setDataEntrega(this.result.getDate("dataentrega"));
            compromisso.setCodCompromisso(this.result.getInt("codCompromisso"));
            
            CompromissoDAO compromissoDAO =  new CompromissoDAO();
            compromissoDAO.buscar(compromisso);
            
            projeto.setCompromisso(compromisso);
            projetos.add(projeto);
         }
         close();
         return projetos;
    }

    @Override
    public Projeto buscar(Projeto projeto) throws SQLException {
        open();
         this.sql = "SELECT * FROM compromisso where codProjeto = ?";
         Projeto retorno = new Projeto();
         
         this.statement = this.connection.prepareStatement(sql);
         this.statement.setInt(1, projeto.getCodProjeto());
         this.result = this.statement.executeQuery();
         if(this.result.next()) {
            Compromisso compromisso =  new Compromisso();
            projeto.setCodProjeto(this.result.getInt("codProjeto"));
            projeto.setDescricaoProjeto(this.result.getString("descricaoProjeto"));
            projeto.setCompromisso(compromisso);
            retorno = projeto;
         }
         close();
         return retorno;
    }
}
