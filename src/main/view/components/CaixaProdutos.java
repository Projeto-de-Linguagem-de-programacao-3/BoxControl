package main.view.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class CaixaProdutos extends JComponent {
  private JLabel labelDisponiveis;
    private JList listDisponiveis;
    private DefaultListModel<String> modelDisponiveis;
    private JButton btnAdicionar;
    private JLabel labelSelecionadas;
    private JList listSelecionadas;
    private DefaultListModel<String> modelSelecionadas;
    private JButton btnRemover;

    /* Construtores ----------------------------------------------------- */
    public CaixaProdutos() {
        GridLayout gridLayout = new GridLayout(0, 2);
        gridLayout.setHgap(15);
        setLayout(gridLayout);

        // inicializa os componentes
        labelDisponiveis = new JLabel("Disciplinas disponíveis:");
        modelDisponiveis = new DefaultListModel<String>();
        modelDisponiveis.addElement("Algoritmos");
        modelDisponiveis.addElement("Sistemas Operacionais");
        modelDisponiveis.addElement("Estruturas de Dados");
        modelDisponiveis.addElement("Armaz. de Informações");
        modelDisponiveis.addElement("Redes de Computadores");
        modelDisponiveis.addElement("Programação Web");
        modelDisponiveis.addElement("Banco de Dados");
        listDisponiveis = new JList(modelDisponiveis);
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
                modelSelecionadas.removeElement(disciplina);
            }
        });
      
        // configurar o layout da esquerda
        JPanel painelEsquerda = new JPanel(new BorderLayout());
        painelEsquerda.add(labelDisponiveis, BorderLayout.NORTH);
        painelEsquerda.add(new JScrollPane(listDisponiveis), BorderLayout.CENTER);
        painelEsquerda.add(btnAdicionar, BorderLayout.SOUTH);

        // configurar o layout da direita
        JPanel painelDireita = new JPanel(new BorderLayout());
        painelDireita.add(labelSelecionadas, BorderLayout.NORTH);
        painelDireita.add(new JScrollPane(listSelecionadas), BorderLayout.CENTER);
        painelDireita.add(btnRemover, BorderLayout.SOUTH);

        // adicionar os componentes
        add(painelEsquerda); // colocado na coluna 0
        add(painelDireita); // colocado na coluna 1
    }

    /* Métodos ---------------------------------------------------------- */

    /* Classes internas ---------------------------------------------------- */
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
