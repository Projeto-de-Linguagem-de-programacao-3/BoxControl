package main.model.entity;          

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DadosProduto {

    private static int ultimoID = 0;
    private int id;
    private String nome;
    private String tipo;
    private Double precoCompra;
    private Double precoVenda;
    private String fabricante;
    private String validade;
    private int quantidadeEstoque;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
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

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String salvarTxt() {
        try {
            ultimoID = encontrarUltimoID();
            this.id = ++ultimoID;

            if (idJaExiste()) {
                return "ID já existe. Detalhes do cliente não foram registrados.";
            }

            FileWriter fWriter = new FileWriter("Produtos.txt", true);
            PrintWriter pWriter = new PrintWriter(fWriter);

            pWriter.println("\nProduto:");
            pWriter.println("ID: " + this.id);
            pWriter.println("Nome: " + this.nome);
            pWriter.println("Tipo: " + this.tipo);
            pWriter.println("Preço de Compra: R$ " + this.precoCompra);
            pWriter.println("Preço de Venda: R$ " + this.precoVenda);
            pWriter.println("Fabricante: " + this.fabricante);
            pWriter.println("Validade: " + this.validade);
            pWriter.println("Quantidade em Estoque: " + this.quantidadeEstoque);

            pWriter.close(); // Fechar o PrintWriter para liberar os recursos
            ///DadosFinanceiro dadosFinanceiro = new DadosFinanceiro();
            //dadosFinanceiro.valorCompraTotal(String.valueOf(this.id));
            return "Produto registrado com sucesso.";

        } catch (IOException e) {
            e.printStackTrace();
            return "Falha ao registrar o produto. Verifique o arquivo Produtos.txt.";
        }
    }

    private boolean idJaExiste() {
        try {
            File file = new File("Produtos.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("ID: " + this.id)) {
                    scanner.close();
                    return true;
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static int encontrarUltimoID() {
        int ultimo = 0;
        try {
            File file = new File("Produtos.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (linha.startsWith("ID: ")) {
                    int id = Integer.parseInt(linha.substring(4));
                    if (id > ultimo) {
                        ultimo = id;
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ultimo;
    }

}
