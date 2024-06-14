package main.model.entity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DadosFinanceiro {
  private String compraProdutos;
  private String vendas;
  private String lucroBruto;
  private String lucroLiquido;
  private String estoqueCompleto;
  private String numeroDeClientes;
  
  public String getLucroBruto() {
    return lucroBruto;
  }
  public void setLucroBruto(String lucroBruto) {
    this.lucroBruto = lucroBruto;
  }
  public String getLucroLiquido() {
    return lucroLiquido;
  }
  public void setLucroLiquido(String lucroLiquido) {
    this.lucroLiquido = lucroLiquido;
  }
  public String getEstoqueCompleto() {
    return estoqueCompleto;
  }
  public void setEstoqueCompleto(String estoqueCompleto) {
    this.estoqueCompleto = estoqueCompleto;
  }
  public String getNumeroDeClientes() {
    return numeroDeClientes;
  }
  public void setNumeroDeClientes(String numeroDeClientes) {
    this.numeroDeClientes = numeroDeClientes;
  }
  public String getCompraProdutos() {
    return compraProdutos;
  }
  public void setCompraProdutos(String compraProdutos) {
    this.compraProdutos = compraProdutos;
  }
  public String getVendas() {
    return vendas;
  }
  public void setVendas(String vendas) {
    this.vendas = vendas;
  }

  public String registraBasico() {
    try {
            FileWriter fWriter = new FileWriter("financeiro.txt", true);
            PrintWriter pWriter = new PrintWriter(fWriter);

            pWriter.println("Valor Estoque: 0");
            pWriter.println("Valor Vendas: 0");
            pWriter.println("Lucro: 0");
            pWriter.println("Numero de Produtos: 0");
            pWriter.println("Numero de Clientes: 0");
            pWriter.println("Numero de Vendas: 0");
            pWriter.close(); // Fechar o PrintWriter para liberar os recursos

            return "Financeiro registrado com sucesso!";

        } catch (IOException e) {
            e.printStackTrace();
            return "Falha ao registrar os detalhes do financeiro. Verifique o arquivo financeiro.txt.";
        }
  }

  public String valorCompraTotal(String id) {
    float valorFinal = 0;
    try {
      File file = new File("Produtos.txt");
      Scanner scanner = new Scanner(file);
      int precoCompra = 0;
      int quantidadeProduto = 0;
      boolean produtoEscolhido = false;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!line.isEmpty()) {
          String[] dados = line.split(":");
          if(line.startsWith("ID")) {
            if (dados[1] == id) {
              produtoEscolhido = true;
            } else {
              produtoEscolhido = false;
            }
          }
          if(line.startsWith("Produto")) {
            continue;
          }
          if(line.startsWith("Preço de Compra") && produtoEscolhido) {
            precoCompra = Integer.parseInt(dados[1].substring(4));
          }
          if(line.startsWith("Quantidade em Estoque") && produtoEscolhido) {
            quantidadeProduto = Integer.parseInt(dados[1].substring(1));
          }
        }
      }
      scanner.close();
      valorFinal = precoCompra * quantidadeProduto;
      System.out.println(valorFinal);
      try {
        Scanner scanner2 = new Scanner(file);
        String valorAtual = "";
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          if (!line.isEmpty()) {
            String[] dados = line.split(":");
            if(line.startsWith("Valor Estoque")) {
              valorAtual = dados[1];
              System.out.println(valorAtual);
              int valorInt = Integer.parseInt(valorAtual);
              float valorFinalInt = valorFinal + valorInt;
              line = "Valor Estoque: "+ String.valueOf(valorFinalInt);
            }
          }
        }
        scanner2.close();
        return "Financeiro registrado com sucesso!";

    } catch (IOException e) {
        e.printStackTrace();
        return "Falha ao registrar os detalhes do financeiro. Verifique o arquivo financeiro.txt.";
    }
    } catch (IOException e) {
            e.printStackTrace();
            return "Falha ao verificar balanço";
        }
  }
}
