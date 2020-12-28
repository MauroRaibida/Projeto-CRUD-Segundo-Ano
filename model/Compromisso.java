package model;

import java.sql.Date;
import java.sql.Time;

/**
 * @author Mauro Raibida
 * @version 1.0
 */

public class Compromisso {
    private Integer codCompromisso;
    private Cliente cliente;
    private Funcionario funcionario;
    private Date dataCompromisso;
    private String horario;
    private boolean atendido;
    private String descricaoCompromisso;

    public Compromisso() {
    }
    
    public Integer getCodCompromisso() {
        return codCompromisso;
    }

    public void setCodCompromisso(Integer codCompromisso) {
        this.codCompromisso = codCompromisso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataCompromisso() {
        return dataCompromisso;
    }

    public void setDataCompromisso(Date dataCompromisso) {
        this.dataCompromisso = dataCompromisso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public String getDescricaoCompromisso() {
        return descricaoCompromisso;
    }

    public void setDescricaoCompromisso(String descricaoCompromisso) {
        this.descricaoCompromisso = descricaoCompromisso;
    }
    
    @Override
    public String toString(){
        return descricaoCompromisso;
    }
}
