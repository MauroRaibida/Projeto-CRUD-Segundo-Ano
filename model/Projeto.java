package model;

import java.sql.Date;

/**
 * @author Mauro Raibida
 * @version 1.0
 */
public class Projeto {
    private Integer codProjeto;
    private Date dataEntrega;
    private String descricaoProjeto;
    private Compromisso compromisso;

    public Projeto() {
    }


    public Integer getCodProjeto() {
        return codProjeto;
    }

    public void setCodProjeto(Integer codProjeto) {
        this.codProjeto = codProjeto;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }


    public Compromisso getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(Compromisso compromisso) {
        this.compromisso = compromisso;
    }
    
    @Override
    public String toString(){
        return descricaoProjeto;
    }
    
}
