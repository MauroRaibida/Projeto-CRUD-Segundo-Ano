package dao;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import interfaceDAO.ICompromisso;
import interfaceDAO.IConst;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Compromisso;
import model.Funcionario;

public class CompromissoDAO implements ICompromisso, IConst {
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
    public void inserir(Compromisso compromisso) throws SQLException {
        open();
        this.sql = "INSERT INTO compromisso (codCompromisso, dataCompromisso, codCliente, codFuncionario, atendido, descricaoCompromisso, horario) VALUES (default, ?, ?, ?, ?, ?, ?);";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setDate(1,compromisso.getDataCompromisso());
        this.statement.setInt(2, compromisso.getCliente().getCodCliente());
        this.statement.setInt(3, compromisso.getFuncionario().getCodFuncionario());
        this.statement.setBoolean(4, compromisso.isAtendido());
        this.statement.setString(5, compromisso.getDescricaoCompromisso());
        this.statement.setString(6,compromisso.getHorario());
        this.statement.execute();
        close();
    }

    @Override
    public void remover(Compromisso compromisso) throws SQLException {
        open();
        this.sql = "DELETE FROM compromisso WHERE codCompromisso = ?;";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setInt(1, compromisso.getCodCompromisso());
        this.statement.execute();
        close();
    }

    @Override
    public void alterar(Compromisso compromisso) throws SQLException {
        open();
        this.sql = "UPDATE compromisso SET dataCompromisso = ?, codCliente = ?, codFuncionario = ?, atendido = ?, descricaoCompromisso = ?, horario = ? WHERE codCompromisso = ?;";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setDate(1,compromisso.getDataCompromisso());
        this.statement.setInt(2, compromisso.getCliente().getCodCliente());
        this.statement.setInt(3, compromisso.getFuncionario().getCodFuncionario());
        this.statement.setBoolean(4, compromisso.isAtendido());
        this.statement.setString(5, compromisso.getDescricaoCompromisso());
        this.statement.setString(6,compromisso.getHorario());
        this.statement.setInt(7, compromisso.getCodCompromisso());
        this.statement.execute();
        close();
    }

    @Override
    public List<Compromisso> listar() throws SQLException {
        open();
         this.sql = "SELECT * FROM compromisso";
         ArrayList<Compromisso> compromissos = new ArrayList<>();
         
         this.statement = this.connection.prepareStatement(sql);
         this.result = this.statement.executeQuery();
         while(this.result.next()) {
            Compromisso compromisso =  new Compromisso();

            Cliente cliente = new Cliente();
            Funcionario funcionario = new Funcionario();
            
            
            compromisso.setCodCompromisso(this.result.getInt("codCompromisso"));
            compromisso.setDataCompromisso(this.result.getDate("dataCompromisso"));
            compromisso.setHorario(this.result.getString("horario"));
            compromisso.setAtendido(this.result.getBoolean("atendido"));
            compromisso.setDescricaoCompromisso(this.result.getString("descricaocompromisso"));
            cliente.setCodCliente(this.result.getInt("codCliente"));
            funcionario.setCodFuncionario(this.result.getInt("codFuncionario"));
            
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.buscar(cliente);
            
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.buscar(funcionario);
            
            compromisso.setCliente(cliente);
            compromisso.setFuncionario(funcionario);
            
            compromissos.add(compromisso);
         }
         close();
         return compromissos;
    }
    
     @Override
    public Compromisso buscar(Compromisso compromisso) throws SQLException {
        open();
        this.sql = "SELECT * FROM compromisso where codCompromisso = ?;";
        Compromisso retorno = new Compromisso();
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setInt(1, compromisso.getCodCompromisso());
        this.result = this.statement.executeQuery();
        if (this.result.next()) {
            Funcionario funcionario =  new Funcionario();
            Cliente cliente = new Cliente();
            compromisso.setCodCompromisso(this.result.getInt("codCompromisso"));
            compromisso.setDataCompromisso(this.result.getDate("dataCompromisso"));
            compromisso.setHorario(this.result.getString("horario"));
            compromisso.setAtendido(this.result.getBoolean("atendido"));
            compromisso.setDescricaoCompromisso(this.result.getString("descricaocompromisso"));
            cliente.setCodCliente(this.result.getInt("codCliente"));
            funcionario.setCodFuncionario(this.result.getInt("codFuncionario"));
            compromisso.setCliente(cliente);
            compromisso.setFuncionario(funcionario);
            retorno = compromisso;
            }
        close();
        return retorno;
    }
}
