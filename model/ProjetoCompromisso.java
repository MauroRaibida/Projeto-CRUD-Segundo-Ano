package model;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
public class ProjetoCompromisso {
    private Compromisso compromisso;
    private Projeto projeto;

    public ProjetoCompromisso() {
    }

    public ProjetoCompromisso(Compromisso compromisso, Projeto projeto) {
        this.compromisso = compromisso;
        this.projeto = projeto;
    }
    
    public Compromisso getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(Compromisso compromisso) {
        this.compromisso = compromisso;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }    
}
