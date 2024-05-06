package main.model.entity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    private Double calcularValorTotal() {
        /*
         * // Aqui você pode implementar a lógica para calcular o valor total da venda
         * // Considerando os produtos vendidos e a forma de pagamento
         * // Por simplicidade, vou apenas somar as quantidades de cada produto
         * double total = 0.0;
         * for (Map.Entry<String, Integer> entry : produtosQuantidade.entrySet()) {
         * // Supondo que cada produto tenha um preço fixo de 10.0 por unidade
         * total += entry.getValue() * 10.0;
         * }
         * 
         * // Aplicando desconto se a forma de pagamento for à vista
         * if (formaPagamento.equalsIgnoreCase("À vista")) {
         * total *= 0.78; // Aplicando desconto de 22%
         * }
         */ double total = 1.1;
        return total;
    }

    public String salvarTxt() {
        try {
            FileWriter fWriter = new FileWriter("Vendas.txt", true);
            PrintWriter pWriter = new PrintWriter(fWriter);

            // Calcular o valor total antes de salvar a venda
            double total = calcularValorTotal();

            pWriter.println("\nInformações da venda");
            pWriter.println("Cliente: " + this.cliente);
            pWriter.println("Produtos & Quantidade: " + converterParaTexto(produtosQuantidade)); // Adicionando a lista
                                                                                                 // de produtos e
                                                                                                 // quantidades
            pWriter.println("Forma de Pagamento: " + this.formaPagamento);

            // Adicionando informações adicionais dependendo da forma de pagamento
            if (formaPagamento.equalsIgnoreCase("Cartão")) {
                pWriter.println("Cartão: AINDA QUER DIVIR EM 12X");
            } else if (formaPagamento.equalsIgnoreCase("Fiado")) {
                pWriter.println("Fiado: TU É DOIDO, AINDA QUER FIADO");
            } else if (formaPagamento.startsWith("À vista")) {
                pWriter.println("À vista: 22% de desconto");
            }

            pWriter.println("Valor Total: " + total); // Adicionando o valor total calculado

            pWriter.close(); // Fechar o PrintWriter para liberar os recursos

            return "Venda registrada com sucesso."; // isso vai sair no terminal para saber se foi salvo corretamente. 

        } catch (IOException e) {
            e.printStackTrace();
            return "Falha ao registrar a venda. Verifique o arquivo Vendas.txt.";
        }
    }

    private String converterParaTexto(Map<String, Integer> mapa) {
        StringBuilder texto = new StringBuilder();
        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
            texto.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }
        // Remover a última vírgula e espaço adicionados
        if (texto.length() > 0) {
            texto.setLength(texto.length() - 2);
        }
        return texto.toString();
    }

}
