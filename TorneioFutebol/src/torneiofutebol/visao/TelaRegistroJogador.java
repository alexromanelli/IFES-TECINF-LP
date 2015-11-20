/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneiofutebol.visao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import torneiofutebol.modelo.DAOFactory;
import torneiofutebol.modelo.Jogador;
import torneiofutebol.modelo.JogadorDAO;
import torneiofutebol.modelo.PosicaoJogador;
import torneiofutebol.modelo.PosicaoJogadorDAO;
import torneiofutebol.modelo.TimeFutebol;
import torneiofutebol.modelo.TimeFutebolDAO;

/**
 *
 * @author alexandre
 */
public class TelaRegistroJogador extends javax.swing.JFrame {
    
    private static final int OPCAO_INSERIR = 0;
    private static final int OPCAO_ALTERAR = 1;
    
    private int opcao;
    private ArrayList<Jogador> colecaoJogador;
    private int indRegistro;
    private Vector<PosicaoJogador> colecaoPosicaoJogador;
    private DefaultComboBoxModel<PosicaoJogador> modeloComboBoxPosicaoJogador;
    private Vector<TimeFutebol> colecaoTimeFutebol;
    private DefaultComboBoxModel<TimeFutebol> modeloComboBoxTimeFutebol;
    
    private void carregarPosicaoJogadorDoBD() {
        colecaoPosicaoJogador = new Vector<>();
        try {
            PosicaoJogadorDAO posicaoJogadorDAO = 
                    DAOFactory.getDefaultDAOFactory().getPosicaoJogadorDAO();
            ResultSet rs = posicaoJogadorDAO.selecionarTodasPosicoesJogador();
            rs.beforeFirst();
            while (rs.next()) {
                int idPosicao = rs.getInt(1);
                String nome = rs.getString(2);
                PosicaoJogador pj = new PosicaoJogador(idPosicao, nome);
                colecaoPosicaoJogador.add(pj);
            }
        } catch (SQLException ex) { }
    }

    private void carregarTimeFutebolDoBD() {
        colecaoTimeFutebol = new Vector<>();
        try {
            TimeFutebolDAO timeFutebolDAO = DAOFactory.getDefaultDAOFactory().getTimeFutebolDAO();
            ResultSet rs = timeFutebolDAO.selecionarTodosTimesFutebol();
            rs.beforeFirst();
            while (rs.next()) {
                int idTime = rs.getInt(1);
                String nome = rs.getString(2);
                String nomeAbreviado = rs.getString(3);
                String sedePais = rs.getString(4);
                String sedeEstado = rs.getString(5);
                String sedeCidade = rs.getString(6);
                TimeFutebol tf = new TimeFutebol(idTime, nome, nomeAbreviado, 
                        sedePais, sedeEstado, sedeCidade);
                colecaoTimeFutebol.add(tf);
            }
        } catch (SQLException e) { }
    }
    
    /**
     * Creates new form TelaRegistroJogador
     */
    public TelaRegistroJogador() {
        initComponents();
        
        carregarPosicaoJogadorDoBD();
        modeloComboBoxPosicaoJogador = new DefaultComboBoxModel<>(
                colecaoPosicaoJogador);
        cbPosicaoPreferencial.setModel(modeloComboBoxPosicaoJogador);
        
        carregarTimeFutebolDoBD();
        modeloComboBoxTimeFutebol = new DefaultComboBoxModel<>(
                colecaoTimeFutebol);
        cbTimeFutebol.setModel(modeloComboBoxTimeFutebol);
    }
    
    public TelaRegistroJogador(ArrayList<Jogador> colecao) {
        this();
        colecaoJogador = colecao;
        indRegistro = -1;
        opcao = OPCAO_INSERIR;
    }
    
    public TelaRegistroJogador(ArrayList<Jogador> colecao, int indRegistro) {
        this(colecao);
        if (indRegistro >= 0 && indRegistro < colecao.size()) {
            this.indRegistro = indRegistro;
            opcao = OPCAO_ALTERAR;

            // exibir dados do registro no formulário
            Jogador j = colecao.get(indRegistro);
            tfIdJogador.setText(Integer.toString(j.getId()));
            tfNome.setText(j.getNome());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            tfDataNascimento.setText(sdf.format(j.getDataNascimento()));
            rbMasculino.setSelected(j.getSexo() == 'M');
            tfNacionalidade.setText(j.getNacionalidade());
            cbPosicaoPreferencial.setSelectedIndex(obterIndicePosicao(j.getPosicaoPreferencial()));
            cbTimeFutebol.setSelectedIndex(obterIndiceTime(j.getTime()));
        }
    }
    
    private int obterIndicePosicao(PosicaoJogador p) {
        int i = 0;
        for (PosicaoJogador pos : colecaoPosicaoJogador) {
            if (pos.getIdPosicao() == p.getIdPosicao())
                return i;
            i++;
        }
        return -1;
    }
    
    private int obterIndiceTime(TimeFutebol t) {
        int i = 0;
        for (TimeFutebol time : colecaoTimeFutebol) {
            if (time.getIdTime() == t.getIdTime())
                return i;
            i++;
        }
        return -1;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfDataNascimento = new javax.swing.JTextField();
        tfNome = new javax.swing.JTextField();
        tfIdJogador = new javax.swing.JTextField();
        rbMasculino = new javax.swing.JRadioButton();
        rbFeminino = new javax.swing.JRadioButton();
        tfNacionalidade = new javax.swing.JTextField();
        cbPosicaoPreferencial = new javax.swing.JComboBox<>();
        cbTimeFutebol = new javax.swing.JComboBox<>();
        bSalvar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Jogadores - Registro");

        jLabel1.setText("ID");

        jLabel2.setText("Nome");

        jLabel3.setText("Data de nascimento");

        jLabel4.setText("Sexo");

        jLabel5.setText("Nacionalidade");

        jLabel6.setText("Posição preferencial");

        jLabel7.setText("Time");

        tfIdJogador.setEditable(false);

        buttonGroup1.add(rbMasculino);
        rbMasculino.setText("Masculino");

        buttonGroup1.add(rbFeminino);
        rbFeminino.setText("Feminino");

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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNome)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbMasculino)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbFeminino))
                                    .addComponent(tfIdJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfNacionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 252, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbPosicaoPreferencial, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbTimeFutebol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                    .addComponent(tfIdJogador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(rbMasculino)
                    .addComponent(rbFeminino))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfNacionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbPosicaoPreferencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbTimeFutebol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSalvar)
                    .addComponent(bCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarActionPerformed
        // obtém dados do formulário
        String nome = tfNome.getText();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento;
        try {
            dataNascimento = formatador.parse(tfDataNascimento.getText());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data invalida", "Erro", JOptionPane.ERROR_MESSAGE);
            tfDataNascimento.requestFocus();
            return;
        }
        char sexo = rbMasculino.isSelected() ? 'M' : 'F';
        String nacionalidade = tfNacionalidade.getText();
        PosicaoJogador posicaoPreferencial = 
                cbPosicaoPreferencial.getItemAt(cbPosicaoPreferencial.getSelectedIndex());
        TimeFutebol time = (TimeFutebol)cbTimeFutebol.getSelectedItem();
        
        JogadorDAO jogadorDAO = DAOFactory.getDefaultDAOFactory().getJogadorDAO();
        switch (opcao) {
            case OPCAO_INSERIR:
                // para inserção: cria objeto com dados do formulário e id = -1 e executa rotina no BD
                Jogador j = new Jogador(-1, nome, dataNascimento, sexo, nacionalidade, posicaoPreferencial, time);
                if (jogadorDAO.inserirJogador(j))
                    JOptionPane.showMessageDialog(
                            null, 
                            "Registro foi inserido", 
                            "Aviso", 
                            JOptionPane.INFORMATION_MESSAGE);
                break;
            case OPCAO_ALTERAR:
                // para alteração: atualiza objeto com dados do formulário e executa rotina no BD
                j = colecaoJogador.get(indRegistro);
                j.setNome(nome);
                j.setDataNascimento(dataNascimento);
                j.setNacionalidade(nacionalidade);
                j.setPosicaoPreferencial(posicaoPreferencial);
                j.setTime(time);
                if (jogadorDAO.atualizarJogador(j))
                    JOptionPane.showMessageDialog(
                            null, 
                            "Registro foi atualizado", 
                            "Aviso", 
                            JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        
        TelaListagemJogador.getInstancia().atualizarTabela();
        this.setVisible(false);
    }//GEN-LAST:event_bSalvarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaRegistroJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRegistroJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRegistroJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRegistroJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRegistroJogador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<PosicaoJogador> cbPosicaoPreferencial;
    private javax.swing.JComboBox<TimeFutebol> cbTimeFutebol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton rbFeminino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTextField tfDataNascimento;
    private javax.swing.JTextField tfIdJogador;
    private javax.swing.JTextField tfNacionalidade;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}
