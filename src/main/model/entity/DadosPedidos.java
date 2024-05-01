package main.model.entity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DadosPedidos {

    private String produto;
    private double precoCompra;
    private String fabricante;
    private String validade;
    private int quantidade;

    // Getters e Setters
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Método para imprimir os detalhes do pedido em um arquivo de texto
    public String salvarTxt() {
        try {
            FileWriter fWriter = new FileWriter("Pedido.txt", true);
            PrintWriter pWriter = new PrintWriter(fWriter);

            pWriter.println("\nPedido de Produto:");
            pWriter.println("Produto: " + this.produto);
            pWriter.println("Preço de Compra: R$ " + this.precoCompra);
            pWriter.println("Fabricante: " + this.fabricante);
            pWriter.println("Validade: " + this.validade);
            pWriter.println("Quantidade: " + this.quantidade);

            pWriter.close(); // Fechar o PrintWriter para liberar os recursos

            return "Detalhes do pedido registrados com sucesso.";

        } catch (IOException e) {
            e.printStackTrace();
            return "Falha ao registrar os detalhes do pedido. Verifique o arquivo Pedido.txt.";
        }
    }
}
