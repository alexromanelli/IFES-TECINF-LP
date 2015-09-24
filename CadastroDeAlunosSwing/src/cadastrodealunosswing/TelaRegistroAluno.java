/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodealunosswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author alexromanelli
 */
public class TelaRegistroAluno extends javax.swing.JFrame {
    
    private static final int OPCAO_INSERIR = 0;
    private static final int OPCAO_ALTERAR = 1;
    
    private int opcao;
    private ArrayList<Aluno> colecaoAlunos;
    private int indiceAluno;

    /**
     * Creates new form TelaRegistroAluno
     */
    public TelaRegistroAluno(ArrayList<Aluno> alunos, int indiceAluno) {
        colecaoAlunos = alunos;
        this.indiceAluno = indiceAluno;
        if (indiceAluno < 0 || indiceAluno >= colecaoAlunos.size()) {
            opcao = OPCAO_INSERIR;
        } else {
            opcao = OPCAO_ALTERAR;
        }
        initComponents();
        getRootPane().setDefaultButton(bSalvar);
        
        if (opcao == OPCAO_ALTERAR) {
            Aluno aluno = colecaoAlunos.get(indiceAluno);
            tfMatricula.setText(Integer.toString(aluno.getMatricula()));
            tfNome.setText(aluno.getNome());
            tfDataNascimento.setText(aluno.getDataNascimento());
            switch (aluno.getSexo()) {
                case 'F':
                    rbFeminino.setSelected(true);
                    break;
                case 'M':
                    rbMasculino.setSelected(true);
            }
            tfEmail.setText(aluno.getEmail());
        }
        
        definirManipuladores();
    }
    
    private void definirManipuladores() {
        bSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfMatricula.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, 
                            "O campo matrícula deve ser preenchido.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int matricula = 0;
                try {
                    matricula = Integer.parseInt(tfMatricula.getText());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, 
                            "O campo matrícula deve conter um número inteiro.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (matricula <= 0) {
                    JOptionPane.showMessageDialog(null, 
                            "A matrícula deve ser um número positivo.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String nome = tfNome.getText();
                String dataNascimento = tfDataNascimento.getText();
                char sexo = rbFeminino.isSelected() ? 'F' : 'M';
                String email = tfEmail.getText();
                
                switch (opcao) {
                    case OPCAO_INSERIR:
                        Aluno aluno = new Aluno(matricula, nome, dataNascimento,
                            sexo, email);
                        AlunoDAO alunoDAO = PostgreSQLDAOFactory.getAlunoDAO();
                        if (alunoDAO.inserirAluno(aluno)) {
                            colecaoAlunos.add(aluno);
                        }
                        break;
                    case OPCAO_ALTERAR:
                        aluno = colecaoAlunos.get(indiceAluno);
                        
                        Aluno copia = new Aluno(matricula, nome, 
                                dataNascimento, sexo, email);
                        alunoDAO = PostgreSQLDAOFactory.getAlunoDAO();
                        if (alunoDAO.atualizarAluno(copia)) {
                            aluno.setMatricula(matricula);
                            aluno.setNome(nome);
                            aluno.setDataNascimento(dataNascimento);
                            aluno.setSexo(sexo);
                            aluno.setEmail(email);
                        }
                        
                        break;
                }
                JOptionPane.showMessageDialog(null, "Registro foi salvo.");
                TelaPrincipal.INSTANCIA.atualizarTabela();
                TelaRegistroAluno.this.setVisible(false);
            }
        });
        bCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaRegistroAluno.this.setVisible(false);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tfMatricula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        tfDataNascimento = new javax.swing.JTextField();
        rbFeminino = new javax.swing.JRadioButton();
        rbMasculino = new javax.swing.JRadioButton();
        tfEmail = new javax.swing.JTextField();
        bSalvar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Matrícula");

        jLabel2.setText("Nome");

        jLabel3.setText("Data de nascimento");

        jLabel4.setText("Sexo");

        jLabel5.setText("Email");

        buttonGroup1.add(rbFeminino);
        rbFeminino.setText("Feminino");

        buttonGroup1.add(rbMasculino);
        rbMasculino.setText("Masculino");

        bSalvar.setText("Salvar");

        bCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNome)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbFeminino)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbMasculino))
                                    .addComponent(tfMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 171, Short.MAX_VALUE))
                            .addComponent(tfEmail)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(bCancelar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rbFeminino)
                    .addComponent(rbMasculino))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSalvar)
                    .addComponent(bCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton rbFeminino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTextField tfDataNascimento;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfMatricula;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}
