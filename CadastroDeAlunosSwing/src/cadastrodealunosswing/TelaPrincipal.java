/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodealunosswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

class ModeloTabelaAluno extends AbstractTableModel {

    private ArrayList<Aluno> colecaoAlunos;
    private String[] colunas = {"Matrícula", "Nome", "Data nasc.",
        "Sexo", "Email"};

    public ModeloTabelaAluno(ArrayList<Aluno> alunos) {
        colecaoAlunos = alunos;
        carregarDoBD();
    }
    
    private void carregarDoBD() {
        try {
            AlunoDAO alunoDAO = PostgreSQLDAOFactory.getAlunoDAO();
            ResultSet rs = alunoDAO.selecionarTodosAlunos();
            rs.beforeFirst();
            while (rs.next()) {
                int matricula = rs.getInt(1);
                String nome = rs.getString(2);
                String dataNascimento = rs.getString(3);
                char sexo = rs.getString(4).charAt(0);
                String email = rs.getString(5);
                Aluno aluno = new Aluno(matricula, nome, dataNascimento, 
                        sexo, email);
                colecaoAlunos.add(aluno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloTabelaAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return colecaoAlunos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aluno aluno = colecaoAlunos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return aluno.getMatricula();
            case 1:
                return aluno.getNome();
            case 2:
                return aluno.getDataNascimento();
            case 3:
                return aluno.getSexo();
            case 4:
                return aluno.getEmail();
            default:
                return null;
        }
    }

}

/**
 *
 * @author alexromanelli
 */
public class TelaPrincipal extends javax.swing.JFrame {

    public static TelaPrincipal INSTANCIA;

    private ArrayList<Aluno> colecaoAlunos;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        INSTANCIA = this;
        initComponents();
        colecaoAlunos = new ArrayList<>();
        tAlunos.setModel(new ModeloTabelaAluno(colecaoAlunos));

        // define larguras das colunas
        tAlunos.getColumnModel().getColumn(0).setPreferredWidth(90);
        tAlunos.getColumnModel().getColumn(1).setPreferredWidth(220);
        tAlunos.getColumnModel().getColumn(2).setPreferredWidth(120);
        tAlunos.getColumnModel().getColumn(3).setPreferredWidth(50);
        tAlunos.getColumnModel().getColumn(4).setPreferredWidth(200);
        definirManipuladores();
    }

    public void definirManipuladores() {
        bRegistrarAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaRegistroAluno tra = new TelaRegistroAluno(colecaoAlunos, -1);
                tra.setVisible(true);
            }
        });
        bAlterarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indAluno = tAlunos.getSelectedRow();
                if (indAluno == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Selecione um aluno antes de alterar.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                TelaRegistroAluno tra = new TelaRegistroAluno(colecaoAlunos, indAluno);
                tra.setVisible(true);
            }
        });
        bRemoverRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indAluno = tAlunos.getSelectedRow();
                if (indAluno == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Selecione um aluno antes de remover.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (JOptionPane.showConfirmDialog(null,
                        "Deseja realmente excluir o registro?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    AlunoDAO alunoDAO = PostgreSQLDAOFactory.getAlunoDAO();
                    if (alunoDAO.excluirAluno(colecaoAlunos.get(indAluno))) {
                        colecaoAlunos.remove(indAluno);
                        atualizarTabela();
                    }
                }
            }
        });
    }

    public void atualizarTabela() {
        ((AbstractTableModel) tAlunos.getModel()).fireTableDataChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bRegistrarAluno = new javax.swing.JButton();
        bAlterarRegistro = new javax.swing.JButton();
        bRemoverRegistro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAlunos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Alunos");

        bRegistrarAluno.setText("Registrar aluno");

        bAlterarRegistro.setText("Alterar registro");

        bRemoverRegistro.setText("Remover registro");

        jScrollPane1.setViewportView(tAlunos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bRegistrarAluno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bAlterarRegistro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bRemoverRegistro)
                        .addGap(0, 69, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bRegistrarAluno)
                    .addComponent(bAlterarRegistro)
                    .addComponent(bRemoverRegistro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlterarRegistro;
    private javax.swing.JButton bRegistrarAluno;
    private javax.swing.JButton bRemoverRegistro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tAlunos;
    // End of variables declaration//GEN-END:variables
}
