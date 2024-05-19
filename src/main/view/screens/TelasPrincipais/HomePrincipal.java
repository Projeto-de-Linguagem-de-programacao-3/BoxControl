package main.view.screens.TelasPrincipais;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import main.view.components.StyleGuide;
import main.view.components.TabelaConsulta;

public class HomePrincipal extends JPanel {
  JButton botaoInternalFrame;
  TabelaConsulta tabelaConsulta;

  public HomePrincipal() {
    super();
    setBackground(StyleGuide.bgScreen);
  }

  public JButton getBotaoInternalFrame() {
    if(botaoInternalFrame == null) {
      botaoInternalFrame = new JButton("Consultar XX");
      botaoInternalFrame.setBounds(100,100,100,100);
    }
    return botaoInternalFrame;
  }

  public TabelaConsulta getTabelaConsulta() {
    if(tabelaConsulta == null) {
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("Nome", new Class[]{String.class});
      modelo.addColumn("CPF", new Class[]{String.class});
      modelo.addColumn("RG", new Class[]{String.class});
      modelo.addColumn("DataNascimento", new Class[]{String.class});
      modelo.addColumn("LimiteDeCredito", new Class[]{String.class});
      tabelaConsulta = new TabelaConsulta(modelo);
    }
    return tabelaConsulta;
  }

  
}
