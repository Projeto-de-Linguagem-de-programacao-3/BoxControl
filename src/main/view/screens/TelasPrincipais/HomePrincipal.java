package main.view.screens.TelasPrincipais;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import main.view.components.StyleGuide;
import main.view.components.TabelaConsulta;

public class HomePrincipal extends JPanel {
    private JButton botaoInternalFrame;
    private TabelaConsulta tabelaConsulta;

    public HomePrincipal() {
        super();
        setBackground(StyleGuide.bgScreen);
        initializeComponents();
    }

    private void initializeComponents() {
        // Set layout if necessary
        // setLayout(new BorderLayout()); // Example layout setup

        // Initialize and add components
        add(getBotaoInternalFrame());
        add(getTabelaConsulta());
    }

    public JButton getBotaoInternalFrame() {
        if (botaoInternalFrame == null) {
            botaoInternalFrame = new JButton("Consultar XX");
            // Set button bounds if necessary
            // botaoInternalFrame.setBounds(100, 100, 100, 100);
            StyleGuide.formataComponente(botaoInternalFrame); // Apply styling
        }
        return botaoInternalFrame;
    }

    public TabelaConsulta getTabelaConsulta() {
        if (tabelaConsulta == null) {
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
