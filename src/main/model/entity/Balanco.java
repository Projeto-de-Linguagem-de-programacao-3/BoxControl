package main.model.entity;

public class Balanco {
  private String numeroClientes;
  private String numeroProdutos;
  private String numeroVendas;
  private String estoqueTotal;
  private String gastoEstoque;
  private String gastoPedidos;
  private String gastoTotal;
  private String faturamento;
  private String lucro;
  
  public Balanco(String numeroClientes, String numeroProdutos, String numeroVendas, String estoqueTotal,
      String gastoEstoque, String gastoPedidos, String gastoTotal, String faturamento, String lucro) {
    this.numeroClientes = numeroClientes;
    this.numeroProdutos = numeroProdutos;
    this.numeroVendas = numeroVendas;
    this.estoqueTotal = estoqueTotal;
    this.gastoEstoque = gastoEstoque;
    this.gastoPedidos = gastoPedidos;
    this.gastoTotal = gastoTotal;
    this.faturamento = faturamento;
    this.lucro = lucro;
  }
  public String getNumeroClientes() {
    return numeroClientes;
  }
  public void setNumeroClientes(String numeroClientes) {
    this.numeroClientes = numeroClientes;
  }
  public String getNumeroProdutos() {
    return numeroProdutos;
  }
  public void setNumeroProdutos(String numeroProdutos) {
    this.numeroProdutos = numeroProdutos;
  }
  public String getNumeroVendas() {
    return numeroVendas;
  }
  public void setNumeroVendas(String numeroVendas) {
    this.numeroVendas = numeroVendas;
  }
  public String getEstoqueTotal() {
    return estoqueTotal;
  }
  public void setEstoqueTotal(String estoqueTotal) {
    this.estoqueTotal = estoqueTotal;
  }
  public String getGastoEstoque() {
    return gastoEstoque;
  }
  public void setGastoEstoque(String gastoEstoque) {
    this.gastoEstoque = gastoEstoque;
  }
  public String getGastoPedidos() {
    return gastoPedidos;
  }
  public void setGastoPedidos(String gastoPedidos) {
    this.gastoPedidos = gastoPedidos;
  }
  public String getGastoTotal() {
    return gastoTotal;
  }
  public void setGastoTotal(String gastoTotal) {
    this.gastoTotal = gastoTotal;
  }
  public String getFaturamento() {
    return faturamento;
  }
  public void setFaturamento(String faturamento) {
    this.faturamento = faturamento;
  }
  public String getLucro() {
    return lucro;
  }
  public void setLucro(String lucro) {
    this.lucro = lucro;
  }

  
}
