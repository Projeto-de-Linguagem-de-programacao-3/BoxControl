package main.controller.actions.Inserir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.Client;

import main.model.database.ClienteDatabase;
import main.model.database.VendasDatabase;
import main.model.entity.Cliente;
import main.model.entity.ItemVenda;
import main.model.entity.Produto;
import main.model.entity.Vendas;
import main.view.screens.TelasPrincipais.VendasPrincipal;

public class ButtonVendasSalvarListener implements ActionListener {
  private VendasPrincipal vendasPrincipal;

  public ButtonVendasSalvarListener(VendasPrincipal vendasPrincipal) {
    this.vendasPrincipal = vendasPrincipal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Obter os dados inseridos pelo usuário
    Cliente cliente = (Cliente) vendasPrincipal.getComboBoxCliente().getSelectedItem();
    String formaPagamento = (String) vendasPrincipal.getComboBoxFormaPagamento().getSelectedItem();
    // Converter o texto em um Double para o valor total
    String valorTotalTexto = vendasPrincipal.getTextValorTotal().getText();
    double valorTotal = 0;
    valorTotalTexto = valorTotalTexto.replace(".", "");
    valorTotalTexto = valorTotalTexto.replace(",", ".");
    try {
      valorTotal = Double.parseDouble(valorTotalTexto);
    } catch (Exception error) {
      JOptionPane.showMessageDialog(vendasPrincipal, "Erro! valor total inválido!",
              "Erro de validação", JOptionPane.ERROR_MESSAGE);
      return;
    }

    DefaultListModel<ItemVenda> itens = vendasPrincipal.getItensSelecionados();
    List<ItemVenda> itemsSelecionados = new ArrayList<ItemVenda>();
    if(itens.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Nenhum produto selecionado.", 
      "Erro de validação", JOptionPane.ERROR_MESSAGE);
      return;
    } else {
      for (int i = 0; i < itens.size(); i++) {
        itemsSelecionados.add(itens.getElementAt(i));
      }
    }

    // Criar objeto DadosVendas e definir os dados da venda
    Vendas vendas = new Vendas();
    vendas.setCliente(cliente);
    vendas.setFormaPagamento(formaPagamento);
    vendas.setValorTotal(valorTotal);

    VendasDatabase vendasDatabase = new VendasDatabase();
    vendasDatabase.cadastrarVenda(vendas);
    System.out.println(vendas.getId());
    for (ItemVenda itemVenda : itemsSelecionados ) {
      itemVenda.setIdVenda(vendas.getId());
      vendasDatabase.cadastrarItemVenda(itemVenda);
    }

    // Aqui você pode adicionar lógica para salvar os dados em outro formato ou
    // local, se necessário
    // Por exemplo, salvar em um banco de dados ou enviar para um serviço web

    // Exibir mensagem de sucesso ou falha
    JOptionPane.showMessageDialog(vendasPrincipal, "Venda registrada com sucesso!", "Resultado",
        JOptionPane.INFORMATION_MESSAGE);
    vendasPrincipal.atualizarTabela(); // Atualizar tabela na interface, se necessário
  }
}
