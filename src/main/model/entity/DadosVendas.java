package main.model.entity;

import java.util.Map;

public class DadosVendas {

    private String cliente;
    private Map<String, Integer> produtosQuantidade;
    private String formaPagamento;
    private Double valorTotal;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Map<String, Integer> getProdutosQuantidade() {
        return produtosQuantidade;
    }

    public void setProdutosQuantidade(Map<String, Integer> produtosQuantidade) {
        this.produtosQuantidade = produtosQuantidade;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    // private Double calcularValorTotal() {

    // // Aqui você pode implementar a lógica para calcular o valor total da venda
    // // Considerando os produtos vendidos e a forma de pagamento
    // // Por simplicidade, vou apenas somar as quantidades de cada produto
    // double total = 0.0;
    // for (Map.Entry<String, Integer> entry : produtosQuantidade.entrySet()) {
    // // Supondo que cada produto tenha um preço fixo de 10.0 por unidade
    // total += entry.getValue() * 10.0;
    // }

    // // Aplicando desconto se a forma de pagamento for à vista
    // if (formaPagamento.equalsIgnoreCase("À vista")) {
    // total *= 0.78; // Aplicando desconto de 22%
    // }
    // double total = 1.1;
    // return total;
    // }

}
