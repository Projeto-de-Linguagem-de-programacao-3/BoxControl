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
// import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// import java.util.List;
import java.util.Map;

public class VendasPrincipal extends JPanel {
  private JComboBox<Produto> comboBoxProdutos;
  private JFormattedTextField textQuantidade;
  // private CaixaProdutos caixaProdutos;
  private JComboBox<Cliente> comboBoxCliente;
  private JComboBox<String> comboBoxFormaPagamento;
  private JFormattedTextField textValorTotal;

  private JLabel labelProduto;
  private JLabel labelQuantidade;
  private JLabel labelCliente;
  private JLabel labelFormaPagamento;
  private JLabel labelValorTotal;

  private JButton btnSalvar;

  private JTable tabelaCliente;

  private ClienteDatabase clienteDatabase = new ClienteDatabase();
  private ProdutoDatabase produtoDatabase = new ProdutoDatabase();

  // Elementos Caixa Produtos
  JComponent caixaProdutos = new JPanel();
  JPanel painelEsquerda;
  JPanel painelDireita;
  private JLabel labelDisponiveis;
  private JList listDisponiveis;
  private DefaultListModel<String> modelDisponiveis;
  private JButton btnAdicionar;
  private JLabel labelSelecionadas;
  private JList listSelecionadas;
  private DefaultListModel<String> modelSelecionadas;
  private JButton btnRemover;

  public VendasPrincipal() {
    super();
    setBackground(StyleGuide.bgScreen);
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 1;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10, 10, 10, 10);

    ActionListener atualizarValorTotal = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          try {
              int quantidade = Integer.parseInt(textQuantidade.getText());
              Produto produtoSelecionado = (Produto) comboBoxProdutos.getSelectedItem();
              Cliente clienteSelecionado = (Cliente) comboBoxCliente.getSelectedItem();
              String formaPagamento = (String) comboBoxFormaPagamento.getSelectedItem();
              if (produtoSelecionado != null && clienteSelecionado != null) {
                  double valorTotal = quantidade * produtoSelecionado.getPrecoVenda();
                  if ("À vista".equals(formaPagamento)) {
                    valorTotal *= 0.98; // Aplicar 2% de desconto
                  } else if ("Fiado".equals(formaPagamento)) {
                      if (valorTotal > clienteSelecionado.getLimiteCredito()) {
                          textValorTotal.setText("Limite insuficiente!");
                          return;
                      }
                  }
                  textValorTotal.setText(String.format("%.2f", valorTotal));
              }
          } catch (NumberFormatException ex) {
            textValorTotal.setText("0.00");
          }
      }
    };

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
    add(getComboBoxProdutos(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 4;
    add(getLabelQuantidade(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 5;
    add(getTextQuantidade(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 6;
    add(getLabelFormaPagamento(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 7;
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
    constraints.gridwidth = 2;
    constraints.gridheight = 9;
    JScrollPane scrollPane = new JScrollPane(getTabelaCliente());
    scrollPane.setMinimumSize(new Dimension(100, 500));
    add(scrollPane, constraints);

    textQuantidade.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        atualizarValorTotal.actionPerformed(null);
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
          atualizarValorTotal.actionPerformed(null);
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
          atualizarValorTotal.actionPerformed(null);
      }
  });
  comboBoxProdutos.addActionListener(atualizarValorTotal);
  comboBoxCliente.addActionListener(atualizarValorTotal);
  comboBoxFormaPagamento.addActionListener(atualizarValorTotal);

  }

  public JComboBox<Produto> getComboBoxProdutos() {
    if (comboBoxProdutos == null) {
      comboBoxProdutos = new JComboBox<>();
      comboBoxProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
      carregarProdutos();
    }
    return comboBoxProdutos;
  }

  public JFormattedTextField getTextQuantidade() {
    if (textQuantidade == null) {
      NumberFormat numberFormat = NumberFormat.getNumberInstance();
      numberFormat.setGroupingUsed(false);
      NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
      numberFormatter.setValueClass(Double.class);
      numberFormatter.setAllowsInvalid(false); // Não permite caracteres não numéricos
      numberFormatter.setMinimum(0.0); // Define um valor mínimo
      textQuantidade = new JFormattedTextField(numberFormatter);
      StyleGuide.formataComponente(textQuantidade);
    }
    return textQuantidade;
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

  public JTable getTabelaCliente() {
    if (tabelaCliente == null) {
      String[] titulos = { "Cliente", "Produtos", "Quantidade", "Forma de Pagamento", "Valor Total" };
      DefaultTableModel modelo = new DefaultTableModel(titulos, 0);
      tabelaCliente = new JTable(modelo);
      atualizarTabela();
    }
    return tabelaCliente;
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

  private void carregarProdutos() {
    List<Produto> produtos = produtoDatabase.listarProdutosVenda();
    comboBoxProdutos.removeAllItems();
    for (Produto produto : produtos) {
      comboBoxProdutos.addItem(produto);
    }
  }

  // METODO PARA TENTAR COLOCAR VARIOS PRODUTOS
  public void caixaProdutos() {
    GridLayout gridLayout = new GridLayout(0, 2);
    gridLayout.setHgap(15);
    caixaProdutos.setLayout(gridLayout);

    // inicializa os componentes
    labelDisponiveis = new JLabel("Disciplinas disponíveis:");
    //modelDisponiveis = new DefaultListModel<>();
    // modelDisponiveis.addElement("Algoritmos");
    // modelDisponiveis.addElement("Sistemas Operacionais");
    // modelDisponiveis.addElement("Estruturas de Dados");
    // modelDisponiveis.addElement("Armaz. de Informações");
    // modelDisponiveis.addElement("Redes de Computadores");
    // modelDisponiveis.addElement("Programação Web");
    // modelDisponiveis.addElement("Banco de Dados");
    //listDisponiveis = new JList(modelDisponiveis);
    listDisponiveis.setVisibleRowCount(5);
    listDisponiveis.setFixedCellWidth(200);
    listDisponiveis.setFixedCellHeight(15);
    listDisponiveis.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    btnAdicionar = new JButton("adicionar >>");
    btnAdicionar.addActionListener( (ActionEvent e)->{
        List<String> selecionadas = listDisponiveis.getSelectedValuesList();
        for(int i =0;i<selecionadas.size();i++){
            String disciplina = selecionadas.get(i);
            modelSelecionadas.addElement(disciplina);
            modelDisponiveis.removeElement(disciplina);
        }
    });

    labelSelecionadas = new JLabel("Disciplinas selecionadas:");
    modelSelecionadas = new DefaultListModel<String>();
    listSelecionadas = new JList(modelSelecionadas);
    listSelecionadas.setVisibleRowCount(5);
    listSelecionadas.setFixedCellWidth(200);
    listSelecionadas.setFixedCellHeight(15);
    listSelecionadas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    btnRemover = new JButton("<< remover");
    btnRemover.addActionListener((ActionEvent e)->{
        List<String> selecionadas = listSelecionadas.getSelectedValuesList();
        for(int i=0;i<selecionadas.size();i++){
            String disciplina = selecionadas.get(i);
            modelDisponiveis.addElement(disciplina);
            modelSelecionadas.removeElement(disciplina);
        }
    });

    // configurar o layout da esquerda
    painelEsquerda = new JPanel(new BorderLayout());
    painelEsquerda.add(labelDisponiveis, BorderLayout.NORTH);
    painelEsquerda.add(new JScrollPane(listDisponiveis), BorderLayout.CENTER);
    painelEsquerda.add(btnAdicionar, BorderLayout.SOUTH);

    // configurar o layout da direita
    painelDireita = new JPanel(new BorderLayout());
    painelDireita.add(labelSelecionadas, BorderLayout.NORTH);
    painelDireita.add(new JScrollPane(listSelecionadas), BorderLayout.CENTER);
    painelDireita.add(btnRemover, BorderLayout.SOUTH);

    // adicionar os componentes
    // caixaProdutos.add(painelEsquerda); // colocado na coluna 0
    // caixaProdutos.add(painelDireita); // colocado na coluna 1
  }

  // METODO DE ADICAO DE UMA LISTA PARA OUTRA
  public void eventoAdicao(ActionEvent e) {
    // código que deve ser executado quando o btn adicionar for clicado
    System.out.println("EVENTO ADIÇÃO");
    // obter a lista de todos os elementos selecionados
    List<String> selecionados = listDisponiveis.getSelectedValuesList();

    // percorre toda a lista dos selecionados
    for (int i = 0; i < selecionados.size(); i++) {
        modelSelecionadas.addElement(selecionados.get(i));
        modelDisponiveis.removeElement(selecionados.get(i));
    }
}

// METODO DE REMOÇÃO DE UMA LISTA PARA OUTRA
public void eventoRemocao(ActionEvent e) {
    // código que deve ser executado quando o btn remover for clicado
    System.out.println("EVENTO REMOÇÃO");
    // obter a lista de todos os elementos selecionados
    List<String> selecionados = listSelecionadas.getSelectedValuesList();

    // percorre toda a lista dos selecionados
    for (int i = 0; i < selecionados.size(); i++) {
        modelDisponiveis.addElement(selecionados.get(i));
        modelSelecionadas.removeElement(selecionados.get(i));
    }
}
}
