package dao;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import interfaceDAO.IConst;
import interfaceDAO.IFuncionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

public class FuncionarioDAO implements IFuncionario, IConst {
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
    public void inserir(Funcionario funcionario) throws SQLException {
        open();
        this.sql = "INSERT INTO funcionario (codFuncionario,  nomeFuncionario, cpfFuncionario, cargo, enderecoFuncionario, telefoneFuncionario) VALUES (default, ?, ?, ?, ?, ?);";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setString(1,funcionario.getNomeFuncionario());
        this.statement.setString(2, funcionario.getCpfFuncionario());
        this.statement.setString(3,funcionario.getCargo());
        this.statement.setString(4, funcionario.getEnderecoFuncionario());
        this.statement.setString(5,funcionario.getTelefoneFuncionario());
        this.statement.execute();
        close();
    }

    @Override
    public void remover(Funcionario funcionario) throws SQLException {
        open();
        this.sql = "DELETE FROM funcionario WHERE codFuncionario = ?;";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setInt(1,funcionario.getCodFuncionario());
        this.statement.execute();
        close();
    }

    @Override
    public void alterar(Funcionario funcionario) throws SQLException {
        open();
        this.sql = "UPDATE funcionario SET nomeFuncionario = ?, cpfFuncionario = ?, cargo = ?, enderecoFuncionario = ?, telefoneFuncionario = ? WHERE codFuncionario = ?;";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setString(1,funcionario.getNomeFuncionario());
        this.statement.setString(2, funcionario.getCpfFuncionario());
        this.statement.setString(3,funcionario.getCargo());
        this.statement.setString(4, funcionario.getEnderecoFuncionario());
        this.statement.setString(5,funcionario.getTelefoneFuncionario());
        this.statement.setInt(6,funcionario.getCodFuncionario());
        this.statement.execute();
        close();
    }

    @Override
    public List<Funcionario> listar() throws SQLException {
        open();
         this.sql = "SELECT * FROM funcionario";
         ArrayList<Funcionario> funcionarios = new ArrayList<>();
         this.statement = this.connection.prepareStatement(sql);
         this.result = this.statement.executeQuery();
         while(this.result.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setCodFuncionario(this.result.getInt("codFuncionario"));
            funcionario.setNomeFuncionario(this.result.getString("nomeFuncionario"));
            funcionario.setEnderecoFuncionario(this.result.getString("enderecoFuncionario"));
            funcionario.setCpfFuncionario(this.result.getString("cpfFuncionario"));
            funcionario.setTelefoneFuncionario(this.result.getString("telefoneFuncionario"));
            funcionario.setCargo(this.result.getString("cargo"));
            funcionarios.add(funcionario);
         }
        close();
         return funcionarios;
        
    }
    @Override
    public List<Funcionario> listarDesenvolvedor() throws SQLException {
        open();
         this.sql = "SELECT * FROM funcionario where cargo = 'Desenvolvedor(a)'";
         ArrayList<Funcionario> funcionarios = new ArrayList<>();
         this.statement = this.connection.prepareStatement(sql);
         this.result = this.statement.executeQuery();
         while(this.result.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setCodFuncionario(this.result.getInt("codFuncionario"));
            funcionario.setNomeFuncionario(this.result.getString("nomeFuncionario"));
            funcionario.setEnderecoFuncionario(this.result.getString("enderecoFuncionario"));
            funcionario.setCpfFuncionario(this.result.getString("cpfFuncionario"));
            funcionario.setTelefoneFuncionario(this.result.getString("telefoneFuncionario"));
            funcionario.setCargo(this.result.getString("cargo"));
            funcionarios.add(funcionario);
         }
        close();
         return funcionarios;
        
    }

    @Override
    public Funcionario buscar(Funcionario funcionario) throws SQLException {
        open();
        this.sql = "SELECT * FROM funcionario where codFuncionario = ?;";
        Funcionario retorno = new Funcionario();
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setInt(1, funcionario.getCodFuncionario());
        this.result = this.statement.executeQuery();
        if (this.result.next()) {
            funcionario.setCodFuncionario(this.result.getInt("codFuncionario"));
            funcionario.setNomeFuncionario(this.result.getString("nomeFuncionario"));
            funcionario.setEnderecoFuncionario(this.result.getString("enderecoFuncionario"));
            funcionario.setCpfFuncionario(this.result.getString("cpfFuncionario"));
            funcionario.setTelefoneFuncionario(this.result.getString("telefoneFuncionario"));
            funcionario.setCargo(this.result.getString("cargo"));
            retorno = funcionario;
            }
        close();
        return retorno;
    }
}
