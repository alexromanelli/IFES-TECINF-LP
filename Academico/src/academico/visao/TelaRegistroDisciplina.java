/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academico.visao;

import academico.modelo.Curso;
import academico.modelo.CursoDAO;
import academico.modelo.Disciplina;
import academico.modelo.DisciplinaDAO;
import academico.modelo.pg.PostgreSQLDAOFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author alexromanelli
 */
public class TelaRegistroDisciplina extends javax.swing.JFrame {
    
    private static final int OPCAO_INSERIR = 0;
    private static final int OPCAO_ALTERAR = 1;
    
    private int opcao;
    private ArrayList<Disciplina> colecaoDisciplina;
    private int indiceDisciplina;
    private Vector<Curso> colecaoCurso; // Curso[] colecaoCurso;
    private DefaultComboBoxModel<Curso> modeloComboBoxCurso;
    
    private void carregarCursoDoBD() {
        colecaoCurso = new Vector<>();
        try {
            CursoDAO cursoDAO = PostgreSQLDAOFactory.getCursoDAO();
            ResultSet rs = cursoDAO.selecionarTodosCursos();
            rs.beforeFirst();
            while (rs.next()) {
                int codigo = rs.getInt(1);
                String nome = rs.getString(2);
                int cargaHoraria = rs.getInt(3);
                String coordenador = rs.getString(4);
                Curso curso = new Curso(codigo, nome, cargaHoraria, coordenador);
                colecaoCurso.add(curso);
            }
        } catch (SQLException e) {
            
        }
    }
    
    public TelaRegistroDisciplina(ArrayList<Disciplina> colecaoDisciplina) {
        this.colecaoDisciplina = colecaoDisciplina;
        opcao = OPCAO_INSERIR;
        initComponents();
        carregarCursoDoBD();
        modeloComboBoxCurso = new DefaultComboBoxModel<>(colecaoCurso);
        cbCurso.setModel(modeloComboBoxCurso);
        tfCodigo.setEnabled(false);
    }
    
    // sobrecarga de método (construtor)
    public TelaRegistroDisciplina(ArrayList<Disciplina> colecaoDisciplina, 
            int indiceDisciplina) {
        this(colecaoDisciplina);
        this.indiceDisciplina = indiceDisciplina;
        opcao = OPCAO_ALTERAR;
        tfCodigo.setEnabled(true);
        tfCodigo.setEditable(false);
        exibirRegistro();
    }
    
    private void exibirRegistro() {
        Disciplina d = colecaoDisciplina.get(indiceDisciplina);
        tfCodigo.setText(Integer.toString(d.getCodDisciplina()));
        tfNome.setText(d.getNome());
        tfCargaHoraria.setText(Integer.toString(d.getCargaHoraria()));
        taEmenta.setText(d.getEmenta());
        cbCurso.setSelectedIndex(obtemIndiceCurso(d.getCurso()));
    }
    
    private int obtemIndiceCurso(Curso c) {
        int i = 0;
        for (Curso curso : colecaoCurso) {
            if (curso.getCodCurso() == c.getCodCurso())
                return i;
            i++;
        }
        return -1;
    }
    
    /**
     * Creates new form TelaRegistroDisciplina
     */
    public TelaRegistroDisciplina() {
        initComponents();
        carregarCursoDoBD();
        modeloComboBoxCurso = new DefaultComboBoxModel<>(colecaoCurso);
        cbCurso.setModel(modeloComboBoxCurso);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        tfNome = new javax.swing.JTextField();
        tfCargaHoraria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taEmenta = new javax.swing.JTextArea();
        cbCurso = new javax.swing.JComboBox();
        bSalvar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de disciplina - registro");

        jLabel1.setText("Código");

        jLabel2.setText("Nome");

        jLabel3.setText("Carga horária");

        jLabel4.setText("Ementa");

        jLabel5.setText("Curso");

        tfCodigo.setPreferredSize(new java.awt.Dimension(80, 19));

        tfCargaHoraria.setPreferredSize(new java.awt.Dimension(80, 19));

        taEmenta.setColumns(20);
        taEmenta.setRows(5);
        jScrollPane1.setViewportView(taEmenta);

        cbCurso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Curso A", "Curso B", "Curso C", "Curso D" }));

        bSalvar.setText("Salvar");
        bSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvarActionPerformed(evt);
            }
        });

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(76, 76, 76)
                        .addComponent(cbCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNome)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bSalvar)
                                .addGap(18, 18, 18)
                                .addComponent(bCancelar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSalvar)
                    .addComponent(bCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarActionPerformed
        // obter campos
        String nome = tfNome.getText();
        int cargaHoraria = Integer.parseInt(tfCargaHoraria.getText());
        String ementa = taEmenta.getText();
        Curso curso = (Curso)cbCurso.getSelectedItem();
        
        // registrar
        DisciplinaDAO disciplinaDAO = PostgreSQLDAOFactory.getDisciplinaDAO();
        Disciplina d;
        switch (opcao) {
            case OPCAO_INSERIR:
                d = new Disciplina(-1, nome, cargaHoraria, ementa, curso);
                if (disciplinaDAO.inserirDisciplina(d)) {
                    colecaoDisciplina.add(d);
                    TelaListagemDisciplina.INSTANCIA.atualizarTabela();
                    this.setVisible(false);
                }
                break;
            case OPCAO_ALTERAR:
                d = colecaoDisciplina.get(indiceDisciplina);
                d.setNome(nome);
                d.setCargaHoraria(cargaHoraria);
                d.setEmenta(ementa);
                d.setCurso(curso);
                if (disciplinaDAO.atualizarDisciplina(d)) {
                    TelaListagemDisciplina.INSTANCIA.atualizarTabela();
                    this.setVisible(false);
                }
                break;
        }
    }//GEN-LAST:event_bSalvarActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_bCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRegistroDisciplina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bSalvar;
    private javax.swing.JComboBox cbCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taEmenta;
    private javax.swing.JTextField tfCargaHoraria;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}
