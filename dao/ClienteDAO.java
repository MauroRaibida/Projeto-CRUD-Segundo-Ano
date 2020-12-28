package dao;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import interfaceDAO.ICliente;
import interfaceDAO.IConst;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDAO implements ICliente, IConst {
    //Atributos Classe ClienteDAO
    private String sql;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    
    
    public void open() throws SQLException {
	this.connection = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);//Abre conexão
    }

    private void close() throws SQLException {
           this.connection.close();//Fecha conexão
    }
    //Metodo para inserir um cliente no banco de dados
    @Override
    public void inserir(Cliente cliente) throws SQLException {
        open();
        this.sql = "INSERT INTO cliente (codCLiente, nomeCliente, telefoneCliente, CPFCliente, enderecoCliente) VALUES (default, ?, ?, ?, ?);";
        this.statement = this.connection.prepareStatement(sql);
        //Atribui os valores dos atributos para os "?" do comando SQL
        this.statement.setString(1,cliente.getNomeCliente());
        this.statement.setString(2,cliente.getTelefoneCliente());
        this.statement.setString(3,cliente.getCpfCliente());
        this.statement.setString(4,cliente.getEnderecoCliente());
        this.statement.execute();
        close();
    }
    
    //Metodo para remover um cliente no banco de dados
    @Override
    public void remover(Cliente cliente) throws SQLException {
        open();
        this.sql = "DELETE FROM cliente WHERE codCliente = ?;";
        this.statement = this.connection.prepareStatement(sql);
        //Atribui os valores dos atributos para os "?" do comando SQL
        this.statement.setInt(1, cliente.getCodCliente());
        this.statement.execute();
        close();
    }

    //Metodo para alterar um cliente no banco de dados
    @Override
    public void alterar(Cliente cliente) throws SQLException {
        open();
        this.sql = "UPDATE cliente SET nomeCliente = ?, telefoneCliente = ?, CPFCliente = ?, enderecoCliente = ? WHERE codCliente = ?;";
        this.statement = this.connection.prepareStatement(sql);
        //Atribui os valores dos atributos para os "?" do comando SQL
        this.statement.setString(1,cliente.getNomeCliente());
        this.statement.setString(2,cliente.getTelefoneCliente());
        this.statement.setString(3,cliente.getCpfCliente());
        this.statement.setString(4,cliente.getEnderecoCliente());
        this.statement.setInt(5, cliente.getCodCliente());
        this.statement.execute();
        close();
    }

    @Override
    public List<Cliente> listar() throws SQLException {
        open();
         this.sql = "SELECT * FROM cliente";
         ArrayList<Cliente> clientes = new ArrayList<>();
         this.statement = this.connection.prepareStatement(sql);
         this.result = this.statement.executeQuery();
         while(this.result.next()) {
            Cliente cliente = new Cliente();
            cliente.setCodCliente(this.result.getInt("codCliente"));
            cliente.setNomeCliente(this.result.getString("nomeCliente"));
            cliente.setEnderecoCliente(this.result.getString("enderecoCliente"));
            cliente.setCpfCliente(this.result.getString("cpfCliente"));
            cliente.setTelefoneCliente(this.result.getString("telefoneCliente"));
            clientes.add(cliente);
         }
         close();
         return clientes;
    }

    @Override
    public Cliente buscar(Cliente cliente) throws SQLException {
        open();
        this.sql = "SELECT * FROM cliente where codCLiente = ?;";
        Cliente retorno = new Cliente();
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setInt(1, cliente.getCodCliente());
        this.result = this.statement.executeQuery();
        if (this.result.next()) {
                cliente.setCodCliente(this.result.getInt("codCliente"));
                cliente.setNomeCliente(this.result.getString("nomeCliente"));
                cliente.setEnderecoCliente(this.result.getString("enderecoCliente"));
                cliente.setCpfCliente(this.result.getString("cpfCliente"));
                cliente.setTelefoneCliente(this.result.getString("telefoneCliente"));
                retorno = cliente;
            }
        close();
        return retorno;
    }
}
