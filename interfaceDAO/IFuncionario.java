package interfaceDAO;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import java.sql.SQLException;
import java.util.List;
import model.Funcionario;

public interface IFuncionario {
    public void inserir(Funcionario funcionario) throws SQLException;
    public void remover(Funcionario funcionario) throws SQLException;
    public void alterar(Funcionario funcionario) throws SQLException;
    public List<Funcionario> listar() throws SQLException;
    public Funcionario buscar(Funcionario funcionario) throws SQLException;
    public List<Funcionario> listarDesenvolvedor() throws SQLException;
}
