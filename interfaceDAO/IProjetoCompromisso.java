package interfaceDAO;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import java.sql.SQLException;
import java.util.List;
import model.ProjetoCompromisso;

public interface IProjetoCompromisso {
    public void inserir(ProjetoCompromisso projetoCompromisso) throws SQLException;
    public void remover(ProjetoCompromisso projetoCompromisso) throws SQLException;
    public void alterar(ProjetoCompromisso projetoCompromisso) throws SQLException;
    public List<ProjetoCompromisso> listar() throws SQLException; 
}