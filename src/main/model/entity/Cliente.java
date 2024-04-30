package main.model.entity;

public class Cliente {
    /*
     * Cliente:
     * Nome: [Insira o nome do cliente]
     * CPF: [Insira o CPF do cliente]
     * RG: [Insira o RG do cliente]
     * Data de Nascimento: [Insira a data de nascimento do cliente]
     * Limite de Crédito: R$ [Insira o limite de crédito do cliente]
     */

     private String nome;
     private String cpf;
     private String rg;
     private String dataDeNacimento;
     private Double limiteDeCredito;
 
     public String getNome() {
         return nome;
     }
 
     public void setNome(String nome) {
         this.nome = nome;
     }
 
     public String getCpf() {
         return cpf;
     }
 
     public void setCpf(String cpf) {
         this.cpf = cpf;
     }
 
     public String getRg() {
         return rg;
     }
 
     public void setRg(String rg) {
         this.rg = rg;
     }
 
     public String getDataDeNacimento() {
         return dataDeNacimento;
     }
 
     public void setDataDeNacimento(String dataDeNacimento) {
         this.dataDeNacimento = dataDeNacimento;
     }
 
     public Double getLimiteDeCredito() {
         return limiteDeCredito;
     }
 
     public void setLimiteDeCredito(Double limiteDeCredito) {
         this.limiteDeCredito = limiteDeCredito;
     }
 
     @Override
     public String toString() {
         return "DadosDoCliente nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", dataDeNacimento=" + dataDeNacimento
                 + ", limiteDeCredito=" + limiteDeCredito + "]";
     }
 
 }
 
