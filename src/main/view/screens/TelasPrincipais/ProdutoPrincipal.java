package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.model.entity.DadosProduto;
import main.view.components.StyleGuide;
import java.awt.Font;

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
    lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblNome.setBounds(20, 3, 120, 20);
    add(lblNome);

    txtNome = new JTextField();
    txtNome.setBounds(20, 26, 200, 20);
    add(txtNome);

    JLabel lblTipo = new JLabel("Tipo:");
    lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblTipo.setBounds(20, 49, 120, 20);
    add(lblTipo);

    txtTipo = new JTextField();
    txtTipo.setBounds(20, 72, 200, 20);
    add(txtTipo);

    JLabel lblPrecoCompra = new JLabel("Preço de Compra:");
    lblPrecoCompra.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblPrecoCompra.setBounds(20, 95, 120, 20);
    add(lblPrecoCompra);

    txtPrecoCompra = new JTextField();
    txtPrecoCompra.setBounds(20, 118, 200, 20);
    add(txtPrecoCompra);

    ((AbstractDocument) txtPrecoCompra.getDocument()).setDocumentFilter(new NumericFilter());

    JLabel lblPrecoVenda = new JLabel("Preço de Venda:");
    lblPrecoVenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblPrecoVenda.setBounds(20, 141, 120, 20);
    add(lblPrecoVenda);

    txtPrecoVenda = new JTextField();
    txtPrecoVenda.setBounds(20, 164, 200, 20);
    add(txtPrecoVenda);
    ((AbstractDocument) txtPrecoVenda.getDocument()).setDocumentFilter(new NumericFilter());

    JLabel lblFabricante = new JLabel("Fabricante:");
    lblFabricante.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblFabricante.setBounds(20, 187, 120, 20);
    add(lblFabricante);

    txtFabricante = new JTextField();
    txtFabricante.setBounds(20, 210, 200, 20);
    add(txtFabricante);

    JLabel lblValidade = new JLabel("Validade:");
    lblValidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblValidade.setBounds(20, 233, 120, 20);
    add(lblValidade);

    txtValidade = new JTextField();
    txtValidade.setBounds(20, 256, 200, 20);
    add(txtValidade);
    ((AbstractDocument) txtValidade.getDocument()).setDocumentFilter(new NumericFilter());

    JLabel lblQuantidadeEstoque = new JLabel("Quantidade em Estoque:");
    lblQuantidadeEstoque.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblQuantidadeEstoque.setBounds(20, 279, 183, 20);
    add(lblQuantidadeEstoque);

    txtQuantidadeEstoque = new JTextField();
    txtQuantidadeEstoque.setBounds(20, 302, 170, 20);
    add(txtQuantidadeEstoque);
    ((AbstractDocument) txtQuantidadeEstoque.getDocument()).setDocumentFilter(new NumericFilter());

    JButton btnSalvar = new JButton("Salvar em Texto");
    btnSalvar.setBounds(20, 325, 150, 20);
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
