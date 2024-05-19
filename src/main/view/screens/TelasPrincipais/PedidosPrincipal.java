package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;

import main.controller.actions.ButtonPedidosSalvarListener;
import main.view.components.StyleGuide;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class PedidosPrincipal extends JPanel {
  private JTextField textProduto;
  private JTextField textPrecoCompra;
  private JTextField textFabricante;
  private JTextField textValidade;
  private JTextField textQuantidade;

  private JLabel labelProduto;
  private JLabel labelPrecoCompra;
  private JLabel labelFabricante;
  private JLabel labelValidade;
  private JLabel labelQuantidade;

  private JButton btnSalvar;

  private JTable tabelaCliente;

    public PedidosPrincipal() {
        super();
        setBackground(StyleGuide.bgScreen);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx=1;
        constraints.weighty=1;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10,10,10,10);

        constraints.gridx=0; 
    constraints.gridy=0; 
    add(getLabelProduto(),constraints);
    constraints.gridx=0; 
    constraints.gridy=1; 
    add(getTextProduto(), constraints);

    constraints.gridx=0; 
    constraints.gridy=2; 
    add(getLabelPrecoCompra(), constraints);
    constraints.gridx=0; 
    constraints.gridy=3; 
    add(getTextPrecoCompra(), constraints);

    constraints.gridx=0; 
    constraints.gridy=4; 
    add(getLabelFabricante(), constraints);
    constraints.gridx=0; 
    constraints.gridy=5; 
    add(getTextFabricante(), constraints);

    constraints.gridx=0; 
    constraints.gridy=6; 
    add(getLabelValidade(), constraints);
    constraints.gridx=0; 
    constraints.gridy=7; 
    add(getTextValidade(), constraints);

    constraints.gridx=0; 
    constraints.gridy=8; 
    add(getLabelQuantidade(), constraints);
    constraints.gridx=0;
    constraints.gridy=9;
    add(getTextQuantidade(), constraints);

    constraints.gridx=0; 
    constraints.gridy=10; 
    constraints.gridwidth=2; // ocupa 2 colunas
    add(getBtnSalvar(), constraints);
    ButtonPedidosSalvarListener buttonPedidosSalvarListener = new ButtonPedidosSalvarListener(this);
    btnSalvar.addActionListener(buttonPedidosSalvarListener);

    constraints.gridx=2;
    constraints.gridy=0;
    constraints.gridwidth=2;
    constraints.gridheight=5;
    JScrollPane scrollPane = new JScrollPane(getTabelaCliente());
    add(scrollPane, constraints);
    }
    
    public JTextField getTextProduto() {
      if (textProduto == null) {
          textProduto = new JTextField();
          StyleGuide.formataComponente(textProduto);
      }
      return textProduto;
  }

  public JTextField getTextPrecoCompra() {
      if (textPrecoCompra == null) {
          textPrecoCompra = new JTextField();
          ((AbstractDocument) textPrecoCompra.getDocument()).setDocumentFilter(new NumericFilter());
          StyleGuide.formataComponente(textPrecoCompra);
      }
      return textPrecoCompra;
  }

  public JTextField getTextFabricante() {
      if (textFabricante == null) {
          textFabricante = new JTextField();
          StyleGuide.formataComponente(textFabricante);
      }
      return textFabricante;
  }

  public JTextField getTextValidade() {
      if (textValidade == null) {
          textValidade = new JTextField();
          ((AbstractDocument) textValidade.getDocument()).setDocumentFilter(new NumericFilter());
          StyleGuide.formataComponente(textValidade);
      }
      return textValidade;
  }

  public JTextField getTextQuantidade() {
      if (textQuantidade == null) {
          textQuantidade = new JTextField();
          ((AbstractDocument) textQuantidade.getDocument()).setDocumentFilter(new NumericFilter());
          StyleGuide.formataComponente(textQuantidade);
      }
      return textQuantidade;
  }

  // Getters for JLabel elements
  public JLabel getLabelProduto() {
      if (labelProduto == null) {
          labelProduto = new JLabel("Produto:");
          StyleGuide.formataComponente(labelProduto);
      }
      return labelProduto;
  }

  public JLabel getLabelPrecoCompra() {
      if (labelPrecoCompra == null) {
          labelPrecoCompra = new JLabel("Preço de Compra:");
          StyleGuide.formataComponente(labelPrecoCompra);
      }
      return labelPrecoCompra;
  }

  public JLabel getLabelFabricante() {
      if (labelFabricante == null) {
          labelFabricante = new JLabel("Fabricante:");
          StyleGuide.formataComponente(labelFabricante);
      }
      return labelFabricante;
  }

  public JLabel getLabelValidade() {
      if (labelValidade == null) {
          labelValidade = new JLabel("Validade:");
          StyleGuide.formataComponente(labelValidade);
      }
      return labelValidade;
  }

  public JLabel getLabelQuantidade() {
      if (labelQuantidade == null) {
          labelQuantidade = new JLabel("Quantidade:");
          StyleGuide.formataComponente(labelQuantidade);
      }
      return labelQuantidade;
  }

  public JButton getBtnSalvar() {
  if(btnSalvar == null) {
    btnSalvar = new JButton("Salvar Pedido");
    StyleGuide.formataComponente(btnSalvar);
  }
  return btnSalvar;
}

public JTable getTabelaCliente() {
  if(tabelaCliente == null) {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("Produto", new Class[]{String.class});
    modelo.addColumn("Preço de compra", new Class[]{String.class});
    modelo.addColumn("Fabricante", new Class[]{String.class});
    modelo.addColumn("Validade", new Class[]{String.class});
    modelo.addColumn("Quantidade", new Class[]{String.class});
    tabelaCliente = new JTable(modelo);
  }
  return tabelaCliente;
}
 // Implementação de DocumentFilter para permitir apenas números
    class NumericFilter extends DocumentFilter {
      @Override
      public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
          throws BadLocationException {
        if (string.matches("[0-9]+")) {
          super.insertString(fb, offset, string, attr);
        }
      }

      @Override
      public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
          throws BadLocationException {
        if (text.matches("[0-9]+") || text.equals("")) {
          super.replace(fb, offset, length, text, attrs);
        }
      }
    }
}
