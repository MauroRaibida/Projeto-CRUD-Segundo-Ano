package model;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
public class Cliente {
    private Integer codCliente;
    private String nomeCliente;
    private String enderecoCliente;
    private String cpfCliente;
    private String telefoneCliente;

    public Cliente(Integer codCliente, String nomeCliente, String enderecoCliente, String cpfCliente, String telefoneCliente) {
        this.codCliente = codCliente;
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
        this.cpfCliente = cpfCliente;
        this.telefoneCliente = telefoneCliente;
    }

    public Cliente() {
    }
    
    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }
    
    @Override
    public String toString(){
        return this.nomeCliente;
    }
    
}
