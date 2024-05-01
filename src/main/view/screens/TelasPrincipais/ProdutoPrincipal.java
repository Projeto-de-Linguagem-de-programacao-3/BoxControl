package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.model.entity.DadosProduto;
import main.view.components.StyleGuide;

public class ProdutoPrincipal extends JPanel {
  private JTextField txtNome;
  private JTextField txtTipo;
  private JTextField txtPrecoCompra;
  private JTextField txtPrecoVenda;
  private JTextField txtFabricante;
  private JTextField txtValidade;
  private JTextField txtQuantidadeEstoque;

  public ProdutoPrincipal() {
    super();
    setBackground(StyleGuide.bgScreen);
    configurarComponentes();
  }

  private void configurarComponentes() {
    setLayout(null);

    JLabel lblNome = new JLabel("Nome:");
    lblNome.setBounds(20, 20, 120, 20);
    add(lblNome);

    txtNome = new JTextField();
    txtNome.setBounds(150, 20, 200, 20);
    add(txtNome);

    JLabel lblTipo = new JLabel("Tipo:");
    lblTipo.setBounds(20, 50, 120, 20);
    add(lblTipo);

    txtTipo = new JTextField();
    txtTipo.setBounds(150, 50, 200, 20);
    add(txtTipo);

    JLabel lblPrecoCompra = new JLabel("Preço de Compra:");
    lblPrecoCompra.setBounds(20, 80, 120, 20);
    add(lblPrecoCompra);

    txtPrecoCompra = new JTextField();
    txtPrecoCompra.setBounds(150, 80, 200, 20);
    add(txtPrecoCompra);

    ((AbstractDocument) txtPrecoCompra.getDocument()).setDocumentFilter(new NumericFilter());

    JLabel lblPrecoVenda = new JLabel("Preço de Venda:");
    lblPrecoVenda.setBounds(20, 110, 120, 20);
    add(lblPrecoVenda);

    txtPrecoVenda = new JTextField();
    txtPrecoVenda.setBounds(150, 110, 200, 20);
    add(txtPrecoVenda);
    ((AbstractDocument) txtPrecoVenda.getDocument()).setDocumentFilter(new NumericFilter());

    JLabel lblFabricante = new JLabel("Fabricante:");
    lblFabricante.setBounds(20, 140, 120, 20);
    add(lblFabricante);

    txtFabricante = new JTextField();
    txtFabricante.setBounds(150, 140, 200, 20);
    add(txtFabricante);

    JLabel lblValidade = new JLabel("Validade:");
    lblValidade.setBounds(20, 170, 120, 20);
    add(lblValidade);

    txtValidade = new JTextField();
    txtValidade.setBounds(150, 170, 200, 20);
    add(txtValidade);
    ((AbstractDocument) txtValidade.getDocument()).setDocumentFilter(new NumericFilter());

    JLabel lblQuantidadeEstoque = new JLabel("Quantidade em Estoque:");
    lblQuantidadeEstoque.setBounds(20, 200, 150, 20);
    add(lblQuantidadeEstoque);

    txtQuantidadeEstoque = new JTextField();
    txtQuantidadeEstoque.setBounds(180, 200, 170, 20);
    add(txtQuantidadeEstoque);
    ((AbstractDocument) txtQuantidadeEstoque.getDocument()).setDocumentFilter(new NumericFilter());

    JButton btnSalvar = new JButton("Salvar em Texto");
    btnSalvar.setBounds(150, 230, 150, 30);
    btnSalvar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        salvarProduto();
      }
    });
    add(btnSalvar);
  }

  private void salvarProduto() {
    // Obter os dados inseridos pelo usuário
    String nome = txtNome.getText();
    String tipo = txtTipo.getText();
    double precoCompra = Double.parseDouble(txtPrecoCompra.getText());
    double precoVenda = Double.parseDouble(txtPrecoVenda.getText());
    String fabricante = txtFabricante.getText();
    String validade = txtValidade.getText();
    int quantidadeEstoque = Integer.parseInt(txtQuantidadeEstoque.getText());

    DadosProduto produto = new DadosProduto();

    produto.setNome(nome);
    produto.setNome(tipo);
    produto.setPrecoCompra(precoCompra);
    produto.setPrecoVenda(precoVenda);
    produto.setFabricante(fabricante);
    produto.setValidade(validade);
    produto.setQuantidadeEstoque(quantidadeEstoque);

    String resultado = produto.salvarTxt();
    System.out.println(resultado);
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
