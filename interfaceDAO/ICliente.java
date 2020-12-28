package interfaceDAO;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import java.sql.SQLException;
import java.util.List;
import model.Cliente;

public interface ICliente {
    public void inserir(Cliente cliente) throws SQLException;
    public void remover(Cliente cliente) throws SQLException;
    public void alterar(Cliente cliente) throws SQLException;
    public List<Cliente> listar() throws SQLException;
    public Cliente buscar(Cliente cliente) throws SQLException;
}
