package main.model.entity;

public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private String rg;
    private String dataNascimento;
    private Double limiteCredito;

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public boolean isValid() {
        return !isNullOrEmpty(nome) && 
               !isNullOrEmpty(cpf) && 
               !isNullOrEmpty(rg) && 
               !isNullOrEmpty(dataNascimento) && 
               limiteCredito != null;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Override
    public String toString() {
        return this.getNome();
    }

}
