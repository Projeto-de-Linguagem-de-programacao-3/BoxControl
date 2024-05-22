package main.view.components;

import java.awt.CardLayout;

import javax.swing.JPanel;

import main.view.screens.ContainerTelas.TelaCliente;
import main.view.screens.ContainerTelas.TelaHome;
import main.view.screens.ContainerTelas.TelaPedidos;
import main.view.screens.ContainerTelas.TelaProduto;
import main.view.screens.ContainerTelas.TelaVenda;

public class TelaSwitch extends JPanel {
  // As telas de cliente, produto, etc ficam aqui. 
  // Só alterar o nome e chamar op tipo delas ao inves do JPanel genérico
  TelaHome telaHome;
  TelaCliente telaCliente;
  TelaProduto telaProduto;
  TelaPedidos telaPedidos;
  TelaVenda telaVendas;
  
  CardLayout cl;

  public TelaSwitch() {
    // Chama o construtor do JPanel, colocando o layout de card
    super(new CardLayout());

    // Cria uma instância de cardLayout para fazer a alteração de telas
    cl = (CardLayout)(this.getLayout());

    // Adiciona as telas, com um nome para cada. Esse nome é usado para trocar de tela.
    add("Home", getTelaHome());
    add("Clientes", getTelaCliente());
    add("Produtos", getTelaProduto());
    add("Pedidos", getTelaPedidos());
    add("Vendas", getTelaVendas());
  }

  // Getters dos panels
  public TelaHome getTelaHome() {
    if(telaHome == null) {
      telaHome = new TelaHome();
    }
    return telaHome;
  }

  public TelaCliente getTelaCliente() {
    if(telaCliente == null) {
      telaCliente = new TelaCliente();
    }
    return telaCliente;
  }

  public TelaProduto getTelaProduto() {
    if(telaProduto == null) {
      telaProduto = new TelaProduto();
    }
    return telaProduto;
  }

  public TelaPedidos getTelaPedidos() {
    if(telaPedidos == null) {
      telaPedidos = new TelaPedidos();
    }
    return telaPedidos;
  }

  public TelaVenda getTelaVendas() {
    if(telaVendas == null) {
      telaVendas = new TelaVenda();
      
    }
    return telaVendas;
  }

  // Métodos para troca de tela. Eu utilizo a instância do CardLayout para fazer a alteração
  // Nesse JPanel (por isso o this), e passo como segundo parâmetro o nome da tela 
  // Que foi colocado lá em cima.
  public void telaClientes() {
    this.cl.show(this,"Clientes");
  }

  public void telaProdutos() {
    this.cl.show(this,"Produtos");
  }

  public void telaPedidos() {
    this.cl.show(this,"Pedidos");
  }

  public void telaVendas() {
    this.cl.show(this,"Vendas");
  }

  public void telaHome() {
    this.cl.show(this,"Home");
  }

}
