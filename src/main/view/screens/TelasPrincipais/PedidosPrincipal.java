package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.DocumentFilter.FilterBypass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.model.entity.DadosPedidos;
import main.view.components.StyleGuide;

public class PedidosPrincipal extends JPanel {
    private JTextField txtProduto;
    private JTextField txtPrecoCompra;
    private JTextField txtFabricante;
    private JTextField txtValidade;
    private JTextField txtQuantidade;

    public PedidosPrincipal() {
        super();
        setBackground(StyleGuide.bgScreen);
        configurarComponentes();
    }

    private void configurarComponentes() {
        setLayout(null);

        JLabel lblProduto = new JLabel("Produto:");
        lblProduto.setBounds(20, 20, 120, 20);
        add(lblProduto);

        txtProduto = new JTextField();
        txtProduto.setBounds(150, 20, 200, 20);
        add(txtProduto);

        JLabel lblPrecoCompra = new JLabel("Preço de Compra:");
        lblPrecoCompra.setBounds(20, 50, 120, 20);
        add(lblPrecoCompra);

        txtPrecoCompra = new JTextField();
        txtPrecoCompra.setBounds(150, 50, 200, 20);
        add(txtPrecoCompra);
        ((AbstractDocument) txtPrecoCompra.getDocument()).setDocumentFilter(new NumericFilter());
        

        JLabel lblFabricante = new JLabel("Fabricante:");
        lblFabricante.setBounds(20, 80, 120, 20);
        add(lblFabricante);

        txtFabricante = new JTextField();
        txtFabricante.setBounds(150, 80, 200, 20);
        add(txtFabricante);

        JLabel lblValidade = new JLabel("Validade:");
        lblValidade.setBounds(20, 110, 120, 20);
        add(lblValidade);

        txtValidade = new JTextField();
        txtValidade.setBounds(150, 110, 200, 20);
        add(txtValidade);
        ((AbstractDocument) txtValidade.getDocument()).setDocumentFilter(new NumericFilter());

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(20, 140, 120, 20);
        add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(150, 140, 200, 20);
        add(txtQuantidade);        
        ((AbstractDocument) txtQuantidade.getDocument()).setDocumentFilter(new NumericFilter());

        

        JButton btnSalvar = new JButton("Salvar em Texto");
        btnSalvar.setBounds(150, 170, 150, 30);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarPedido();
            }
        });
        add(btnSalvar);
    }

    private void salvarPedido() {
        // Obter os dados inseridos pelo usuário
        String produto = txtProduto.getText();
        double precoCompra = Double.parseDouble(txtPrecoCompra.getText());
        String fabricante = txtFabricante.getText();
        String validade = txtValidade.getText();
        int quantidade = Integer.parseInt(txtQuantidade.getText());
        
        
        DadosPedidos pedido = new DadosPedidos();
        pedido.setProduto(produto);
        pedido.setPrecoCompra(precoCompra);
        pedido.setFabricante(fabricante);
        pedido.setValidade(validade);
        pedido.setQuantidade(quantidade);
        
        
        String resuldado = pedido.salvarTxt();
        
        System.out.println(resuldado);
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
