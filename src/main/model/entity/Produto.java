package main.model.entity;

public class Produto {
    /*
     * Nome: [Insira o nome do produto]
     * Tipo: [Insira o tipo do produto]
     * Preço de Compra: R$ [Insira o preço de compra do produto]
     * Preço de Venda: R$ [Insira o preço de venda do produto]
     * Fabricante: [Insira o fabricante do produto]
     * Validade: [Insira a validade do produto]
     * Quantidade em Estoque: [Insira a quantidade em estoque do produto]
     */
    private String nome;
    private String tipo;
    private double precoCompra;
    private double precoVenda;
    private String fabricante;
    private String validade;
    private Integer quantidadeEstoque;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        return "DadosDoProduto [nome=" + nome + ", tipo=" + tipo + ", precoCompra=" + precoCompra + ", precoVenda="
                + precoVenda + ", fabricante=" + fabricante + ", validade=" + validade + ", quantidadeEstoque="
                + quantidadeEstoque + "]";
    }

}