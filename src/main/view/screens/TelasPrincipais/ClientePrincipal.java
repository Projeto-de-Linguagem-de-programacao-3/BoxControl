package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import main.view.components.StyleGuide;
import main.controller.actions.ButtonClientesSalvarListener;
import main.model.database.ClienteDatabase;
import main.model.entity.Cliente;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;


public class ClientePrincipal extends JPanel {
  private JLabel labelNome;
  private JTextField textNome;
  private JLabel labelCPF;
  private JTextField textCPF;
  private JLabel labelRG;
  private JTextField textRG;
  private JLabel labelNascimento;
  private JFormattedTextField textNascimento;
  private JLabel labelCredito;
  private JFormattedTextField textCredito;
  private JTable tabelaCliente;
  private JButton btnSalvar;
  private JButton btnAtivarCliente;
  private JButton btnDesativarCliente;
  private JButton btnEditarCliente;

  public ClientePrincipal() {
    super();

    setBackground(StyleGuide.bgScreen);
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 1;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10, 10, 10, 10);

    constraints.gridx = 0;
    constraints.gridy = 0;
    add(getLabelNome(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 1;
    add(getTextNome(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    add(getLabelCPF(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 3;
    add(getTextCPF(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 4;
    add(getLabelRG(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 5;
    add(getTextRG(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 6;
    add(getLabelNascimento(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 7;
    add(getTextNascimento(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 8;
    add(getLabelCredito(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 9;
    add(getTextCredito(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 10;
    constraints.gridwidth = 2; // ocupa 2 colunas
    add(getBtnSalvar(), constraints);
    ButtonClientesSalvarListener buttonClientesSalvarListener = new ButtonClientesSalvarListener(this);
    btnSalvar.addActionListener(buttonClientesSalvarListener);

    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 10;
    JScrollPane scrollPane = new JScrollPane(getTabelaCliente());
    scrollPane.setMinimumSize(new Dimension(100, 300));
    add(scrollPane, constraints);
    getTabelaCliente().getSelectionModel().addListSelectionListener(e -> {
      if (!e.getValueIsAdjusting()) {
          int selectedRow = getTabelaCliente().getSelectedRow();
          if (selectedRow != -1) {
              Object id = getTabelaCliente().getValueAt(selectedRow, 0);
              Object nome = getTabelaCliente().getValueAt(selectedRow, 1);
              Object cpf = getTabelaCliente().getValueAt(selectedRow, 2);
              Object rg = getTabelaCliente().getValueAt(selectedRow, 3);
              Object nascimento = getTabelaCliente().getValueAt(selectedRow, 4);
              Object credito = getTabelaCliente().getValueAt(selectedRow, 5);
              
              // Exemplo de preenchimento dos campos (adaptar conforme seus campos)
              getTextNome().setText(nome.toString());
              getTextCPF().setText(cpf.toString());
              getTextRG().setText(rg.toString());
              getTextNascimento().setText(nascimento.toString());
              getTextCredito().setText(credito.toString());
          }
      }
  });

    constraints.gridx = 2;
    constraints.gridy = 9;
    constraints.gridwidth = 1;
    add(getBtnAtivarCliente(),constraints);
    btnAtivarCliente.addActionListener((ActionEvent e) -> {
      int resposta = JOptionPane.showConfirmDialog(this, "Deseja ativar esse cliente?", "confirmação", JOptionPane.YES_NO_OPTION);
      if(resposta == JOptionPane.YES_OPTION) {
        int colunaSelecionada = getTabelaCliente().getSelectedRow();
        if(colunaSelecionada == -1) {
          JOptionPane.showMessageDialog(this, "Nenhum cliente selecionado!", "Erro de validação", JOptionPane.ERROR_MESSAGE);
          return;
        } else {
          int id = (Integer) getTabelaCliente().getModel().getValueAt(colunaSelecionada, 0);
          ClienteDatabase clienteDatabase = new ClienteDatabase();
          clienteDatabase.ativarCliente(id);
          JOptionPane.showMessageDialog(this, "Cliente ativado com sucesso!", "Resultado:",
          JOptionPane.INFORMATION_MESSAGE);
          atualizarTabela();
        }
      } else {
        return;
      }
    });
    constraints.gridx = 3;
    constraints.gridy = 9;
    add(getBtnDesativarCliente(),constraints);
    btnDesativarCliente.addActionListener((ActionEvent e) -> {
      int resposta = JOptionPane.showConfirmDialog(this, "Deseja desativar esse cliente?", "confirmação", JOptionPane.YES_NO_OPTION);
      if(resposta == JOptionPane.YES_OPTION) {
        int colunaSelecionada = getTabelaCliente().getSelectedRow();
        if(colunaSelecionada == -1) {
          JOptionPane.showMessageDialog(this, "Nenhum cliente selecionado!", "Erro de validação", JOptionPane.ERROR_MESSAGE);
          return;
        } else {
          int id = (Integer) getTabelaCliente().getModel().getValueAt(colunaSelecionada, 0);
          ClienteDatabase clienteDatabase = new ClienteDatabase();
          clienteDatabase.desativarCliente(id);
          JOptionPane.showMessageDialog(this, "Cliente desativado com sucesso!", "Resultado:",
          JOptionPane.INFORMATION_MESSAGE);
          atualizarTabela();
        }
      } else {
        return;
      }
    });

    // Preencher a tabela inicialmente
    atualizarTabela();
  }

  public JLabel getLabelNome() {
    if (labelNome == null) {
      labelNome = new JLabel("Nome:");
      StyleGuide.formataComponente(labelNome);
    }
    return labelNome;
  }

  public JTextField getTextNome() {
    if (textNome == null) {
      textNome = new JTextField(20); // Ajustar o tamanho como desejado
      StyleGuide.formataComponente(textNome);
    }
    return textNome;
  }

  public JLabel getLabelCPF() {
    if (labelCPF == null) {
      labelCPF = new JLabel("CPF:");
      StyleGuide.formataComponente(labelCPF);
    }
    return labelCPF;
  }

  public JTextField getTextCPF() {
    if (textCPF == null) {
      try {
        MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
        textCPF = new JFormattedTextField(mascaraCpf);
        StyleGuide.formataComponente(textCPF);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    return textCPF;
  }

  public JLabel getLabelRG() {
    if (labelRG == null) {
      labelRG = new JLabel("RG:");
      StyleGuide.formataComponente(labelRG);
    }
    return labelRG;
  }

  public JTextField getTextRG() {
    if (textRG == null) {
      try {
        MaskFormatter dateFormatter = new MaskFormatter("##.###.###-##");
        textRG = new JFormattedTextField(dateFormatter);
        StyleGuide.formataComponente(textRG);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    return textRG;
  }

  public JLabel getLabelNascimento() {
    if (labelNascimento == null) {
      labelNascimento = new JLabel("Nascimento:");
      StyleGuide.formataComponente(labelNascimento);
    }
    return labelNascimento;
  }

  public JFormattedTextField getTextNascimento() {
    if (textNascimento == null) {
      try {
        MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
        textNascimento = new JFormattedTextField(dateFormatter);
        StyleGuide.formataComponente(textNascimento);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    return textNascimento;
  }

  public JLabel getLabelCredito() {
    if (labelCredito == null) {
      labelCredito = new JLabel("Crédito:");
      StyleGuide.formataComponente(labelCredito);
    }
    return labelCredito;
  }

  public JFormattedTextField getTextCredito() {
    if (textCredito == null) {
      NumberFormat numberFormat = NumberFormat.getNumberInstance();
      numberFormat.setGroupingUsed(false);
      NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
      numberFormatter.setValueClass(Double.class);
      numberFormatter.setAllowsInvalid(false);
      numberFormatter.setMinimum(0.0);
      textCredito = new JFormattedTextField(numberFormatter);
      textCredito.setColumns(10); // Ajustar o tamanho como desejado
      StyleGuide.formataComponente(textCredito);
    }
    return textCredito;
  }

  public JButton getBtnSalvar() {
    if (btnSalvar == null) {
      btnSalvar = new JButton("Salvar cliente");
      StyleGuide.formataComponente(btnSalvar);
    }
    return btnSalvar;
  }

  public JButton getBtnAtivarCliente() {
    if(btnAtivarCliente == null) {
      btnAtivarCliente = new JButton("Ativar Cliente");
    }
    return btnAtivarCliente;
  }

  public JButton getBtnDesativarCliente() {
    if(btnDesativarCliente == null) {
      btnDesativarCliente = new JButton("Desativar Cliente");
    }
    return btnDesativarCliente;
  }

  public JButton getBtnEditarCliente() {
    if(btnEditarCliente == null) {
      btnEditarCliente = new JButton("Editar Cliente");
    }
    return btnEditarCliente;
  }

  public JTable getTabelaCliente() {
    if (tabelaCliente == null) {
      String[] titulos = { "ID", "Nome", "CPF", "RG", "Data de Nascimento", "Limite de Crédito", "Estado" };
      DefaultTableModel modelo = new DefaultTableModel(titulos, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
          return false;
        }
      };
      tabelaCliente = new JTable(modelo);
    }
    return tabelaCliente;
  }

  public void atualizarTabela() {
    DefaultTableModel modelo = (DefaultTableModel) getTabelaCliente().getModel();
    modelo.setRowCount(0); // Limpa a tabela antes de adicionar novos dados
    
    ClienteDatabase clienteDatabase = new ClienteDatabase();
    List<Object[]> dadosCliente = clienteDatabase.consultarClientes();
    for (Object[] linha : dadosCliente) {
      modelo.addRow(linha);
    }
  }
}
