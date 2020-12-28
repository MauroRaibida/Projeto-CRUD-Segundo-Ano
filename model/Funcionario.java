package model;

/**
 * @author Mauro Raibida
 * @version 1.0
 */
public class Funcionario {

    private Integer codFuncionario;
    private String cargo;
    private String cpfFuncionario;
    private String nomeFuncionario;
    private String telefoneFuncionario;
    private String enderecoFuncionario;

    public Funcionario() {
    }

    public Funcionario(Integer codFuncionario, String cargo, String cpfFuncionario, String nomeFuncionario) {
        this.codFuncionario = codFuncionario;
        this.cargo = cargo;
        this.cpfFuncionario = cpfFuncionario;
        this.nomeFuncionario = nomeFuncionario;
    }
    
    public Integer getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(Integer codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getTelefoneFuncionario() {
        return telefoneFuncionario;
    }

    public void setTelefoneFuncionario(String telefoneFuncionario) {
        this.telefoneFuncionario = telefoneFuncionario;
    }

    public String getEnderecoFuncionario() {
        return enderecoFuncionario;
    }

    public void setEnderecoFuncionario(String enderecoFuncionario) {
        this.enderecoFuncionario = enderecoFuncionario;
    }
    
    @Override
    public String toString(){
        return nomeFuncionario;
    }

}
