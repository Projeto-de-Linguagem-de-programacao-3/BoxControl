package main.model.entity;

public class ItemVenda {
  private int idProduto;
  private Produto produto;
  private long idVenda;
  private int quantidade;

  public Produto getProduto() {
    return produto;
  }
  public void setProduto(Produto produto) {
    this.produto = produto;
  }
  public long getIdVenda() {
    return idVenda;
  }
  public void setIdVenda(long idVenda) {
    this.idVenda = idVenda;
  }
  public int getQuantidade() {
    return quantidade;
  }
  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  @Override
  public String toString() {
      if(this.quantidade == 1) {
        return this.getProduto().getNome() + " - " + this.getQuantidade() + " unidade";
      }
      return this.getProduto().getNome() + " - " + this.getQuantidade() + " unidades";
  }
  public int getIdProduto() {
    return idProduto;
  }
  public void setIdProduto(int idProduto) {
    this.idProduto = idProduto;
  }
}
