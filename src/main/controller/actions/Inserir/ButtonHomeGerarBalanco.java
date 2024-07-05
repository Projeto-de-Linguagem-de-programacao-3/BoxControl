package main.controller.actions.Inserir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.model.database.BalancoDatabase;
import main.model.entity.Balanco;
import main.view.screens.TelasPrincipais.HomePrincipal;

public class ButtonHomeGerarBalanco implements ActionListener {
  private HomePrincipal homePrincipal;

  public ButtonHomeGerarBalanco(HomePrincipal homePrincipal) {
    this.homePrincipal = homePrincipal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    BalancoDatabase balancoDatabase = new BalancoDatabase();
    Balanco balanco = balancoDatabase.consultaResumo();
    homePrincipal.setBalanco(balanco);
    homePrincipal.gerarBalanco();
    System.out.println("Numero de clientes:" + balanco.getNumeroClientes());
    System.out.println("Numero de produtos:" + balanco.getNumeroProdutos());
    System.out.println("Numero de vendas:" + balanco.getNumeroVendas());
    System.out.println("Estoque total:" + balanco.getEstoqueTotal());
    System.out.println("Gastos Estoque:" + balanco.getGastoEstoque());
    System.out.println("Gastos Pedidos:" + balanco.getGastoPedidos());
    System.out.println("Gastos Totais:" + balanco.getGastoTotal());
    System.out.println("Faturamento:" + balanco.getFaturamento());
    System.out.println("Lucro:" + balanco.getLucro());
  }
  
}
