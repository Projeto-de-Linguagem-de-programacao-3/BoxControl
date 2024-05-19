package main.view.components;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaConsulta extends JInternalFrame {
  JTable tabelaConsulta;

  public TabelaConsulta(DefaultTableModel modelo) {
    super();
    setTitle("Consulta");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setClosable(true);
    setSize(500,500);
    add(getTabelaConsulta(modelo));
    setVisible(true);
  }

  public JTable getTabelaConsulta(DefaultTableModel modelo) {
    if(tabelaConsulta == null) {
      tabelaConsulta = new JTable(modelo);
    }
    return tabelaConsulta;
  }
}
