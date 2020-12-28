package interfaceDAO;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import java.sql.SQLException;
import java.util.List;
import model.Projeto;

public interface IProjeto {
    public void inserir(Projeto projeto) throws SQLException;
    public void remover(Projeto projeto) throws SQLException;
    public void alterar(Projeto projeto) throws SQLException;
    public List<Projeto> listar() throws SQLException;
    public Projeto buscar(Projeto projeto) throws SQLException;
}
