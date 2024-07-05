package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import main.controller.actions.ButtonVendasSalvarListener;
import main.model.database.ClienteDatabase;
import main.model.database.ProdutoDatabase;
import main.model.database.VendasDatabase;
import main.model.entity.Cliente;
import main.model.entity.ItemVenda;
import main.model.entity.Produto;
import main.view.components.StyleGuide;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// import java.util.List;
import java.util.Map;

public class VendasPrincipal extends JPanel {
  // Databases que serão usadas
  private ClienteDatabase clienteDatabase = new ClienteDatabase();
  private ProdutoDatabase produtoDatabase = new ProdutoDatabase();

  private JComboBox<Cliente> comboBoxCliente;
  private JComboBox<String> comboBoxFormaPagamento;
  private JFormattedTextField textValorTotal;

  private JLabel labelProduto;
  private JLabel labelQuantidade;
  private JLabel labelCliente;
  private JLabel labelFormaPagamento;
  private JLabel labelValorTotal;

  private JButton btnSalvar;
  private JButton btnAtualizarListas;
  private JButton btnEstornarCompra;
  private JTable tabelaCliente;

  // Elementos Caixa Produtos
  JPanel painelEsquerda;
  JPanel painelDireita;
  private JLabel labelDisponiveis;
  private JList<Produto> listDisponiveis;
  private DefaultListModel<Produto> produtoDisponiveis;
  private JButton btnAdicionar;
  private JLabel labelSelecionadas;
  private JList<ItemVenda> listSelecionadas;
  private DefaultListModel<ItemVenda> itensSelecionados;
  private JButton btnRemover;

  public VendasPrincipal() {
    super();
    setBackground(StyleGuide.bgScreen);
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 1;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 10, 5, 10);

    constraints.gridx = 0;
    constraints.gridy = 0;
    add(getLabelCliente(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 1;
    add(getComboBoxCliente(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    add(getLabelProduto(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 3;
    add(getPainelEsquerda(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 4;
    add(getPainelDireita(), constraints);

    btnAdicionar.addActionListener( (ActionEvent e)->{
      List<Produto> selecionadas = listDisponiveis.getSelectedValuesList();
      for(int i =0;i<selecionadas.size();i++){
          Produto produto = selecionadas.get(i);
          ItemVenda itemVenda = new ItemVenda();
          itemVenda.setProduto(produto);
          int valorNumerico = 0;
          boolean valorValido = false;
          while (!valorValido) {
              String valorInserido = JOptionPane.showInputDialog(null, "Digite a quantidade desse produto:", "Entrada de Valor", JOptionPane.QUESTION_MESSAGE);
              if (valorInserido != null) {
                  try {
                      valorNumerico = Integer.parseInt(valorInserido);
                      if (valorNumerico <= 0) {
                        JOptionPane.showMessageDialog(null, "O valor não pode ser negativo.", "Erro", JOptionPane.ERROR_MESSAGE);
                      } else if (valorNumerico > itemVenda.getProduto().getQuantidadeEstoque()) {
                          JOptionPane.showMessageDialog(null, "O valor não pode ser maior que a quantidade existente.", "Erro", JOptionPane.ERROR_MESSAGE);
                      } else {
                          valorValido = true;
                      }
                  } catch (NumberFormatException error) {
                      JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                  }
              } else {
                  JOptionPane.showMessageDialog(null, "Entrada cancelada.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                  return;
              }
          }
          itemVenda.setQuantidade(valorNumerico);
          System.out.println(itemVenda.getQuantidade());
          itensSelecionados.addElement(itemVenda);
          produtoDisponiveis.removeElement(produto);
          getValorProdutos();
      }
    });
    btnRemover.addActionListener((ActionEvent e)->{
        List<ItemVenda> selecionadas = listSelecionadas.getSelectedValuesList();
        for(int i=0;i<selecionadas.size();i++){
            ItemVenda itemVenda = selecionadas.get(i);
            Produto produto = itemVenda.getProduto();
            produtoDisponiveis.addElement(produto);
            itensSelecionados.removeElement(itemVenda);
        }
    });
    carregaProdutos();

    constraints.gridx = 0;
    constraints.gridy = 5;
    add(getLabelFormaPagamento(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 6;
    add(getComboBoxFormaPagamento(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 8;
    add(getLabelValorTotal(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 9;
    add(getTextValorTotal(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 10;
    add(getBtnSalvar(), constraints);
    ButtonVendasSalvarListener buttonVendasSalvarListener = new ButtonVendasSalvarListener(this);
    btnSalvar.addActionListener(buttonVendasSalvarListener);

    constraints.gridx = 2;
    constraints.gridy = 0;
    add(getBtnAtualizarListas(), constraints);
    btnAtualizarListas.addActionListener((ActionEvent e) -> {
      carregarCliente();
      carregaProdutos();
    });

    constraints.gridx = 2;
    constraints.gridy = 1;
    constraints.gridwidth = 2;
    constraints.gridheight = 9;
    JScrollPane scrollPane = new JScrollPane(getTabelaCliente());
    scrollPane.setMinimumSize(new Dimension(100, 500));
    add(scrollPane, constraints);

    constraints.gridx = 2;
    constraints.gridy = 10;
    add(getBtnEstornarCompra(), constraints);
    btnEstornarCompra.addActionListener((ActionEvent e) -> {
      int resposta = JOptionPane.showConfirmDialog(this, "Deseja estornar essa venda?", "Confirmação", JOptionPane.YES_NO_OPTION);
      if(resposta == JOptionPane.YES_OPTION) {
        int selectedRow = getTabelaCliente().getSelectedRow();
        if(selectedRow == -1) {
          JOptionPane.showMessageDialog(this, "Nenhuma venda selecionada!", "Erro de validação", JOptionPane.ERROR_MESSAGE);
          return;
        } else {
          int venda = (Integer) getTabelaCliente().getModel().getValueAt(selectedRow, 0);
          VendasDatabase vendasDatabase = new VendasDatabase();
          ProdutoDatabase produtoDatabase = new ProdutoDatabase();
          List<ItemVenda> produtosDaVenda = vendasDatabase.consultarItemVendas(venda);
          System.out.println(produtosDaVenda);
          // for(ItemVenda produtoVenda : produtosDaVenda) {
          //   produtoDatabase.estornarCompra(produtoVenda.getQuantidade(), produtoVenda.getIdProduto());
          // }
          // vendasDatabase.deletaItemVenda(venda);
          // vendasDatabase.deletaVenda(venda);
          // atualizarTabela();
        }
      } else if (resposta == JOptionPane.NO_OPTION) {
        return;
      }
    });
  }

  public JComboBox<Cliente> getComboBoxCliente() {
    if (comboBoxCliente == null) {
      comboBoxCliente = new JComboBox<>();
      comboBoxCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
      carregarCliente();
    }
    return comboBoxCliente;
  }

  public JComboBox<String> getComboBoxFormaPagamento() {
    if (comboBoxFormaPagamento == null) {
      String[] formasPagamento = { "À vista: 2% de desconto", "Cartão", "Fiado" };
      comboBoxFormaPagamento = new JComboBox<>(formasPagamento);
      StyleGuide.formataComponente(comboBoxFormaPagamento);
    }
    return comboBoxFormaPagamento;
  }

  public JFormattedTextField getTextValorTotal() {
    if (textValorTotal == null) {
      textValorTotal = new JFormattedTextField();
      StyleGuide.formataComponente(textValorTotal);
      textValorTotal.setEditable(false);
    }
    return textValorTotal;
  }

  public JLabel getLabelProduto() {
    if (labelProduto == null) {
      labelProduto = new JLabel("Produto:");
      StyleGuide.formataComponente(labelProduto);
    }
    return labelProduto;
  }

  public JLabel getLabelQuantidade() {
    if (labelQuantidade == null) {
      labelQuantidade = new JLabel("Quantidade:");
      StyleGuide.formataComponente(labelQuantidade);
    }
    return labelQuantidade;
  }

  public JLabel getLabelCliente() {
    if (labelCliente == null) {
      labelCliente = new JLabel("Cliente:");
      StyleGuide.formataComponente(labelCliente);
    }
    return labelCliente;
  }

  public JLabel getLabelFormaPagamento() {
    if (labelFormaPagamento == null) {
      labelFormaPagamento = new JLabel("Forma de Pagamento:");
      StyleGuide.formataComponente(labelFormaPagamento);
    }
    return labelFormaPagamento;
  }

  public JLabel getLabelValorTotal() {
    if (labelValorTotal == null) {
      labelValorTotal = new JLabel("Valor Total:");
      StyleGuide.formataComponente(labelValorTotal);
    }
    return labelValorTotal;
  }

  public JButton getBtnSalvar() {
    if (btnSalvar == null) {
      btnSalvar = new JButton("Salvar venda");
      StyleGuide.formataComponente(btnSalvar);
    }
    return btnSalvar;
  }

  public JButton getBtnAtualizarListas() {
    if(btnAtualizarListas == null) {
        btnAtualizarListas = new JButton("Atualizar seletores");
        StyleGuide.formataComponente(btnAtualizarListas);
    }
    return btnAtualizarListas;
}

  public JTable getTabelaCliente() {
    if (tabelaCliente == null) {
      String[] titulos = { "ID", "Cliente", "Produtos", "Quantidade", "Forma de Pagamento", "Valor Total" };
      DefaultTableModel modelo = new DefaultTableModel(titulos, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
          return false;
        }
      };
      tabelaCliente = new JTable(modelo);
      atualizarTabela();
    }
    return tabelaCliente;
  }

  public JButton getBtnEstornarCompra() {
    if(btnEstornarCompra == null) {
      btnEstornarCompra = new JButton("Estornar Compra Selecionada");
    }
    return btnEstornarCompra;
  }

  public void atualizarTabela() {
    DefaultTableModel modelo = (DefaultTableModel) getTabelaCliente().getModel();
    modelo.setRowCount(0); // Limpa a tabela antes de adicionar novos dados
    VendasDatabase vendasDatabase = new VendasDatabase();
    List<Object[]> dadosCliente = vendasDatabase.consultarVendas();
    for (Object[] linha : dadosCliente) {
    modelo.addRow(linha);
    }
  }

  public Map<String, Integer> converterParaMapa(String texto) {
    Map<String, Integer> mapa = new HashMap<>();

    // Divida o texto em pares de chave e valor
    String[] pares = texto.split(",");

    // Para cada par de chave e valor, divida novamente para obter o nome do produto
    // e sua quantidade
    for (String par : pares) {
      String[] partes = par.trim().split(":");
      if (partes.length == 2) {
        String produto = partes[0].trim();
        int quantidade = Integer.parseInt(partes[1].trim());
        mapa.put(produto, quantidade);
      }
    }

    return mapa;
  }

  private void carregarCliente() {
    List<Cliente> clientes = clienteDatabase.listarClientesComboBox();
    comboBoxCliente.removeAllItems();       
    for (Cliente cliente : clientes) {
      comboBoxCliente.addItem(cliente);
    }  
  }
  // ---------------------------------------------------------------------------------------

  public DefaultListModel<ItemVenda> getItensSelecionados() {
    return itensSelecionados;
  }

  public JLabel getlabelDisponiveis() {
    if(labelDisponiveis == null) {
      labelDisponiveis = new JLabel("Produtos disponíveis:");
    }
    return labelDisponiveis;
  }

  public JLabel getLabelSelecionados() {
    if(labelSelecionadas == null) {
      labelSelecionadas = new JLabel("Produtos selecionados:");
    }
    return labelSelecionadas;
  }

  public JList<Produto> getListDisponiveis() {
    if(listDisponiveis == null) {
      produtoDisponiveis = new DefaultListModel<>();
      listDisponiveis = new JList<Produto>(produtoDisponiveis);
      listDisponiveis.setVisibleRowCount(5);
      listDisponiveis.setFixedCellWidth(200);
      listDisponiveis.setFixedCellHeight(15);
      listDisponiveis.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    return listDisponiveis;
  }

  public JList<ItemVenda> getListSelecionadas() {
    if(listSelecionadas == null) {
      itensSelecionados = new DefaultListModel<ItemVenda>();
      listSelecionadas = new JList<ItemVenda>(itensSelecionados);
      listSelecionadas.setVisibleRowCount(5);
      listSelecionadas.setFixedCellWidth(200);
      listSelecionadas.setFixedCellHeight(15);
      listSelecionadas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    return listSelecionadas;
  }

  public JButton getBtnAdicionar() {
    if(btnAdicionar == null) {
      btnAdicionar = new JButton("adicionar >>");
    }
    return btnAdicionar;
  }

  public JButton getBtnRemover() {
    if(btnRemover == null) {
      btnRemover = new JButton("<< remover");
    }
    return btnRemover;
  }

  public JPanel getPainelEsquerda() {
    if(painelEsquerda == null) {
      painelEsquerda = new JPanel(new BorderLayout());
      painelEsquerda.add(getlabelDisponiveis(), BorderLayout.NORTH);
      painelEsquerda.add(new JScrollPane(getListDisponiveis()), BorderLayout.CENTER);
      painelEsquerda.add(getBtnAdicionar(), BorderLayout.SOUTH);
    }
    return painelEsquerda;
  }

  public JPanel getPainelDireita() {
    if(painelDireita == null) {
      painelDireita = new JPanel(new BorderLayout());
      painelDireita.add(getLabelSelecionados(), BorderLayout.NORTH);
      painelDireita.add(new JScrollPane(getListSelecionadas()), BorderLayout.CENTER);
      painelDireita.add(getBtnRemover(), BorderLayout.SOUTH);
    }
    return painelDireita;
  }

  public void carregaProdutos() {
    List<Produto> produtosQuery = produtoDatabase.listarProdutosVenda();
    produtoDisponiveis.removeAllElements();
    for (Produto produto : produtosQuery ) {
      produtoDisponiveis.addElement(produto);
    }
    itensSelecionados.removeAllElements();
  }

  public void getValorProdutos() {
    DefaultListModel<ItemVenda> itens = getItensSelecionados();
    Double valorTotal = 0.00;
    if(!itens.isEmpty()) {
      List<ItemVenda> itemsSelecionados = new ArrayList<ItemVenda>();
      for (int i = 0; i < itens.size(); i++) {
        itemsSelecionados.add(itens.getElementAt(i));
      }
      for (ItemVenda itemVenda : itemsSelecionados ) {
        Double valorItemVenda = itemVenda.getQuantidade() * itemVenda.getProduto().getPrecoVenda();
        valorTotal += valorItemVenda;
      }
      textValorTotal.setValue(valorTotal);
    }
  }
}
