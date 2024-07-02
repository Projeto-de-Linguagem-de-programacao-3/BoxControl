package main.view.screens.TelasPrincipais;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import main.controller.actions.ButtonHomeGerarBalanco;
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
        ButtonHomeGerarBalanco buttonHomeGerarBalanco = new ButtonHomeGerarBalanco(this);
        botaoInternalFrame.addActionListener(buttonHomeGerarBalanco);
    }

    public JButton getBotaoInternalFrame() {
        if (botaoInternalFrame == null) {
            botaoInternalFrame = new JButton("Gerar Balanço");
            StyleGuide.formataComponente(botaoInternalFrame); // Apply styling
        }
        return botaoInternalFrame;
    }
}
