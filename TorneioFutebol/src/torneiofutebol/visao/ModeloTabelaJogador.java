/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneiofutebol.visao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import torneiofutebol.modelo.DAOFactory;
import torneiofutebol.modelo.Jogador;
import torneiofutebol.modelo.JogadorDAO;
import torneiofutebol.modelo.PosicaoJogador;
import torneiofutebol.modelo.TimeFutebol;

/**
 *
 * @author alexandre
 */
public class ModeloTabelaJogador extends AbstractTableModel {

    private ArrayList<Jogador> colecao;
    private String[] colunas = {
        "Nome", "Posição", "Time atual"
    };

    public ArrayList<Jogador> getColecao() {
        return colecao;
    }
    
    public ModeloTabelaJogador() {
        colecao = new ArrayList<>();
        carregarDoBD();
    }
    
    public void atualizarDoBD() {
        colecao.clear();
        carregarDoBD();
    }
    
    private void carregarDoBD() {
        try {
            JogadorDAO jogadorDAO = DAOFactory.getDefaultDAOFactory().getJogadorDAO();
            ResultSet rs = jogadorDAO.selecionarTodosJogadores();
            rs.beforeFirst();
            while (rs.next()) {
                int idJogador = rs.getInt(1);
                String nomeJogador = rs.getString(2);
                Date dataNascimento = rs.getDate(3);
                char sexo = rs.getString(4).charAt(0);
                String nacionalidade = rs.getString(5);
                int idPosicao = rs.getInt(6);
                String nomePosicao = rs.getString(7);
                int idTime = rs.getInt(8);
                String nomeTime = rs.getString(9);
                String nomeAbreviado = rs.getString(10);
                String sedePais = rs.getString(11);
                String sedeEstado = rs.getString(12);
                String sedeCidade = rs.getString(13);
                
                PosicaoJogador p = new PosicaoJogador(idPosicao, nomePosicao);
                TimeFutebol t = new TimeFutebol(idTime, nomeTime, nomeAbreviado, 
                        sedePais, sedeEstado, sedeCidade);
                Jogador j = new Jogador(idJogador, nomeJogador, dataNascimento, 
                        sexo, nacionalidade, p, t);
                colecao.add(j);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao obter dados do banco.", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return colecao.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < colecao.size() &&
                columnIndex >= 0 && columnIndex < colunas.length) {
            Jogador j = colecao.get(rowIndex);
            switch (columnIndex) {
                case 0: return j.getNome();
                case 1: return j.getPosicaoPreferencial().getNome();
                case 2: return j.getTime().getNome();
                default: return null;
            }
        }
        return null;
    }
    
}
