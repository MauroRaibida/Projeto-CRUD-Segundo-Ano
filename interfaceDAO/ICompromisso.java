package interfaceDAO;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import java.sql.SQLException;
import java.util.List;
import model.Compromisso;

public interface ICompromisso {
    public void inserir(Compromisso compromisso) throws SQLException;
    public void remover(Compromisso compromisso) throws SQLException;
    public void alterar(Compromisso compromisso) throws SQLException;
    public List<Compromisso> listar() throws SQLException;
    public Compromisso buscar(Compromisso compromisso) throws SQLException;
}
