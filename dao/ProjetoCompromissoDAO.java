package dao;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import interfaceDAO.IConst;
import interfaceDAO.IProjetoCompromisso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.ProjetoCompromisso;

public class ProjetoCompromissoDAO implements IProjetoCompromisso, IConst {
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
    public void inserir(ProjetoCompromisso projetoCompromisso) throws SQLException {
        open();
        this.sql = "INSERT INTO projetocompromisso (codCompromisso, codProjeto) VALUES (?, ?);";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setInt(1, projetoCompromisso.getCompromisso().getCodCompromisso());
        this.statement.setInt(2, projetoCompromisso.getProjeto().getCodProjeto());
        this.statement.execute();
        close();
    }

    @Override
    public void remover(ProjetoCompromisso projetoCompromisso) throws SQLException {
        open();
        this.sql = "DELETE FROM projetocompromisso WHERE codCompromisso = ? and codProjeto = ?;";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setInt(1, projetoCompromisso.getCompromisso().getCodCompromisso());
        this.statement.setInt(2, projetoCompromisso.getProjeto().getCodProjeto());
        this.statement.execute();
        close();
    }

    @Override
    public void alterar(ProjetoCompromisso projetoCompromisso) throws SQLException {
        open();
        this.sql = "UPDATE projetocompromisso SET codCompromisso = ?, codProjeto = ? WHERE codCompromisso = ? and codProjeto = ?;";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setInt(1, projetoCompromisso.getCompromisso().getCodCompromisso());
        this.statement.setInt(2, projetoCompromisso.getProjeto().getCodProjeto());
        this.statement.setInt(3, projetoCompromisso.getCompromisso().getCodCompromisso());
        this.statement.setInt(4, projetoCompromisso.getProjeto().getCodProjeto());
        this.statement.execute();
        close();
    }

    @Override
    public List<ProjetoCompromisso> listar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
